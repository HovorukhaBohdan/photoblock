package devbhdn.photoblock.service.impl;

import devbhdn.photoblock.dto.post.PostRequestDto;
import devbhdn.photoblock.dto.post.PostResponseDto;
import devbhdn.photoblock.exception.AccessDeniedException;
import devbhdn.photoblock.exception.PostNotFoundException;
import devbhdn.photoblock.exception.UserNotFoundException;
import devbhdn.photoblock.mapper.PostMapper;
import devbhdn.photoblock.model.Post;
import devbhdn.photoblock.model.User;
import devbhdn.photoblock.repository.PostRepository;
import devbhdn.photoblock.repository.UserRepository;
import devbhdn.photoblock.service.PostService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostMapper postMapper;

    @Override
    public PostResponseDto uploadPost(
            MultipartFile file,
            PostRequestDto requestDto,
            User user
    ) throws IOException {
        Post post = Post.builder()
                .user(user)
                .image(file.getBytes())
                .caption(requestDto.caption())
                .createdAt(LocalDateTime.now())
                .build();

        return postMapper.toDto(postRepository.save(post));
    }

    @Override
    public List<PostResponseDto> getPostsByUserId(Long userId) {
        return postRepository.getAllByUserId(userId).stream()
                .map(postMapper::toDto)
                .toList();
    }

    @Override
    public PostResponseDto getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new PostNotFoundException(
                        String.format("Post with id: %d not found", id))
        );

        return postMapper.toDto(post);
    }

    @Override
    @Transactional
    public PostResponseDto editPost(
            PostRequestDto requestDto,
            Long postId,
            Long userId
    ) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(
                        String.format("Can't get user with id: %d", userId)
                )
        );

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new PostNotFoundException(
                        String.format("Post with id: %d not found", postId))
        );

        if (!post.getUser().equals(user)) {
            throw new AccessDeniedException("Access denied");
        }

        post.setCaption(requestDto.caption());

        return postMapper.toDto(postRepository.save(post));
    }

    @Override
    public void deletePost(Long postId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(
                        String.format("Can't get user with id: %d", userId)
                )
        );

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new PostNotFoundException(
                        String.format("Post with id: %d not found", postId))
        );

        if (post.getUser().equals(user) || user.hasRole("ADMIN")) {
            postRepository.delete(post);
        }

        throw new AccessDeniedException("Access denied");
    }
}
