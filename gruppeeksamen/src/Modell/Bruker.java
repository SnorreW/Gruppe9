package Modell;

public class Bruker {
    private String brukenavn, passord;

    public Bruker(String brukenavn, String passord) {
        this.brukenavn = brukenavn;
        this.passord = passord;
    }



    public String getBrukenavn() {
        return brukenavn;
    }

    public String getPassord() {
        return passord;
    }

    @Override
    public String toString() {
        return "Brukernavn: " + brukenavn + ", Passord: " + passord;
    }
}
