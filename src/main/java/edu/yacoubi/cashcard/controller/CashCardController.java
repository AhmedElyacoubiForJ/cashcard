package edu.yacoubi.cashcard.controller;

import edu.yacoubi.cashcard.repository.CashCardRepository;
import edu.yacoubi.cashcard.model.CashCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cashcards")
class CashCardController {

    private final CashCardRepository cashCardRepository;

    private CashCardController(CashCardRepository cashCardRepository) {
        this.cashCardRepository = cashCardRepository;
    }

    @GetMapping("/{requestedId}")
    private ResponseEntity<CashCard> findById(
            @PathVariable Long requestedId,
            Principal principal) {

        Optional<CashCard> cashCardOptional = Optional.ofNullable(
                cashCardRepository.findByIdAndOwner(
                        requestedId,
                        principal.getName()
                )
        );

        if (cashCardOptional.isPresent()) {
            return ResponseEntity.ok(cashCardOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    private ResponseEntity<Void> createCashCard(
            @RequestBody CashCard newCashCardRequest,
            UriComponentsBuilder ucb,
            Principal principal) {

        CashCard cashCardWithOwner = new CashCard(
                null,
                newCashCardRequest.amount(),
                principal.getName()
        );

        CashCard savedCashCard = cashCardRepository.save(cashCardWithOwner);
        // This is constructing a URI to the newly created CashCard.
        // This is the URI that the caller can then use
        // to GET the newly-created CashCard.
        URI locationOfNewCashCard = ucb
                .path("cashcards/{id}")
                .buildAndExpand(savedCashCard.id())
                .toUri();

        return ResponseEntity.created(locationOfNewCashCard).build();
    }

    @GetMapping
    private ResponseEntity<List<CashCard>> findAll(
            Pageable pageable,
            Principal principal) {

        Page<CashCard> page = cashCardRepository
                .findByOwner(
                        principal.getName(),
                        PageRequest.of(
                                pageable.getPageNumber(),
                                pageable.getPageSize(),
                                pageable.getSortOr(
                                        Sort.by(
                                                Sort.Direction.ASC,
                                                "amount"
                                        )
                                )
                        )
                );
        return ResponseEntity.ok(page.getContent());
    }
}