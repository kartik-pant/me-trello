package trello.models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
    private List<Board> boards;
    private List<Card> cards;

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.boards = new ArrayList<>();
        this.cards = new ArrayList<>();
    }

    public User(Long id, User user) {
        this.id = id;
        this.username = user.username;
        this.email = user.email;
        this.password = user.password;
        this.boards = user.boards;
        this.cards = user.cards;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<Board> getBoards() {
        return boards;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addNewBoard(Board board) {
        this.boards.add(board);
    }

    public void addNewCard(Card card) {
        this.cards.add(card);
    }
}
