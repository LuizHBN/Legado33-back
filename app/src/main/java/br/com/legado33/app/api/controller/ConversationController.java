package br.com.legado33.app.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.legado33.app.api.controller.dto.request.newDTO.NewConversationDTO;
import br.com.legado33.app.api.controller.dto.request.updateDTO.UpdateConversationDTO;
import br.com.legado33.app.api.controller.dto.response.ReadConversationDTO;
import br.com.legado33.app.domain.conversation.service.ConversationService;
import jakarta.validation.Valid;

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
    public ResponseEntity<Page<ReadConversationDTO>> getAllConversation(@RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="7") int size){
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        return ResponseEntity.ok(conversationService.getAllConversations(pageable));
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
