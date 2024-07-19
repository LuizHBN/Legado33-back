package br.com.legado33.app.user.controller;


import br.com.legado33.app.user.service.UserService;
import br.com.legado33.app.user.dto.NewUserDTO;
import br.com.legado33.app.user.dto.ReadUserDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public Page<ReadUserDTO> findAllUsers(Pageable page){
        return userService.getAllUsers(page);
    }

    //TODO -> Mudar para responseEntity
    @PostMapping
    public void createUser(@RequestBody @Valid NewUserDTO userDTO) {
        userService.saveNewUser(userDTO);
    }
}
