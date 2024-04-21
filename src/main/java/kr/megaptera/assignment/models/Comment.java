package kr.megaptera.assignment.models;

public class Comment {
    private final CommentId id;
    private final String author;
    private String content;

    public Comment(String id, String author, String content) {
        this.id = CommentId.of(id);
        this.author = author;
        this.content = content;
    }

    public Comment(String author, String content) {
        this.id = CommentId.generate();
        this.author = author;
        this.content = content;
    }

    public CommentId id() {
        return id;
    }

    public String author() {
        return author;
    }

    public String content() {
        return content;
    }

    public void update(String content) {
        this.content = content;
    }
}
