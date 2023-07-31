package trello.models;

import java.util.ArrayList;
import java.util.List;

public class Column {
    private Long id;
    private String title;
    private List<Card> cards;

    public Column() {
    }

    public Column(String title) {
        this.title = title;
        this.cards = new ArrayList<>();
    }

    public Column(Long id, Column column) {
        this.id = id;
        this.title = column.title;
        this.cards = column.cards;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void addNewCard(Card card) {
        this.cards.add(card);
    }

}
