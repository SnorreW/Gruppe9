package Modell;


import java.time.LocalDate;
import java.util.Comparator;


public class Aktivitet implements Comparable<Aktivitet>{

    private String navnPaaAktivitet;
    private int tid;
    private LocalDate dato;
    private Bruker utover;

    public Aktivitet(String navnPaaAktivitet, int tid, LocalDate dato, Bruker utover){
        this.navnPaaAktivitet = navnPaaAktivitet;
        this.tid = tid;
        this.dato = dato;
        this.utover = utover;
    }

    public String getNavnPaaAktivitet() {
        return navnPaaAktivitet;
    }

    public LocalDate getDato() {
        return dato;
    }

    public Bruker getUtover() {
        return utover;
    }

    public int getTid(){
        return tid;
    }

    public void setNavnPaaAktivitet(String navnPaaAktivitet) {
        this.navnPaaAktivitet = navnPaaAktivitet;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public void setDato(LocalDate dato) {
        this.dato = dato;
    }

    public void setUtover(Bruker utover) {
        this.utover = utover;
    }

    @Override
    public int compareTo(Aktivitet enTid) {

        if ( this.tid == enTid.getTid()){
            return this.tid - enTid.getTid();
        }
        else{
            return this.tid - enTid.getTid();
        }
    }

    @Override
    public String toString() {
        return navnPaaAktivitet + ": " + tid;
    }
}
