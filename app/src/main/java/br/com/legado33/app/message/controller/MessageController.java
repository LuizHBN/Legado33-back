package br.com.legado33.app.message.controller;

import br.com.legado33.app.message.dto.NewMessageDTO;
import br.com.legado33.app.message.dto.ReadMessageDTO;
import br.com.legado33.app.message.service.MessageService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mensagem")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService reelService;

    @PostMapping("/save")
    public ResponseEntity<ReadMessageDTO> createMessage(@RequestBody @Valid NewMessageDTO reelDTO) {
        return ResponseEntity.ok(reelService.saveNewMessage(reelDTO));
    }

    @GetMapping
    public ResponseEntity<Page<ReadMessageDTO>> getAllMessages(Pageable page) {
        return ResponseEntity.ok(reelService.getAllMessages(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadMessageDTO> getMessageById(@PathVariable Long id) {

            return ResponseEntity.ok(reelService.findById(id));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
            reelService.delete(id);
            return ResponseEntity.noContent().build();
    }
}
