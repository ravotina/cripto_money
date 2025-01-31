# Étape 1 : Utiliser openjdk de base
FROM openjdk:19-jdk-alpine AS builder

# Installer Maven
RUN apk add --no-cache maven

# Définir le répertoire de travail
WORKDIR /app

# Copier le pom.xml et télécharger les dépendances (en mode hors ligne)
COPY pom.xml . 
# RUN mvn dependency:go-offline

# Copier le code source et construire l'application
COPY src /app/src
RUN mvn clean package -DskipTests

# Étape 2 : Créer l'image finale avec le fichier JAR
FROM openjdk:19-jdk-alpine
WORKDIR /app

# Copier le fichier JAR depuis l'étape de build
COPY --from=builder /app/target/demo-1.0.0.jar app.jar

# Exposer le port de l'application
EXPOSE 8080

# Lancer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
