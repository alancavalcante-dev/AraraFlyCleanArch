package br.com.alanpcavalcante.araraflyapi.application.usecases.deploy;

import br.com.alanpcavalcante.araraflyapi.domain.deploy.Environment;
import br.com.alanpcavalcante.araraflyapi.domain.deploy.PortExpose;

import java.util.List;
import java.util.StringJoiner;

public class AddEnvironmentAndPorts {

    public static StringJoiner add(StringJoiner file, List<Environment> environments, List<PortExpose> ports) {
        if (environments != null && !environments.isEmpty()) {
            environments.forEach(env -> file.add(String.format("ENV %s=\"%s\"", env.getKey(), env.getValue())));
        }
        if (ports != null && !ports.isEmpty()) {
            ports.forEach(p -> file.add(String.format("EXPOSE %s", p.getPort())));
        }
        return file;
    }
}
