
service_name=springcloudalibaba
project_name=gateway
tag=latest

echo '当前执行路径:' $(pwd) '当前用户组:' $(groups)

#查询容器是否存在，存在则删除
containerId=`docker ps -a | grep -w ${project_name}:${tag}  | awk '{print $1}'`
if [ "$containerId" !=  "" ] ; then
    docker rm -f $containerId
	echo "停止并删除容器服务"
else
	echo "没有容器服务"
fi

#查询镜像是否存在，存在则删除
imageId=`docker images | grep -w ${service_name}/$project_name  | awk '{print $3}'`
if [ "$imageId" !=  "" ] ; then
    #删除镜像
    docker rmi -f $imageId
	echo "成功删除镜像"
else
	echo "没有镜像存在"
fi


echo '执行构建镜像'
docker build --build-arg JAR_FILE=./${project_name}/target/${project_name}-0.0.1-SNAPSHOT.jar -f ./${project_name}/Dockerfile -t ${service_name}/${project_name}:${tag} .
echo '构建完毕'

echo '启动镜像容器服务开始'
docker run --privileged=true -itd --name ${project_name} --restart=always -m 512m -v /home/zlf/springcloudalibaba/logs/${project_name}/:${project_name}/logs/${project_name} -e JVM_PARAM="" -p 8884:8884 ${service_name}/${project_name}:${tag}
echo '启动镜像容器服务结束'