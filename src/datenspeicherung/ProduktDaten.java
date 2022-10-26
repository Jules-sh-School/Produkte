package datenspeicherung;

public final class ProduktDaten {
    private String bezeichnung;
    private double lagerbestand;
    private int produktNr;
    private double verkaufspreis;


    public ProduktDaten(String bezeichnung, double lagerbestand, int produktNr, double verkaufspreis) {
        this.bezeichnung = bezeichnung;
        this.lagerbestand = lagerbestand;
        this.produktNr = produktNr;
        this.verkaufspreis = verkaufspreis;
    }

    public String liesBezeichnung() {
        return bezeichnung;
    }

    public double liesLagerbestand() {
        return lagerbestand;
    }

    public int liesProduktNr() {
        return produktNr;
    }

    public double liesVerkaufspreis() {
        return verkaufspreis;
    }
}
