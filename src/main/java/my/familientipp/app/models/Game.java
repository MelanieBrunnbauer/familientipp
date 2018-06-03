package my.familientipp.app.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity

public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name="home_team_id")
    private SoccerTeam home_team;

    @NotNull
    @ManyToOne
    @JoinColumn(name="guest_team_id")
    private SoccerTeam guest_team;

    @NotNull
    private LocalDateTime kickoff;

    public SoccerTeam getHome_team() {
        return home_team;
    }

    public void setHome_team(SoccerTeam home_team) {
        this.home_team = home_team;
    }

    public SoccerTeam getGuest_team() {
        return guest_team;
    }

    public void setGuest_team(SoccerTeam guest_team) {
        this.guest_team = guest_team;
    }

    public LocalDateTime getKickoff() {
        return kickoff;
    }

    public void setKickoff(LocalDateTime kickoff) {
        this.kickoff = kickoff;
    }
}
