package br.com.legado33.app.user.service;

import br.com.legado33.app.access.Access;
import br.com.legado33.app.access.repository.AccessRepository;
import br.com.legado33.app.reels.dto.ReadReelDTO;
import br.com.legado33.app.reels.exceptions.ReelNotFoundException;
import br.com.legado33.app.user.User;
import br.com.legado33.app.user.dto.NewUserDTO;
import br.com.legado33.app.user.dto.ReadUserDTO;
import br.com.legado33.app.user.exceptions.UserNotFoundException;
import br.com.legado33.app.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final AccessRepository accessRepository;

    public UserService(UserRepository userRepository, AccessRepository accessRepository){
        this.accessRepository = accessRepository;
        this.userRepository = userRepository;
    }

    public void saveNewUser(NewUserDTO userDTO){
        User newUser = new User(userDTO);
        Access access = accessRepository.findById(1L).orElseThrow(RuntimeException::new);
        newUser.setAccess(access);
        userRepository.save(new User(userDTO));
    }


    public ReadUserDTO findUserById(Long id) {
        return  userRepository.findById(id)
                .map(ReadUserDTO::new)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public Page<ReadUserDTO> getAllUsers(Pageable page){
        return userRepository.findAll(page).map(ReadUserDTO :: new);
    }
}
