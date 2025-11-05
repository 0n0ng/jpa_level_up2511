package com.ll.jpa2511.domain.post;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUsername(String username);

    // 비관적 락(데이터를 읽을 때 누가 수정할지도 몰라~ 하고 미리 락 걸어둠)
    // 읽기 락
    @Lock(LockModeType.PESSIMISTIC_READ)
    Optional<Post> findWithShareLockById(Long id);

    // 비관적 락
    // 쓰기 락
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Post> findWithWriteLockById(Long id);
}
