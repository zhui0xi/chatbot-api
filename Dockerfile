FROM bienao/jdk-1.8.0_121:latest
MAINTAINER bienao
ADD /chatbot-api-interfaces/target/chatbot-api.jar /opt/chatbot-api.jar
