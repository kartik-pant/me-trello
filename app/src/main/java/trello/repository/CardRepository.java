package trello.repository;

import java.util.List;
import java.util.Optional;

import trello.models.Card;

public interface CardRepository {
    Card save(Card card);

    List<Card> findAllCards();

    Optional<Card> findById(Long cardId);

    Card deleteCard(Long cardId);
}
