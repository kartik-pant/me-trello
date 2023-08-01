package trello.services;

import java.util.Date;
import java.util.Optional;

import trello.models.Card;
import trello.models.Column;
import trello.models.Comment;
import trello.models.Label;
import trello.models.User;
import trello.repository.BoardRepository;
import trello.repository.CardRepository;
import trello.repository.UserRepository;
import trello.utils.AuthHelpers;

public class CardService {

    CardRepository cardRepo;
    BoardRepository boardRepo;
    UserRepository userRepo;
    AuthHelpers authHelpers = new AuthHelpers();

    public CardService(CardRepository cardRepo, BoardRepository boardRepo, UserRepository userRepo) {
        this.cardRepo = cardRepo;
        this.boardRepo = boardRepo;
        this.userRepo = userRepo;
    }

    public Card createCard(Long boardId, Long userId, Long columnId, String title, String description) {
        if (!authHelpers.isUserBoardMember(boardId, userId))
            throw new RuntimeException("Requested user is not a board member");
        Optional<Column> column = boardRepo.findColumnById(columnId);
        if (!column.isPresent())
            throw new RuntimeException("Requested column does not exist");
        Card card = cardRepo.save(new Card(title, description));
        column.get().addNewCard(card);
        return card;
    }

    // TODO - should this function allow to un-archive the card
    public void archiveCard(Long boardId, Long userId, Long cardId) {
        if (!authHelpers.isUserBoardMember(boardId, userId))
            throw new RuntimeException("Requested user is not a board member");
        Optional<Card> card = cardRepo.findById(cardId);
        Optional<User> user = userRepo.findById(userId);
        if (!card.isPresent() || !user.isPresent())
            throw new RuntimeException("Requested card/user does not exist");
        card.get().setArchived(true);
        card.get().addNewComment(new Comment("Card archived", user.get()));
    }

    public void setDueDate(Long boardId, Long userId, Long cardId, Date newDate) {
        if (!authHelpers.isUserBoardMember(boardId, userId))
            throw new RuntimeException("Requested user is not a board member");
        Optional<Card> card = cardRepo.findById(cardId);
        Optional<User> user = userRepo.findById(userId);
        if (!card.isPresent() || !user.isPresent())
            throw new RuntimeException("Requested card/user does not exist");
        card.get().setDueDate(newDate);
        card.get().addNewComment(new Comment("Due date changed.", user.get()));
    }

    public void setReminderDate(Long boardId, Long userId, Long cardId, Date newReminderDate) {
        if (!authHelpers.isUserBoardMember(boardId, userId))
            throw new RuntimeException("Requested user is not a board member");
        Optional<Card> card = cardRepo.findById(cardId);
        Optional<User> user = userRepo.findById(userId);
        if (!card.isPresent() || !user.isPresent())
            throw new RuntimeException("Requested card/user does not exist");
        card.get().setReminderDate(newReminderDate);
        card.get().addNewComment(new Comment("Reminder date changed.", user.get()));
    }

    public void addLabels(Long boardId, Long userId, Long cardId, Label label) {
        if (!authHelpers.isUserBoardMember(boardId, userId))
            throw new RuntimeException("Requested user is not a board member");
        Optional<Card> card = cardRepo.findById(cardId);
        Optional<User> user = userRepo.findById(userId);
        if (!card.isPresent() || !user.isPresent())
            throw new RuntimeException("Requested card/user does not exist");
        card.get().addNewLabel(label);
        card.get().addNewComment(new Comment("New label added.", user.get()));
    }

}
