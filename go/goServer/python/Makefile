
.PHONY: gen_api build_image_dc build_image_k8s
gen_api:
	rm -rf api
	mkdir api
	python -m grpc_tools.protoc \
		-Iproto --python_out=. --grpc_python_out=. \
		./proto/api/python.proto

build_image_dc:
	docker build -f Dockerfile -t ai-hello-world:0.1-dc .

build_image_k8s:
	docker build -f Dockerfile -t ai-hello-world:0.1-k8s . --build-arg PROFILE=k8s

