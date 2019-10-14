package no.hiof.gruppeoblig.java.Modell;

public class Brukere {
    private String brukenavn, passord;

    public Brukere(String brukenavn, String passord) {
        this.brukenavn = brukenavn;
        this.passord = passord;
    }

    @Override
    public String toString() {
        return brukenavn;
    }
}