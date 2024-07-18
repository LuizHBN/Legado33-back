package br.com.legado33.app.conversation.service;

import br.com.legado33.app.conversation.Conversation;
import br.com.legado33.app.conversation.dto.NewConversationDTO;
import br.com.legado33.app.conversation.dto.ReadConversationDTO;
import br.com.legado33.app.conversation.dto.UpdateConversationDTO;
import br.com.legado33.app.conversation.exceptions.ConversationNotFoundException;
import br.com.legado33.app.conversation.repository.ConversationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ConversationService {
    private final ConversationRepository conversationRepository;
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
        if(!conversationDTO.user_1().equals(conversation.getUser_1())){
            conversation.setUser_1(conversationDTO.user_1());
        }
        if(!conversationDTO.user_2().equals(conversation.getUser_2())){
            conversation.setUser_2(conversationDTO.user_2());
        }
        return conversation;
    }

    public void delete(Long id) {
        conversationRepository.findById(id).orElseThrow(() -> new ConversationNotFoundException(id));
        conversationRepository.deleteById(id);
    }
}
