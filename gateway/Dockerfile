FROM maven:3.8.4 AS builder

WORKDIR /app

COPY pom.xml .

COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:17

WORKDIR /app

COPY --from=builder /app/target/gateway-0.0.1-SNAPSHOT.jar gateway.jar
ENV HOST_CLIENTES=clientes
ENV HOST_CARTAO=cartao
ENV HOST_PAGAMENTOS=pagamentos
ENV HOST_AUTENTICACAO=autenticacao

EXPOSE 8080

CMD ["java", "-jar", "gateway.jar"]