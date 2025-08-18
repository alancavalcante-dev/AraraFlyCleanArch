package br.com.alanpcavalcante.araraflyapi.application.usecases.commit;

public class ResponseGetCommits {

    private String message, content, branch;

    public ResponseGetCommits(String message, String content, String branch) {
        this.message = message;
        this.content = content;
        this.branch = branch;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}

