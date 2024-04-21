package kr.megaptera.assignment.dtos;

import kr.megaptera.assignment.models.Comment;

import java.util.Objects;

public class CommentDto {
    private String id;
    private String author;
    private String content;

    public CommentDto() {
    }

    public CommentDto(String id, String author, String content) {
        this.id = id;
        this.author = author;
        this.content = content;
    }

    public CommentDto(Comment comment) {
        this.id = comment.id().toString();
        this.author = comment.author();
        this.content = comment.content();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentDto that = (CommentDto) o;
        return Objects.equals(id, that.id) && Objects.equals(author, that.author) && Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, content);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }
    

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
