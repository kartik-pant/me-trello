package trello.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Card {
    private Long id;
    private String title;
    private String description;
    private List<Comment> comments;
    private boolean isArchived;
    private List<User> members;
    private List<Label> labels;
    private Date dueDate;
    private Date reminderDate;

    public Card() {
    }

    public Card(String title, String description) {
        this.title = title;
        this.description = description;
        this.comments = new ArrayList<>();
        this.members = new ArrayList<>();
        this.labels = new ArrayList<>();
        this.isArchived = Boolean.FALSE;
    }

    public Card(Long id, Card card) {
        this.id = id;
        this.title = card.title;
        this.description = card.description;
        this.comments = card.comments;
        this.members = card.members;
        this.labels = card.labels;
        this.isArchived = card.isArchived;
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

    public List<Comment> getComments() {
        return comments;
    }

    public List<User> getMembers() {
        return members;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Date getReminderDate() {
        return reminderDate;
    }

    public boolean getIsArchived() {
        return isArchived;
    }

    public void addNewComment(Comment comment) {
        this.comments.add(comment);
    }

    public void addNewMember(User user) {
        this.members.add(user);
    }

    public void addNewLabel(Label label) {
        this.labels.add(label);
    }

    public void setDueDate(Date date) {
        this.dueDate = date;
    }

    public void setReminderDate(Date date) {
        this.reminderDate = date;
    }

    public void setArchived(boolean isArchived) {
        this.isArchived = isArchived;
    }
}
