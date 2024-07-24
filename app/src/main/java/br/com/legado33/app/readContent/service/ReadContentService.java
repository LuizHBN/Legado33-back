package br.com.legado33.app.readContent.service;

import br.com.legado33.app.worshipMaterial.WorshipMaterial;
import br.com.legado33.app.readContent.ReadContent;
import br.com.legado33.app.readContent.dto.NewReadContentDTO;
import br.com.legado33.app.readContent.dto.ReadReadContentDTO;
import br.com.legado33.app.readContent.dto.UpdateReadContentDTO;
import br.com.legado33.app.readContent.exceptions.ReadContentNotFoundException;
import br.com.legado33.app.readContent.repository.ReadContentRepository;
import br.com.legado33.app.worshipMaterial.dto.ReadWorshipMaterialDTO;
import br.com.legado33.app.worshipMaterial.service.WorshipMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReadContentService {

    private final ReadContentRepository readContentRepository;
    private final WorshipMaterialService worshipMaterialService;

    @Autowired
    public ReadContentService(ReadContentRepository readContentRepository, WorshipMaterialService worshipMaterialService) {
        this.readContentRepository = readContentRepository;
        this.worshipMaterialService = worshipMaterialService;
    }

    public ReadReadContentDTO saveNewReadContent(NewReadContentDTO readContentDTO) {
        ReadWorshipMaterialDTO worshipMaterialDTO = worshipMaterialService.findWorshipMaterialById(readContentDTO.worshipMaterial().getId());
        WorshipMaterial worshipMaterial = new WorshipMaterial(worshipMaterialDTO);
        ReadContent readContent = new ReadContent(readContentDTO, worshipMaterial);
        ReadContent savedReadContent = readContentRepository.save(readContent);
        return new ReadReadContentDTO(savedReadContent);
    }

    public Page<ReadReadContentDTO> getAllReadContents(Pageable page) {
        return readContentRepository.findAll(page).map(ReadReadContentDTO :: new);
    }

    public ReadReadContentDTO findReadContentById(Long id) {
        return  readContentRepository.findById(id)
                .map(ReadReadContentDTO::new)
                .orElseThrow(() -> new ReadContentNotFoundException(id));
    }

    public ReadReadContentDTO update(UpdateReadContentDTO readContentDTO, Long id) {
        ReadContent existingReadContent = readContentRepository
                .findById(id)
                .orElseThrow(() -> new ReadContentNotFoundException(id));
        updateReadContentFromDTO(readContentDTO,existingReadContent);

        return new ReadReadContentDTO(readContentRepository.save(existingReadContent));
    }

    public void delete(Long id) {
        readContentRepository.findById(id).orElseThrow(() -> new ReadContentNotFoundException(id));
        readContentRepository.deleteById(id);
    }

    private ReadContent updateReadContentFromDTO(UpdateReadContentDTO readContentDTO, ReadContent readContent) {
        if (!readContentDTO.book().equals(readContent.getBook())) {
            readContent.setBook(readContentDTO.book());
        }
        if (!readContentDTO.initialChapter().equals(readContent.getInitialChapter())) {
            readContent.setInitialChapter(readContentDTO.initialChapter());
        }
        if (!readContentDTO.finalChapter().equals(readContent.getFinalChapter())) {
            readContent.setFinalChapter(readContentDTO.finalChapter());
        }
        if (!readContentDTO.initialVerse().equals(readContent.getInitialVerse())) {
            readContent.setInitialVerse(readContentDTO.initialVerse());
        }
        if (!readContentDTO.finalVerse().equals(readContent.getFinalVerse())) {
            readContent.setFinalVerse(readContentDTO.finalVerse());
        }
        if (!readContentDTO.comment().equals(readContent.getComment())) {
            readContent.setComment(readContentDTO.comment());
        }
        if (!readContentDTO.worshipMaterial().equals(readContent.getWorshipMaterial())) {
            ReadWorshipMaterialDTO worshipMaterialDTO = worshipMaterialService.findWorshipMaterialById(readContentDTO.worshipMaterial().getId());
            WorshipMaterial worshipMaterial = new WorshipMaterial(worshipMaterialDTO);

            readContent.setWorshipMaterial(worshipMaterial);

        }
        return readContent;
    }

}