package br.com.legado33.app.message.service;

import br.com.legado33.app.conversation.Conversation;
import br.com.legado33.app.conversation.dto.ReadConversationDTO;
import br.com.legado33.app.conversation.service.ConversationService;
import br.com.legado33.app.message.Message;
import br.com.legado33.app.message.dto.NewMessageDTO;
import br.com.legado33.app.message.dto.ReadMessageDTO;
import br.com.legado33.app.message.exceptions.MessageNotFoundException;
import br.com.legado33.app.message.repository.MessageRepository;
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

    public ReadMessageDTO findById(Long id) {
        return messageRepository.findById(id)
                .map(ReadMessageDTO::new)
                .orElseThrow(() -> new MessageNotFoundException(id));
    }


    public void delete(Long id) {
        messageRepository.findById(id).orElseThrow(() -> new MessageNotFoundException(id));
        messageRepository.deleteById(id);
    }

}

