package trello.services;

import java.util.Date;
import java.util.Optional;

import trello.models.Card;
import trello.models.Column;
import trello.models.Comment;
import trello.models.User;
import trello.repository.BoardRepository;
import trello.repository.BoardRepositoryImpl;
import trello.repository.CardRepository;
import trello.repository.CardRepositoryImpl;
import trello.repository.UserRepository;
import trello.repository.UserRepositoryImpl;

public class CardService {

    CardRepository cardRepo = new CardRepositoryImpl();
    BoardRepository boardRepo = new BoardRepositoryImpl();
    UserRepository userRepo = new UserRepositoryImpl();

    // skipped user authorization
    public Card createCard(Long columnId, String title, String description) {
        Optional<Column> column = boardRepo.findColumnById(columnId);
        if (!column.isPresent())
            throw new RuntimeException("Requested column does not exist");
        Card card = cardRepo.save(new Card(title, description));
        column.get().addNewCard(card);
        return card;
    }

    // should this function allow to un-archive the card
    public void archiveCard(Long userId, Long cardId) {
        Optional<Card> card = cardRepo.findById(cardId);
        Optional<User> user = userRepo.findById(userId);
        if (!card.isPresent() || !user.isPresent())
            throw new RuntimeException("Requested card/user does not exist");
        card.get().setArchived(true);
        card.get().addNewComment(new Comment("Card archived", user.get()));
    }

    public void setDueDate(Long userId, Long cardId, Date newDate) {
        Optional<Card> card = cardRepo.findById(cardId);
        Optional<User> user = userRepo.findById(userId);
        if (!card.isPresent() || !user.isPresent())
            throw new RuntimeException("Requested card/user does not exist");
        card.get().setDueDate(newDate);
        card.get().addNewComment(new Comment("Due date changed.", user.get()));
    }

    public void setReminderDate(Long userId, Long cardId, Date newReminderDate) {
        Optional<Card> card = cardRepo.findById(cardId);
        Optional<User> user = userRepo.findById(userId);
        if (!card.isPresent() || !user.isPresent())
            throw new RuntimeException("Requested card/user does not exist");
        card.get().setReminderDate(newReminderDate);
        card.get().addNewComment(new Comment("Reminder date changed.", user.get()));
    }

}
