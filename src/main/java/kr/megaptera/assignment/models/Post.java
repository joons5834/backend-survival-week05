package kr.megaptera.assignment.models;

public class Post {
    private PostId postId;
    private String title;
    private String author;
    private String content;

    public Post(PostId postId, String title, String author, String content) {
        this.postId = postId;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public Post(String title, String author, String content) {
        this.postId = PostId.generate();
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public PostId postId() {
        return postId;
    }

    public String title() {
        return title;
    }

    public String author() {
        return author;
    }

    public String content() {
        return content;
    }
}
