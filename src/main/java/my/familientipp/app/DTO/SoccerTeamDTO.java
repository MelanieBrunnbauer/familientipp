package my.familientipp.app.DTO;

public class SoccerTeamDTO {

    private String fifaCode;
    private String country;

    public SoccerTeamDTO(String fifaCode, String country) {
        this.fifaCode = fifaCode;
        this.country = country;
    }

    public String getFifaCode() {
        return fifaCode;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return country + " (" + fifaCode + ")";
    }
}
