package main

//go:generate rm -rf gen
//go:generate mkdir -p gen
//go:generate protoc --go_out=gen/ --go_opt=paths=source_relative --go-grpc_out=gen/ --go-grpc_opt=paths=source_relative api/python.proto
