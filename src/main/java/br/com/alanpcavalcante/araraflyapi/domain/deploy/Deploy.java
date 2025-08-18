package br.com.alanpcavalcante.araraflyapi.domain.deploy;

import br.com.alanpcavalcante.araraflyapi.domain.languages.Language;
import br.com.alanpcavalcante.araraflyapi.domain.project.Project;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Deploy {


    private UUID idDeploy;

    private String surnameService;

    private TypeService typeService;

    private Language language;

    private String languageVersion;

    private String entrypoint;

    private List<PortExpose> portsExposes = new ArrayList<>();

    private List<Environment> variableEnvironments = new ArrayList<>();

    private Boolean isUp;

    private Project project;

    private Path path;


    public UUID getIdDeploy() {
        return idDeploy;
    }

    public void setIdDeploy(UUID idDeploy) {
        this.idDeploy = idDeploy;
    }

    public String getSurnameService() {
        return surnameService;
    }

    public void setSurnameService(String surnameService) {
        this.surnameService = surnameService;
    }

    public TypeService getTypeService() {
        return typeService;
    }

    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getLanguageVersion() {
        return languageVersion;
    }

    public void setLanguageVersion(String languageVersion) {
        this.languageVersion = languageVersion;
    }

    public String getEntrypoint() {
        return entrypoint;
    }

    public void setEntrypoint(String entrypoint) {
        this.entrypoint = entrypoint;
    }

    public List<PortExpose> getPortsExposes() {
        return portsExposes;
    }

    public void setPortsExposes(List<PortExpose> portsExposes) {
        this.portsExposes = portsExposes;
    }

    public List<Environment> getVariableEnvironments() {
        return variableEnvironments;
    }

    public void setVariableEnvironments(List<Environment> variableEnvironments) {
        this.variableEnvironments = variableEnvironments;
    }

    public Boolean getUp() {
        return isUp;
    }

    public void setUp(Boolean up) {
        isUp = up;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }
}
