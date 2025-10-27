#
# ---- Build stage ----
#
FROM maven:3.8.3-openjdk-17-alpine AS build
WORKDIR /app

# Copia el contenido del proyecto
COPY . .

# Compila el proyecto
RUN mvn clean package -DskipTests

#
# ---- Runtime stage ----
#
FROM openjdk:17-alpine
WORKDIR /app

# Instala dependencias necesarias para JasperReports (fuentes y renderizado PDF)
RUN apk add --no-cache fontconfig freetype ttf-dejavu

# Copia el JAR compilado desde la etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Render asigna dinámicamente el puerto; no usar EXPOSE fijo
ENV PORT=8080
ENV JAVA_OPTS=""

# Comando de inicio
CMD ["sh", "-c", "java $JAVA_OPTS -jar app.jar --server.port=$PORT"]
