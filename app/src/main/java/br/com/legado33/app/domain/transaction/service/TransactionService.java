package br.com.legado33.app.domain.transaction.service;

import br.com.legado33.app.domain.campaign.Campaign;
import br.com.legado33.app.domain.transaction.exception.TransactionNotFoundException;
import br.com.legado33.app.domain.transaction.repository.TransactionRepository;
import br.com.legado33.app.domain.user.service.UserService;
import br.com.legado33.app.api.controller.dto.response.ReadCampaignDTO;
import br.com.legado33.app.domain.campaign.service.CampaignService;
import br.com.legado33.app.domain.transaction.Transaction;
import br.com.legado33.app.api.controller.dto.request.newDTO.NewTransactionDTO;
import br.com.legado33.app.api.controller.dto.response.ReadTransactionDTO;
import br.com.legado33.app.api.controller.dto.request.updateDTO.UpdateTransactionDTO;
import br.com.legado33.app.domain.user.User;
import br.com.legado33.app.api.controller.dto.response.ReadUserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserService userService;
    private final CampaignService campaignService;

    public TransactionService(TransactionRepository transactionRepository, UserService userService, CampaignService campaign) {
        this.transactionRepository = transactionRepository;
        this.userService = userService;
        this.campaignService = campaign;

    }

    public ReadTransactionDTO saveNewTransaction(NewTransactionDTO transactionDTO) {
        ReadUserDTO userDTO = userService.findUserById(transactionDTO.user().getId());
        User user = new User(userDTO);

        ReadCampaignDTO campaignDTO = campaignService.findCampaignById(transactionDTO.campaign().getId());
        Campaign campaign = new Campaign(campaignDTO);

        Transaction transaction = new Transaction(transactionDTO, user, campaign);
        Transaction savedTransaction = transactionRepository.save(transaction);
        return new ReadTransactionDTO(savedTransaction);
    }

    public Page<ReadTransactionDTO> getAllTransactions(Pageable page) {
        return transactionRepository.findAll(page).map(ReadTransactionDTO :: new);
    }

    public ReadTransactionDTO FindTransactionById(Long id) {
        return  transactionRepository.findById(id)
                .map(ReadTransactionDTO::new)
                .orElseThrow(() -> new TransactionNotFoundException(id));
    }
    
    public ReadTransactionDTO FindTransactionByUser(Long userId) {
        return  transactionRepository.findByUserId(userId)
                .map(ReadTransactionDTO::new)
                .orElseThrow(() -> new TransactionNotFoundException(userId));
    }

    public ReadTransactionDTO update(UpdateTransactionDTO transactionDTO, Long id) {
        Transaction existingTransaction = transactionRepository
                .findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(id));
        updateTransactionFromDTO(transactionDTO,existingTransaction);

        return new ReadTransactionDTO(transactionRepository.save(existingTransaction));
    }

    public void delete(Long id) {
        transactionRepository.findById(id).orElseThrow(() -> new TransactionNotFoundException(id));
        transactionRepository.deleteById(id);
    }

    private Transaction updateTransactionFromDTO(UpdateTransactionDTO transactionDTO,Transaction transaction ) {
        if (!transactionDTO.value().equals(transaction.getValue())) {
            transaction.setValue(transactionDTO.value());
        }
        if (!transactionDTO.user().equals(transaction.getUser())) {
            transaction.setUser(transactionDTO.user());
            //TODO -> Verificar se a atualização de usuário funciona sem usar o findById
        }
        if (!transactionDTO.type().equals(transaction.getType())) {
            transaction.setType(transactionDTO.type());
        }
        if (!transactionDTO.campaign().equals(transaction.getCampaign())) {
            transaction.setCampaign(transactionDTO.campaign());
            //TODO -> Verificar se a atualização de transação funciona sem usar o findById
        }
        return transaction;
    }

}