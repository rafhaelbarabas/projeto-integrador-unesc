# Rule "start"
.PHONY: start
start:
	docker exec -it mongodb mongo

# Rule "pull-image"
.PHONY: pull-image
pull-image:
	docker run --name mongodb -d mongo:latest