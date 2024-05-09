package br.com.legado33.app.user;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public Page<ReadUserDTO> findAllUsers(Pageable page){
        return userService.getAllUsers(page);
    }

    @PostMapping
    public void createUser(@RequestBody @Valid NewUserDTO userDTO) {
        userService.saveNewUser(userDTO);
    }
}
