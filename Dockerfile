FROM openjdk:11
LABEL maintainer="biagiolibe"
RUN mkdir /codestacktrace/
WORKDIR /codestacktrace/
COPY build/libs/codestacktrace-0.0.1-SNAPSHOT.jar /codestacktrace/

CMD ["java", "-jar", "/codestacktrace/codestacktrace-0.0.1-SNAPSHOT.jar"]