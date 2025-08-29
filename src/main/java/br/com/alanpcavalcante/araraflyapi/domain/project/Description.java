package br.com.alanpcavalcante.araraflyapi.domain.project;

import br.com.alanpcavalcante.araraflyapi.domain.exceptions.TextMinAndMaxCharacters;

public class Description {

    private String description;

    public Description(String description) {
        lengthMinChars(description);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        lengthMinChars(description);
        this.description = description;
    }

    private void lengthMinChars(String field) {
        if (field.length() < 3) {
            throw new TextMinAndMaxCharacters("Invalid field, must have at least 3 characters");
        }

        if (field.length() > 100) {
            throw new TextMinAndMaxCharacters("Invalid field, cannot exceed 100 characters");
        }
    }

}
