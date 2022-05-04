package main

import (
	"context"
	genapi "dubbo-server/gen/api"
	"dubbo-server/pkg/api"
	"dubbo.apache.org/dubbo-go/v3/common/logger" // dubbogo 框架日志
	"dubbo.apache.org/dubbo-go/v3/config"
	_ "dubbo.apache.org/dubbo-go/v3/imports" // dubbogo 框架依赖，所有dubbogo进程都需要隐式引入一次
	"google.golang.org/grpc"
	"google.golang.org/grpc/credentials/insecure"
	pbtime "google.golang.org/protobuf/types/known/timestamppb"
	"log"
	"math/rand"
	"strconv"
	"time"
)

type UserProvider struct {
}

// 实现接口方法
func (u *UserProvider) GetUser(ctx context.Context, req int32) (*api.User, error) {
	var err error
	logger.Infof("req:%#v", req)
	user := &api.User{}
	user.ID = strconv.Itoa(int(req))
	user.Name = "laurence"
	user.Age = 22
	user.Time = time.Now()
	return user, err
}

//// MethodMapper 定义方法名映射，从 Go 的方法名映射到 Java 小写方法名，只有 dubbo 协议服务接口才需要使用
//// go -> go 互通无需使用
//func (s *UserProvider) MethodMapper() map[string]string {
//	return map[string]string{
//		"GetUser": "getUser",
//	}
//}

func init() {
	config.SetProviderService(&UserProvider{}) // 注册服务提供者类，类名与配置文件中的 service 对应
}

// export DUBBO_GO_CONFIG_PATH=dubbogo.yaml 运行前需要设置环境变量，指定配置文件位置
func main() {

	if err := config.Load(); err != nil {
		panic(err)
	}
	select {}

	addr := "localhost:9999"
	conn, err := grpc.Dial(addr, grpc.WithTransportCredentials(insecure.NewCredentials()), grpc.WithBlock())
	if err != nil {
		log.Fatal(err)
	}
	defer conn.Close()

	client := genapi.NewOutliersClient(conn)
	req := &genapi.OutliersRequest{
		Metrics: dummyData(),
	}

	resp, err := client.Detect(context.Background(), req)
	if err != nil {
		log.Fatal(err)
	}
	log.Printf("outliers at: %v", resp.Indices)

}

func dummyData() []*genapi.Metric {
	const size = 1000
	out := make([]*genapi.Metric, size)
	t := time.Date(2020, 5, 22, 14, 13, 11, 0, time.UTC)
	for i := 0; i < size; i++ {
		m := genapi.Metric{
			Time: Timestamp(t),
			Name: "CPU",
			// normally we're below 40% CPU utilization
			Value: rand.Float64() * 40,
		}
		out[i] = &m
		t.Add(time.Second)
	}
	// Create some outliers
	out[7].Value = 97.3
	out[113].Value = 92.1
	out[835].Value = 93.2
	return out
}

// Timestamp converts time.Time to protobuf *Timestamp
func Timestamp(t time.Time) *pbtime.Timestamp {
	return &pbtime.Timestamp{
		Seconds: t.Unix(),
		Nanos:   int32(t.Nanosecond()),
	}
}
