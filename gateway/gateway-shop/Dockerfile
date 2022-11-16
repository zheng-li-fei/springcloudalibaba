#FROM frolvlad/alpine-java:jdk8.202.08-slim

#替换成自带skywalking的jdk,配置SW_AGENT_NAME 和 SW_AGENT_COLLECTOR_BACKEND_SERVICES 环境变量
FROM apache/skywalking-java-agent:8.7.0-jdk8

COPY --from=hengyunabc/arthas:latest /opt/arthas /opt/arthas

LABEL author=zlf
LABEL description=商城网关
LABEL version=1.0

ENV projectName=gateway-shop

WORKDIR /${projectName}

ARG JVM_PARAM
ARG JAVA_PARAM
ARG JAR_FILE
COPY ${JAR_FILE} ${projectName}.jar

EXPOSE 9120

ENV JVM_PARAM_INNER=${JVM_PARAM}
ENV JAVA_PARAM_INNER=${JAVA_PARAM}
ENTRYPOINT java ${JVM_PARAM_INNER} -jar ${projectName}.jar ${JAVA_PARAM_INNER}