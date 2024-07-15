package br.com.legado33.app.access;

import br.com.legado33.app.access.dto.NewAccessDTO;
import br.com.legado33.app.access.dto.ReadAccessDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccessController {

    private final AccessService service;
    @Autowired
    public AccessController(AccessService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ReadAccessDTO> addAccess(@RequestBody @Valid NewAccessDTO accessDTO){
        return ResponseEntity.ok(new ReadAccessDTO(service.saveNewAccess(accessDTO)));
    }

    @GetMapping
    public ResponseEntity<Page<ReadAccessDTO>> getAllAccess(Pageable page){
        return ResponseEntity.ok(service.findAllAccesses(page));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReadAccessDTO> getAccessById(@PathVariable Long id){
        return service.findAccessById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

//    public ResponseEntity<ReadAccessDTO> updateAccess(@PathVariable Long id, @RequestBody UpdateAccessDTO accessDTO){
//
//    }


    public void deleteAccess(){

    }
}



