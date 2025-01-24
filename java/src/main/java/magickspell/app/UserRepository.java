package magickspell.app;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
  List<UserEntity> findByName(String name);
}
