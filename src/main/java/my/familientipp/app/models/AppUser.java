package my.familientipp.app.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name="app_user")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private int score;

    @ManyToOne
    private SoccerTeam winnertip;

    @OneToMany(mappedBy = "appUser")
    private List<GameTipp> gamesTipped;

    public AppUser() {
    }

    public AppUser(@NotNull String firstName, @NotNull String lastName, SoccerTeam winnertip, int score) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.winnertip = winnertip;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Optional<SoccerTeam> getWinnertip() {
        return Optional.ofNullable(winnertip);
    }

    public void setWinnertip(SoccerTeam winnertip) {
        this.winnertip = winnertip;
    }

    List<GameTipp> getGamesTipped() {
        return gamesTipped;
    }

    public void setGamesTipped(List<GameTipp> gamesTipped) {
        this.gamesTipped = gamesTipped;
    }
}
