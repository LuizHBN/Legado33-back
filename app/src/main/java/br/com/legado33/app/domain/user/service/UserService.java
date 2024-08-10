package br.com.legado33.app.domain.user.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AccessService accessService;

    public UserService(UserRepository repository, AccessService accessService) {
        this.userRepository = repository;
        this.accessService = accessService;
    }

    public Page<ReadUserDTO> getAllUsers(Pageable page) {
        return userRepository.findAll(page).map(ReadUserDTO::new);
    }

    public ReadUserDTO saveNewUser(NewUserDTO userDTO) {
        User user = new User(userDTO);
        User savedUser = userRepository.save(user);
        return new ReadUserDTO(savedUser);
    }

    public ReadUserDTO findUserById(Long id) {
        return userRepository.findById(id)
                .map(ReadUserDTO::new)
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
        if (userDTO.name().equals(user.getName()) ) {
            user.setName(userDTO.name());
        }
        if (userDTO.mail().equals(user.getMail()) ) {
            user.setMail(userDTO.mail());
        }
        return user;
    }
    public User updateUserAccessFromDTO(UpdateUserAccessDTO userDTO, User user) {
        if(!userDTO.access().equals(user.getAccessId())){
            Access access = new Access(accessService.findAccessById(userDTO.access().getId()));
            user.setAccessId(access);
        }
        return user;
    }


    public void delete(Long id) {
        userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userRepository.deleteById(id);
    }
}