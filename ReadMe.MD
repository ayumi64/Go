
## 运行
    go run hello.go 

# Go Zero

https://zeromicro.github.io/go-zero/

### 安装

GOPROXY=https://goproxy.cn/,direct go get -u github.com/tal-tech/go-zero

#### 安装goctl
GO111MODULE=on GOPROXY=https://goproxy.cn/,direct go get -u github.com/tal-tech/go-zero/tools/goctl

快速生成api服务：
   goctl api new greet
   cd greet
   go run greet.go -f etc/greet-api.yaml

调试：
   curl -i http://localhost:8888/from/you



可以根据api文件生成前端需要的Java, TypeScript, Dart, JavaScript代码
goctl api java -api greet.api -dir greet
goctl api dart -api greet.api -dir greet
...
