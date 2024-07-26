package br.com.legado33.app.domain.message.service;

import br.com.legado33.app.domain.conversation.service.ConversationService;
import br.com.legado33.app.domain.message.Message;
import br.com.legado33.app.domain.message.exception.MessageNotFoundException;
import br.com.legado33.app.domain.message.repository.MessageRepository;
import br.com.legado33.app.domain.conversation.Conversation;
import br.com.legado33.app.api.controller.dto.response.ReadConversationDTO;
import br.com.legado33.app.api.controller.dto.request.newDTO.NewMessageDTO;
import br.com.legado33.app.api.controller.dto.response.ReadMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final ConversationService conversationService;

    @Autowired
    public MessageService(MessageRepository messageRepository, ConversationService conversationService) {
        this.messageRepository = messageRepository;
        this.conversationService = conversationService;
    }

    public ReadMessageDTO saveNewMessage(NewMessageDTO messageDTO) {
        //TODO -> Test sending a unreacheable conversation
        ReadConversationDTO conversationDTO = conversationService.findConversationById(messageDTO.conversation().getId());
        Conversation conversation = new Conversation(conversationDTO);

        Message message = new Message(messageDTO, conversation);
        Message savedMessage = messageRepository.save(message);

        return new ReadMessageDTO(savedMessage);
    }

    public Page<ReadMessageDTO> getAllMessages(Pageable page) {
        return messageRepository.findAll(page).map(ReadMessageDTO::new);
    }

    public ReadMessageDTO findMessageById(Long id) {
        return messageRepository.findById(id)
                .map(ReadMessageDTO::new)
                .orElseThrow(() -> new MessageNotFoundException(id));
    }


    public void delete(Long id) {
        messageRepository.findById(id).orElseThrow(() -> new MessageNotFoundException(id));
        messageRepository.deleteById(id);
    }

}

