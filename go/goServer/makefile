.PHONY: build_image_dc build_image_k8s
build_image_dc:
	docker image prune -f
	docker build -f images/Dockerfile_dev -t go-producer:0.1-dc .

build_image_k8s:
	docker image prune -f
	docker build -f images/Dockerfile_dev -t go-producer:0.1-k8s . --build-arg PROFILE=k8s