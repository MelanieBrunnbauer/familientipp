package my.familientipp.app.DTO;

public class WinnerTipDTO {

    private String firstNameOfAppUser;
    private String fifaCodeOfSoccerTeam;

    public WinnerTipDTO(String firstNameOfAppUser, String fifaCodeOfSoccerTeam) {
        this.firstNameOfAppUser = firstNameOfAppUser;
        this.fifaCodeOfSoccerTeam = fifaCodeOfSoccerTeam;
    }

    public String getFirstNameOfAppUser() {
        return firstNameOfAppUser;
    }

    public String getFifaCodeOfSoccerTeam() {
        return fifaCodeOfSoccerTeam;
    }
}
