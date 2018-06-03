package my.familientipp.app.repositories;

import my.familientipp.app.models.Game;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GameRepository extends CrudRepository<Game,Long> {
    List<Game> findAll();
}
