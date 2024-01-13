package com.spring.tming.domain.post.repository;

import com.spring.tming.domain.post.entity.PostStack;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostStackRepository extends JpaRepository<PostStack, Long> {

    void deleteByPostPostId(Long postId);

    List<PostStack> findAllByPostPostId(Long postId);
}
