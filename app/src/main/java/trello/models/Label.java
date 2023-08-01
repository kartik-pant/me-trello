package trello.models;

public class Label {
    private Long id;
    private String name;
    private String color;

    public Label() {
    }

    public Label(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public Label(Long id, Label label) {
        this.id = id;
        this.name = label.name;
        this.color = label.color;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

}
