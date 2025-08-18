package br.com.alanpcavalcante.araraflyapi.domain.deploy.generators;

import br.com.alanpcavalcante.araraflyapi.application.usecases.deploy.AddEnvironmentAndPorts;
import br.com.alanpcavalcante.araraflyapi.domain.deploy.Deploy;

import java.util.StringJoiner;

public class NextGenerator implements GeneratorFileLanguage{

    private StringJoiner file = new StringJoiner("\n");

    @Override
    public String get(RepoConfig conf, Deploy deploy) {
        int version = Integer.parseInt(deploy.getLanguageVersion());

        file.add("FROM node:" + version + "-alpine AS builder");
        file.add("ARG GITEA_TOKEN");
        file.add("RUN apk add --no-cache git");
        file.add("WORKDIR /app");
        file.add(String.format("RUN git clone https://oauth2:${GITEA_TOKEN}@%s/%s/%s.git .", conf.getHost(), conf.getOwner(), conf.getRepoName()));
        file.add("RUN npm install");
        file.add("RUN npm run build");
        file.add("FROM node:" + version + "-alpine");
        file.add("WORKDIR /app");
        file.add("ENV NODE_ENV production");
        file.add("COPY --from=builder /app/public ./public");
        file.add("COPY --from=builder --chown=node:node /app/.next/standalone ./");
        file.add("COPY --from=builder --chown=node:node /app/.next/static ./.next/static");
        file.add("USER node");

        file = AddEnvironmentAndPorts.add(file, deploy.getVariableEnvironments(), deploy.getPortsExposes());

        file.add(String.format("CMD %s", deploy.getEntrypoint()));
        return file.toString();
    }
}
