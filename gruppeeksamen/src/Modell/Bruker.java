package Modell;

public class Bruker {
    private String brukenavn, passord;

    public Bruker(String brukenavn, String passord) {
        this.brukenavn = brukenavn;
        this.passord = passord;
    }

    @Override
    public String toString() {
        return brukenavn;
    }
}
