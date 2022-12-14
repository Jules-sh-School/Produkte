package benutzerschnittstelle;

import datenspeicherung.ProduktDaten;
import steuerung.Steuerung;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public final class Benutzerschnittstelle extends JFrame {
    /*
    Values
     */
    private final JTable tableProdukte = new JTable();
    private final JScrollPane scrPaneProdukte = new JScrollPane();
    private final JTextField textFieldProduktNr = new JTextField();
    private final JTextField textFieldBezeichnung = new JTextField();
    private final JTextField textFieldVerkaufspreis = new JTextField();
    private final JTextField textFieldLagerbestand = new JTextField();
    private final Steuerung steuerung;

    public Benutzerschnittstelle() {
        init();
        steuerung = new Steuerung(this);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ce) {
            System.out.print("Error: " + ce.getLocalizedMessage());
        }
        EventQueue.invokeLater(() -> {
            final Benutzerschnittstelle ui = new Benutzerschnittstelle();
            ui.setVisible(true);
        });
    }

    /*
    Init Methods
     */

    private void init() {
        setTitle("Verwaltungsprodukte");
        setEnabled(true);
        setResizable(true);
        final JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        setFocusable(true);
        requestFocus();
        setAlwaysOnTop(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 600);
        setContentPane(contentPaneInit());
        setVisible(true);
    }

    private JPanel contentPaneInit() {
        final JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new GridBagLayout());
        scrPaneProdukte.setBounds(10, 11, 598, 356);
        tableProdukte.setModel(
                new DefaultTableModel(
                        new Object[][]{null, null, null, null},
                        new String[]{"Produktnummer", "Bezeichnung", "Verkaufspreis", "Lagerbestand"}
                ) {
                    final Class<?>[] columnTypes = new Class[]{
                            Integer.class, String.class, Double.class, Integer.class
                    };
                    final boolean[] columnEditables = new boolean[]{false, false, false, false};

                    @Override
                    public Class<?> getColumnClass(int columnIndex) {
                        return columnTypes[columnIndex];
                    }

                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return columnEditables[column];
                    }
                }
        );
        scrPaneProdukte.setViewportView(tableProdukte);
        contentPane.add(scrPaneProdukte);
        contentPane.add(textFieldBezeichnung);
        contentPane.add(textFieldVerkaufspreis);
        contentPane.add(textFieldLagerbestand);
        return contentPane;
    }

    /*
    Runtime Methods
     */

    private void geklicktAendern() {
        steuerung.geklicktAendern(
                Integer.parseInt(textFieldProduktNr.getText()),
                textFieldBezeichnung.getText(),
                Double.parseDouble(textFieldVerkaufspreis.getText()),
                Double.parseDouble(textFieldLagerbestand.getText())
        );
    }

    private void geklicktHinzufuegen() {
        steuerung.geklicktHinzufuegen(
                textFieldBezeichnung.getText(),
                Double.parseDouble(textFieldVerkaufspreis.getText()),
                Double.parseDouble(textFieldLagerbestand.getText())
        );
    }

    private void geklicktLoeschen() {
        steuerung.geklicktLoeschen(Integer.parseInt(tableProdukte.getModel().getValueAt(tableProdukte.getSelectedRow(), 0).toString()));
    }

    private void geklicktProdukt() {
        textFieldProduktNr.setText(tableProdukte.getModel().getValueAt(tableProdukte.getSelectedRow(), 0).toString());
        textFieldBezeichnung.setText(tableProdukte.getModel().getValueAt(tableProdukte.getSelectedRow(), 1).toString());
        textFieldVerkaufspreis.setText(tableProdukte.getModel().getValueAt(tableProdukte.getSelectedRow(), 2).toString());
        textFieldLagerbestand.setText(tableProdukte.getModel().getValueAt(tableProdukte.getSelectedRow(), 3).toString());
    }

    public void zeigeMeldung(String meldung) {
        JOptionPane.showMessageDialog(this, meldung);
    }

    public void zeigeProdukte(ArrayList<ProduktDaten> daten) {
        DefaultTableModel model = (DefaultTableModel) tableProdukte.getModel();
        model.setRowCount(0);
        for (ProduktDaten produkt : daten) {
            model.addRow(new Object[]{
                    produkt.liesProduktNr(),
                    produkt.liesBezeichnung(),
                    produkt.liesVerkaufspreis(),
                    produkt.liesLagerbestand()
            });
        }
    }
}
