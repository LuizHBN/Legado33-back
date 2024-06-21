package br.com.legado33.app.access;

import br.com.legado33.app.access.dto.NewAccessDTO;
import br.com.legado33.app.access.dto.ReadAccessDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AccessService {
    private final AccessRepository repository;
    @Autowired
    public AccessService(AccessRepository repository){
        this.repository = repository;
    }

    public Page<ReadAccessDTO> getAllAccesses(Pageable page){
        return repository.findAll(page).map(ReadAccessDTO :: new);
    }

    public void saveNewAccess(NewAccessDTO accessDTO){
       repository.save(new Access(accessDTO));
    }
}
