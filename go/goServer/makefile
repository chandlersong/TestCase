.PHONY: build_image
build_image:
	docker image prune -f
	docker build -f images/Dockerfile_dc -t go-producer:0.1-dc .