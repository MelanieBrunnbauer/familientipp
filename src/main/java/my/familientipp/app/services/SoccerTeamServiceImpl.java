package my.familientipp.app.services;

import my.familientipp.app.models.SoccerTeam;
import my.familientipp.app.repositories.SoccerTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class SoccerTeamServiceImpl implements SoccerTeamService{

    private SoccerTeamRepository repository;

    @Autowired
    SoccerTeamServiceImpl(SoccerTeamRepository repository) {
        this.repository = repository;
    }

    public List<SoccerTeam> findAll() {
        return repository.findAll();
    }
}
