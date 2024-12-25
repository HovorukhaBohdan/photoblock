package devbhdn.photoblock.controller;

import devbhdn.photoblock.dto.post.PostRequestDto;
import devbhdn.photoblock.dto.post.PostResponseDto;
import devbhdn.photoblock.model.User;
import devbhdn.photoblock.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public PostResponseDto uploadPost(
            @RequestParam("image") MultipartFile file,
            @RequestParam("caption") String caption,
            Authentication authentication
    ) throws IOException {
        User user = (User) authentication.getPrincipal();
        return postService.uploadPost(file, caption, user);
    }

    @GetMapping
    public List<PostResponseDto> getPostsByUsersUsername(
            @RequestParam String username
    ) {
        return postService.getPostsByUsersUsername(username);
    }

    @GetMapping("/{id}")
    public PostResponseDto getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @PatchMapping("/{id}")
    public PostResponseDto editPost(
            @RequestBody PostRequestDto requestDto,
            @PathVariable Long id,
            Authentication authentication
    ) {
        User user = (User) authentication.getPrincipal();
        return postService.editPost(requestDto, id, user.getId());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(
            @PathVariable Long id,
            Authentication authentication
    ) {
        User user = (User) authentication.getPrincipal();
        postService.deletePost(id, user.getId());
    }
}