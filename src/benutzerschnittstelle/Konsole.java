package benutzerschnittstelle;

import datenspeicherung.ProduktDaten;
import datenspeicherung.datei.ProduktSpeicher;

import java.io.IOException;
import java.util.ArrayList;

public final class Konsole {

    public static void main(String[] args) {
        // Objekt erstellen
        final ProduktSpeicher sp = new ProduktSpeicher();
        // Daten hinzufuegen
        try {
            sp.fuegeHinzu("Tolles Produkt", 200.00, 20);
        } catch (IOException ignored) {
        }
        final ArrayList<ProduktDaten> d;
        try {
            d = sp.liesProdukte();
        } catch (IOException ignored) {
            return;
        }
        System.out.format("%10s %-50s %13s %13s", "Produktnr", "Bezeichnung", "Verkaufspreis", "Lagerbestand");
        System.out.print(System.lineSeparator());
        System.out.println("-------------------------------------------------------------------------------------");
        for (ProduktDaten data : d) {
            System.out.printf("%10d %-50s %10.2f, %10.2f", data.liesProduktNr(), data.liesBezeichnung(), data.liesVerkaufspreis(), data.liesLagerbestand());
            System.out.print(System.lineSeparator());
        }


    }
}
