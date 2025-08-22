package br.com.alanpcavalcante.araraflyapi.infrastructure.config;

import br.com.alanpcavalcante.araraflyapi.application.gateways.deploy.DeployRepository;
import br.com.alanpcavalcante.araraflyapi.application.gateways.deploy.FileDeployment;
import br.com.alanpcavalcante.araraflyapi.application.gateways.notification.Notification;
import br.com.alanpcavalcante.araraflyapi.application.gateways.profile.ProfileRepository;
import br.com.alanpcavalcante.araraflyapi.application.gateways.project.ProjectRepository;
import br.com.alanpcavalcante.araraflyapi.application.gateways.security.EncryptPassword;
import br.com.alanpcavalcante.araraflyapi.application.gateways.user.UserRepository;
import br.com.alanpcavalcante.araraflyapi.application.usecases.deploy.CreateDeploy;
import br.com.alanpcavalcante.araraflyapi.application.usecases.project.CreateProjectCustomer;
import br.com.alanpcavalcante.araraflyapi.application.usecases.project.GetProjectCustomer;
import br.com.alanpcavalcante.araraflyapi.application.usecases.project.ListProjectCustomer;
import br.com.alanpcavalcante.araraflyapi.application.usecases.project.UpdateProjectToContainerProduction;
import br.com.alanpcavalcante.araraflyapi.application.usecases.user.CreateUser;
import br.com.alanpcavalcante.araraflyapi.application.usecases.match.MatchValidateFacade;
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
import br.com.alanpcavalcante.araraflyapi.infrastructure.deploy.DeployRepositoryImpl;
import br.com.alanpcavalcante.araraflyapi.infrastructure.gateways.ProjectRepositoryImpl;
import br.com.alanpcavalcante.araraflyapi.infrastructure.security.EncryptPasswordImpl;
import br.com.alanpcavalcante.araraflyapi.infrastructure.user.UserRepositoryImpl;
import br.com.alanpcavalcante.araraflyapi.infrastructure.docker.DeploymentImpl;
import br.com.alanpcavalcante.araraflyapi.infrastructure.docker.DockerConnect;
import br.com.alanpcavalcante.araraflyapi.infrastructure.mappers.AddressMapper;
import br.com.alanpcavalcante.araraflyapi.infrastructure.mappers.UserMapper;
import br.com.alanpcavalcante.araraflyapi.infrastructure.model.UserEntity;
import br.com.alanpcavalcante.araraflyapi.infrastructure.profile.ProfileRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BeanConfig {

    @Bean
    public EncryptPassword encryptPassword() {
        return new EncryptPasswordImpl(new BCryptPasswordEncoder());
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

    @Bean
    public PriceFactory priceFactory() {
        return new PriceFactory();
    }

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

    // Projects

    @Bean
    public ListProjectCustomer listProjectCustomer(ProjectRepository projectRepository) {
        return new ListProjectCustomer(projectRepository);
    }

    @Bean
    public GetProjectCustomer getProjectCustomer(ProjectRepository projectRepository) {
        return new GetProjectCustomer(projectRepository);
    }


    @Bean
    public Project project() {
        return new Project();
    }

    @Bean
    public CreateProjectCustomer createProjectCustomer(ProjectRepository repository, Project project) {
        return new CreateProjectCustomer(repository, project);
    }

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
    public ProjectRepository projectRepository() {
        return new ProjectRepositoryImpl();
    }

    @Bean
    public UserRepository userRepository() {
        return new UserRepositoryImpl();
    }

}