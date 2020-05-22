# microservice-demo

## microservice-provider

  `microservice-provider`是一个服务提供者，使用H2数据库和Jpa进行模拟操作  

接口|地址
:----|:----
Hello|http://localhost:8080/provider/hello
获取用户|http://localhost:8080/provider/get/{id}

## microservice-consumer

`microservice-consumer`为服务消费方

接口|地址
:----|:----
获取用户|http://localhost:8081/consumer/get/{id}

当调用 `http://localhost:8081/consumer/get/{id}` 接口时，会自动转发到服务提供方进行处理
