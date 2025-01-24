#this is for production build
FROM eclipse-temurin:21
RUN apt update && apt -y upgrade
RUN apt install -y inotify-tools dos2unix
ENV HOME=/java-app-dir
RUN mkdir -p $HOME
WORKDIR $HOME
ADD . .
RUN ./mvnw install -DskipTests
ENTRYPOINT ["java","-jar","target/app-0.0.1-SNAPSHOT.jar"]