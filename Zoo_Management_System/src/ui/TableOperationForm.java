package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import dao.*;
import model.*;

public class TableOperationForm extends JFrame {

    private String tableName;
    private String primaryKey;
    private String action;
    private JPanel formPanel;
    private java.util.List<JTextField> textFields;

    public TableOperationForm(String tableName, String primaryKey, String action) {
        this.tableName = tableName;
        this.primaryKey = primaryKey;
        this.action = action;

        setTitle(tableName + " - " + action);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        textFields = new ArrayList<>();

        if (action.equals("View")) {
            openViewForm();
        } else if (action.equals("Delete")) {
            performDelete();
        } else if (action.equals("Update")) {
            performUpdate();
        } else {
            openCRUDForm();
        }

        setVisible(true);
    }

    // -------------------- INSERT FORM --------------------
    private void openCRUDForm() {
        formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        String[] attributes = getAttributes(tableName);

        for (String attr : attributes) {
            JLabel lbl = new JLabel(attr + ":");
            JTextField txt = new JTextField();
            formPanel.add(lbl);
            formPanel.add(txt);
            textFields.add(txt);
        }

        JButton btnInsert = new JButton("Insert");
        btnInsert.addActionListener(e -> performAction());

        JPanel btnPanel = new JPanel();
        btnPanel.add(btnInsert);

        add(formPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
    }

    // -------------------- VIEW FORM --------------------
    private void openViewForm() {
        String[] columns = getAttributes(tableName);
        java.util.List<String[]> data = new ArrayList<>();

        try {
            switch (tableName) {
                case "Staff":
                    for (Staff s : new StaffDAO().getAllStaff()) {
                        data.add(new String[]{
                                String.valueOf(s.getStaffID()), s.getName(), s.getRole(),
                                String.valueOf(s.getContact()), String.valueOf(s.getSalary()),
                                s.getDateOfJoining(), s.getAddress(), s.getShift(), s.getDob()
                        });
                    }
                    break;
                    
                case "Species":
                    for (Species sp : new SpeciesDAO().getAllSpecies()) {
                        data.add(new String[]{
                                String.valueOf(sp.getSpeciesID()), sp.getScientificName(),
                                sp.getCommonName(), sp.getCategory(),
                                String.valueOf(sp.getAvgLifespan())
                        });
                    }
                    break;

                case "Enclosure":
                    for (Enclosure e : new EnclosureDAO().getAllEnclosures()) {
                        data.add(new String[]{
                                String.valueOf(e.getEnclosureID()), e.getName(), e.getSize(),
                                e.getType(), String.valueOf(e.getCapacity()),
                                String.valueOf(e.getStaffID())
                        });
                    }
                    break;

                case "FoodInfo":
                    for (FoodInfo f : new FoodInfoDAO().getAllFood()) {
                        data.add(new String[]{
                                f.getFoodName(), f.getDescription(), String.valueOf(f.getCost())
                        });
                    }
                    break;

                case "DietDetails":
                    for (DietDetails d : new DietDetailsDAO().getAllDiet()) {
                        data.add(new String[]{
                                String.valueOf(d.getDietID()), d.getFoodName(), d.getQuantity()
                        });
                    }
                    break;

                case "Animal":
                    for (Animal a : new AnimalDAO().getAllAnimals()) {
                        data.add(new String[]{
                                String.valueOf(a.getAnimalID()), a.getName(), a.getDob(),
                                a.getGender(), a.getArrivalDate(),
                                String.valueOf(a.getSpeciesID()), String.valueOf(a.getDietID()),
                                String.valueOf(a.getEnclosureID())
                        });
                    }
                    break;

                case "Donor":
                    for (Donor d : new DonorDAO().getAllDonors()) {
                        data.add(new String[]{
                                String.valueOf(d.getDonorID()), d.getDonorName(),
                                d.getContact(), d.getDonorAddr()
                        });
                    }
                    break;

                case "Sponsorship":
                    for (Sponsorship s : new SponsorshipDAO().getAllSponsorships()) {
                        data.add(new String[]{
                                String.valueOf(s.getSponsorshipID()), String.valueOf(s.getDonorID()),
                                String.valueOf(s.getAnimalID()), s.getStartDate(), s.getEndDate(),
                                String.valueOf(s.getAmount())
                        });
                    }
                    break;

                case "MedicalCheckup":
                    for (MedicalCheckup m : new MedicalCheckupDAO().getAllCheckups()) {
                        data.add(new String[]{
                                String.valueOf(m.getCheckupID()), String.valueOf(m.getAnimalID()),
                                String.valueOf(m.getStaffID()), m.getDate(),
                                m.getDiagnosis(), m.getTreatment(), m.getNextCheckupDate()
                        });
                    }
                    break;

                case "QuarantineRecord":
                    for (QuarantineRecord q : new QuarantineRecordDAO().getAllQuarantines()) {
                        data.add(new String[]{
                                String.valueOf(q.getQuarantineID()), String.valueOf(q.getAnimalID()),
                                String.valueOf(q.getStaffID()), q.getStartDate(), q.getEndDate(),
                                q.getReason(), q.getLocation()
                        });
                    }
                    break;

                case "FeedingSchedule":
                    for (FeedingSchedule f : new FeedingScheduleDAO().getAllFeedings()) {
                        data.add(new String[]{
                                String.valueOf(f.getStaffID()), String.valueOf(f.getAnimalID()),
                                String.valueOf(f.getDietID()), f.getTime(), f.getActualFoodQuantity()
                        });
                    }
                    break;

                default:
                    JOptionPane.showMessageDialog(this, "Table not supported yet!");
                    return;
                // ... other tables remain unchanged ...
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JTable table = new JTable(new DefaultTableModel(data.toArray(new Object[0][]), columns));
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.addActionListener(e -> {
            dispose();
            new TableOperationForm(tableName, primaryKey, "View");
        });

        JPanel bottom = new JPanel();
        bottom.add(refreshBtn);
        add(bottom, BorderLayout.SOUTH);
    }

    // -------------------- UPDATE --------------------
    private void performUpdate() {
        String id = JOptionPane.showInputDialog(this, "Enter " + primaryKey + " to update:");
        if (id == null || id.trim().isEmpty()) return;

        formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        try {
            switch (tableName) {
                case "Staff":
                    StaffDAO staffDao = new StaffDAO();
                    Staff s = staffDao.getStaffById(Integer.parseInt(id));
                    if (s == null) { JOptionPane.showMessageDialog(this, "No record found!"); return; }

                    String[] staffLabels = {"StaffID", "Name", "Role", "Contact", "Salary", "Date of Joining", "Address", "Shift", "DOB"};
                    String[] staffValues = {
                            String.valueOf(s.getStaffID()), s.getName(), s.getRole(),
                            String.valueOf(s.getContact()), String.valueOf(s.getSalary()),
                            s.getDateOfJoining(), s.getAddress(), s.getShift(), s.getDob()
                    };
                    for (int i = 0; i < staffLabels.length; i++) {
                        JLabel lbl = new JLabel(staffLabels[i] + ":");
                        JTextField txt = new JTextField(staffValues[i]);
                        if (i == 0) txt.setEditable(false);
                        formPanel.add(lbl);
                        formPanel.add(txt);
                        textFields.add(txt);
                    }
                    break;

                case "Species":
                    SpeciesDAO speciesDao = new SpeciesDAO();
                    Species sp = speciesDao.getSpeciesById(Integer.parseInt(id));
                    if (sp == null) { JOptionPane.showMessageDialog(this, "No record found!"); return; }

                    String[] speciesLabels = {"SpeciesID", "Scientific Name", "Common Name", "Category", "Avg Lifespan"};
                    String[] speciesValues = {
                            String.valueOf(sp.getSpeciesID()), sp.getScientificName(),
                            sp.getCommonName(), sp.getCategory(), String.valueOf(sp.getAvgLifespan())
                    };
                    for (int i = 0; i < speciesLabels.length; i++) {
                        JLabel lbl = new JLabel(speciesLabels[i] + ":");
                        JTextField txt = new JTextField(speciesValues[i]);
                        if (i == 0) txt.setEditable(false);
                        formPanel.add(lbl);
                        formPanel.add(txt);
                        textFields.add(txt);
                    }
                    break;

                case "Enclosure":
                    EnclosureDAO enclosureDao = new EnclosureDAO();
                    Enclosure e = enclosureDao.getEnclosureById(Integer.parseInt(id));
                    if (e == null) { JOptionPane.showMessageDialog(this, "No record found!"); return; }

                    String[] encLabels = {"EnclosureID", "Name", "Size", "Type", "Capacity", "StaffID"};
                    String[] encValues = {
                            String.valueOf(e.getEnclosureID()), e.getName(), e.getSize(),
                            e.getType(), String.valueOf(e.getCapacity()), String.valueOf(e.getStaffID())
                    };
                    for (int i = 0; i < encLabels.length; i++) {
                        JLabel lbl = new JLabel(encLabels[i] + ":");
                        JTextField txt = new JTextField(encValues[i]);
                        if (i == 0) txt.setEditable(false);
                        formPanel.add(lbl);
                        formPanel.add(txt);
                        textFields.add(txt);
                    }
                    break;

                case "FoodInfo":
                    FoodInfoDAO foodDao = new FoodInfoDAO();
                    FoodInfo f = foodDao.getFoodByName(id); // primary key is FoodName
                    if (f == null) { JOptionPane.showMessageDialog(this, "No record found!"); return; }

                    String[] foodLabels = {"FoodName", "Description", "Cost"};
                    String[] foodValues = {f.getFoodName(), f.getDescription(), String.valueOf(f.getCost())};
                    for (int i = 0; i < foodLabels.length; i++) {
                        JLabel lbl = new JLabel(foodLabels[i] + ":");
                        JTextField txt = new JTextField(foodValues[i]);
                        if (i == 0) txt.setEditable(false);
                        formPanel.add(lbl);
                        formPanel.add(txt);
                        textFields.add(txt);
                    }
                    break;

                case "DietDetails":
                    DietDetailsDAO dietDao = new DietDetailsDAO();
                    DietDetails d = dietDao.getDietById(Integer.parseInt(id));
                    if (d == null) { JOptionPane.showMessageDialog(this, "No record found!"); return; }

                    String[] dietLabels = {"DietID", "FoodName", "Quantity"};
                    String[] dietValues = {String.valueOf(d.getDietID()), d.getFoodName(), d.getQuantity()};
                    for (int i = 0; i < dietLabels.length; i++) {
                        JLabel lbl = new JLabel(dietLabels[i] + ":");
                        JTextField txt = new JTextField(dietValues[i]);
                        if (i == 0) txt.setEditable(false);
                        formPanel.add(lbl);
                        formPanel.add(txt);
                        textFields.add(txt);
                    }
                    break;

                case "Animal":
                    AnimalDAO animalDao = new AnimalDAO();
                    Animal a = animalDao.getAnimalById(Integer.parseInt(id));
                    if (a == null) { JOptionPane.showMessageDialog(this, "No record found!"); return; }

                    String[] animalLabels = {"AnimalID", "Name", "DOB", "Gender", "ArrivalDate", "SpeciesID", "DietID", "EnclosureID"};
                    String[] animalValues = {
                            String.valueOf(a.getAnimalID()), a.getName(), a.getDob(),
                            a.getGender(), a.getArrivalDate(),
                            String.valueOf(a.getSpeciesID()), String.valueOf(a.getDietID()), String.valueOf(a.getEnclosureID())
                    };
                    for (int i = 0; i < animalLabels.length; i++) {
                        JLabel lbl = new JLabel(animalLabels[i] + ":");
                        JTextField txt = new JTextField(animalValues[i]);
                        if (i == 0) txt.setEditable(false);
                        formPanel.add(lbl);
                        formPanel.add(txt);
                        textFields.add(txt);
                    }
                    break;

                case "Donor":
                    DonorDAO donorDao = new DonorDAO();
                    Donor donor = donorDao.getDonorById(Integer.parseInt(id));
                    if (donor == null) { JOptionPane.showMessageDialog(this, "No record found!"); return; }

                    String[] donorLabels = {"DonorID", "DonorName", "Contact", "DonorAddr"};
                    String[] donorValues = {String.valueOf(donor.getDonorID()), donor.getDonorName(), donor.getContact(), donor.getDonorAddr()};
                    for (int i = 0; i < donorLabels.length; i++) {
                        JLabel lbl = new JLabel(donorLabels[i] + ":");
                        JTextField txt = new JTextField(donorValues[i]);
                        if (i == 0) txt.setEditable(false);
                        formPanel.add(lbl);
                        formPanel.add(txt);
                        textFields.add(txt);
                    }
                    break;

                case "Sponsorship":
                    SponsorshipDAO spnDao = new SponsorshipDAO();
                    Sponsorship spn = spnDao.getSponsorshipById(Integer.parseInt(id));
                    if (spn == null) { JOptionPane.showMessageDialog(this, "No record found!"); return; }

                    String[] spnLabels = {"SponsorshipID", "DonorID", "AnimalID", "StartDate", "EndDate", "Amount"};
                    String[] spnValues = {String.valueOf(spn.getSponsorshipID()), String.valueOf(spn.getDonorID()), String.valueOf(spn.getAnimalID()), spn.getStartDate(), spn.getEndDate(), String.valueOf(spn.getAmount())};
                    for (int i = 0; i < spnLabels.length; i++) {
                        JLabel lbl = new JLabel(spnLabels[i] + ":");
                        JTextField txt = new JTextField(spnValues[i]);
                        if (i == 0) txt.setEditable(false);
                        formPanel.add(lbl);
                        formPanel.add(txt);
                        textFields.add(txt);
                    }
                    break;

                case "MedicalCheckup":
                    MedicalCheckupDAO mcDao = new MedicalCheckupDAO();
                    MedicalCheckup mc = mcDao.getCheckupById(Integer.parseInt(id));
                    if (mc == null) { JOptionPane.showMessageDialog(this, "No record found!"); return; }

                    String[] mcLabels = {"CheckupID", "AnimalID", "StaffID", "Date", "Diagnosis", "Treatment", "NextCheckupDate"};
                    String[] mcValues = {String.valueOf(mc.getCheckupID()), String.valueOf(mc.getAnimalID()), String.valueOf(mc.getStaffID()), mc.getDate(), mc.getDiagnosis(), mc.getTreatment(), mc.getNextCheckupDate()};
                    for (int i = 0; i < mcLabels.length; i++) {
                        JLabel lbl = new JLabel(mcLabels[i] + ":");
                        JTextField txt = new JTextField(mcValues[i]);
                        if (i == 0) txt.setEditable(false);
                        formPanel.add(lbl);
                        formPanel.add(txt);
                        textFields.add(txt);
                    }
                    break;

                case "QuarantineRecord":
                    QuarantineRecordDAO qrDao = new QuarantineRecordDAO();
                    QuarantineRecord qr = qrDao.getQuarantineById(Integer.parseInt(id));
                    if (qr == null) { JOptionPane.showMessageDialog(this, "No record found!"); return; }

                    String[] qrLabels = {"QuarantineID", "AnimalID", "StaffID", "StartDate", "EndDate", "Reason", "Location"};
                    String[] qrValues = {String.valueOf(qr.getQuarantineID()), String.valueOf(qr.getAnimalID()), String.valueOf(qr.getStaffID()), qr.getStartDate(), qr.getEndDate(), qr.getReason(), qr.getLocation()};
                    for (int i = 0; i < qrLabels.length; i++) {
                        JLabel lbl = new JLabel(qrLabels[i] + ":");
                        JTextField txt = new JTextField(qrValues[i]);
                        if (i == 0) txt.setEditable(false);
                        formPanel.add(lbl);
                        formPanel.add(txt);
                        textFields.add(txt);
                    }
                    break;

                case "FeedingSchedule":
                    // Composite key: ask user to enter all 3 IDs separated by comma
                    String key = JOptionPane.showInputDialog(this, "Enter StaffID,AnimalID,DietID separated by commas:");
                    if (key == null || key.trim().isEmpty()) return;
                    String[] keys = key.split(",");
                    if (keys.length != 3) { JOptionPane.showMessageDialog(this, "Please enter all 3 IDs!"); return; }

                    FeedingScheduleDAO fsDao = new FeedingScheduleDAO();
                    FeedingSchedule fs = fsDao.getFeedingById(Integer.parseInt(keys[0].trim()), Integer.parseInt(keys[1].trim()), Integer.parseInt(keys[2].trim()));
                    if (fs == null) { JOptionPane.showMessageDialog(this, "No record found!"); return; }

                    String[] fsLabels = {"StaffID", "AnimalID", "DietID", "Time", "ActualFoodQuantity"};
                    String[] fsValues = {String.valueOf(fs.getStaffID()), String.valueOf(fs.getAnimalID()), String.valueOf(fs.getDietID()), fs.getTime(), fs.getActualFoodQuantity()};
                    for (int i = 0; i < fsLabels.length; i++) {
                        JLabel lbl = new JLabel(fsLabels[i] + ":");
                        JTextField txt = new JTextField(fsValues[i]);
                        if (i < 3) txt.setEditable(false); // composite PK not editable
                        formPanel.add(lbl);
                        formPanel.add(txt);
                        textFields.add(txt);
                    }
                    break;

                default:
                    JOptionPane.showMessageDialog(this, "Update not supported for this table.");
                    return;
            }

            JButton btnUpdate = new JButton("Update");
            btnUpdate.addActionListener(e -> performAction());
            JPanel btnPanel = new JPanel();
            btnPanel.add(btnUpdate);

            add(formPanel, BorderLayout.CENTER);
            add(btnPanel, BorderLayout.SOUTH);
            revalidate();
            repaint();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }


    // -------------------- DELETE --------------------
    private void performDelete() {
        try {
            switch (tableName) {
                case "Staff":
                    String staffId = JOptionPane.showInputDialog(this, "Enter " + primaryKey + " to delete:");
                    if (staffId == null || staffId.trim().isEmpty()) return;
                    new StaffDAO().deleteStaff(Integer.parseInt(staffId));
                    break;

                case "Species":
                    String speciesId = JOptionPane.showInputDialog(this, "Enter " + primaryKey + " to delete:");
                    if (speciesId == null || speciesId.trim().isEmpty()) return;
                    new SpeciesDAO().deleteSpecies(Integer.parseInt(speciesId));
                    break;

                case "Enclosure":
                    String encId = JOptionPane.showInputDialog(this, "Enter " + primaryKey + " to delete:");
                    if (encId == null || encId.trim().isEmpty()) return;
                    new EnclosureDAO().deleteEnclosure(Integer.parseInt(encId));
                    break;

                case "FoodInfo":
                    String foodName = JOptionPane.showInputDialog(this, "Enter " + primaryKey + " to delete:");
                    if (foodName == null || foodName.trim().isEmpty()) return;
                    new FoodInfoDAO().deleteFood(foodName);
                    break;

                case "DietDetails":
                    String dietId = JOptionPane.showInputDialog(this, "Enter " + primaryKey + " to delete:");
                    if (dietId == null || dietId.trim().isEmpty()) return;
                    new DietDetailsDAO().deleteDiet(Integer.parseInt(dietId));
                    break;

                case "Animal":
                    String animalId = JOptionPane.showInputDialog(this, "Enter " + primaryKey + " to delete:");
                    if (animalId == null || animalId.trim().isEmpty()) return;
                    new AnimalDAO().deleteAnimal(Integer.parseInt(animalId));
                    break;

                case "Donor":
                    String donorId = JOptionPane.showInputDialog(this, "Enter " + primaryKey + " to delete:");
                    if (donorId == null || donorId.trim().isEmpty()) return;
                    new DonorDAO().deleteDonor(Integer.parseInt(donorId));
                    break;

                case "Sponsorship":
                    String spnId = JOptionPane.showInputDialog(this, "Enter " + primaryKey + " to delete:");
                    if (spnId == null || spnId.trim().isEmpty()) return;
                    new SponsorshipDAO().deleteSponsorship(Integer.parseInt(spnId));
                    break;

                case "MedicalCheckup":
                    String mcId = JOptionPane.showInputDialog(this, "Enter " + primaryKey + " to delete:");
                    if (mcId == null || mcId.trim().isEmpty()) return;
                    new MedicalCheckupDAO().deleteCheckup(Integer.parseInt(mcId));
                    break;

                case "QuarantineRecord":
                    String qrId = JOptionPane.showInputDialog(this, "Enter " + primaryKey + " to delete:");
                    if (qrId == null || qrId.trim().isEmpty()) return;
                    new QuarantineRecordDAO().deleteQuarantine(Integer.parseInt(qrId));
                    break;

                case "FeedingSchedule":
                    // composite key: StaffID, AnimalID, DietID
                    String key = JOptionPane.showInputDialog(this, "Enter StaffID,AnimalID,DietID separated by commas:");
                    if (key == null || key.trim().isEmpty()) return;
                    String[] keys = key.split(",");
                    if (keys.length != 3) { JOptionPane.showMessageDialog(this, "Please enter all 3 IDs!"); return; }
                    new FeedingScheduleDAO().deleteFeeding(
                            Integer.parseInt(keys[0].trim()),
                            Integer.parseInt(keys[1].trim()),
                            Integer.parseInt(keys[2].trim())
                    );
                    break;

                default:
                    JOptionPane.showMessageDialog(this, "Delete not supported for this table.");
                    return;
            }

            JOptionPane.showMessageDialog(this, "Record deleted successfully!");
            dispose();
            new TableOperationForm(tableName, primaryKey, "View");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }


    // -------------------- ATTRIBUTES --------------------
    private String[] getAttributes(String tableName) {
        switch (tableName) {
            case "Staff":
                return new String[]{"StaffID", "Name", "Role", "Contact", "Salary", "DateOfJoining", "Address", "Shift", "DOB"};

            case "Species":
                return new String[]{"SpeciesID", "ScientificName", "CommonName", "Category", "Avg_Lifespan"};

            case "Enclosure":
                return new String[]{"EnclosureID", "Name", "Size", "Type", "EnclosureCapacity", "StaffID"};

            case "FoodInfo":
                return new String[]{"Food_Name", "Description", "Cost"};

            case "DietDetails":
                return new String[]{"DietID", "Food_Name", "Quantity"};

            case "Animal":
                return new String[]{"AnimalID", "Name", "DOB", "Gender", "Arrival_Date", "SpeciesID", "DietID", "EnclosureID"};

            case "Donor":
                return new String[]{"DonorID", "Donor_Name", "Contact", "Donor_Addr"};

            case "Sponsorship":
                return new String[]{"SponsorshipID", "DonorID", "AnimalID", "StartDate", "EndDate", "Amount"};

            case "MedicalCheckup":
                return new String[]{"CheckupID", "AnimalID", "StaffID", "Date", "Diagnosis", "Treatment", "Next_Checkup_Date"};

            case "QuarantineRecord":
                return new String[]{"QuarantineID", "AnimalID", "StaffID", "StartDate", "EndDate", "Reason", "Location"};

            case "FeedingSchedule":
                return new String[]{"StaffID", "AnimalID", "DietID", "Time", "ActualFoodQuantity"};

            default:
                return new String[]{};
        }
    }


    // -------------------- PERFORM ACTION (INSERT/UPDATE) --------------------
    private void performAction() {
        try {
            switch (tableName) {
                case "Staff":
                    Staff s = new Staff(
                            Integer.parseInt(textFields.get(0).getText()), textFields.get(1).getText(),
                            textFields.get(2).getText(), Long.parseLong(textFields.get(3).getText()),
                            Double.parseDouble(textFields.get(4).getText()), textFields.get(5).getText(),
                            textFields.get(6).getText(), textFields.get(7).getText(), textFields.get(8).getText()
                    );
                    StaffDAO staffDao = new StaffDAO();
                    if (action.equals("Insert")) staffDao.addStaff(s);
                    else staffDao.updateStaff(s);
                    break;

                case "Species":
                    Species sp = new Species(
                            Integer.parseInt(textFields.get(0).getText()), textFields.get(1).getText(),
                            textFields.get(2).getText(), textFields.get(3).getText(),
                            Integer.parseInt(textFields.get(4).getText())
                    );
                    SpeciesDAO speciesDao = new SpeciesDAO();
                    if (action.equals("Insert")) speciesDao.addSpecies(sp);
                    else speciesDao.updateSpecies(sp);
                    break;

                case "Enclosure":
                    Enclosure e = new Enclosure(
                            Integer.parseInt(textFields.get(0).getText()), textFields.get(1).getText(),
                            textFields.get(2).getText(), textFields.get(3).getText(),
                            Integer.parseInt(textFields.get(4).getText()),
                            Integer.parseInt(textFields.get(5).getText())
                    );
                    EnclosureDAO enclosureDao = new EnclosureDAO();
                    if (action.equals("Insert")) enclosureDao.addEnclosure(e);
                    else enclosureDao.updateEnclosure(e);
                    break;

                case "FoodInfo":
                    FoodInfo f = new FoodInfo(
                            textFields.get(0).getText(), textFields.get(1).getText(),
                            Double.parseDouble(textFields.get(2).getText())
                    );
                    FoodInfoDAO foodDao = new FoodInfoDAO();
                    if (action.equals("Insert")) foodDao.addFood(f);
                    else foodDao.updateFood(f);
                    break;

                case "DietDetails":
                    DietDetails d = new DietDetails(
                            Integer.parseInt(textFields.get(0).getText()), textFields.get(1).getText(),
                            textFields.get(2).getText()
                    );
                    DietDetailsDAO dietDao = new DietDetailsDAO();
                    if (action.equals("Insert")) dietDao.addDiet(d);
                    else dietDao.updateDiet(d);
                    break;

                case "Animal":
                    Animal a = new Animal(
                            Integer.parseInt(textFields.get(0).getText()), textFields.get(1).getText(),
                            textFields.get(2).getText(), textFields.get(3).getText(),
                            textFields.get(4).getText(), Integer.parseInt(textFields.get(5).getText()),
                            Integer.parseInt(textFields.get(6).getText()), Integer.parseInt(textFields.get(7).getText())
                    );
                    AnimalDAO animalDao = new AnimalDAO();
                    if (action.equals("Insert")) animalDao.addAnimal(a);
                    else animalDao.updateAnimal(a);
                    break;

                case "Donor":
                    Donor donor = new Donor(
                            Integer.parseInt(textFields.get(0).getText()), textFields.get(1).getText(),
                            textFields.get(2).getText(), textFields.get(3).getText()
                    );
                    DonorDAO donorDao = new DonorDAO();
                    if (action.equals("Insert")) donorDao.addDonor(donor);
                    else donorDao.updateDonor(donor);
                    break;

                case "Sponsorship":
                    Sponsorship spn = new Sponsorship(
                            Integer.parseInt(textFields.get(0).getText()), Integer.parseInt(textFields.get(1).getText()),
                            Integer.parseInt(textFields.get(2).getText()), textFields.get(3).getText(),
                            textFields.get(4).getText(), Double.parseDouble(textFields.get(5).getText())
                    );
                    SponsorshipDAO spnDao = new SponsorshipDAO();
                    if (action.equals("Insert")) spnDao.addSponsorship(spn);
                    else spnDao.updateSponsorship(spn);
                    break;

                case "MedicalCheckup":
                    MedicalCheckup mc = new MedicalCheckup(
                            Integer.parseInt(textFields.get(0).getText()), Integer.parseInt(textFields.get(1).getText()),
                            Integer.parseInt(textFields.get(2).getText()), textFields.get(3).getText(),
                            textFields.get(4).getText(), textFields.get(5).getText(), textFields.get(6).getText()
                    );
                    MedicalCheckupDAO mcDao = new MedicalCheckupDAO();
                    if (action.equals("Insert")) mcDao.addCheckup(mc);
                    else mcDao.updateCheckup(mc);
                    break;

                case "QuarantineRecord":
                    QuarantineRecord qr = new QuarantineRecord(
                            Integer.parseInt(textFields.get(0).getText()), Integer.parseInt(textFields.get(1).getText()),
                            Integer.parseInt(textFields.get(2).getText()), textFields.get(3).getText(),
                            textFields.get(4).getText(), textFields.get(5).getText(), textFields.get(6).getText()
                    );
                    QuarantineRecordDAO qrDao = new QuarantineRecordDAO();
                    if (action.equals("Insert")) qrDao.addQuarantine(qr);
                    else qrDao.updateQuarantine(qr);
                    break;

                case "FeedingSchedule":
                    FeedingSchedule fs = new FeedingSchedule(
                            Integer.parseInt(textFields.get(0).getText()), Integer.parseInt(textFields.get(1).getText()),
                            Integer.parseInt(textFields.get(2).getText()), textFields.get(3).getText(),
                            textFields.get(4).getText()
                    );
                    FeedingScheduleDAO fsDao = new FeedingScheduleDAO();
                    if (action.equals("Insert")) fsDao.addFeeding(fs);
                    else fsDao.updateFeeding(fs);
                    break;

                default:
                    JOptionPane.showMessageDialog(this, "Table not supported!");
                    return;
            }

            JOptionPane.showMessageDialog(this, tableName + " " + action + "d successfully!");
            dispose();
            new TableOperationForm(tableName, primaryKey, "View");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

}
