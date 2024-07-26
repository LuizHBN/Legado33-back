package br.com.legado33.app.api.controller;


import br.com.legado33.app.api.controller.dto.request.updateDTO.UpdateUserAccessDTO;
import br.com.legado33.app.api.controller.dto.request.updateDTO.UpdateUserDTO;
import br.com.legado33.app.domain.user.service.UserService;
import br.com.legado33.app.api.controller.dto.request.newDTO.NewUserDTO;
import br.com.legado33.app.api.controller.dto.response.ReadUserDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UserController {

    private final UserService userService;

    public UserController(UserService service){
        this.userService = service;
    }

    @PostMapping("/save")
    public ResponseEntity<ReadUserDTO> createUser(@RequestBody @Valid NewUserDTO userDTO){
        return ResponseEntity.ok(userService.saveNewUser(userDTO));
    }

    @GetMapping
    public ResponseEntity<Page<ReadUserDTO>> getAllUser(Pageable page){
        return ResponseEntity.ok(userService.getAllUsers(page));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReadUserDTO> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ReadUserDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UpdateUserDTO userDTO) {
        if (userDTO == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userService.update(userDTO, id));
    }
    @PutMapping("/access/update/{id}")
    public ResponseEntity<ReadUserDTO> updateUserAccess(@PathVariable Long id, @Valid @RequestBody UpdateUserAccessDTO userDTO) {
        if (userDTO == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userService.updateAccess(userDTO, id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}