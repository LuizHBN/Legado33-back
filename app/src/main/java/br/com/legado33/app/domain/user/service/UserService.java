package br.com.legado33.app.domain.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.legado33.app.api.controller.dto.request.newDTO.NewUserDTO;
import br.com.legado33.app.api.controller.dto.request.updateDTO.UpdateUserAccessDTO;
import br.com.legado33.app.api.controller.dto.request.updateDTO.UpdateUserDTO;
import br.com.legado33.app.api.controller.dto.response.ReadUserDTO;
import br.com.legado33.app.domain.access.Access;
import br.com.legado33.app.domain.access.service.AccessService;
import br.com.legado33.app.domain.user.User;
import br.com.legado33.app.domain.user.exception.UserNotFoundException;
import br.com.legado33.app.domain.user.repository.UserRepository;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AttributeType;
import software.amazon.awssdk.services.cognitoidentityprovider.model.CognitoIdentityProviderException;
import software.amazon.awssdk.services.cognitoidentityprovider.model.SignUpRequest;

@Service
public class UserService { 

    // MARK: - Properties
    private final CognitoIdentityProviderClient identityProviderClient;
    private final UserRepository userRepository;
    private final AccessService accessService;
    @Value("${spring.security.oauth2.client.registration.cognito.client-id}")
    private String clientId;


    // MARK: - Private Functions
    private ReadUserDTO saveNewUser(String name, String mail) {
        User user = new User(name, mail);
        User savedUser = userRepository.save(user);
        return new ReadUserDTO(savedUser);
    }

    // MARK: - Public Functions
    public UserService(UserRepository repository, AccessService accessService) {
        this.identityProviderClient = CognitoIdentityProviderClient.builder().region(Region.US_EAST_1).build(); // TODO: Remover esse mock e testar 
        this.userRepository = repository;
        this.accessService = accessService;
    }

    public Page<ReadUserDTO> getAllUsers(Pageable page) {
        return userRepository.findAll(page).map(ReadUserDTO::new);
    }

    public ReadUserDTO findReadUserDTOById(Long id) {
        return userRepository.findById(id)
                .map(ReadUserDTO::new)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public ReadUserDTO update(UpdateUserDTO userDTO, Long id) {
        User existingUser = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        existingUser = updateUserFromDTO(userDTO, existingUser);

        return new ReadUserDTO(userRepository.save(existingUser));
    }

    public ReadUserDTO updateAccess(UpdateUserAccessDTO userDTO, Long id) {
        User existingUser = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        existingUser = updateUserAccessFromDTO(userDTO, existingUser);

        return new ReadUserDTO(userRepository.save(existingUser));
    }

    public User updateUserFromDTO(UpdateUserDTO userDTO, User user) {
        if (userDTO.name().equals(user.getName())) {
            user.setName(userDTO.name());
        }
        if (userDTO.mail().equals(user.getMail())) {
            user.setMail(userDTO.mail());
        }
        return user;
    }

    public User updateUserAccessFromDTO(UpdateUserAccessDTO userDTO, User user) {
        if (!userDTO.access().equals(user.getAccess())) {
            Access access = new Access(accessService.findReadAccessDTOById(userDTO.access().getId()));
            user.setAccess(access);
        }
        return user;
    }

    public void delete(Long id) {
        userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userRepository.deleteById(id);
    }

    // MARK: - Cognito
    public ResponseEntity<Object> signUp(NewUserDTO userDTO) {
        AttributeType userAttrs = AttributeType.builder()
                .name("email")
                .value(userDTO.mail())
                .build();

        List<AttributeType> userAttrsList = new ArrayList<>();
        userAttrsList.add(userAttrs);
        try {
            SignUpRequest signUpRequest = SignUpRequest.builder()
                    .userAttributes(userAttrsList)
                    .username(userDTO.name())
                    .clientId(this.clientId)
                    .password(userDTO.password())
                    .build();

            this.identityProviderClient.signUp(signUpRequest);
            System.out.println("User has been signed up ");
            return ResponseEntity.ok(this.saveNewUser(userDTO.name(), userDTO.mail()));
        } catch (CognitoIdentityProviderException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            return ResponseEntity.badRequest().body("Error: " + e.awsErrorDetails().errorMessage());
        }
    }
}
