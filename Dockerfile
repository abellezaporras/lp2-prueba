#
# ---- Build stage ----
#
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copiar el proyecto
COPY . .

# Compilar sin ejecutar tests
RUN mvn clean package -DskipTests

#
# ---- Runtime stage ----
#
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Instalar librerías necesarias para JasperReports (fuentes PDF)
RUN apk add --no-cache fontconfig freetype ttf-dejavu

# Copiar el JAR compilado desde la etapa de build
COPY --from=build /app/target/*.jar app.jar

# Configurar puerto dinámico para Render
ENV PORT=8080
ENV JAVA_OPTS=""

# Comando de inicio
CMD ["sh", "-c", "java $JAVA_OPTS -jar app.jar --server.port=$PORT"]
