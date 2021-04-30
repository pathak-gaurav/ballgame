package none.gauravpathak.ballgame;

import none.gauravpathak.ballgame.model.BallGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BallGameRepository extends JpaRepository<BallGame, Long> {
}
