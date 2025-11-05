package com.ll.jpa2511.domain.post;


import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final PostRepository postRepository;

    public Post getPost(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Post not found"));
    }

    public List<Post> getFindByUsername(String username) {
        return postRepository.findByUsername(username);
    }

    public Post create(String subject, String content, String username) {
        Post post = Post.builder()
                .subject(subject)
                .content(content)
                .username(username)
                .build();

        return postRepository.save(post);
    }

    @SneakyThrows
    public Optional<Post> getFindWithShareLockById(Long id) {
        postRepository.findWithShareLockById(id);
        Thread.sleep(10000);
        return postRepository.findWithShareLockById(id);

    }

    public Optional<Post> findWithWriteLockById(Long id) {
        return postRepository.findWithWriteLockById(id);
    }
}