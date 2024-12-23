package devbhdn.photoblock.service.impl;

import devbhdn.photoblock.dto.post.PostEditRequestDto;
import devbhdn.photoblock.dto.post.PostRequestDto;
import devbhdn.photoblock.dto.post.PostResponseDto;
import devbhdn.photoblock.model.User;
import devbhdn.photoblock.repository.PostRepository;
import devbhdn.photoblock.repository.UserRepository;
import devbhdn.photoblock.service.PostService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public PostResponseDto uploadPost(MultipartFile file, PostRequestDto requestDto, User user) {
        return null;
    }

    @Override
    public List<PostResponseDto> getPostsByUserId(Long userId) {
        return List.of();
    }

    @Override
    public PostResponseDto getPost(Long id) {
        return null;
    }

    @Override
    public PostResponseDto editPost(
            PostEditRequestDto requestDto,
            Long postId,
            Long userId
    ) {
        return null;
    }

    @Override
    public void deletePost(Long postId, Long userId) {

    }
}
