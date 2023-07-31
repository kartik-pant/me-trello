package trello.models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Board {
    private Long id;
    private String title;
    private String description;
    private BoardVisbility visibility;
    private User owner;
    private List<Column> columns;
    private List<User> members;

    public Board() {
    }

    public Board(String title, String description, BoardVisbility visibility, User owner) {
        this.title = title;
        this.description = description;
        this.visibility = visibility;
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
        this.visibility = board.visibility;
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

    public BoardVisbility getVisbility() {
        return visibility;
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

    public void setVisibilty(BoardVisbility visibility) {
        this.visibility = visibility;
    }

    public void addNewColumn(Column column) {
        this.columns.add(column);
    }

    public void removeColumn(Column column) {
        this.columns.remove(column);
    }

    public void moveColumn(Long columnId, int newPosition) {
        int currentPosition = IntStream.range(0, columns.size())
                .filter(index -> columns.get(index).getId().equals(columnId))
                .findFirst().orElse(-1);
        if (currentPosition == -1)
            return;
        columns.add(newPosition, columns.get(currentPosition));
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
