
FROM openjdk:8-jdk-alpine

# can it be in the docker-file ?
# need to declare the vol + a java agent variable
#ADD inspectit-agent/inspectit-agent.jar /

VOLUME /tmp

# We added a VOLUME pointing to "/tmp" because that is where a Spring Boot application creates working directories for Tomcat by default.
# The effect is to create a temporary file on your host under "/var/lib/docker" and link it to the container under "/tmp".
# This step is optional for the simple app that we wrote here, but can be necessary for other Spring Boot applications if they need to actually write in the filesystem.
#(from https://spring.io/guides/gs/spring-boot-docker)

ENV JAVA_OPTS="-Djava.security.egd=file:/dev/./urandom -Xmx128m"
# By default, spring boot apps allocate more than 500M for heap. 128M sounds reasonable for dev.
# java.security.egd is set to reduce startup time (faster but less secured).

ENV JAVA_DEBUG_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000"

ENV SPRING_PROFILES_ACTIVE="none"

ENV JAVA_AGENT=""

# redefine spring-boot server port
ENV SERVER_PORT=80

# yes use the exec form of entry point
#ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS $JAVA_DEBUG_OPTS -jar /app.jar --spring.profiles.active=$SPRING_PROFILES_ACTIVE" ]
ENTRYPOINT [ "java", "-jar", "/app.jar", "$JAVA_OPTS", "$JAVA_DEBUG_OPTS", "--spring.profiles.active=$SPRING_PROFILES_ACTIVE" ]

#CMD java $JAVA_OPTS $JAVA_DEBUG_OPTS $JAVA_AGENT \
#  -jar /app.jar \
#  --spring.profiles.active=$SPRING_PROFILES_ACTIVE

