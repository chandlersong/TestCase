import os
from concurrent.futures import ThreadPoolExecutor

import fire
import grpc
from loguru import logger
from dotenv import load_dotenv

from api.python_pb2_grpc import add_OutliersServicer_to_server
from service.Service import OutliersServer


class Command:

    def start_server(self):
        load_dotenv()
        logger.info("start server")
        server = grpc.server(ThreadPoolExecutor())
        add_OutliersServicer_to_server(OutliersServer(), server)
        port = os.environ.get("localPort")
        server.add_insecure_port(f'[::]:{port}')
        server.start()
        logger.info(f'server ready on port {port}')
        server.wait_for_termination()


if __name__ == '__main__':
    fire.Fire(Command, command="start_server")
