package br.com.legado33.app.readContent.service;

import br.com.legado33.app.worshipMaterial.WorshipMaterial;
import br.com.legado33.app.readContent.ReadContent;
import br.com.legado33.app.readContent.dto.NewReadContentDTO;
import br.com.legado33.app.readContent.dto.ReadReadContentDTO;
import br.com.legado33.app.readContent.dto.UpdateReadContentDTO;
import br.com.legado33.app.readContent.exceptions.ReadContentNotFoundException;
import br.com.legado33.app.readContent.repository.ReadContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReadContentService {

//    private final ReadContentRepository readContentRepository;
//    private final WorshipMaterialService worshipMaterialService;
//
//    @Autowired
//    public ReadContentService(ReadContentRepository readContentRepository, WorshipMaterialService worshipMaterialService) {
//        this.readContentRepository = readContentRepository;
//        this.worshipMaterialService = worshipMaterialService;
//    }
//
//    public ReadReadContentDTO saveNewReadContent(NewReadContentDTO readContentDTO) {
//        //TODO -> Test sending a unreacheable worshipMaterial
//        ReadWorshipMaterialDTO worshipMaterialDTO = worshipMaterialService.findWorshipMaterialById(readContentDTO.worshipMaterial());
//        WorshipMaterial worshipMaterial = new WorshipMaterial(worshipMaterialDTO);
//        ReadContent readContent = new ReadContent(readContentDTO, worshipMaterial);
//        ReadContent savedReadContent = readContentRepository.save(readContent);
//        return new ReadReadContentDTO(savedReadContent);
//    }
//
//    public Page<ReadReadContentDTO> getAllReadContents(Pageable page) {
//        return readContentRepository.findAll(page).map(ReadReadContentDTO :: new);
//    }
//
//    public ReadReadContentDTO findById(Long id) {
//        return  readContentRepository.findById(id)
//                .map(ReadReadContentDTO::new)
//                .orElseThrow(() -> new ReadContentNotFoundException(id));
//    }
//
//    public ReadReadContentDTO update(UpdateReadContentDTO readContentDTO, Long id) {
//        ReadContent existingReadContent = readContentRepository
//                .findById(id)
//                .orElseThrow(() -> new ReadContentNotFoundException(id));
//        updateReadContentFromDTO(readContentDTO,existingReadContent);
//
//        return new ReadReadContentDTO(readContentRepository.save(existingReadContent));
//    }
//
//    public void delete(Long id) {
//        readContentRepository.findById(id).orElseThrow(() -> new ReadContentNotFoundException(id));
//        readContentRepository.deleteById(id);
//    }
//
//    private ReadContent updateReadContentFromDTO(UpdateReadContentDTO readContentDTO, ReadContent readContent) {
//        if (readContentDTO.title() != null && !readContentDTO.title().equals(readContent.getTitle())) {
//            readContent.setTitle(readContentDTO.title());
//        }
//        if (readContentDTO.description() != null && !readContentDTO.description().equals(readContent.getDescription())) {
//            readContent.setDescription(readContentDTO.description());
//        }
//        if (readContentDTO.worshipMaterial() != null) {
//            //TODO -> Fazer verificação e tratamento de categoria não encontrada
//            ReadWorshipMaterialDTO worshipMaterialDTO = worshipMaterialService.findWorshipMaterialById(readContentDTO.worshipMaterial());
//            WorshipMaterial worshipMaterial = new WorshipMaterial(worshipMaterialDTO);
//            if (!worshipMaterial.equals(readContent.getWorshipMaterial())) {
//                readContent.setWorshipMaterial(worshipMaterial);
//            }
//        }
//        return readContent;
//    }

}