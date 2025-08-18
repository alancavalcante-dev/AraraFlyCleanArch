package br.com.alanpcavalcante.araraflyapi.domain.deploy;

import java.util.UUID;

public class PortExpose {

    private UUID idPortExpose;

    private String port;

    private Deploy deploy;

    public PortExpose(){}

    public PortExpose(UUID idPortExpose, String port, Deploy deploy) {
        this.idPortExpose = idPortExpose;
        this.port = port;
        this.deploy = deploy;
    }

    public UUID getIdPortExpose() {
        return idPortExpose;
    }

    public void setIdPortExpose(UUID idPortExpose) {
        this.idPortExpose = idPortExpose;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Deploy getDeploy() {
        return deploy;
    }

    public void setDeploy(Deploy deploy) {
        this.deploy = deploy;
    }
}
