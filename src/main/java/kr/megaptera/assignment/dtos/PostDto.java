package kr.megaptera.assignment.dtos;

import kr.megaptera.assignment.models.Post;

import java.util.Objects;

public class PostDto {
    private String id;
    private String title;
    private String author;
    private String content;

    public PostDto() {
    }

    public PostDto(String id, String title, String author, String content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public PostDto(Post post) {
        this.id = post.postId().toString();
        this.title = post.title();
        this.author = post.author();
        this.content = post.content();
    }


    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostDto postDto = (PostDto) o;
        return Objects.equals(id, postDto.id) && Objects.equals(title, postDto.title) && Objects.equals(author, postDto.author) && Objects.equals(content, postDto.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, content);
    }
}
