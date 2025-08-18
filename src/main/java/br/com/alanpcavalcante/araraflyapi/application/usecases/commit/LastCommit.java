package br.com.alanpcavalcante.araraflyapi.application.usecases.commit;

import br.com.alanpcavalcante.araraflyapi.application.gateways.commit.GitCommit;
import br.com.alanpcavalcante.araraflyapi.domain.project.Project;
import br.com.alanpcavalcante.araraflyapi.domain.user.User;

import java.util.Objects;


public class LastCommit {

    private final GitCommit gitCommit;

    public LastCommit(GitCommit gitCommit) {
        this.gitCommit = gitCommit;
    }

    public Object commit(User user, Project project) {

        if (!(Objects.equals(user.getId(), project.getUser().getId()) || Objects.equals(user.getId(), project.getDeveloper().getId()))) {
            throw new IllegalArgumentException("Não tem acesso à esse projeto");
        }


        var lastCommit = gitCommit.getCommits(project.getDeveloper().getLogin().getLogin(), project.getTitle().getTitle()).getLast();
        return lastCommit.get("commit");
    }
}
