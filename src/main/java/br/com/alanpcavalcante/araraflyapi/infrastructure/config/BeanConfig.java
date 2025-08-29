package br.com.alanpcavalcante.araraflyapi.infrastructure.config;

import br.com.alanpcavalcante.araraflyapi.application.gateways.deploy.DeployRepository;
import br.com.alanpcavalcante.araraflyapi.application.gateways.deploy.FileDeployment;
import br.com.alanpcavalcante.araraflyapi.application.gateways.match.MatchRepository;
import br.com.alanpcavalcante.araraflyapi.application.gateways.notification.Notification;
import br.com.alanpcavalcante.araraflyapi.application.gateways.profile.ProfileRepository;
import br.com.alanpcavalcante.araraflyapi.application.gateways.project.ProjectRepository;
import br.com.alanpcavalcante.araraflyapi.application.gateways.security.EncryptPassword;
import br.com.alanpcavalcante.araraflyapi.application.gateways.user.UserRepository;
import br.com.alanpcavalcante.araraflyapi.application.usecases.deploy.CreateDeploy;
import br.com.alanpcavalcante.araraflyapi.application.usecases.match.*;
import br.com.alanpcavalcante.araraflyapi.application.usecases.project.*;
import br.com.alanpcavalcante.araraflyapi.application.usecases.user.CreateUser;
import br.com.alanpcavalcante.araraflyapi.application.usecases.user.GetUser;
import br.com.alanpcavalcante.araraflyapi.domain.deploy.Deploy;
import br.com.alanpcavalcante.araraflyapi.domain.match.ValidateCustomerProjectConfirm;
import br.com.alanpcavalcante.araraflyapi.domain.match.ValidateCustomerVotedOnOwnMatch;
import br.com.alanpcavalcante.araraflyapi.domain.match.ValidateDeveloperProjectConfirm;
import br.com.alanpcavalcante.araraflyapi.domain.match.ValidateDeveloperProjectMatch;
import br.com.alanpcavalcante.araraflyapi.domain.profile.Profile;
import br.com.alanpcavalcante.araraflyapi.domain.profile.ProfileBuild;
import br.com.alanpcavalcante.araraflyapi.domain.project.PriceFactory;
import br.com.alanpcavalcante.araraflyapi.domain.project.Project;
import br.com.alanpcavalcante.araraflyapi.domain.user.Address;
import br.com.alanpcavalcante.araraflyapi.domain.user.User;
import br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.deploy.DeployRepositoryImpl;
import br.com.alanpcavalcante.araraflyapi.infrastructure.security.EncryptPasswordImpl;
import br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.user.UserRepositoryImpl;
import br.com.alanpcavalcante.araraflyapi.infrastructure.docker.DeploymentImpl;
import br.com.alanpcavalcante.araraflyapi.infrastructure.docker.DockerConnect;
import br.com.alanpcavalcante.araraflyapi.infrastructure.mappers.AddressMapper;
import br.com.alanpcavalcante.araraflyapi.infrastructure.mappers.UserMapper;
import br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.model.UserEntity;
import br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.profile.ProfileRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
public class BeanConfig {

    // Match

    @Bean
    public MatchValidateFacade matchValidateFacade(
            ValidateCustomerVotedOnOwnMatch validateCustomerProjectMatch,
            ValidateCustomerProjectConfirm validateCustomerProjectConfirm,
            ValidateDeveloperProjectMatch validateDeveloperProjectMatch,
            ValidateDeveloperProjectConfirm validateDeveloperProjectConfirm) {
        return new MatchValidateFacade(validateCustomerProjectMatch, validateCustomerProjectConfirm, validateDeveloperProjectMatch, validateDeveloperProjectConfirm);
    }

    @Bean
    public ConfirmMatch confirmMatch(MatchValidateFacade matchValidateFacade, MatchRepository matchRepository, UpdateProjectToContainerProduction update) {
        return new ConfirmMatch(matchValidateFacade, matchRepository, update);
    }

    @Bean
    public GetListMatchCustomer getListMatchCustomer(MatchRepository matchRepository) {
        return new GetListMatchCustomer(matchRepository);
    }

    @Bean
    public GetListMatchDeveloper getListMatchDeveloper(MatchRepository matchRepository) {
        return new GetListMatchDeveloper(matchRepository);
    }

    @Bean
    public CreateMatchDeveloper createMatchDeveloper(MatchRepository matchRepository, MatchValidateFacade matchValidateFacade) {
        return new CreateMatchDeveloper(matchRepository, matchValidateFacade);
    }

    @Bean
    public DeleteMatchDeveloper deleteMatchDeveloper(MatchRepository matchRepository) {
        return new DeleteMatchDeveloper(matchRepository);
    }

