import numpy as np
from loguru import logger

from api.python_pb2 import OutliersResponse
from api.python_pb2_grpc import OutliersServicer


def find_outliers(data: np.ndarray):
    """Return indices where values more than 2 standard deviations from mean"""
    out = np.where(np.abs(data - data.mean()) > 2 * data.std())
    # np.where returns a tuple for each dimension, we want the 1st element
    return out[0]


class OutliersServer(OutliersServicer):
    def Detect(self, request, context):
        logger.info(f'detect request size: { len(request.metrics)}')
        # Convert metrics to numpy array of values only
        data = np.fromiter((m.value for m in request.metrics), dtype='float64')
        indices = find_outliers(data)
        logger.info(f'found {len(indices)} outliers')
        resp = OutliersResponse(indices=indices)
        return resp