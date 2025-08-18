package br.com.alanpcavalcante.araraflyapi.application.gateways.deploy;


import java.nio.file.Path;


public interface FileDeployment {

    void up(Path path);
    void down(Path path);

}
