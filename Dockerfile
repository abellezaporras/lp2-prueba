#
# Build stage
#
FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /app

# Copiar archivos del proyecto
COPY . /app/

# Construir la app
RUN mvn clean package -DskipTests

# Instalar librerías de fuentes necesarias para JasperReports
RUN apt-get update && apt-get install -y fontconfig libfreetype6

#
# Runtime stage
#
FROM openjdk:17-slim
WORKDIR /app

# Instalar fuentes y dependencias necesarias para PDF
RUN apt-get update && apt-get install -y \
    fontconfig \
    libfreetype6 \
    fonts-dejavu-core \
    && rm -rf /var/lib/apt/lists/*

# Copiar el JAR desde el build
COPY --from=build /app/target/*.jar /app/app.jar

# Exponer el puerto del backend
EXPOSE 8080

# Ejecutar la app
ENTRYPOINT ["java","-jar","app.jar"]
