FROM maven:3.8.4 AS builder

WORKDIR /app

COPY pom.xml .

COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:17

WORKDIR /app

COPY --from=builder /app/target/*.jar ms_clientes.jar
ENV PG_HOST=ms-postgres
ENV PG_USER=postgres
ENV PG_PASSWORD=postgres
ENV API_SECRET=FIAP2024
ENV MS_USUARIO_URL=autenticacao

CMD ["java", "-jar", "ms_clientes.jar"]