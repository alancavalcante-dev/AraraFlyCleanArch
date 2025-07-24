package br.com.alanpcavalcante.araraflyapi.application.usecases.user;

import br.com.alanpcavalcante.araraflyapi.application.gateways.user.UserRepository;
import br.com.alanpcavalcante.araraflyapi.infrastructure.gateways.user.CreateUserDto;
import org.junit.jupiter.api.Test;
import br.com.alanpcavalcante.araraflyapi.domain.user.*;
import br.com.alanpcavalcante.araraflyapi.domain.user.UserBuild;
import org.junit.jupiter.api.BeforeEach;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


class CreateUserTest {

    private UserRepository userRepository;
    private UserBuild userBuilder;
    private CreateUser createUser;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userBuilder = mock(UserBuild.class);
        createUser = new CreateUser(userRepository, userBuilder);
    }

    @Test
    void createUser_shouldCreateNewUserSuccessfully() throws Exception {
        // Arrange
        CreateUserDto command = new CreateUserDto(
                "Alan Pereira",    // name
                "504.680.460-38",        // cpf
                "alan@email.com",        // email
                "11999999999",           // phone
                "Rua X",                 // street
                "São Paulo",             // city
                "SP",                    // state
                "123",                   // number
                "alan",                  // login
                "senhaForte"             // password
        );


        User expectedUser = new User(); // ou crie com dados fictícios
        when(userBuilder
                .createLogin(any(Login.class), any(Password.class)))
                .thenReturn(userBuilder);
        when(userBuilder
                .createProfile(any(Name.class), any(Cpf.class), any(Email.class), any(Phone.class)))
                .thenReturn(userBuilder);
        when(userBuilder
                .implementAddress(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(userBuilder);
        when(userBuilder.build()).thenReturn(expectedUser);

        when(userRepository.getUserByLoginOrCpfOrEmail(any(), any(), any()))
                .thenReturn(Optional.empty());

        when(userRepository.save(expectedUser)).thenReturn(expectedUser);

        // Act
        User result = createUser.create(command);

        // Assert
        assertEquals(expectedUser, result);
        verify(userRepository).save(expectedUser);
    }

    @Test
    void createUser_shouldThrowExceptionIfUserExists() {
        // Arrange
        CreateUserDto command = new CreateUserDto(
                "Alan Pereira",     // name
                "504.680.460-38",        // cpf
                "alan@email.com",        // email
                "11999999999",           // phone
                "Rua X",                 // street
                "São Paulo",             // city
                "SP",                    // state
                "123",                   // number
                "alan",                  // login
                "senhaForte"             // password
        );

        User existingUser = new User();

        when(userBuilder
                .createLogin(any(Login.class), any(Password.class)))
                .thenReturn(userBuilder);
        when(userBuilder
                .createProfile(any(Name.class), any(Cpf.class), any(Email.class), any(Phone.class)))
                .thenReturn(userBuilder);
        when(userBuilder
                .implementAddress(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(userBuilder);
        when(userBuilder.build()).thenReturn(existingUser);

        when(userRepository.getUserByLoginOrCpfOrEmail(any(), any(), any()))
                .thenReturn(Optional.of(existingUser));

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> createUser.create(command));
        assertEquals("Login, Cpf ou Email já cadastrado", exception.getMessage());

        verify(userRepository, never()).save(any());
    }
}