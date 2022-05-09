.PHONY: build_image_dc build_image_k8s
build_image_dc:
	docker build -f images/Dockerfile_dev -t go-producer:0.1-dc .

build_image_k8s:
	docker build -f images/Dockerfile_dev -t go-producer:0.1-k8s . --build-arg PROFILE=k8s
