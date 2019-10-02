package no.hiof.aleksaps.SE;

import javax.management.relation.Role;
import java.time.LocalDate;
import java.util.Set;

public class User {
    private int id;
    private String brukernavn;
    private String passord;
    private String passordBekreftelse;
    private String klubb;
    //private Set<Role> roller;

    public User(int id, String brukernavn, String passord, String passordBekreftelse, String klubb) {
        this.id = id;
        this.brukernavn = brukernavn;
        this.passord = passord;
        this.passordBekreftelse = passordBekreftelse;
        this.klubb = klubb;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrukernavn() {
        return brukernavn;
    }

    public void setBrukernavn(String brukernavn) {
        this.brukernavn = brukernavn;
    }

    public String getPassord() {
        return passord;
    }

    public void setPassord(String passord) {
        this.passord = passord;
    }

    public String getPassordBekreftelse() {
        return passordBekreftelse;
    }

    public void setPassordBekreftelse(String passordBekreftelse) {
        this.passordBekreftelse = passordBekreftelse;
    }

    /*public Set<Role> getRoller() {
        return roller;
    }

    public void setRoller(Set<Role> roller) {
        this.roller = roller;
    }
    */
    public String getKlubb() {
        return klubb;
    }

    public void setKlubb(String klubb) {
        this.klubb = klubb;
    }

    @Override
    public String toString() {
        return "ID: " + getId() + ", Brukernavn: " + getBrukernavn() + ", Klubb: " + getKlubb();

    }
}
