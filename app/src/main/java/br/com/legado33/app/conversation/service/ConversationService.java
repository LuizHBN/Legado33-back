package br.com.legado33.app.conversation.service;

import br.com.legado33.app.conversation.Conversation;
import br.com.legado33.app.conversation.dto.NewConversationDTO;
import br.com.legado33.app.conversation.dto.ReadConversationDTO;
import br.com.legado33.app.conversation.dto.UpdateConversationDTO;
import br.com.legado33.app.conversation.exceptions.ConversationNotFoundException;
import br.com.legado33.app.conversation.repository.ConversationRepository;
import br.com.legado33.app.user.User;
import br.com.legado33.app.user.dto.ReadUserDTO;
import br.com.legado33.app.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ConversationService {
    private final ConversationRepository conversationRepository;
    private final UserService userService;
    @Autowired
    public ConversationService(ConversationRepository conversationRepository, UserService userService){
        this.conversationRepository = conversationRepository;
        this.userService = userService;
    }

    public Page<ReadConversationDTO> getAllConversations(Pageable page){
        return conversationRepository.findAll(page).map(ReadConversationDTO :: new);
    }

    public ReadConversationDTO saveNewConversation(NewConversationDTO conversationDTO){
            ReadUserDTO userDTO_1 = userService.findUserById(conversationDTO.user_1().getId());
            User user_1 = new User(userDTO_1);

            ReadUserDTO userDTO_2 = userService.findUserById(conversationDTO.user_2().getId());
            User user_2 = new User(userDTO_2);


            Conversation conversation = new Conversation(conversationDTO);
            conversation.setUser_1(user_1);
            conversation.setUser_2(user_2);
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
