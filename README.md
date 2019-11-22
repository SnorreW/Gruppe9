# Gruppe 9 Gruppeeksamen
Dette programmet er en liten demo av et større system vi ser for oss. All funksjonaliteten er ikke på plass enda.

## Hvordan komme i gang
Disse instruksjonene vil gi deg en kjørende kopi av prosjektet på din lokale maskin: 

1. Last ned JavaFX, følg instruksjonen under hva du må laste ned. 
2. Last ned en kopi av prosjektet fra Github.
3. Start intellij.
4. Åpne mappen "gruppe9/gruppeeksamen".
5. Trykk på "edit config" oppe i høyre hjørne i intellij.
6. Legg til en konfigurasjon med MainJavaFX som hovedklasse. Pass på å sett VM-options før du trykker på "Apply" og deretter "Ok".
7. Trykk på "Play" knappen. En grønn pil i høyre hjørne av intellij.

## Hva du må laste ned
Følgende programvare må innstalleres før programmet kjøres for første gang:

* [Javafx](https://openjfx.io/) - Rich Client Applications

1. Bla ned og trykk på "getting started" knappen.
2. Klikk på "JavaFX and Intellij" i menyen på venstre side.
3. Hvis du ikke har JDK 13 så må det lastes ned først via linken på toppen av siden.
4. Følg instruksjonene på siden til du har satt opp JavaFX i Intellij.

## Hvordan kjøre testene
For å kjøre testene må du høyreklikke på mappen "Test" og velge "Run "All Tests" with coverage"

## Laget med
Vi har brukt følgende programmer til å lage programmet vårt: 

* [Maven](https://maven.apache.org/) - Dependency Management
* [Javafx](https://openjfx.io/) - Rich Client Applications

## Hvis programmet ikke kjører
Ofte når vi får problemer så viser det seg å være javafx som er grunnen. Ofte kan VM options forsvinne fra intellij og må bli lagt til på nytt. 

Da er det denne du må legge inn for windows: --module-path "\path\to\javafx-sdk-13\lib" --add-modules javafx.controls,javafx.fxml.

Så er det denne du må legge inn hvis du har linux/mac: --module-path /path/to/javafx-sdk-13/lib --add-modules javafx.controls,javafx.fxml.

## Skrevet av
* Aleksander Pleym Sandnes

Se også en [liste](https://github.com/SnorreW/Gruppe9/blob/master/CONTRIBUTING.mb) av alle som har vært med på dette prosjektet
