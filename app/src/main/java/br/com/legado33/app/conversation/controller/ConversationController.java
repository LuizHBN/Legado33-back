package br.com.legado33.app.conversation.controller;

import br.com.legado33.app.conversation.dto.NewConversationDTO;
import br.com.legado33.app.conversation.dto.ReadConversationDTO;
import br.com.legado33.app.conversation.dto.UpdateConversationDTO;
import br.com.legado33.app.conversation.exceptions.ConversationNotFoundException;
import br.com.legado33.app.conversation.service.ConversationService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conversas")
public class ConversationController {
    private final ConversationService conversationService;
    public ConversationController(ConversationService service){
        this.conversationService = service;
    }

    @PostMapping("/save")
    public ResponseEntity<ReadConversationDTO> createConversation(@RequestBody @Valid NewConversationDTO conversationDTO){
        return ResponseEntity.ok(conversationService.saveNewConversation(conversationDTO));
    }

    @GetMapping
    public ResponseEntity<Page<ReadConversationDTO>> getAllConversation(Pageable page){
        return ResponseEntity.ok(conversationService.getAllConversations(page));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReadConversationDTO> getConversationById(@PathVariable Long id){
            return ResponseEntity.ok(conversationService.findConversationById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ReadConversationDTO> updateConversation(@PathVariable Long id, @Valid @RequestBody UpdateConversationDTO conversationDTO) {
        if (conversationDTO == null){
            return ResponseEntity.notFound().build();
        }
            return ResponseEntity.ok(conversationService.update(conversationDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConversation(@PathVariable Long id) {
            conversationService.delete(id);
            return ResponseEntity.noContent().build();

    }

}
