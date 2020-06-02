# microservice-gateway-demo

路由网关`gateway`的练习

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

## microservice-gateway

路由网关服务

我们不想暴露服务消费者，或者对消费者服务做负载，以及授权访问时，我们使用`gateway`进行路由网关配置
启动消费者服务时，复制一份模板，启动一个`8085`的消费者与`8080`消费者进行负载均衡，可以通过查看控制台输出日志验证

通过`yaml`配置

```yaml
#应用名称
spring:
  application:
    name: microservice-gateway
  cloud:
    gateway:
      discovery:
        locator:
          enabled: true  #开启从注册中心动态创建路由的功能，利用微服务名进行路由
      routes:
        - id: consumer                    #路由的ID，没有固定规则但要求唯一，建议配合服务名
          uri: lb://microservice-consumer #匹配后提供服务的路由地址且做负载均衡
          predicates:
            - Path=/consumer/**           # 断言，路径相匹配的进行路由
```

当调用时，访问地址就是：`http://localhost:8760/consumer/hello?username=1` 其中`username`为过滤用户名，不能为空的即可

通过配置类`GateWayConfig`配置
当调用时，访问地址就是：`http://localhost:8760/guoji?username=1` 其中`username`为过滤用户名，不能为空的即可
