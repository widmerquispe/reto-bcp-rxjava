#FROM amazoncorretto:11
#WORKDIR /usr/src/app
#COPY target/BcpRetoJavaRx-0.0.1-SNAPSHOT.jar /usr/src/app/BcpRetoJavaRx.jar
#CMD [ "java", "-jar", "BcpRetoJavaRx.jar" ]

FROM openjdk:8
VOLUME /tmp
EXPOSE 8080
ADD target/BcpRetoJavaRx-0.0.1-SNAPSHOT.jar BcpRetoJavaRx-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/BcpRetoJavaRx-0.0.1-SNAPSHOT.jar"]
