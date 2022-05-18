package main

import (
	"context"
	"dubbo-server/pkg/aicleint"
	"dubbo.apache.org/dubbo-go/v3/common/logger" // dubbogo 框架日志
	"dubbo.apache.org/dubbo-go/v3/config"
	_ "dubbo.apache.org/dubbo-go/v3/imports" // dubbogo 框架依赖，所有dubbogo进程都需要隐式引入一次
	"github.com/avast/retry-go/v4"
	"time"
)

type UserProvider struct {
}

// 实现接口方法
func (u *UserProvider) PredictMnist(ctx context.Context, req int32) (int32, error) {
	logger.Infof("req:%#v", req)
	minst := aicleint.NewMinst()

	var res int32
	var err error
	errorTimes := 1
	now := time.Now()

	retry.Do(
		func() error {
			res, err = minst.PredictMnist(req)
			return err
		},
		retry.OnRetry(func(n uint, err error) {
			logger.Infof("request minst at %v,times is %v ,error is  %v", now, errorTimes, err)
			errorTimes = errorTimes + 1
		}),
		retry.Delay(time.Second*3),
	)
	return res, nil
}

func (u *UserProvider) InfoGan(ctx context.Context, req int32) (string, error) {
	logger.Infof("req:%#v", req)
	infoGan := aicleint.NewInfoGan()
	var res string
	var err error
	errorTimes := 1
	now := time.Now()
	retry.Do(
		func() error {
			res, err = infoGan.Create(req)
			return err
		},
		retry.OnRetry(func(n uint, err error) {
			logger.Infof("request minst at %v,times is %v ,error is  %v", now, errorTimes, err)
			errorTimes = errorTimes + 1
		}),
		retry.Delay(time.Second*3),
	)
	return res, nil
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

}
