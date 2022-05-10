FROM python:3.7-slim-buster

ARG PROFILE=dc

WORKDIR /app

COPY requirements.txt requirements.txt
RUN  /usr/local/bin/python -m pip install --upgrade pip -i https://pypi.tuna.tsinghua.edu.cn/simple/ &&\
      pip3 install -r requirements.txt -i https://pypi.tuna.tsinghua.edu.cn/simple/

COPY . .
RUN mv dc.env .env && \
    python -m grpc_tools.protoc -I. --python_out=. --grpc_python_out=. ./api/python.proto


CMD [ "python", "./server.py"]