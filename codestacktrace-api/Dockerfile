FROM openjdk:11
LABEL maintainer="biagiolibe"
RUN mkdir /opt/codestacktrace-api/
WORKDIR /opt/codestacktrace-api/
COPY build/libs/codestacktrace-api-0.0.1-SNAPSHOT.jar /opt/codestacktrace-api/

CMD ["java", "-jar", "/opt/codestacktrace-api/codestacktrace-api-0.0.1-SNAPSHOT.jar", "--logging.file=/opt/codestacktrace-api/logs/codestacktrace-api.log"]