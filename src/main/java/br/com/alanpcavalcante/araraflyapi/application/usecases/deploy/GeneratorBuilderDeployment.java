package br.com.alanpcavalcante.araraflyapi.application.usecases.deploy;

import br.com.alanpcavalcante.araraflyapi.domain.deploy.Deploy;
import br.com.alanpcavalcante.araraflyapi.domain.deploy.generators.*;


public class GeneratorBuilderDeployment {

    private final String host = "http://sada...";

    public String generate(Deploy deploy, String repoOwner, String repoName) {
        String language = deploy.getLanguage().toString();

        RepoConfig conf = new RepoConfig(host, repoOwner, repoName);

        if (language.equals("Javascript") || deploy.getTypeService().equals("Static")) {
            return new JavascriptGenerator().get(conf, deploy);
        }

        return switch (language) {
            case "Python" -> new PythonGenerator().get(conf, deploy);
            case "Java" -> new JavaGenerator().get(conf, deploy);
            case "Node" -> new NodeGenerator().get(conf, deploy);
            case "Next" -> new NextGenerator().get(conf, deploy);
            default -> throw new IllegalArgumentException("Linguagem não suportada");
        };

    }
}
