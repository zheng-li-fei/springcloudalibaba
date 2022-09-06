FROM frolvlad/alpine-java:jdk8.202.08-slim

COPY --from=hengyunabc/arthas:latest /opt/arthas /opt/arthas

LABEL author=zlf
LABEL description=库存
LABEL version=1.0

WORKDIR /service-stock

ARG JVM_PARAM
ARG JAVA_PARAM
ARG JAR_FILE
COPY ${JAR_FILE} app.jar

EXPOSE 8886 8886

ENV JVM_PARAM_INNER=${JVM_PARAM}
ENV JAVA_PARAM_INNER=${JAVA_PARAM}
ENTRYPOINT ["/bin/sh"]
CMD java ${JVM_PARAM_INNER} -jar /service-stock/app.jar ${JAVA_PARAM_INNER}