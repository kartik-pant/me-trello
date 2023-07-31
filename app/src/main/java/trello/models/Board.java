package trello.models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private Long id;
    private String title;
    private String description;
    private boolean isPrivate;
    private User owner;
    private List<Column> columns;
    private List<User> members;

    public Board() {
    }

    public Board(String title, String description, boolean isPrivate, User owner) {
        this.title = title;
        this.description = description;
        this.isPrivate = isPrivate;
        this.owner = owner;
        this.columns = new ArrayList<>();
        this.members = new ArrayList<>();

        // adding owner as the member of the Board
        addNewMember(owner);
    }

    public Board(Long id, Board board) {
        this.id = id;
        this.title = board.title;
        this.description = board.description;
        this.owner = board.owner;
        this.members = board.members;
        this.columns = board.columns;
        this.isPrivate = board.isPrivate;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public User getOwner() {
        return owner;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public void addNewColumn(Column column) {
        this.columns.add(column);
    }

    public void removeColumn(Column column) {
        this.columns.remove(column);
    }

    public void moveColumn(Column column, int newPosition) {
        int currentPosition = columns.indexOf(column);
        columns.add(newPosition, column);
        // if new position is less than current position
        // then the element is pushed one index ahead
        if (newPosition <= currentPosition) {
            currentPosition += 1;
        }
        columns.remove(currentPosition);
    }

    public void addNewMember(User member) {
        this.members.add(member);
    }
}
