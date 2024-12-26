package devbhdn.photoblock.service;

import com.dropbox.core.DbxException;
import devbhdn.photoblock.dto.post.PostRequestDto;
import devbhdn.photoblock.dto.post.PostResponseDto;

import java.io.IOException;
import java.util.List;

import devbhdn.photoblock.dto.post.PostWithImageLinkResponseDto;
import devbhdn.photoblock.model.User;
import org.springframework.web.multipart.MultipartFile;

public interface PostService {
    PostResponseDto uploadPost(MultipartFile file, String caption, User user)
            throws IOException, DbxException;

    PostWithImageLinkResponseDto getPost(Long id) throws DbxException;

    PostResponseDto editPost(PostRequestDto requestDto, Long postId, Long userId);

    void deletePost(Long postId, Long userId) throws DbxException;
}
