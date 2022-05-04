from concurrent.futures import ThreadPoolExecutor

import fire
import grpc
from loguru import logger
from dotenv import load_dotenv

from api.python_pb2_grpc import add_OutliersServicer_to_server
from code.Service import OutliersServer


class Command:
    def __int__(self):
        load_dotenv()

    def start_server(self):
        logger.info("start server")
        server = grpc.server(ThreadPoolExecutor())
        add_OutliersServicer_to_server(OutliersServer(), server)
        port = 9999
        server.add_insecure_port(f'[::]:{port}')
        server.start()
        logger.info(f'server ready on port {port}')
        server.wait_for_termination()


if __name__ == '__main__':
    fire.Fire(Command, command="start_server")
