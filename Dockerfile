FROM openjdk:17-oracle

WORKDIR /bulaukin

COPY target/student-accounting-0.0.1-SNAPSHOT.jar bulaukin.jar

ENV ENABLED=true

ENV PATH_READ=/default-students.txt

CMD ["java", "-jar", "bulaukin.jar"]

#Start app: docker run --rm -it -e ENABLED=true app
