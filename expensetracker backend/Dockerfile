#====================
# BUILD STAGE
#====================
FROM gradle:9.4.1-jdk21-alpine AS builder
LABEL authors="vivek"

WORKDIR /app
COPY . .

RUN chmod +x gradlew
RUN ./gradlew build -x test

#====================
# RUN STAGE
#====================
FROM amazoncorretto:21-alpine
COPY --from=builder /app/build/libs/*.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]