package Modell;

public class Bruker {
    private String brukenavn, passord, klubb, fornavn, etternavn, mail;

    public Bruker(String brukenavn, String passord, String fornavn, String etternavn, String klubb, String mail) {
        this.brukenavn = brukenavn;
        this.passord = passord;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.klubb = klubb;
        this.mail = mail;
    }

    public Bruker(String brukenavn, String passord, String klubb) {
        this.brukenavn = brukenavn;
        this.passord = passord;
        this.klubb = klubb;
    }

    public Bruker(String klubb, String fornavn, String etternavn, String mail) {
        this.klubb = klubb;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.mail = mail;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getBrukenavn() {
        return brukenavn;
    }

    public String getPassord() {
        return passord;
    }

    public String getKlubb() {return klubb;}

    public void setBrukenavn(String brukenavn) {
        this.brukenavn = brukenavn;
    }

    public void setPassord(String passord) {
        this.passord = passord;
    }

    public void setKlubb(String klubb) {
        this.klubb = klubb;
    }

    @Override
    public String toString() {
        return "Brukernavn: " + brukenavn + ", Passord: " + passord;
    }
}
