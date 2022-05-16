import os
from concurrent.futures import ThreadPoolExecutor

import fire
import grpc
from loguru import logger
from dotenv import load_dotenv

from api.python_pb2_grpc import add_MinstServiceServicer_to_server, add_InfoGanServiceServicer_to_server
from service.Service import MnistServer, InfoGanServer


class Command:

    def __init__(self):
        load_dotenv()

    def start_mnist(self, port=9999):
        logger.info("start mnist server")
        server = grpc.server(ThreadPoolExecutor())
        add_MinstServiceServicer_to_server(MnistServer(server), server)
        if port is None:
            port = os.environ.get("localPort")
        server.add_insecure_port(f'[::]:{port}')
        server.start()
        logger.info(f'server ready on port {port}')
        server.wait_for_termination()

    def start_infogan(self, port=9998):
        logger.info("start infogan server")
        server = grpc.server(ThreadPoolExecutor())
        add_InfoGanServiceServicer_to_server(InfoGanServer(server), server)
        if port is None:
            port = os.environ.get("localPort")
        server.add_insecure_port(f'[::]:{port}')
        server.start()
        logger.info(f'server ready on port {port}')
        server.wait_for_termination()


if __name__ == '__main__':
    fire.Fire(Command)
