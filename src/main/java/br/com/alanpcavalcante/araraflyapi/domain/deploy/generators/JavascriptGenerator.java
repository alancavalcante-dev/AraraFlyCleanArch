package br.com.alanpcavalcante.araraflyapi.domain.deploy.generators;

import br.com.alanpcavalcante.araraflyapi.domain.deploy.Deploy;

import java.util.StringJoiner;

public class JavascriptGenerator implements GeneratorFileLanguage {

    private StringJoiner file = new StringJoiner("\n");

    @Override
    public String get(RepoConfig conf, Deploy deploy) {
        file.add("FROM alpine/git:latest AS cloner");
        file.add("ARG GITEA_TOKEN");
        file.add("ARG CACHE_BUSTER");
        file.add("WORKDIR /app");
        file.add("RUN echo \"Busting cache with ${CACHE_BUSTER}\"");
        file.add(String.format("RUN git clone http://oauth2:${GITEA_TOKEN}@%s/%s/%s.git .", conf.getHost(), conf.getOwner(), conf.getRepoName()));
        file.add("FROM nginx:stable-alpine");
        file.add("COPY --from=cloner /app /usr/share/nginx/html");
        file.add("EXPOSE 80");
        file.add("CMD [\"nginx\", \"-g\", \"daemon off;\"]");
        return file.toString();
    }


}
