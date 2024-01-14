package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AngajatFrame extends JFrame {
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;

    private JLabel LabelNume, LabelPrenume, LabelDepartament, LabelStatut, LabelTipJob, LabelDescriereJob;
    private JButton button_salvare, button_cancel;
    private JTextField textField1, textField2;
    private JTextArea textArea;
    private JRadioButton fullTime_radio, partTime_radio;
    private JComboBox<String> departamenteCombo;

    private static List<Angajat> angajatiList = new ArrayList<>();

    public AngajatFrame() {
        prepareGUI();
        showAngajatForm();
    }

    public static void main(String[] args) {


            try {
                // Load existing employees from the JSON file
                angajatiList = readJson();
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }

            AngajatFrame angajatFrame = new AngajatFrame();

    }


    private void prepareGUI() {
        mainFrame = new JFrame("Formular Angajat");
        mainFrame.setSize(650, 1000);
        mainFrame.setLayout(new GridLayout(4, 2));

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("", JLabel.CENTER);
        statusLabel.setSize(350, 25);

        controlPanel = new JPanel();
        controlPanel.setPreferredSize(new Dimension(500, 600));  // Adăugăm aceste linii pentru dimensiunea preferată și minimă
        controlPanel.setMinimumSize(new Dimension(500, 600));
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }

    private void showAngajatForm() {
        headerLabel.setText("Formular Angajat");
        headerLabel.setPreferredSize(new Dimension(200, 10));  // Adăugăm aceste linii pentru dimensiunea preferată și minimă
        headerLabel.setMinimumSize(new Dimension(200, 10));

        // Adăugare componentelor pentru formularul Angajat
        JPanel numePanel = new JPanel();
        numePanel.setLayout(new FlowLayout());
        LabelNume = new JLabel("Nume:");
        LabelNume.setPreferredSize(new Dimension(60, 25));
        numePanel.add(LabelNume);
        textField1 = new JTextField();
        textField1.setPreferredSize(new Dimension(200, 20));
        numePanel.add(textField1);
        controlPanel.add(numePanel);


        JPanel prenumePanel = new JPanel();
        prenumePanel.setLayout(new FlowLayout());
        LabelPrenume = new JLabel("Prenume:");
        LabelPrenume.setPreferredSize(new Dimension(60, 25));
        prenumePanel.add(LabelPrenume);
        textField2 = new JTextField();
        textField2.setPreferredSize(new Dimension(200, 20));
        prenumePanel.add(textField2);
        controlPanel.add(prenumePanel);

        JPanel departamentePanel = new JPanel();
        departamentePanel.setLayout(new FlowLayout());
        LabelDepartament = new JLabel("Departament");
        LabelDepartament.setPreferredSize(new Dimension(150, 25));
        departamentePanel.add(LabelDepartament);

        final DefaultComboBoxModel<String> departamente = new DefaultComboBoxModel<>();
        departamente.addElement("IT");
        departamente.addElement("Financiar");
        departamente.addElement("Vanzari");
        departamente.addElement("Resurse Umane");

        departamenteCombo = new JComboBox<>(departamente);
        departamenteCombo.setSelectedItem("IT");
        departamentePanel.add(departamenteCombo);
        controlPanel.add(departamentePanel);


        JPanel statutPanel = new JPanel();
        statutPanel.setLayout(new FlowLayout());
        LabelStatut = new JLabel("Stare civila:");
        LabelStatut.setPreferredSize(new Dimension(90, 25));
        final JCheckBox chkCasatorit = new JCheckBox("Casatorit/a");
        final JCheckBox chkNecasatorit = new JCheckBox("Necasatorit/a");

        chkCasatorit.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                JCheckBox source = (JCheckBox) e.getSource();

                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // Deselectează celălalt checkbox
                    if (source == chkCasatorit) {
                        chkNecasatorit.setSelected(false);
                    } else if (source == chkNecasatorit) {
                        chkCasatorit.setSelected(false);
                    }
                }
            }
        });

        chkNecasatorit.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                JCheckBox source = (JCheckBox) e.getSource();

                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // Deselectează celălalt checkbox
                    if (source == chkCasatorit) {
                        chkNecasatorit.setSelected(false);
                    } else if (source == chkNecasatorit) {
                        chkCasatorit.setSelected(false);
                    }
                }
            }
        });

        statutPanel.add(LabelStatut);
        statutPanel.add(chkCasatorit);
        statutPanel.add(chkNecasatorit);
        controlPanel.add(statutPanel);


        JPanel tipJobPanel = new JPanel();
        tipJobPanel.setLayout(new FlowLayout());
        LabelTipJob = new JLabel("Tipul job-ului:");
        LabelTipJob.setPreferredSize(new Dimension(100, 25));
        fullTime_radio = new JRadioButton("Full-time");
        partTime_radio = new JRadioButton("Part-time");

        fullTime_radio.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                JRadioButton source = (JRadioButton) e.getSource();

                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // Deselectează celălalt radio
                    if (source == fullTime_radio) {
                        partTime_radio.setSelected(false);
                    } else if (source == partTime_radio) {
                        fullTime_radio.setSelected(false);
                    }
                }
            }
        });

        partTime_radio.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                JRadioButton source = (JRadioButton) e.getSource();

                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // Deselectează celălalt radio
                    if (source == fullTime_radio) {
                        partTime_radio.setSelected(false);
                    } else if (source == partTime_radio) {
                        fullTime_radio.setSelected(false);
                    }
                }
            }
        });

        tipJobPanel.add(LabelTipJob);
        tipJobPanel.add(fullTime_radio);
        tipJobPanel.add(partTime_radio);
        controlPanel.add(tipJobPanel);

        // Adăugare descriere job
        JPanel textDescriereJob = new JPanel();
        textDescriereJob.setLayout(new FlowLayout());
        LabelDescriereJob = new JLabel("Descrierea job-ului:");
        LabelDescriereJob.setPreferredSize(new Dimension(130, 25));
        textArea = new JTextArea();
        textArea.setPreferredSize(new Dimension(300, 50));
        JScrollPane scrollPane = new JScrollPane(textArea);
        textDescriereJob.add(LabelDescriereJob);
        textDescriereJob.add(scrollPane);
        controlPanel.add(textDescriereJob);


        // Adaugare butoane
        button_salvare = new JButton("Salvare");
        button_salvare.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {

               String nume = textField1.getText();
               String prenume = textField2.getText();
               String departament = departamenteCombo.getSelectedItem().toString();
               boolean statutCasatorit = chkCasatorit.isSelected();
               boolean statutNecasatorit = chkNecasatorit.isSelected();
               boolean tipFullTime = fullTime_radio.isSelected();
               boolean tipPartTime = partTime_radio.isSelected();
               String descriereJob = textArea.getText();

               // Creare un nou obiect Angajat
               Angajat angajat = new Angajat(nume, prenume, departament, tipFullTime, statutCasatorit, descriereJob);

               // Adăugați noul angajat la lista existentă
               angajatiList.add(angajat);

               try {
                   // Salvați lista actualizată în fișierul JSON
                   writeJson(angajatiList);
                   JOptionPane.showMessageDialog(mainFrame, "Angajatul a fost salvat cu succes!", "Salvare reușita", JOptionPane.INFORMATION_MESSAGE);
                   // Resetarea câmpurilor după salvare
                   textField1.setText("");
                   textField2.setText("");
                   departamenteCombo.setSelectedItem("IT");
                   chkCasatorit.setSelected(false);
                   chkNecasatorit.setSelected(false);
                   fullTime_radio.setSelected(false);
                   partTime_radio.setSelected(false);
                   textArea.setText("");

               } catch (IOException ex) {
                   JOptionPane.showMessageDialog(mainFrame, "Eroare la salvare: " + ex.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
                   ex.printStackTrace();
               }
           }
        });

        controlPanel.add(button_salvare);

        button_cancel = new JButton("Cancel");
        button_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Acțiunea de anulare
                System.out.println("Butonul Cancel a fost apăsat!");

                // Închide fereastra curentă
                mainFrame.dispose();
            }
        });
        controlPanel.add(button_cancel);


        mainFrame.setVisible(true);
    }
    private static List<Angajat> readJson() throws IOException, ParseException {
        List<Angajat> angajatiList = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("angajati.json")) {
            Object obj = jsonParser.parse(reader);
            if (obj instanceof JSONArray) {
                JSONArray jsonArray = (JSONArray) obj;
                for (Object o : jsonArray) {
                    if (o instanceof JSONObject) {
                        JSONObject jsonAngajat = (JSONObject) o;
                        Angajat angajat = new Angajat(
                                jsonAngajat.get("nume").toString(),
                                jsonAngajat.get("prenume").toString(),
                                jsonAngajat.get("departament").toString(),
                                (boolean) jsonAngajat.get("tip job"),
                                (boolean) jsonAngajat.get("statut"),
                                jsonAngajat.get("descriere").toString()
                        );
                        angajatiList.add(angajat);
                    }
                }
            }
        }
        return angajatiList;
    }
    private static void writeJson(List<Angajat> angajatiList) throws IOException {
        JSONArray jsonArray = new JSONArray();
        for (Angajat angajat : angajatiList) {
            JSONObject jsonAngajat = new JSONObject();
            jsonAngajat.put("nume", angajat.getNume());
            jsonAngajat.put("prenume", angajat.getPrenume());
            jsonAngajat.put("departament", angajat.getDepartament());
            jsonAngajat.put("tip job", angajat.getTip_job());
            jsonAngajat.put("statut", angajat.getStatut_angajat());
            jsonAngajat.put("descriere", angajat.getDescriere_job());
            jsonArray.add(jsonAngajat);
        }

        try (FileWriter fileWriter = new FileWriter("angajati.json")) {
            fileWriter.write(jsonArray.toJSONString());
        }
    }
}


