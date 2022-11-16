# springcloudalibaba
	1.架构:
		应用网关:   gateway
        		           gateway-xxx
        		           gateway-xxx
        
        认证中心:
        支付中心:
        异步任务:
        rocketmq:
        		
		服务调用:   openFeign
		注册中心:   nacos
		分布式事务: seata
		熔断降级:   sentinel
        链路追踪:   skywalking + elasticsearch 
        日志收集:   elk
		
	2.服务组件搭建: docker-compose容器编排
		mysql
		redis
		nacos
		es
		seata
		sentinel
		skywalking

	3.应用服务构建: jenkins自动化部署