    @Bean
    public MatchValidateFacade matchConfirmFacade(
            ValidateCustomerVotedOnOwnMatch validateCustomerVotedOnOwnMatch,
            ValidateCustomerProjectConfirm validateCustomerProjectConfirm,
            ValidateDeveloperProjectMatch validateDeveloperProjectMatch,
            ValidateDeveloperProjectConfirm validateDeveloperProjectConfirm)
    {
        return new MatchValidateFacade(
                validateCustomerVotedOnOwnMatch, validateCustomerProjectConfirm,
                validateDeveloperProjectMatch, validateDeveloperProjectConfirm);
    }

    // End Beans Match


    // Projects

    @Bean
    public ListProjectOpenSearch listProjectOpenSearch(ProjectRepository projectRepository) {
        return new ListProjectOpenSearch(projectRepository);
    }

    @Bean
    public GetProjectDeveloper getProjectDeveloper(ProjectRepository projectRepository) {
        return new GetProjectDeveloper(projectRepository);
    }

    @Bean
    public Project project() {
        return new Project();
    }

    @Bean
    public PriceFactory priceFactory() {
        return new PriceFactory();
    }

//    @Bean
//    public ProjectRepository projectRepository() {
//        return new ProjectRepositoryImpl();
//    }

    @Bean
    public GetProjectCustomer getProjectCustomer(ProjectRepository projectRepository) {
        return new GetProjectCustomer(projectRepository);
    }

    @Bean
    public CreateProjectCustomer createProjectCustomer(ProjectRepository repository) {
        return new CreateProjectCustomer(repository);
    }

    @Bean
    public UpdateProjectCustomer updateProjectCustomer(ProjectRepository projectRepository, PriceFactory priceFactory) {
        return new UpdateProjectCustomer(projectRepository, priceFactory);
    }

    @Bean
    public ListProjectCustomer listProjectCustomer(ProjectRepository projectRepository) {
        return new ListProjectCustomer(projectRepository);
    }

    @Bean
    public DeleteProjectCustomer deleteProjectCustomer(ProjectRepository projectRepository) {
        return new DeleteProjectCustomer(projectRepository);
    }

    @Bean
    public UpdateProjectToContainerProduction updateProjectToContainerProduction(
            ProjectRepository projectRepository, Notification notification, CreateDeploy createDeploy) {
        return new UpdateProjectToContainerProduction(projectRepository, notification, createDeploy);
    }

    @Bean
    public ValidateDeveloperProjectMatch validateDeveloperQuantityProjectsIsConfirm() {
        return new ValidateDeveloperProjectMatch();
    }

    @Bean
    public ValidateCustomerProjectConfirm validateCustomerProjectConfirm() {
        return new ValidateCustomerProjectConfirm();
    }

    @Bean
    public ValidateCustomerVotedOnOwnMatch validateCustomerVotedOnOwnMatch() {
        return new ValidateCustomerVotedOnOwnMatch();
    }

    @Bean
    public ValidateDeveloperProjectConfirm validateDeveloperProjectConfirm() {
        return new ValidateDeveloperProjectConfirm();
    }

    // End Beans Project


    // Deploy

    @Bean
    public Deploy deploy() {
        return new Deploy();
    }

    @Bean
    public DeployRepository deployRepository() {
        return new DeployRepositoryImpl();
    }

    @Bean
    public CreateDeploy createDeploy(Deploy deploy, DeployRepository deployRepository) {
        return new CreateDeploy(deploy, deployRepository);
    }

    @Bean
    public FileDeployment fileDeployment(DockerConnect dockerConnect) {
        return new DeploymentImpl(dockerConnect);
    }

    // End Beans Deploy


    // Users

    @Bean
    public User user() {
        return new User();
    }

    @Bean
    public ProfileBuild profileBuild(Profile profile, Address address) {
        return new ProfileBuild(profile, address);
    }

    @Bean
    public ProfileRepository profileRepository() {
        return new ProfileRepositoryImpl();
    }

    @Bean
    public CreateUser createUser(UserRepository userRepository, ProfileRepository profileRepository, EncryptPassword encryptPassword) {
        return new CreateUser(userRepository, profileRepository, encryptPassword);
    }

    @Bean
    public UserRepository userRepository() {
        return new UserRepositoryImpl();
    }

    @Bean
    public UserEntity userEntity() {
        return new UserEntity();
    }

    @Bean
    public UserMapper userMapper() {
        return new UserMapper();
    }

    @Bean
    public GetUser getUser(UserRepository userRepository) {
        return new GetUser(userRepository);
    }

    @Bean
    public AddressMapper addressMapper(Address address) {
        return new AddressMapper(address);
    }

    @Bean
    public Address address() {
        return new Address();
    }

    @Bean
    public Profile profile() {
        return new Profile();
    }

    // End Beans Users Profiles Address


    // Security

    @Bean
    public EncryptPassword encryptPassword() {
        return new EncryptPasswordImpl(new BCryptPasswordEncoder());
    }

    // End Beans Security
}