



define moveProtoBuf
    rm -rf api
	mkdir api
	cp ../api/python.proto api
endef

define genApiCode
    python -m grpc_tools.protoc \
		-I. --python_out=. --grpc_python_out=. \
		./api/python.proto
endef

.PHONY: gen_api build_image_dc build_image_k8s
gen_api:
	$(call moveProtoBuf)
	$(call genApiCode)


build_image_dc:
	$(call moveProtoBuf)
	docker build -f Dockerfile -t ai-hello-world:0.1-dc .
	$(call genApiCode)

build_image_k8s:
	$(call moveProtoBuf)
	docker build -f Dockerfile -t ai-hello-world:0.1-k8s . --build-arg PROFILE=k8s
	$(call genApiCode)

