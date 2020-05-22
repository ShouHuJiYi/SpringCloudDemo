# microservice-eureka-demo

## microservice-eureka

服务注册与发现中心`Eureka`服务，地址为：`http://localhost:8761/`

登录用户名/密码：`eureka/eureka`

## microservice-provider

服务提供者服务

请求地址：`http://localhost:8081/provider/hello`

可选路径参数`name`,例如`http://localhost:8081/provider/hello?name=tom`

## microservice-consumer

服务消费者

请求地址：`http://localhost:8082/consumer/hello`

可选路径参数`name`,例如`http://localhost:8082/consumer/hello?name=tom`
