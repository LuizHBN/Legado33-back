package br.com.legado33.app.access.service;

import br.com.legado33.app.access.Access;
import br.com.legado33.app.access.dto.NewAccessDTO;
import br.com.legado33.app.access.dto.ReadAccessDTO;
import br.com.legado33.app.access.dto.UpdateAccessDTO;
import br.com.legado33.app.access.exceptions.AccessNotFoundException;
import br.com.legado33.app.access.repository.AccessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AccessService {
    private final AccessRepository accessRepository;
    @Autowired
    public AccessService(AccessRepository repository){
        this.accessRepository = repository;
    }

    public Page<ReadAccessDTO> getAllAccesses(Pageable page){
        return accessRepository.findAll(page).map(ReadAccessDTO :: new);
    }

    public ReadAccessDTO saveNewAccess(NewAccessDTO accessDTO){
        Access access = new Access(accessDTO);
        Access savedAccess = accessRepository.save(access);
        return new ReadAccessDTO(savedAccess);
    }

    public ReadAccessDTO findAccessById(Long id){
        return accessRepository.findById(id)
                .map(ReadAccessDTO::new)
                .orElseThrow(() -> new AccessNotFoundException(id));
    }

    public ReadAccessDTO update(UpdateAccessDTO accessDTO, Long id) {
        Access existingAccess = accessRepository
                .findById(id)
                .orElseThrow(() -> new AccessNotFoundException(id));
        existingAccess = updateAccessFromDTO(accessDTO, existingAccess);

        return new ReadAccessDTO(accessRepository.save(existingAccess));
    }

    public Access updateAccessFromDTO(UpdateAccessDTO accessDTO,Access access) {
        if (accessDTO.description() != null) {
            access.setDescription(accessDTO.description());
        }
        if (accessDTO.canEditWorship() != access.isCanEditWorship()) {
            access.setCanEditWorship(accessDTO.canEditWorship());
        }
        if (accessDTO.canEditReels() != access.isCanEditReels()) {
            access.setCanEditReels(accessDTO.canEditReels());
        }
        if (accessDTO.canEditPosts() != access.isCanEditPosts()) {
            access.setCanEditPosts(accessDTO.canEditPosts());
        }
        if (accessDTO.canAccessFinances() != access.isCanAccessFinances()) {
            access.setCanAccessFinances(accessDTO.canAccessFinances());
        }
        if (accessDTO.canSendNotifications() != access.isCanSendNotifications()) {
            access.setCanSendNotifications(accessDTO.canSendNotifications());
        }
        if (accessDTO.canReplyMessage() != access.isCanReplyMessage()) {
            access.setCanReplyMessage(accessDTO.canReplyMessage());
        }
        if (accessDTO.canEditNews() != access.isCanEditNews()) {
            access.setCanEditNews(accessDTO.canEditNews());
        }
        if (accessDTO.canEditFaq() != access.isCanEditFaq()) {
            access.setCanEditFaq(accessDTO.canEditFaq());
        }

        return access;
    }

    public void delete(Long id) {
        accessRepository.findById(id).orElseThrow(() -> new AccessNotFoundException(id));
        accessRepository.deleteById(id);
    }
}