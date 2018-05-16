package my.familientipp.app.repositories;

import my.familientipp.app.models.SoccerTeam;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SoccerTeamRepository extends CrudRepository<SoccerTeam, Long> {
    List<SoccerTeam> findAll();
}
