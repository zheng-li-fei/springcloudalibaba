FROM frolvlad/alpine-java:jdk8.202.08-slim

LABEL author=zlf
LABEL description=日志服务
LABEL version=1.0

WORKDIR /service-log

ARG JVM_PARAM
ARG JAVA_PARAM
ARG JAR_FILE
COPY ${JAR_FILE} app.jar

EXPOSE 8884 8884

ENV JVM_PARAM_INNER=${JVM_PARAM}
ENV JAVA_PARAM_INNER=${JAVA_PARAM}
ENTRYPOINT java ${JVM_PARAM_INNER} -jar app.jar ${JAVA_PARAM_INNER}