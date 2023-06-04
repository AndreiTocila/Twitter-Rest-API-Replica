package ro.uaic.info.fiipractic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.uaic.info.fiipractic.entity.ReplyEntity;

public interface ReplyRepository extends JpaRepository<ReplyEntity, Integer> {
}
