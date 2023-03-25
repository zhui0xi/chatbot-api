FROM bienao/jdk-1.8.0_121:latest
MAINTAINER bienao
ADD chatbot-api.jar /opt/chatbot-api.jar
RUN chmod +x /opt/chatbot-api.jar
CMD java -jar /opt/chatbot-api.jar
