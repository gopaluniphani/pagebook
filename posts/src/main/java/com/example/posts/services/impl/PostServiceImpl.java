package com.example.posts.services.impl;

import com.example.posts.entity.Post;
import com.example.posts.model.Response;
import com.example.posts.repositories.PostRepository;
import com.example.posts.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Override
    public Response addPost(Post post) {
        Response response;
        try {
            Post addedPost = postRepository.save(post);
            response = Response.builder()
                    .status(true)
                    .body(addedPost)
                    .build();
        }
        catch (Exception e)
        {
            response = Response.builder()
                    .errorMessage(e.toString())
                    .build();
        }
        return response;
    }

    @Override
    public Response findPostByUserId(String userId) {
        Response response;
        List<Post> posts = postRepository.findPostByUserId(userId);
        response = Response.builder()
                .status(true)
                .body(posts)
                .build();
        return  response;
    }

    @Override
    public Post findById(String postId) {
        return postRepository.findById(postId).get();
    }

    @Override
    public String findUserIdByPostId(String postId) {
        return postRepository.findUserIdByPostId(postId);
    }

}
