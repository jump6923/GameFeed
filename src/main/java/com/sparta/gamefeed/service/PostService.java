package com.sparta.gamefeed.service;

import com.sparta.gamefeed.dto.PostRequestDto;
import com.sparta.gamefeed.dto.PostResponseDto;
import com.sparta.gamefeed.entity.CategoryFolder;
import com.sparta.gamefeed.entity.Post;
import com.sparta.gamefeed.entity.User;
import com.sparta.gamefeed.repository.CategoryFolderRepository;
import com.sparta.gamefeed.repository.PostRepository;
import com.sparta.gamefeed.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryFolderRepository categoryFolderRepository;


    public List<PostResponseDto> getPostList() {
        List<Post> postList = postRepository.findAllByOrderByModifiedAtDesc();
        List<PostResponseDto> responseDto = new ArrayList<>();

        for (Post post : postList) {
            responseDto.add(new PostResponseDto(post));
        }
        return responseDto;
    }

    public List<PostResponseDto> getPostList(Long categoryfolderId) {
        List<Post> postList = postRepository.findAllById(categoryfolderId);
        List<PostResponseDto> responseDto = new ArrayList<>();

        for (Post post : postList) {
            responseDto.add(new PostResponseDto(post));
        }
        return responseDto;
    }

    public PostResponseDto getPost(Long postId) {
        Post post = findPost(postId);
        return new PostResponseDto(post);
    }

    public PostResponseDto createPost(PostRequestDto requestDto, Long categoryfolderId , String userId) {
        User user = userRepository.findByUserId(userId).orElseThrow(
                () -> new IllegalArgumentException("사용자가 존재하지 않습니다."));
        CategoryFolder categoryFolder = categoryFolderRepository.findById(categoryfolderId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 경로입니다."));
        Post post = new Post(requestDto, user, categoryFolder);
        postRepository.save(post);
        return new PostResponseDto(post);
    }

    @Transactional
    public PostResponseDto updatePost(Long postId, PostRequestDto requestDto, Long userId) {
        User user = findUser(userId);
        Post post = findPost(postId);
        if (user.getId().equals(post.getUser().getId())) {
            post.updatePost(requestDto);
        } else {
            throw new IllegalArgumentException("작성자만 게시물을 수정/삭제 할 수 있습니다.");
        }
        return new PostResponseDto(post);
    }

    public Long deletePost(Long postId) {

        Post post = getValidationPost(postId);
        postRepository.delete(post);
        return postId;
    }

    public Post getValidationPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));
        return post;
    }


}
