package gruppeeksamen.Data;

import gruppeeksamen.Modell.Utover;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;

public class NyDataHandler {
    public static ObservableList<Utover> hentUtoverData() {
        ArrayList<Utover> utoverTilFil = new ArrayList<>();

        //Definerer filen vi skal skrive til
        File kilden = new File("src/main/java/gruppeeksamen/brukere.csv");

        //Skriver listen til den spesifiserte filen
        skrivTilCSVFil(utoverTilFil, kilden);

        //Leser ut fra filen og legger det i en ny liste
        ArrayList<Utover> utoverFraFil = lesFraCSVFil(kilden);

        //Sender Arraylisten til en ObservableArrayList
        return FXCollections.observableArrayList(utoverFraFil);
    }

    public static void skrivTilCSVFil(ArrayList<Utover> utovere, File filSOmSkrivesTil) {
        try (BufferedWriter bufretSkriver = new BufferedWriter(new FileWriter(filSOmSkrivesTil,true))) {
            //Går gjennom alle utovere i listen
            for(Utover enUtover: utovere) {
                //Skriver alt til filen avskilt med ";"
                bufretSkriver.write(enUtover.getBrukenavn() + ";" + enUtover.getPassord() + ";" +
                        enUtover.getNavn() + ";" + enUtover.getEtternavn() + ";" + enUtover.getAlder());

                //Skriver et linjeskift
                bufretSkriver.newLine();
            }
        }
        catch (FileNotFoundException fnfn) {
            //Skriver ut feilmelding om filen ikke finnes
            System.out.println(fnfn.getMessage());
        }
        catch (IOException ioexc) {
            //Skriver ut feilmelding om det oppstår feil vei skriving til fil
            System.out.println(ioexc.getLocalizedMessage());
        }
    }

    public static ArrayList<Utover> lesFraCSVFil(File filSomLesesFra) {
        ArrayList<Utover> utoverFraFil = new ArrayList<>();

        try (BufferedReader bufretLeser = new BufferedReader(new FileReader(filSomLesesFra))) {
            //Lager variabel for å holde på hver tekstlinje
            String linje;

            //Løkken går så lenge det finnes linjer
            while ((linje = bufretLeser.readLine()) != null) {
                //Deler opp teksten med tegnet ";" -> dette resulterer i en array som heter deler
                //Index 0 -> Brukernavn
                //Index 1 -> Passord
                //Index 2 -> Navn
                //Index 3 -> Etternavn
                //Index 4 -> Alder
                String[] deler = linje.split(";");

                //Lager en ny utover med denne informasjonen
                Utover enUtover = new Utover(deler[0], deler[1], deler[2], deler[3], Integer.parseInt(deler[4]));

                //Legger til denne utoveren i listen
                utoverFraFil.add(enUtover);
            }
        }
        catch (IOException e) {
            //Skriver ut feilmelding om det oppstår feil med utoveren
            System.out.println(e.getLocalizedMessage());
        }

        //Returnerer listen med utovere
        return utoverFraFil;
    }
}
