package com.spring.tming.domain.members.repository;

import com.spring.tming.domain.members.entity.Member;
import com.spring.tming.domain.post.entity.Post;
import com.spring.tming.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByPostAndUser(Post post, User user);
}
