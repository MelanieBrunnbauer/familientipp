package my.familientipp.app.DTO;

public class WinnerTipDTO {

    private String firstNameOfAppUser;
    private String fifaCodeOfSoccerTeam;

    public WinnerTipDTO(String firstNameOfAppUser, String fifaCodeOfSoccerTeam) {
        this.firstNameOfAppUser = firstNameOfAppUser;
        this.fifaCodeOfSoccerTeam = fifaCodeOfSoccerTeam;
    }

    public WinnerTipDTO() {
    }

    public WinnerTipDTO(String firstNameOfAppUser) {
        this.firstNameOfAppUser = firstNameOfAppUser;
    }

    public String getFirstNameOfAppUser() {
        return firstNameOfAppUser;
    }

    public String getFifaCodeOfSoccerTeam() {
        return fifaCodeOfSoccerTeam;
    }

    public void setFirstNameOfAppUser(String firstNameOfAppUser) {
        this.firstNameOfAppUser = firstNameOfAppUser;
    }

    public void setFifaCodeOfSoccerTeam(String fifaCodeOfSoccerTeam) {
        this.fifaCodeOfSoccerTeam = fifaCodeOfSoccerTeam;
    }
}
