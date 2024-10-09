package br.com.legado33.app.domain.worship.service;

import br.com.legado33.app.domain.worship.Worship;
import br.com.legado33.app.domain.worship.exception.WorshipNotFoundException;
import br.com.legado33.app.api.controller.dto.request.newDTO.NewWorshipDTO;
import br.com.legado33.app.api.controller.dto.response.ReadWorshipDTO;
import br.com.legado33.app.api.controller.dto.request.updateDTO.UpdateWorshipDTO;
import br.com.legado33.app.domain.worship.repository.WorshipRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class WorshipService {
    private final WorshipRepository worshipRepository;
    public WorshipService(WorshipRepository repository){
        this.worshipRepository = repository;
    }

    public Page<ReadWorshipDTO> getAllWorshipes(Pageable page){
        return worshipRepository.findAll(page).map(ReadWorshipDTO :: new);
    }

    public ReadWorshipDTO saveNewWorship(NewWorshipDTO worshipDTO){
        Worship worship = new Worship(worshipDTO);
        Worship savedWorship = worshipRepository.save(worship);
        return new ReadWorshipDTO(savedWorship);
    }

    public ReadWorshipDTO findReadWorshipDTOById(Long id){
        return worshipRepository.findById(id)
                .map(ReadWorshipDTO::new)
                .orElseThrow(() -> new WorshipNotFoundException(id));
    }

    public Worship findWorshipById(Long id){
        return worshipRepository.findById(id)
                .orElseThrow(() -> new WorshipNotFoundException(id));
    }

    public ReadWorshipDTO update(UpdateWorshipDTO worshipDTO, Long id) {
        Worship existingWorship = worshipRepository
                .findById(id)
                .orElseThrow(() -> new WorshipNotFoundException(id));
        existingWorship = updateWorshipFromDTO(worshipDTO, existingWorship);

        return new ReadWorshipDTO(worshipRepository.save(existingWorship));
    }

    public Worship updateWorshipFromDTO(UpdateWorshipDTO worshipDTO,Worship worship) {
        if(!worshipDTO.schedule().equals(worship.getSchedule())){
            worship.setSchedule(worshipDTO.schedule());
        }
        if(!worshipDTO.location().equals(worship.getLocation())){
            worship.setLocation(worshipDTO.location());
        }
        if(!worshipDTO.duration().equals(worship.getDuration())){
            worship.setDuration(worshipDTO.duration());
        }
        if(!worshipDTO.theme().equals(worship.getTheme())){
            worship.setTheme(worshipDTO.theme());
        }

        return worship;
    }

    public void delete(Long id) {
        worshipRepository.findById(id).orElseThrow(() -> new WorshipNotFoundException(id));
        worshipRepository.deleteById(id);
    }
}
