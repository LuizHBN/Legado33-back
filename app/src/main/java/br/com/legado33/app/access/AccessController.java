package br.com.legado33.app.access;

import br.com.legado33.app.access.dto.NewAccessDTO;
import br.com.legado33.app.access.dto.ReadAccessDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccessController {

    private final AccessService service;
    @Autowired
    public AccessController(AccessService service){
        this.service = service;
    }

    @GetMapping
    public Page<ReadAccessDTO> findAllAccess(Pageable page){
        return service.getAllAccesses(page);
    }
    @PostMapping
    public void addAccess(@RequestBody @Valid NewAccessDTO accessDTO){
        service.saveNewAccess(accessDTO);
    }
}



