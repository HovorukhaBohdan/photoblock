package devbhdn.photoblock.controller;

import com.dropbox.core.DbxException;
import devbhdn.photoblock.dto.post.PostRequestDto;
import devbhdn.photoblock.dto.post.PostResponseDto;
import devbhdn.photoblock.dto.post.PostWithImageLinkResponseDto;
import devbhdn.photoblock.model.User;
import devbhdn.photoblock.service.PostService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    ) throws IOException, DbxException {
        User user = (User) authentication.getPrincipal();
        return postService.uploadPost(file, caption, user);
    }

    @GetMapping("/{id}")
    public PostWithImageLinkResponseDto getPost(@PathVariable Long id) throws DbxException {
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
    ) throws DbxException {
        User user = (User) authentication.getPrincipal();
        postService.deletePost(id, user.getId());
    }
}
