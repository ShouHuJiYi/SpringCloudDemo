# microservice-zuul-demo

路由网关`zuul`的练习

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

## microservice-zuul

路由网关服务

我们不想暴露服务消费者，或者对消费者服务做负载，以及授权访问时，我们使用`zuul`组件进行路由网关配置
启动消费者服务时，复制一份模板，启动一个`8085`的消费者与`8080`消费者进行负载均衡

```yaml
#配置路由规则
# 路径中包含 provider 的都转发到 MICROSERVICE-PROVIDER 服务
# 路径中包含 consumer 的都转发到 MICROSERVICE-CONSUMER 服务
zuul:
  routes:
    provider:
      path: /provider/**
      serviceId: MICROSERVICE-PROVIDER
    consumer:
      path: /consumer/**
      serviceId: MICROSERVICE-CONSUMER
#对服务配置负载均衡
MICROSERVICE-CONSUMER:
  ribben:
    listOfServers: http://localhost:8080,http://localhost:8085 #需要再启动一个端口为8085的服务消费方
```

当调用时，真是的地址就是：`http://localhost:8760/consumer/consumer/hello?token=111` 其中token为授权码，不能为空的即可
