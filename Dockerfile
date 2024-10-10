FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM tomcat:10.1-jdk17
WORKDIR /usr/local/tomcat/webapps/

COPY --from=build /app/target/*.war ./ROOT.war

# Configuración para evitar el escaneo innecesario de JARs
RUN echo '<?xml version="1.0" encoding="UTF-8"?><Context><JarScanner><JarScanFilter defaultPluggabilityScan="false"/></JarScanner></Context>' > /usr/local/tomcat/conf/context.xml

EXPOSE 8080
