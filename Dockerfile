FROM maven:3.5.3-jdk-8 as notes-java-build
WORKDIR /notes
COPY . /notes
RUN mvn clean install -DskipTests

FROM java:8-jre
WORKDIR /notes
COPY --from=notes-java-build /notes/target/notes-java-*.jar ./notes.jar
EXPOSE 4000
ENTRYPOINT ["java", "-jar", "notes.jar"]