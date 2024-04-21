package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.*;
import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.exceptions.PostNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final GetPostsService getPostsService;
    private final GetPostService getPostService;
    private final UpdatePostService updatePostService;
    private final DeletePostService deletePostService;
    private final CreatePostService createPostService;

    public PostController(GetPostsService getPostsService,
                          GetPostService getPostService,
                          UpdatePostService updatePostService,
                          DeletePostService deletePostService,
                          CreatePostService createPostService) {
        this.getPostsService = getPostsService;
        this.getPostService = getPostService;
        this.updatePostService = updatePostService;
        this.deletePostService = deletePostService;
        this.createPostService = createPostService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PostDto> list() {
        return getPostsService.getPostDtos();
    }

    @GetMapping("/{id}")
    public PostDto detail(@PathVariable String id) {
        PostDto postDto = getPostService.getPostDto(id);
        if (postDto == null) {
            throw new PostNotFound();
        }
        return postDto;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto create(@RequestBody PostDto postDto) {
        return createPostService.createPost(postDto);
    }

    @PatchMapping("/{id}")
    public PostDto update(@PathVariable String id,
                          @RequestBody PostDto postDto) {
        return updatePostService.updatePost(id, postDto);
    }

    @DeleteMapping("/{id}")
    public PostDto delete(@PathVariable String id) {
        return deletePostService.deletePost(id);
    }

    @ExceptionHandler(PostNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String postNotFound() {
        return "게시물을 찾을 수 없습니다";
    }


}
