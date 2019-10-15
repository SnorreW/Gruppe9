package Modell;

public class Bruker {
    private String brukenavn, passord, klubb;

    public Bruker(String brukenavn, String passord) {
        this.brukenavn = brukenavn;
        this.passord = passord;
    }

    public Bruker(String brukenavn, String passord, String klubb) {
        this.brukenavn = brukenavn;
        this.passord = passord;
        this.klubb = klubb;
    }

    public String getBrukenavn() {
        return brukenavn;
    }

    public String getPassord() {
        return passord;
    }

    public String getKlubb() {return klubb;}

    @Override
    public String toString() {
        return "Brukernavn: " + brukenavn + ", Passord: " + passord;
    }
}
