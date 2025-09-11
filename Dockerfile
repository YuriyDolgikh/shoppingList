FROM ubuntu:latest
LABEL authors="Yuriy"

ENTRYPOINT ["top", "-b"]

# Используем официальный образ Maven для сборки
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Копируем pom.xml и загружаем зависимости
COPY pom.xml .
RUN mvn dependency:go-offline

# Копируем исходники и собираем jar
COPY src ./src
RUN mvn clean package -DskipTests

# Создаём минимальный образ для запуска
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Копируем jar из предыдущего этапа
COPY --from=build /app/target/*.jar app.jar

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "app.jar"]
