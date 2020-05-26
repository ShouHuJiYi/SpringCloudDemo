# microservice-hystrix-demo

`hystrix turbine` 熔断监控练习

## microservice-eureka

`Eureka`服务注册中心

访问地址：`http://localhost:8761/`

## microservice-provider

服务提供者

`hello`服务：请求地址：`http://localhost:8081/provider/hello` 或者 `http://localhost:8081/provider/hello?name=tom`

`hi`服务：请求地址：`http://localhost:8081/provider/hi?name=tom`

当请求`hi`服务时，不携带`name`参数，会自动默认参数`provider`此时服务内部直接抛出一个运行异常，出现运行异常后，将由`hystrix`全局异常方法进行处理

当请求`hello`服务时，该服务出现奔溃时，此时会有`hystrix`自定义异常方法进行处理

## microservice-consumer

服务消费者，提供`Hello`、`hi`和`getName`服务，其中`Hello`、`hi`是通过`openFeign`调用服务提供者`microservice-provider`的服务

`hello`服务：请求地址：`http://localhost:8080/consumer/hello?name=tom` 或者 `http://localhost:8080/consumer/hello`

`hi`服务：请求地址：`http://localhost:8080/consumer/hi?name=tom`

`getName`服务：请求地址：`http://localhost:8080/consumer/getName`

当请求`Hello`和`hi`服务时，若是服务提供者已经宕机，则会触发熔断，防止服务雪崩。

## microservice-turbine

微服务集群监控服务

地址：`http://localhost:8764/hystrix` 在地址栏中填写`http://localhost:8764/turbine.stream` 可以查看所有服务监控，

TIPS:若是一直时加载中得状态，请刷新几次请求接口即可
