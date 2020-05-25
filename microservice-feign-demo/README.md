# microservice-feign-demo

`open feign` 练习

## microservice-eureka

`Eureka`服务注册中心

访问地址：http://localhost:8761/

## microservice-provider

服务提供者，提供`Hello`服务

请求地址：`http://localhost:8081/provider/hello?name=tom` 或者 `http://localhost:8081/provider/hello`

`name` 为可选参数

## microservice-consumer

服务消费者，提供`Hello`服务，通过`openFeign`调用服务提供者的Hello服务

请求地址：`http://localhost:8080/consumer/hello?name=tom` 或者 `http://localhost:8080/consumer/hello`

`name` 为可选参数
