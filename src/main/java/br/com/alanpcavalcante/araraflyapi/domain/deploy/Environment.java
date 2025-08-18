package br.com.alanpcavalcante.araraflyapi.domain.deploy;

import java.util.UUID;

public class Environment {

    private UUID idEnvironment;

    private String key;

    private String value;

    private Deploy deploy;

    public Environment() {}

    public Environment(UUID idEnvironment, String key, String value) {
        this.idEnvironment = idEnvironment;
        this.key = key;
        this.value = value;
    }

    public UUID getIdEnvironment() {
        return idEnvironment;
    }

    public void setIdEnvironment(UUID idEnvironment) {
        this.idEnvironment = idEnvironment;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public Deploy getDeploy() {
        return deploy;
    }

    public void setDeploy(Deploy deploy) {
        this.deploy = deploy;
    }
}
