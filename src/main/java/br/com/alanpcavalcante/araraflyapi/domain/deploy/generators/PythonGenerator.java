package br.com.alanpcavalcante.araraflyapi.domain.deploy.generators;

import br.com.alanpcavalcante.araraflyapi.application.usecases.deploy.AddEnvironmentAndPorts;
import br.com.alanpcavalcante.araraflyapi.domain.deploy.Deploy;
import java.util.StringJoiner;

public class PythonGenerator implements GeneratorFileLanguage {

    private StringJoiner file = new StringJoiner("\n");

    @Override
    public String get(RepoConfig conf, Deploy deploy) {
        file.add("FROM python:" + 3 + "-slim");
        file.add("ARG GITEA_TOKEN");
        file.add("RUN apt-get update && apt-get install -y git");
        file.add("WORKDIR /app");
        file.add(String.format("RUN git clone https://oauth2:${GITEA_TOKEN}@%s/%s/%s.git .", conf.getHost(), conf.getOwner(), conf.getRepoName()));
        file.add("RUN pip install --no-cache-dir -r requirements.txt");

        file = AddEnvironmentAndPorts.add(file, deploy.getVariableEnvironments(), deploy.getPortsExposes());

        file.add(String.format("CMD %s", deploy.getEntrypoint()));

        return file.toString();
    }

}
