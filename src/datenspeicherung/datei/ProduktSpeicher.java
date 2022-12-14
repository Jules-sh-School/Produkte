package datenspeicherung.datei;

import datenspeicherung.ProduktDaten;

import java.io.*;
import java.util.ArrayList;

public final class ProduktSpeicher {

    public ProduktSpeicher() {
    }

    public void aendere(int produktNr, String bezeichnung, double verkaufspreis, double lagerbestand) {
        loesche(produktNr);
        try {
            fuegeHinzu(bezeichnung, verkaufspreis, lagerbestand);
        } catch (IOException ignored) {
        }
    }

    public void fuegeHinzu(String bezeichnung, double verkaufspreis, double lagerbestand) throws IOException {
        final BufferedWriter writer = new BufferedWriter(new FileWriter("produkt.csv", true));
        final BufferedReader reader = new BufferedReader(new FileReader("produktnr.csv"));
        final int nr = reader.read();
        final String s = nr + ";" + bezeichnung + ";" + verkaufspreis + ";" + lagerbestand;
        writer.write(s);
        writer.newLine();
        writer.close();
    }

    public ArrayList<ProduktDaten> liesProdukte() {
        final ArrayList<ProduktDaten> list = new ArrayList<>();
        final BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("produkt.csv"));
        } catch (IOException e) {
            return list;
        }
        while (true) {
            final String zeile;
            try {
                zeile = reader.readLine();
            } catch (IOException e) {
                break;
            }
            if (zeile == null) {
                break;
            }
            final String[] daten = zeile.split(";");
            final int produktNr = Integer.parseInt(daten[0]);
            final String bezeichnung = daten[1];
            final double verkaufspreis = Double.parseDouble(daten[2]);
            final double lagerbestand = Double.parseDouble(daten[3]);
            final ProduktDaten produkt = new ProduktDaten(produktNr, bezeichnung, verkaufspreis, lagerbestand);
            list.add(produkt);
        }
        try {
            reader.close();
        } catch (IOException ignored) {
            return list;
        }
        return list;
    }

    public void loesche(int produktNr) {
        final ArrayList<ProduktDaten> list;
        list = liesProdukte();
        list.removeIf(produktDaten -> produktDaten.liesProduktNr() == produktNr);
    }
}
