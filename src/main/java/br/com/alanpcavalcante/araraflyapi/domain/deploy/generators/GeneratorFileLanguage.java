package br.com.alanpcavalcante.araraflyapi.domain.deploy.generators;


import br.com.alanpcavalcante.araraflyapi.domain.deploy.Deploy;
import br.com.alanpcavalcante.araraflyapi.domain.deploy.Environment;
import br.com.alanpcavalcante.araraflyapi.domain.deploy.PortExpose;

import java.util.List;
import java.util.StringJoiner;

public interface GeneratorFileLanguage {

    String get(RepoConfig conf, Deploy deploy);

}
