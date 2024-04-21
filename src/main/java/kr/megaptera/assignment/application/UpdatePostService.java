package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdatePostService {
    private final PostRepository postRepository;

    public UpdatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto updatePost(String idForUpdate, PostDto postDto) {
        Post postFound = postRepository.find(PostId.of(idForUpdate));
        if (postFound == null) {
            return null;
        }

        postFound.update(postDto.getTitle(), postDto.getContent());
        return new PostDto(postFound);
    }
}
