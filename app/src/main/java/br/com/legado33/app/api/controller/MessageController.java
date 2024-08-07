package br.com.legado33.app.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.legado33.app.api.controller.dto.request.newDTO.NewMessageDTO;
import br.com.legado33.app.api.controller.dto.response.ReadMessageDTO;
import br.com.legado33.app.domain.message.service.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

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
    public ResponseEntity<Page<ReadMessageDTO>> getAllMessages(@RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="7") int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        return ResponseEntity.ok(reelService.getAllMessages(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadMessageDTO> getMessageById(@PathVariable Long id) {

            return ResponseEntity.ok(reelService.findMessageById(id));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
            reelService.delete(id);
            return ResponseEntity.noContent().build();
    }
}
