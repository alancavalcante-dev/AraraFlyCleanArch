package br.com.alanpcavalcante.araraflyapi.application.gateways.deploy;

import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

public interface Workspace {

    boolean findPathBuilderFile(Path path);
    Path upSaveBuilderFileWorkspace(String build, UUID idProject) throws IOException;
    boolean dropBuilderFileWorkspace(Path path);

}
