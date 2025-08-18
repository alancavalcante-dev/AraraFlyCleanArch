package br.com.alanpcavalcante.araraflyapi.domain.project;

public class Title {

    private String title;

    public Title(String title) {
        lengthMinChars(title);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        lengthMinChars(title);
        this.title = title;
    }

    private void lengthMinChars(String field) {
        if (field.length() < 3) {
            throw new IllegalArgumentException("Invalid field, must have at least 3 characters");
        }

        if (field.length() > 100) {
            throw new IllegalArgumentException("Invalid field, cannot exceed 100 characters");
        }
    }
}
