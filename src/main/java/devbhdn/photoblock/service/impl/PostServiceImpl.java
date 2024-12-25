package devbhdn.photoblock.service.impl;

import devbhdn.photoblock.dto.post.PostRequestDto;
import devbhdn.photoblock.dto.post.PostResponseDto;
import devbhdn.photoblock.model.User;
import devbhdn.photoblock.service.PostService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Override
    public PostResponseDto uploadPost(MultipartFile file, String caption, User user)
            throws IOException {
        return null;
    }

    @Override
    public List<PostResponseDto> getPostsByUsersUsername(String username) {
        return List.of();
    }

    @Override
    public PostResponseDto getPost(Long id) {
        return null;
    }

    @Override
    public PostResponseDto editPost(PostRequestDto requestDto, Long postId, Long userId) {
        return null;
    }

    @Override
    public void deletePost(Long postId, Long userId) {

    }
}
