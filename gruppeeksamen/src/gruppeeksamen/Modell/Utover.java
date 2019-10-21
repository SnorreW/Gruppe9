package gruppeeksamen.Modell;

public class Utover extends Bruker {

    public Utover(String brukenavn, String passord, String klubb, String navn, String etternavn) {
        super(brukenavn, passord, klubb, navn, etternavn);
    }

    public Utover(String klubb, String navn, String etternavn) {
        super(klubb, navn, etternavn);
    }

    //Hvilke arragementer som brukeren har vært med i


    @Override
    public String toString(){
        return "Navn: " + getNavn() + ". Etternavn: " + getEtternavn() + ". Klubb: " + getKlubb() + ".";
    }

}
