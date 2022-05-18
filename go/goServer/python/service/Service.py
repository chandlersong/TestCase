import random

from loguru import logger

from ai.helloworld_infogan import InfoGanService
from ai.mnist_service import MnistService
from api.python_pb2 import MinstResponse, InfoGanResponse
from api.python_pb2_grpc import MinstServiceServicer, InfoGanServiceServicer


class MnistServer(MinstServiceServicer):

    def __init__(self, server, error_rate) -> None:
        self.service = MnistService()
        self.server = server
        self._error_rate = error_rate

    def Predict(self, request, context):
        logger.info(f'request is {request.fileId}')

        random_random = random.random()
        logger.info(f'random is {random_random},error rate is {self._error_rate}')
        if random_random < self._error_rate:

            self.server.stop(grace=False)

        return MinstResponse(number=self.service.predict(request.fileId))

    @property
    def error_rate(self):
        return self._error_rate

    @error_rate.setter
    def error_rate(self, error_rate):
        self._error_rate = error_rate


class InfoGanServer(InfoGanServiceServicer):

    def Create(self, request, context):
        logger.info(f'request is {request.number}')

        random_random = random.random()
        logger.info(f'random is {random_random},error rate is {self._error_rate}')
        if random_random < self._error_rate:
            self.server.stop(grace=False)

        return InfoGanResponse(filename=self.service.save(request.number))

    def __init__(self, server, error_rate) -> None:
        self.service = InfoGanService()
        self.server = server
        self._error_rate = error_rate

    @property
    def error_rate(self):
        return self._error_rate

    @error_rate.setter
    def error_rate(self, error_rate):
        self._error_rate = error_rate
