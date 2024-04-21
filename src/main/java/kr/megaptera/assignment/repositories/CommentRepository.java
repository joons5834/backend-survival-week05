package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.PostId;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CommentRepository {
    private final Map<PostId, Map<CommentId, Comment>> commentsMap = new HashMap<>();

    public List<Comment> findAll(PostId postId) {
        return commentsMap.get(postId).values()
                .stream().toList();
    }

    public void save(PostId postId, Comment comment) {
        Map<CommentId, Comment> commentsToAPost = commentsMap.get(postId);
        if (commentsToAPost == null) {
            HashMap<CommentId, Comment> initialCommentMap = new HashMap<>();
            initialCommentMap.put(comment.id(), comment);
            commentsMap.put(postId, initialCommentMap);
        } else {
            commentsToAPost.put(comment.id(), comment);
        }
    }

    public Comment find(CommentId commentId, PostId postId) {
        Map<CommentId, Comment> commentsMapToAPost = commentsMap.get(postId);
        if (commentsMapToAPost == null) {
            return null;
        }
        return commentsMapToAPost.get(commentId);
    }

    public void delete(CommentId commentId, PostId postId) {
        commentsMap.get(postId).remove(commentId);
    }
}
