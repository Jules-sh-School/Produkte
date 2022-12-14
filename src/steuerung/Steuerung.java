package steuerung;

import benutzerschnittstelle.Benutzerschnittstelle;
import datenspeicherung.datei.ProduktSpeicher;

import java.io.IOException;

public final class Steuerung {
    private final Benutzerschnittstelle benutzerschnittstelle;

    private final ProduktSpeicher speicher;

    public Steuerung(Benutzerschnittstelle benutzerschnittstelle) {
        this.benutzerschnittstelle = benutzerschnittstelle;
        speicher = new ProduktSpeicher();
        benutzerschnittstelle.zeigeProdukte(speicher.liesProdukte());
    }

    public void geklicktAendern(int produktNr, String bezeichnung, double verkaufspreis, double lagerbestand) {
        speicher.aendere(produktNr, bezeichnung, verkaufspreis, lagerbestand);
    }

    public void geklicktHinzufuegen(String bezeichnung, double verkaufspreis, double lagerbestand) {
        try {
            speicher.fuegeHinzu(bezeichnung, verkaufspreis, lagerbestand);
        } catch (IOException ignored) {
        }
    }

    public void geklicktLoeschen(int produktNr) {
        speicher.loesche(produktNr);
    }
}
