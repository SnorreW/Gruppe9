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

    //Antall utovere i ett arrangement må være 0 eller mer.
    public static int minimumAntallUtovere(int faktiskAntall){
        //int antallUtovereIArrangementet;

        if (faktiskAntall <= 0) {
            return 0;
        } else {
            return faktiskAntall - 1;
        }
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