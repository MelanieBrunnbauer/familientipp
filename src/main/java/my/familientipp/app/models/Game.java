package my.familientipp.app.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name="home_team_id")
    private SoccerTeam homeTeam;

    @NotNull
    @ManyToOne
    @JoinColumn(name="guest_team_id")
    private SoccerTeam guestTeam;

    @NotNull
    private LocalDateTime kickoff;

    @OneToMany(mappedBy = "game")
    private List<GameTipp> tippsByAppUsers;

    public Game() {
    }

    public Game(@NotNull SoccerTeam homeTeam, @NotNull SoccerTeam guestTeam, @NotNull LocalDateTime kickoff) {
        this.homeTeam = homeTeam;
        this.guestTeam = guestTeam;
        this.kickoff = kickoff;
    }

    public Long getId() {
        return id;
    }

    public SoccerTeam getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(SoccerTeam homeTeam) {
        this.homeTeam = homeTeam;
    }

    public SoccerTeam getGuestTeam() {
        return guestTeam;
    }

    public void setGuestTeam(SoccerTeam guestTeam) {
        this.guestTeam = guestTeam;
    }

    public LocalDateTime getKickoff() {
        return kickoff;
    }

    public void setKickoff(LocalDateTime kickoff) {
        this.kickoff = kickoff;
    }

    public List<GameTipp> getTippsByAppUsers() {
        return tippsByAppUsers;
    }

    public void setTippsByAppUsers(List<GameTipp> tippsByAppUsers) {
        this.tippsByAppUsers = tippsByAppUsers;
    }

    public void addTippBy(AppUser appUser){
        GameTipp gameTipp = new GameTipp(this,appUser);
        tippsByAppUsers.add(gameTipp);
        appUser.getGamesTipped().add(gameTipp);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(homeTeam, game.homeTeam) &&
                Objects.equals(guestTeam, game.guestTeam) &&
                Objects.equals(kickoff, game.kickoff);
    }

    @Override
    public int hashCode() {

        return Objects.hash(homeTeam, guestTeam, kickoff);
    }
}
