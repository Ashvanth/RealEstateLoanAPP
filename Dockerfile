FROM openjdk:17
EXPOSE 8080
ADD target/HomeLoanAPI-0.0.1-SNAPSHOT.jar HomeLoanAPI-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/HomeLoanAPI-0.0.1-SNAPSHOT.jar"]