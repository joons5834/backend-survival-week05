package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class DeletePostService {
    private final PostRepository postRepository;

    public DeletePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto deletePost(String id) {
        PostId postId = PostId.of(id);
        Post postFound = postRepository.find(postId);
        if (postFound == null) {
            return null;
        }

        postRepository.deletePost(postId);
        return new PostDto(postFound);
    }
}
