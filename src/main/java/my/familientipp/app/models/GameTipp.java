package my.familientipp.app.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="game_tipp")
class GameTipp {

    @EmbeddedId
    private GameTippId id;

    @ManyToOne
    @MapsId("gameId")
    @JoinColumn(name="game_id")
    private Game game;

    @ManyToOne
    @MapsId("appUserId")
    @JoinColumn(name="app_user_id")
    private AppUser appUser;

    @Column(name="score_hometeam")
    private int scoreHomeTeam;

    @Column(name="score_guestteam")
    private int scoreGuestTeam;

    public GameTipp() {
    }

    GameTipp(Game game, AppUser appUser) {
        this.id = new GameTippId();
        this.game = game;
        this.appUser = appUser;
    }

    public GameTippId getId() {
        return id;
    }

    public void setId(GameTippId id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public int getScoreHomeTeam() {
        return scoreHomeTeam;
    }

    public void setScoreHomeTeam(int scoreHomeTeam) {
        this.scoreHomeTeam = scoreHomeTeam;
    }

    public int getScoreGuestTeam() {
        return scoreGuestTeam;
    }

    public void setScoreGuestTeam(int scoreGuestTeam) {
        this.scoreGuestTeam = scoreGuestTeam;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameTipp gameTipp = (GameTipp) o;
        return Objects.equals(game, gameTipp.game) &&
                Objects.equals(appUser, gameTipp.appUser);
    }

    @Override
    public int hashCode() {

        return Objects.hash(game, appUser);
    }
}
