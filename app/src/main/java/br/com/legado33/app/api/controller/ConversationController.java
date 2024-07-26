package br.com.legado33.app.api.controller;

import br.com.legado33.app.api.controller.dto.request.newDTO.NewConversationDTO;
import br.com.legado33.app.api.controller.dto.response.ReadConversationDTO;
import br.com.legado33.app.api.controller.dto.request.updateDTO.UpdateConversationDTO;
import br.com.legado33.app.domain.conversation.service.ConversationService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conversa")
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
