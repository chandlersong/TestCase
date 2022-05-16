import random

from loguru import logger

from ai.helloworld_infogan import InfoGanService
from ai.mnist_service import MnistService
from api.python_pb2 import MinstResponse, InfoGanResponse
from api.python_pb2_grpc import MinstServiceServicer, InfoGanServiceServicer


class MnistServer(MinstServiceServicer):

    def __init__(self , server) -> None:
        self.service = MnistService()
        self.server = server

    def Predict(self, request, context):
        logger.info(f'request is {request.fileId}')

        if random.random() > 0.9:
            self.server.stop(grace=False)

        return MinstResponse(number=self.service.predict(request.fileId))


class InfoGanServer(InfoGanServiceServicer):

    def Create(self, request, context):
        logger.info(f'request is {request.number}')

        if random.random() > 0.9:
            self.server.stop(grace=False)

        return InfoGanResponse(filename=self.service.save(request.number))

    def __init__(self, server) -> None:
        self.service = InfoGanService()
        self.server = server
