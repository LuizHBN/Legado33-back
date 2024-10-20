package br.com.legado33.app.conversation.service;

import br.com.legado33.app.domain.conversation.service.ConversationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.legado33.app.domain.conversation.Conversation;
import br.com.legado33.app.api.controller.dto.request.newDTO.NewConversationDTO;
import br.com.legado33.app.api.controller.dto.request.updateDTO.UpdateConversationDTO;
import br.com.legado33.app.domain.conversation.exception.ConversationNotFoundException;
import br.com.legado33.app.domain.conversation.repository.ConversationRepository;
import br.com.legado33.app.domain.user.User;
import br.com.legado33.app.api.controller.dto.request.newDTO.NewUserDTO;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ConversationServiceTest {

    @Mock
    private ConversationRepository conversationRepository;

    @InjectMocks
    private ConversationService conversationService;

    User user1;
    User user2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        NewUserDTO userDTO1 = new NewUserDTO("NomeUsuario1", "emailusuario1@example.com");
        NewUserDTO userDTO2 = new NewUserDTO("NomeUsuario2", "emailusuario2@example.com");
        user1 = new User(userDTO1);
        user2 = new User(userDTO2);
    }

    @Test
    void testGetAllConversations() {
        when(conversationRepository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.singletonList(new Conversation())));
        conversationService.getAllConversations(Pageable.unpaged());
        verify(conversationRepository).findAll(any(Pageable.class));
    }

    @Test
    void testSaveNewConversation() {
        when(conversationRepository.save(any(Conversation.class))).thenReturn(new Conversation());
        conversationService.saveNewConversation(new NewConversationDTO(user1, user2));
        verify(conversationRepository).save(any(Conversation.class));
    }

    @Test
    void testFindReadConversationDTOById_Found() {
        when(conversationRepository.findById(anyLong())).thenReturn(Optional.of(new Conversation()));
        conversationService.findReadConversationDTOById(1L);
        verify(conversationRepository).findById(anyLong());
    }

    @Test
    void testFindReadConversationDTOById_NotFound() {
        when(conversationRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ConversationNotFoundException.class, () -> conversationService.findReadConversationDTOById(1L));
    }

    @Test
    void testUpdate_Found() {
        when(conversationRepository.findById(anyLong())).thenReturn(Optional.of(new Conversation()));
        when(conversationRepository.save(any(Conversation.class))).thenReturn(new Conversation());
        conversationService.update(new UpdateConversationDTO(user1, user2), 1L);
        verify(conversationRepository).save(any(Conversation.class));
    }

    @Test
    void testUpdate_NotFound() {
        when(conversationRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ConversationNotFoundException.class, () -> conversationService.update(new UpdateConversationDTO(user1, user2), 1L));
    }

    @Test
    void testDelete_Found() {
        when(conversationRepository.findById(anyLong())).thenReturn(Optional.of(new Conversation()));
        doNothing().when(conversationRepository).deleteById(anyLong());
        conversationService.delete(1L);
        verify(conversationRepository).deleteById(anyLong());
    }

    @Test
    void testDelete_NotFound() {
        when(conversationRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ConversationNotFoundException.class, () -> conversationService.delete(1L));
    }
}
