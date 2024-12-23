package devbhdn.photoblock.service;

import devbhdn.photoblock.dto.post.PostRequestDto;
import devbhdn.photoblock.dto.post.PostResponseDto;

import java.io.IOException;
import java.util.List;

import devbhdn.photoblock.model.User;
import org.springframework.web.multipart.MultipartFile;

public interface PostService {
    PostResponseDto uploadPost(MultipartFile file, PostRequestDto requestDto, User user) throws IOException;

    List<PostResponseDto> getPostsByUserId(Long userId);

    PostResponseDto getPost(Long id);

    PostResponseDto editPost(PostRequestDto requestDto, Long postId, Long userId);

    void deletePost(Long postId, Long userId);
}
