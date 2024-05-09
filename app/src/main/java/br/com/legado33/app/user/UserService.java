package br.com.legado33.app.user;

import br.com.legado33.app.access.Access;
import br.com.legado33.app.access.AccessRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final AccessRepository accessRepository;

    @Autowired
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

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(RuntimeException :: new);
    }

    public Page<ReadUserDTO> getAllUsers(Pageable page){
        return userRepository.findAll(page).map(ReadUserDTO :: new);
    }
}
