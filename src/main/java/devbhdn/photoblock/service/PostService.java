package devbhdn.photoblock.service;

import devbhdn.photoblock.dto.post.PostEditRequestDto;
import devbhdn.photoblock.dto.post.PostRequestDto;
import devbhdn.photoblock.dto.post.PostResponseDto;
import java.util.List;

import devbhdn.photoblock.model.User;
import org.springframework.web.multipart.MultipartFile;

public interface PostService {
    PostResponseDto uploadPost(MultipartFile file, PostRequestDto requestDto, User user);

    List<PostResponseDto> getPostsByUserId(Long userId);

    PostResponseDto getPost(Long id);

        PostResponseDto editPost(PostEditRequestDto requestDto, Long postId, Long userId);

    void deletePost(Long postId, Long userId);
}
