package br.com.legado33.app.access;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccessController {

    public Page<Access> findAllAccess(Pageable page){

    }
}
