
#change line below
FROM maven:openjdk:17
#FROM jboss/keycloak:latest

ENV KEYCLOAK_USER admin
ENV KEYCLOAK_PASSWORD admin
ENV PROXY_ADDRESS_FORWARDING true
ENV PORT 8080
COPY docker-entrypoint.sh /opt/jboss/tools
#COPY --from=maven_build target/Java-Database-Project-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT [ "/opt/jboss/tools/docker-entrypoint.sh" ]

CMD ["-b", "0.0.0.0"]