package trello.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import trello.models.Card;

public class CardRepositoryImpl implements CardRepository {
    private static Map<Long, Card> cards = new HashMap<>();
    private static Long autoIncrementCardId = 1L;

    @Override
    public Card deleteCard(Long cardId) {
        return cards.remove(cardId);
    }

    @Override
    public List<Card> findAllCards() {
        return new ArrayList<>(cards.values());
    }

    @Override
    public Optional<Card> findById(Long cardId) {
        return Optional.ofNullable(cards.get(cardId));
    }

    @Override
    public Card save(Card card) {
        Card createdCard = new Card(autoIncrementCardId, card);
        cards.put(autoIncrementCardId, createdCard);
        autoIncrementCardId++;
        return createdCard;
    }

}
