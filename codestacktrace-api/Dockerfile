FROM openjdk:11
LABEL maintainer="biagiolibe"
RUN mkdir /opt/codestacktrace/
WORKDIR /opt/codestacktrace/
COPY build/libs/codestacktrace-0.0.1-SNAPSHOT.jar /opt/codestacktrace/

CMD ["java", "-jar", "/opt/codestacktrace/codestacktrace-0.0.1-SNAPSHOT.jar"]