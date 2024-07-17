package br.com.legado33.app.conversation.service;

import br.com.legado33.app.conversation.Conversation;
import br.com.legado33.app.conversation.dto.NewConversationDTO;
import br.com.legado33.app.conversation.dto.ReadConversationDTO;
import br.com.legado33.app.conversation.dto.UpdateConversationDTO;
import br.com.legado33.app.conversation.exceptions.ConversationNotFoundException;
import br.com.legado33.app.conversation.repository.ConversationRepository;
import br.com.legado33.app.conversation.Conversation;
import br.com.legado33.app.conversation.dto.NewConversationDTO;
import br.com.legado33.app.conversation.dto.ReadConversationDTO;
import br.com.legado33.app.conversation.dto.UpdateConversationDTO;
import br.com.legado33.app.conversation.exceptions.ConversationNotFoundException;
import br.com.legado33.app.conversation.repository.ConversationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ConversationService {
    private final ConversationRepository conversationRepository;
    @Autowired
    public ConversationService(ConversationRepository conversationRepository){
        this.conversationRepository = conversationRepository;
    }

    public Page<ReadConversationDTO> getAllConversations(Pageable page){
        return conversationRepository.findAll(page).map(ReadConversationDTO :: new);
    }

    public ReadConversationDTO saveNewConversation(NewConversationDTO conversationDTO){
        Conversation conversation = new Conversation(conversationDTO);
        Conversation savedConversation = conversationRepository.save(conversation);
        return new ReadConversationDTO(savedConversation);
    }

    public ReadConversationDTO findConversationById(Long id){
        return conversationRepository.findById(id)
                .map(ReadConversationDTO::new)
                .orElseThrow(() -> new ConversationNotFoundException(id));
    }

    public ReadConversationDTO update(UpdateConversationDTO conversationDTO, Long id) {
        Conversation existingConversation = conversationRepository
                .findById(id)
                .orElseThrow(() -> new ConversationNotFoundException(id));
        existingConversation = updateConversationFromDTO(conversationDTO, existingConversation);

        return new ReadConversationDTO(conversationRepository.save(existingConversation));
    }

    public Conversation updateConversationFromDTO(UpdateConversationDTO conversationDTO,Conversation conversation) {
        if (!conversationDTO.description().equals(conversation.getDescription())){
            conversation.setDescription(conversationDTO.description());
        }
        if (!conversationDTO.title().equals(conversation.getTitle())){
            conversation.setTitle(conversationDTO.title());
        }
        if (!conversationDTO.image().equals(conversation.getImage())){
            conversation.setImage(conversationDTO.image());
        }
        return conversation;
    }

    public void delete(Long id) {
        conversationRepository.findById(id).orElseThrow(() -> new ConversationNotFoundException(id));
        conversationRepository.deleteById(id);
    }
}
