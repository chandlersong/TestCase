package main

import "dubbo-server/pkg/api"

func main() {
	service := api.NewPythonApi()
	go func() {
		service.CheckStatus("two")
		service.CheckStatus("one")
	}()
	service.Wait()
}
