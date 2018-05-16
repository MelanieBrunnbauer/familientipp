package my.familientipp.app.services;

import my.familientipp.app.models.SoccerTeam;
import my.familientipp.app.repositories.SoccerTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class SoccerTeamService {

    private SoccerTeamRepository repository;

    @Autowired
    SoccerTeamService(SoccerTeamRepository repository) {
        this.repository = repository;
    }

    List<SoccerTeam> findAll() {
        return repository.findAll();
    }
}
