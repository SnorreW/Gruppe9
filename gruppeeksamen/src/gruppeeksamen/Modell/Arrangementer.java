package gruppeeksamen.Modell;

public class Arrangementer implements Comparable<Arrangementer> {
    String navn;

    public Arrangementer(String navn) {
        this.navn = navn;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    @Override
    public String toString() {
        return navn;
    }

    @Override
    public int compareTo(Arrangementer etArrangement) {
        return getNavn().compareTo(etArrangement.getNavn());
    }

}
