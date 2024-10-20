package br.com.legado33.app.api.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.legado33.app.api.controller.dto.request.newDTO.NewUserDTO;
import br.com.legado33.app.api.controller.dto.request.updateDTO.UpdateUserAccessDTO;
import br.com.legado33.app.api.controller.dto.request.updateDTO.UpdateUserDTO;
import br.com.legado33.app.api.controller.dto.response.ReadUserDTO;
import br.com.legado33.app.domain.user.service.UserService;
import jakarta.validation.Valid;

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
    public ResponseEntity<Page<ReadUserDTO>> getAllUser(@RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="7") int size){
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        return ResponseEntity.ok(userService.getAllUsers(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReadUserDTO> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.findReadUserDTOById(id));
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