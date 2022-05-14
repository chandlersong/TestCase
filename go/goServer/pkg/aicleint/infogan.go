package aicleint

import (
	"context"
	genapi "dubbo-server/gen/api"
	"dubbo.apache.org/dubbo-go/v3/common/logger"
	"google.golang.org/grpc"
	"google.golang.org/grpc/credentials/insecure"
	"log"
	"os"
	"time"
)

func NewInfoGan() *InfoGan {
	return &InfoGan{}
}

type InfoGan struct {
}

func (m *InfoGan) Create(fileId int32) string {
	addr := os.Getenv("AI_HOST")
	if addr == "" {
		addr = "localhost:9998"
	}
	logger.Infof("ai host is %v", addr)
	conn, err := grpc.Dial(addr, grpc.WithTransportCredentials(insecure.NewCredentials()), grpc.WithBlock())
	if err != nil {
		log.Fatal(err)
	}
	defer conn.Close()

	client := genapi.NewInfoGanServiceClient(conn)
	resp, err := client.Create(context.Background(), createInfoGanData(fileId))
	if err != nil {
		log.Fatal(err)
	}
	log.Printf("create fileName is: %v", resp.Filename)
	return resp.Filename
}

func createInfoGanData(fileId int32) *genapi.InfoGanRequest {
	t := time.Date(2020, 5, 22, 14, 13, 11, 0, time.UTC)
	out := &genapi.InfoGanRequest{
		Time:   Timestamp(t),
		Number: fileId,
	}
	return out
}
