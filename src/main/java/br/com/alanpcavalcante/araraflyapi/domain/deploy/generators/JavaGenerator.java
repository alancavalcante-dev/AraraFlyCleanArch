package br.com.alanpcavalcante.araraflyapi.domain.deploy.generators;


import br.com.alanpcavalcante.araraflyapi.application.usecases.deploy.AddEnvironmentAndPorts;
import br.com.alanpcavalcante.araraflyapi.domain.deploy.Deploy;
import java.util.StringJoiner;


public class JavaGenerator implements GeneratorFileLanguage{

    private StringJoiner file = new StringJoiner("\n");

    @Override
    public String get(RepoConfig conf, Deploy deploy) {
        int version = Integer.parseInt(deploy.getLanguageVersion());

        file.add("FROM eclipse-temurin:" + version + "-jdk-jammy AS builder");
        file.add("ARG GITEA_TOKEN");
        file.add("RUN apt-get update && apt-get install -y git");
        file.add("WORKDIR /app");
        file.add(String.format("RUN git clone https://oauth2:${GITEA_TOKEN}@%s/%s/%s.git .", conf.getHost(), conf.getOwner(), conf.getRepoName()));
        file.add("RUN ./mvnw package -DskipTests");
        file.add("FROM eclipse-temurin:" + version + "-jre-jammy");
        file.add("WORKDIR /app");
        file.add("COPY --from=builder /app/target/*.jar app.jar");

        file = AddEnvironmentAndPorts.add(file, deploy.getVariableEnvironments(), deploy.getPortsExposes());

        file.add(String.format("ENTRYPOINT %s", deploy.getEntrypoint()));

        return file.toString();
    }
}
