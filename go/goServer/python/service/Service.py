import numpy as np
from loguru import logger

from ai.mnist_service import MnistService
from api.python_pb2 import MinstResponse
from api.python_pb2_grpc import MinstServiceServicer


def find_outliers(data: np.ndarray):
    """Return indices where values more than 2 standard deviations from mean"""
    out = np.where(np.abs(data - data.mean()) > 2 * data.std())
    # np.where returns a tuple for each dimension, we want the 1st element
    return out[0]


class MnistServer(MinstServiceServicer):

    def __init__(self) -> None:
        self.service = MnistService()

    def Predict(self, request, context):
        logger.info(f'request is {request.fileId}')
        return MinstResponse(number=self.service.predict(request.fileId))
