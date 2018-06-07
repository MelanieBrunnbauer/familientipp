package my.familientipp.app.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class GameTippId implements Serializable {

    @Column(name="game_id")
    private Long gameId;

    @Column(name="appser_id")
    private Long appUserId;

    GameTippId() {
    }

    public GameTippId(Long gameId, Long appUserId) {
        this.gameId = gameId;
        this.appUserId = appUserId;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Long getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(Long appUserId) {
        this.appUserId = appUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameTippId that = (GameTippId) o;
        return Objects.equals(gameId, that.gameId) &&
                Objects.equals(appUserId, that.appUserId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(gameId, appUserId);
    }
}
