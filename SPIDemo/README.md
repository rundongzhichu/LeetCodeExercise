# 启动
1.首先build SPI-interface-provider， 再build SPI-service-provider
2.运行SPIDemo里面的test

# Java SPI 机制
SPI 即 Service Provider Interface ，字面意思就是：“服务提供者的接口”，我的理解是：专门提供给服务提供者或者扩展框架功能的开发者去使用的一个接口。  
SPI 将服务接口和具体的服务实现分离开来，将服务调用方和服务实现者解耦，能够提升程序的扩展性、可维护性。修改或者替换服务实现并不需要修改调用方。  
很多框架都使用了 Java 的 SPI 机制，比如：Spring 框架、数据库加载驱动、日志接口、以及 Dubbo 的扩展实现等等。
![spi示意图.png](spi示意图.png)
![Spi原理图.png](Spi原理图.png)
![log4j%20spi原理图.png](log4j%20spi原理图.png)