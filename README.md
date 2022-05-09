# springcloudalibaba
	1.架构:
		应用网关:	gateway
		服务调用: 	openFeign
		注册中心: 	nacos
		分布式事务:      seata
		链路追踪: 	skywalking + elasticsearch 
		
	2.服务组件搭建: docker-compose容器编排
		mysql
		redis
		nacos
		es
		seata
		skywalking

	3.应用服务构建: jenkins自动化部署
