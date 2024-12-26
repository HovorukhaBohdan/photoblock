package devbhdn.photoblock.service.impl;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import devbhdn.photoblock.dto.post.PostRequestDto;
import devbhdn.photoblock.dto.post.PostResponseDto;
import devbhdn.photoblock.dto.post.PostWithImageLinkResponseDto;
import devbhdn.photoblock.exception.AccessDeniedException;
import devbhdn.photoblock.exception.PostNotFoundException;
import devbhdn.photoblock.mapper.PostMapper;
import devbhdn.photoblock.model.Post;
import devbhdn.photoblock.model.User;
import devbhdn.photoblock.repository.PostRepository;
import devbhdn.photoblock.repository.UserRepository;
import devbhdn.photoblock.service.PostService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    @Value("${dropbox.api.key}")
    private String dropboxApiKey;
    private DbxClientV2 client;
    private final DbxRequestConfig config = DbxRequestConfig.newBuilder("photoblock")
            .build();
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostMapper postMapper;

    @PostConstruct
    private void postConstruct() {
        client = new DbxClientV2(config, dropboxApiKey);
    }

    @Override
    public PostResponseDto uploadPost(MultipartFile file, String caption, User user)
            throws IOException, DbxException {
        FileMetadata metadata;

        try (InputStream in = file.getInputStream()) {
            metadata = client.files()
                    .uploadBuilder(formPath(file, user))
                    .uploadAndFinish(in);
        }

        Post post = new Post()
                .setUser(user)
                .setDropboxImageId(metadata.getPathLower())
                .setCaption(caption)
                .setCreatedAt(LocalDateTime.now());

        return postMapper.toDto(postRepository.save(post));
    }

    @Override
    public PostWithImageLinkResponseDto getPost(Long id) throws DbxException {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new PostNotFoundException(
                        String.format("Can't find post with id: %d", id)
                )
        );

        String link = client.files().getTemporaryLink(post.getDropboxImageId()).getLink();
        PostWithImageLinkResponseDto dtoWithImageLink = postMapper.toDtoWithImageLink(post);
        dtoWithImageLink.setImageLink(link);

        return dtoWithImageLink;
    }

    @Override
    public PostResponseDto editPost(PostRequestDto requestDto, Long postId, Long userId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new PostNotFoundException(
                        String.format("Can't find post with id: %d", postId)
                )
        );

        User user = userRepository.findById(userId).orElseThrow(
                () -> new PostNotFoundException(
                        String.format("Can't find user with id: %d", userId)
                )
        );

        if (post.getUser().equals(user)) {
            post.setCaption(requestDto.caption());

            return postMapper.toDto(postRepository.save(post));
        }

        throw new AccessDeniedException("Access denied");
    }

    @Override
    public void deletePost(Long postId, Long userId) throws DbxException {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new PostNotFoundException(
                        String.format("Can't find post with id: %d", postId)
                )
        );

        User user = userRepository.findById(userId).orElseThrow(
                () -> new PostNotFoundException(
                        String.format("Can't find user with id: %d", userId)
                )
        );

        if (!post.getUser().equals(user) || !user.hasRole("ADMIN")) {
            throw new AccessDeniedException("Access denied");
        }

        client.files().deleteV2(post.getDropboxImageId());
        postRepository.delete(post);
    }

    private String formPath(MultipartFile file, User user) {
        return String.format(
                "/%s-%s-%s",
                user.getUsername(),
                LocalDateTime.now(),
                file.getOriginalFilename()
        );
    }
}
