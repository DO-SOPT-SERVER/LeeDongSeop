package com.example.secondSeminar.post;

import com.example.secondSeminar.common.dto.ApiResponse;
import com.example.secondSeminar.post.dto.request.PostCreateRequest;
import com.example.secondSeminar.post.dto.request.PostUpdateRequest;
import com.example.secondSeminar.post.dto.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.secondSeminar.common.exception.enums.SuccessType.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    private static final String CUSTOM_AUTH_ID = "X-Auth-Id";

//    @PostMapping
//    public ResponseEntity<Void> createPost(@RequestHeader(CUSTOM_AUTH_ID) Long memberId,
//                                           @RequestBody PostCreateRequest request) {
//        URI location = URI.create("/posts/" + postService.create(request, memberId));
//        return ResponseEntity.created(location).build();
//    }
    @PostMapping
    public ApiResponse<?> createPost(@RequestHeader(CUSTOM_AUTH_ID) Long memberId,
                                        @RequestBody PostCreateRequest request) {
        return ApiResponse.success(CREATE_POST_SUCCESS, postService.create(request, memberId));
    }

    @GetMapping("/{postId}")
    public ApiResponse<PostResponse> getPostById(@PathVariable Long postId) {
        return ApiResponse.success(GET_POST_SUCCESS, postService.getById(postId));
    }

    @GetMapping
    public ApiResponse<List<PostResponse>> getPosts(@RequestHeader(CUSTOM_AUTH_ID) Long memberId) {
        return ApiResponse.success(GET_POST_LIST_SUCCESS, postService.getPosts(memberId));
    }

    @PatchMapping("/{postId}")
    public ApiResponse<?> updatePost(@PathVariable Long postId, @RequestBody PostUpdateRequest request) {
        postService.editContent(postId, request);
        return ApiResponse.success(UPDATE_POST_SUCCESS);
    }

    @DeleteMapping("/{postId}")
    public ApiResponse<?> deletePost(@PathVariable Long postId) {
        postService.deleteById(postId);
        return ApiResponse.success(DELETE_POST_SUCCESS);
    }
}
