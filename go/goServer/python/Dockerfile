FROM python:3.7-slim-buster

ADD sources.list /etc/apt/sources.list
RUN apt-get update &&\
    apt-get install ffmpeg libsm6 libxext6  -y
WORKDIR /app
COPY requirements.txt requirements.txt
RUN  /usr/local/bin/python -m pip install --upgrade pip -i https://pypi.tuna.tsinghua.edu.cn/simple/ &&\
      pip3 install -r requirements.txt -i https://pypi.tuna.tsinghua.edu.cn/simple/


ARG PROFILE=dc
COPY . .
RUN mv  ${PROFILE}.env .env && \
    python -m grpc_tools.protoc -I. --python_out=. --grpc_python_out=. ./api/python.proto &&\
    mkdir -p /app/ai/infoganResult

ENTRYPOINT [ "python", "./server.py"]
CMD [""]
