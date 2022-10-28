package datenspeicherung;

public final class ProduktDaten {

    private final int produktNr;
    private final String bezeichnung;

    private final double verkaufspreis;
    private final double lagerbestand;


    public ProduktDaten(int produktNr, String bezeichnung, double verkaufspreis, double lagerbestand) {
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
