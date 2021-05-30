FROM openjdk:16-jdk-alpine
COPY entrypoint.sh /entrypoint.sh
RUN dos2unix /entrypoint.sh
COPY target/gateway-1.0.0.jar gateway-1.0.0.jar
COPY consul-client.json /consul-config/consul-client.json
RUN apk --no-cache add \
    curl \
    unzip \
    && curl https://releases.hashicorp.com/consul/1.9.5/consul_1.9.5_linux_amd64.zip -o consul.zip \
    && unzip consul.zip \
    && chmod +x consul \
    && rm -f consul.zip \
    && chmod +x /entrypoint.sh \
    && apk --no-cache del \
    && mkdir consul-data \
    curl \
    unzip
CMD ["/entrypoint.sh"]
