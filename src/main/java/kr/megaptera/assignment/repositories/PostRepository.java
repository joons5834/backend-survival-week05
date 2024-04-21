package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostRepository {
    private final Map<PostId, Post> postMap = new HashMap<>();

    public List<Post> findAll() {
        return postMap.values().stream().toList();
    }

    public Post find(PostId id) {
        return postMap.get(id);
    }

    public void deletePost(PostId postId) {
        postMap.remove(postId);
    }

    public void save(Post post) {
        postMap.put(post.postId(), post);
    }
}
