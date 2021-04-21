# Start with a base image containing Java runtime
FROM centos
RUN yum install -y java-11

# Add Author info
LABEL maintainer="s20062@gsm.hs.kr"

# Add a volume to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=target/the-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} the_moment.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/the_moment.jar"]