import os
from concurrent.futures import ThreadPoolExecutor
from io import StringIO

import fire
import grpc
from loguru import logger
from dotenv import load_dotenv, dotenv_values
import nacos

from api.python_pb2_grpc import add_MinstServiceServicer_to_server, add_InfoGanServiceServicer_to_server
from service.Service import MnistServer, InfoGanServer


class ConfigurationUpdate:

    def __init__(self):
        self._service = None

    def __call__(self, data):
        load_dotenv(stream=StringIO(data['content']), override=True)
        error_rate = os.environ.get("errorRate")
        logger.info(f'new error rate is  {error_rate}')
        self._refresh(error_rate)

    @property
    def service(self):
        return self._service

    @service.setter
    def service(self, service):
        self._service = service

    def _refresh(self, error_rate):
        self._service.error_rate = error_rate


def load_config_from_nacos():
    SERVER_ADDRESSES = os.environ.get("nacos.server_address")
    NAMESPACE = os.environ.get("nacos.namespace")

    client = nacos.NacosClient(SERVER_ADDRESSES, namespace=NAMESPACE)

    # get config
    data_id = os.environ.get("nacos.data_id")
    group = os.environ.get("nacos.group")

    # 全局服务配置
    configupdate = ConfigurationUpdate()
    configupdate.__name__ = "configupdate"
    load_dotenv(stream=StringIO(client.get_config(data_id, group)))
    client.add_config_watcher(data_id, group, configupdate)
    logger.info(f'default error rate  is {os.environ.get("errorRate")}')
    return configupdate


class Command:

    def __init__(self):
        load_dotenv()
        self.config_update = load_config_from_nacos()

    def start_mnist(self, port=9999):
        logger.info("start mnist server")
        server = grpc.server(ThreadPoolExecutor())

        mnist_server = MnistServer(server, os.environ.get("errorRate"))
        self.config_update.service = mnist_server
        add_MinstServiceServicer_to_server(mnist_server, server)
        if port is None:
            port = os.environ.get("localPort")
        server.add_insecure_port(f'[::]:{port}')
        server.start()
        logger.info(f'server ready on port {port}')
        server.wait_for_termination()

    def start_infogan(self, port=9998):
        logger.info("start infogan server")
        server = grpc.server(ThreadPoolExecutor())
        info_gan = InfoGanServer(server, os.environ.get("errorRate"))
        self.config_update.service = info_gan
        add_InfoGanServiceServicer_to_server(info_gan, server)
        if port is None:
            port = os.environ.get("localPort")
        server.add_insecure_port(f'[::]:{port}')
        server.start()
        logger.info(f'server ready on port {port}')
        server.wait_for_termination()


if __name__ == '__main__':
    fire.Fire(Command)
