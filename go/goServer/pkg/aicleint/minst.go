package aicleint

import (
	"context"
	genapi "dubbo-server/gen/api"
	"dubbo.apache.org/dubbo-go/v3/common/logger"
	"google.golang.org/grpc"
	"google.golang.org/grpc/credentials/insecure"
	pbtime "google.golang.org/protobuf/types/known/timestamppb"
	"log"
	"os"
	"strconv"
	"time"
)

func NewMinst() *Minst {
	return &Minst{}
}

type Minst struct {
}

func (m *Minst) Predict(fileId string) int32 {
	addr := os.Getenv("AI_HOST")
	if addr == "" {
		addr = "localhost:9999"
	}
	logger.Infof("ai host is %v", addr)
	conn, err := grpc.Dial(addr, grpc.WithTransportCredentials(insecure.NewCredentials()), grpc.WithBlock())
	if err != nil {
		log.Fatal(err)
	}
	defer conn.Close()

	client := genapi.NewMinstServiceClient(conn)
	resp, err := client.Predict(context.Background(), createData(fileId))
	if err != nil {
		log.Fatal(err)
	}
	log.Printf("outliers at: %v", resp.Number)
	return resp.Number
}

func createData(fileId string) *genapi.MinstRequest {
	par, _ := strconv.ParseInt(fileId, 10, 32)
	t := time.Date(2020, 5, 22, 14, 13, 11, 0, time.UTC)
	out := &genapi.MinstRequest{
		Time:   Timestamp(t),
		FileId: int32(par),
	}
	return out
}

// Timestamp converts time.Time to protobuf *Timestamp
func Timestamp(t time.Time) *pbtime.Timestamp {
	return &pbtime.Timestamp{
		Seconds: t.Unix(),
		Nanos:   int32(t.Nanosecond()),
	}
}
