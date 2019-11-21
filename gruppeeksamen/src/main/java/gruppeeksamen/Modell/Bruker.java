package gruppeeksamen.Modell;

public abstract class Bruker {
    private String brukenavn, passord, klubb, navn, etternavn;
    private int alder;


    public Bruker(String klubb, String navn, String etternavn) {
        this.klubb = klubb;
        this.navn = navn;
        this.etternavn = etternavn;
    }

    public Bruker(String brukenavn, String passord, String navn, String etternavn, int alder) {
        this.brukenavn = brukenavn;
        this.passord = passord;
        this.navn = navn;
        this.etternavn = etternavn;
        this.alder = alder;
    }
    public Bruker(String brukenavn, String passord, String klubb, String navn, String etternavn) {
        this.brukenavn = brukenavn;
        this.passord = passord;
        this.klubb = klubb;
        this.navn = navn;
        this.etternavn = etternavn;
    }

    public Bruker(String brukenavn, String passord) {
        this.brukenavn = brukenavn;
        this.passord = passord;
    }

    public Bruker() {
    }

    public String getBrukenavn() {
        return brukenavn;
    }

    public void setBrukernavn(String brukenavn) {
        this.brukenavn = brukenavn;
    }

    public String getPassord() {
        return passord;
    }

    public void setPassord(String passord) {
        this.passord = passord;
    }

    public String getKlubb() {
        return klubb;
    }

    public void setKlubb(String klubb) {
        this.klubb = klubb;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public int getAlder() {
        return alder;
    }

    public void setAlder(int alder) {
        this.alder = alder;
    }

    //Sjekk at loggin info er riktig
    public static boolean sjekkAtBrukerInputErFylt(String brukernavn, String passord){
        boolean godkjent = false;
        if (brukernavn == null|| brukernavn == "" && passord == null||passord == "") {
            godkjent = false;
        } else if (brukernavn == null|| brukernavn == "") {

            godkjent = false;
        } else if (passord == null||passord == "") {

            godkjent = false;
        } else {
            godkjent = true;

        }
        return godkjent;
    }


    @Override
    public String toString() {
        return "Brukernavn: " + brukenavn + ", Passord: " + passord;
    }
}
