/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package core.views;

import core.controllers.FlightController;
import core.models.Location;
import core.models.Flight;
import core.models.Passenger;
import core.models.Plane;
import core.controllers.LocationController;
import core.controllers.PassengerController;
import core.controllers.PlaneController;
import core.controllers.table.FlightTableController;
import core.controllers.table.LocationTableController;
import core.controllers.table.PassengerTableController;
import core.controllers.table.PlaneTableController;
import core.controllers.table.TableObserverController;
import core.controllers.utils.Response;
import core.models.loadData.ShowJsonComboBox;
import core.models.storage.FlightRepository;
import core.models.storage.LocationRepository;
import core.models.storage.PassengerRepository;
import core.models.storage.PlaneRepository;
import java.awt.Color;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author edangulo
 */
public class AirportFrame extends javax.swing.JFrame {

    /**
     * Creates new form AirportFrame
     */
    private int x, y;

    public AirportFrame() {
        initComponents();

        this.setBackground(new Color(0, 0, 0, 0));
        this.setLocationRelativeTo(null);

        this.generateMonths();
        this.generateDays();
        this.generateHours();
        this.generateMinutes();
        this.blockPanels();
        
        ShowJsonComboBox.loadPassengers(userSelect);
        ShowJsonComboBox.loadPlanes(comboBoxPlaneFlightRegistration);
        ShowJsonComboBox.loadLocations(comboBoxDepartureLocationFlightRegistration);
        ShowJsonComboBox.loadLocations(comboBoxArrivalLocationFlightRegistration);
        ShowJsonComboBox.loadLocations(comboBoxScaleLocationFlightRegistration);
        ShowJsonComboBox.loadFlights(comboBoxFlightAdd);
        ShowJsonComboBox.loadFlights(comboBoxIDDelayFlight);
        comboBoxScaleLocationFlightRegistration.addItem("None");
        TableObserverController observerPassenger = new TableObserverController(() -> {
        PassengerTableController.updatePassengersTable((DefaultTableModel) tableShowAllPassengers.getModel());
        });
        PassengerRepository.getInstance().addObserver(observerPassenger);
        
        TableObserverController observerPlane = new TableObserverController(() -> {
        PlaneTableController.updatePlanesTable((DefaultTableModel) tableShowAllPlanes.getModel());
        });
        PlaneRepository.getInstance().addObserver(observerPlane);
        
        TableObserverController observerLocation = new TableObserverController(() -> {
        LocationTableController.updateLocationsTable((DefaultTableModel) tableShowAllLocations.getModel());
        });
        LocationRepository.getInstance().addObserver(observerLocation);
        
        TableObserverController observerFlight = new TableObserverController(() -> {
        FlightTableController.updateFlightsTable((DefaultTableModel) tableShowAllFlights.getModel());
        });
        FlightRepository.getInstance().addObserver(observerFlight);
        
        
        TableObserverController observerShowMyFlight = new TableObserverController(() -> {
        FlightTableController.updateMyFlightsTable( userSelect.getSelectedItem().toString(), (DefaultTableModel) tableShowMyFlights.getModel());
        });
        FlightRepository.getInstance().addObserver(observerShowMyFlight);
    }
    
    private void blockPanels() {
        //9, 11
        for (int i = 1; i < jTabbedPane1.getTabCount(); i++) {
            if (i != 9 && i != 11) {
                jTabbedPane1.setEnabledAt(i, false);
            }
        }
    }
    private void metodo (){
        
    }
    private void generateMonths() {
        for (int i = 1; i < 13; i++) {
            comboBoxMonthRegistration.addItem("" + i);
            comboBoxMonthFlightRegistration.addItem("" + i);
            comboBoxMonthUpdate.addItem("" + i);
        }
    }

    private void generateDays() {
        for (int i = 1; i < 32; i++) {
            comboBoxDayRegistration.addItem("" + i);
            comboBoxDayFlightRegistration.addItem("" + i);
            comboBoxDayUpdate.addItem("" + i);
        }
    }

    private void generateHours() {
        for (int i = 0; i < 24; i++) {
            comboBoxHourDepartureDateFlightRegistration.addItem("" + i);
            comboBoxHourArrivalLocationFlightRegistration.addItem("" + i);
            comboBoxHourScaleLocationFlightRegistration.addItem("" + i);
            comboBoxHourDeayFlight.addItem("" + i);
        }
    }

    private void generateMinutes() {
        for (int i = 0; i < 60; i++) {
            comboBoxMinuteDepartureDateFlightRegistration.addItem("" + i);
            comboBoxMinuteArrivalFlightRegistration.addItem("" + i);
            comboBoxMinuteScaleLocationFlightRegistration.addItem("" + i);
            comboBoxMinuteDelayFlight.addItem("" + i);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panelRound1 = new core.views.PanelRound();
        panelRound2 = new core.views.PanelRound();
        btnExit = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        user = new javax.swing.JRadioButton();
        administrator = new javax.swing.JRadioButton();
        userSelect = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtPhoneCodeRegistration = new javax.swing.JTextField();
        txtIDRegistration = new javax.swing.JTextField();
        txtBirthdateRegistration = new javax.swing.JTextField();
        txtCountryRegistration = new javax.swing.JTextField();
        txtPhoneRegistration = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtLastNameRegistration = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        comboBoxMonthRegistration = new javax.swing.JComboBox<>();
        txtFirstNameRegistration = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        comboBoxDayRegistration = new javax.swing.JComboBox<>();
        btnRegister = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtIDAirplaneRegistrarion = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtBrandAirplaneRegistration = new javax.swing.JTextField();
        txtModelAirplaneRegistration = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtMaxCapacityAirplaneRegistration = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtAirlineAirplaneRegistration = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        btnCreateAirplaneRegistration = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        txtAirportIDLocationRegistration = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtAirportNameLocationRegistration = new javax.swing.JTextField();
        txtAirportCityLocationRegistration = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtAirportCountryLocationRegistration = new javax.swing.JTextField();
        txtAirportLatitudeLocationRegistration = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtAirportLongitudeLocationRegistration = new javax.swing.JTextField();
        btnCreateLocationRegistration = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        txtIDFlightRegistration = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        comboBoxPlaneFlightRegistration = new javax.swing.JComboBox<>();
        comboBoxDepartureLocationFlightRegistration = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        comboBoxArrivalLocationFlightRegistration = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        comboBoxScaleLocationFlightRegistration = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        txtYearFlightRegistration = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        comboBoxMonthFlightRegistration = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        comboBoxDayFlightRegistration = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        comboBoxHourDepartureDateFlightRegistration = new javax.swing.JComboBox<>();
        jLabel33 = new javax.swing.JLabel();
        comboBoxMinuteDepartureDateFlightRegistration = new javax.swing.JComboBox<>();
        comboBoxHourArrivalLocationFlightRegistration = new javax.swing.JComboBox<>();
        jLabel34 = new javax.swing.JLabel();
        comboBoxMinuteArrivalFlightRegistration = new javax.swing.JComboBox<>();
        jLabel35 = new javax.swing.JLabel();
        comboBoxHourScaleLocationFlightRegistration = new javax.swing.JComboBox<>();
        comboBoxMinuteScaleLocationFlightRegistration = new javax.swing.JComboBox<>();
        btnCreateFlightRegistration = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        txtIDUpdate = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        txtFirstNameUpdate = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        txtLastNameUpdate = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        txtBirthDateUpdate = new javax.swing.JTextField();
        comboBoxMonthUpdate = new javax.swing.JComboBox<>();
        comboBoxDayUpdate = new javax.swing.JComboBox<>();
        txtPhoneUpdate = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        txtPhoneCodeUpdate = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        txtCountryUpdate = new javax.swing.JTextField();
        btnUpdate = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        txtIDAdd = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        comboBoxFlightAdd = new javax.swing.JComboBox<>();
        btnAdd = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableShowMyFlights = new javax.swing.JTable();
        btnRefreshShowMyFlights = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableShowAllPassengers = new javax.swing.JTable();
        btnRefreshShowAllPassengers = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableShowAllFlights = new javax.swing.JTable();
        btnRefreshShowAllFlights = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        btnRefreshShowAllPlanes = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableShowAllPlanes = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableShowAllLocations = new javax.swing.JTable();
        btnRefreshShowAllLocation = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        comboBoxHourDeayFlight = new javax.swing.JComboBox<>();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        comboBoxIDDelayFlight = new javax.swing.JComboBox<>();
        jLabel48 = new javax.swing.JLabel();
        comboBoxMinuteDelayFlight = new javax.swing.JComboBox<>();
        btnDelayDelayFlight = new javax.swing.JButton();
        panelRound3 = new core.views.PanelRound();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panelRound1.setRadius(40);
        panelRound1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panelRound2MouseDragged(evt);
            }
        });
        panelRound2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelRound2MousePressed(evt);
            }
        });

        btnExit.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        btnExit.setText("X");
        btnExit.setBorderPainted(false);
        btnExit.setContentAreaFilled(false);
        btnExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                .addContainerGap(1083, Short.MAX_VALUE)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addComponent(btnExit)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        panelRound1.add(panelRound2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, -1));

        jTabbedPane1.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonGroup1.add(user);
        user.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        user.setText("User");
        user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userActionPerformed(evt);
            }
        });
        jPanel1.add(user, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 230, -1, -1));

        buttonGroup1.add(administrator);
        administrator.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        administrator.setText("Administrator");
        administrator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                administratorActionPerformed(evt);
            }
        });
        jPanel1.add(administrator, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 164, -1, -1));

        userSelect.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        userSelect.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select User" }));
        userSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userSelectActionPerformed(evt);
            }
        });
        jPanel1.add(userSelect, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 300, 130, -1));

        jTabbedPane1.addTab("Administration", jPanel1);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel1.setText("Country:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 400, -1, -1));

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel2.setText("ID:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, -1));

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel3.setText("First Name:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, -1));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel4.setText("Last Name:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, -1, -1));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel5.setText("Birthdate:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, -1, -1));

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel6.setText("+");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 340, 20, -1));

        txtPhoneCodeRegistration.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jPanel2.add(txtPhoneCodeRegistration, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 340, 50, -1));

        txtIDRegistration.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jPanel2.add(txtIDRegistration, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 130, -1));

        txtBirthdateRegistration.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jPanel2.add(txtBirthdateRegistration, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 280, 90, -1));

        txtCountryRegistration.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jPanel2.add(txtCountryRegistration, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 400, 130, -1));

        txtPhoneRegistration.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jPanel2.add(txtPhoneRegistration, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 340, 130, -1));

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel7.setText("Phone:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, -1, -1));

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel8.setText("-");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 280, 30, -1));

        txtLastNameRegistration.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jPanel2.add(txtLastNameRegistration, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, 130, -1));

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel9.setText("-");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 340, 30, -1));

        comboBoxMonthRegistration.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        comboBoxMonthRegistration.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Month" }));
        jPanel2.add(comboBoxMonthRegistration, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 280, -1, -1));

        txtFirstNameRegistration.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jPanel2.add(txtFirstNameRegistration, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, 130, -1));

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel10.setText("-");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 280, 30, -1));

        comboBoxDayRegistration.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        comboBoxDayRegistration.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Day" }));
        jPanel2.add(comboBoxDayRegistration, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 280, -1, -1));

        btnRegister.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        btnRegister.setText("Register");
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });
        jPanel2.add(btnRegister, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 480, -1, -1));

        jTabbedPane1.addTab("Passenger registration", jPanel2);

        jPanel3.setLayout(null);

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel11.setText("ID:");
        jPanel3.add(jLabel11);
        jLabel11.setBounds(53, 96, 21, 25);

        txtIDAirplaneRegistrarion.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jPanel3.add(txtIDAirplaneRegistrarion);
        txtIDAirplaneRegistrarion.setBounds(180, 93, 130, 31);

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel12.setText("Brand:");
        jPanel3.add(jLabel12);
        jLabel12.setBounds(53, 157, 50, 25);

        txtBrandAirplaneRegistration.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jPanel3.add(txtBrandAirplaneRegistration);
        txtBrandAirplaneRegistration.setBounds(180, 154, 130, 31);

        txtModelAirplaneRegistration.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jPanel3.add(txtModelAirplaneRegistration);
        txtModelAirplaneRegistration.setBounds(180, 213, 130, 31);

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel13.setText("Model:");
        jPanel3.add(jLabel13);
        jLabel13.setBounds(53, 216, 55, 25);

        txtMaxCapacityAirplaneRegistration.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jPanel3.add(txtMaxCapacityAirplaneRegistration);
        txtMaxCapacityAirplaneRegistration.setBounds(180, 273, 130, 31);

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel14.setText("Max Capacity:");
        jPanel3.add(jLabel14);
        jLabel14.setBounds(53, 276, 110, 25);

        txtAirlineAirplaneRegistration.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jPanel3.add(txtAirlineAirplaneRegistration);
        txtAirlineAirplaneRegistration.setBounds(180, 333, 130, 31);

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel15.setText("Airline:");
        jPanel3.add(jLabel15);
        jLabel15.setBounds(53, 336, 70, 25);

        btnCreateAirplaneRegistration.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        btnCreateAirplaneRegistration.setText("Create");
        btnCreateAirplaneRegistration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateAirplaneRegistrationActionPerformed(evt);
            }
        });
        jPanel3.add(btnCreateAirplaneRegistration);
        btnCreateAirplaneRegistration.setBounds(490, 480, 120, 40);

        jTabbedPane1.addTab("Airplane registration", jPanel3);

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel16.setText("Airport ID:");

        txtAirportIDLocationRegistration.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel17.setText("Airport name:");

        txtAirportNameLocationRegistration.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        txtAirportCityLocationRegistration.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel18.setText("Airport city:");

        jLabel19.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel19.setText("Airport country:");

        txtAirportCountryLocationRegistration.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        txtAirportLatitudeLocationRegistration.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel20.setText("Airport latitude:");

        jLabel21.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel21.setText("Airport longitude:");

        txtAirportLongitudeLocationRegistration.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        btnCreateLocationRegistration.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        btnCreateLocationRegistration.setText("Create");
        btnCreateLocationRegistration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateLocationRegistrationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21))
                        .addGap(80, 80, 80)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAirportLongitudeLocationRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAirportIDLocationRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAirportNameLocationRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAirportCityLocationRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAirportCountryLocationRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAirportLatitudeLocationRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(515, 515, 515)
                        .addComponent(btnCreateLocationRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(515, 515, 515))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel17)
                        .addGap(34, 34, 34)
                        .addComponent(jLabel18)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel19)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel20))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(txtAirportIDLocationRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(txtAirportNameLocationRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(txtAirportCityLocationRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(txtAirportCountryLocationRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(txtAirportLatitudeLocationRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtAirportLongitudeLocationRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(btnCreateLocationRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );

        jTabbedPane1.addTab("Location registration", jPanel13);

        jLabel22.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel22.setText("ID:");

        txtIDFlightRegistration.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel23.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel23.setText("Plane:");

        comboBoxPlaneFlightRegistration.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        comboBoxPlaneFlightRegistration.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Plane" }));

        comboBoxDepartureLocationFlightRegistration.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        comboBoxDepartureLocationFlightRegistration.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Location" }));

        jLabel24.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel24.setText("Departure location:");

        comboBoxArrivalLocationFlightRegistration.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        comboBoxArrivalLocationFlightRegistration.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Location" }));

        jLabel25.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel25.setText("Arrival location:");

        jLabel26.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel26.setText("Scale location:");

        comboBoxScaleLocationFlightRegistration.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        comboBoxScaleLocationFlightRegistration.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Location" }));
        comboBoxScaleLocationFlightRegistration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxScaleLocationFlightRegistrationActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel27.setText("Duration:");

        jLabel28.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel28.setText("Duration:");

        jLabel29.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel29.setText("Departure date:");

        txtYearFlightRegistration.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        txtYearFlightRegistration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtYearFlightRegistrationActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel30.setText("-");

        comboBoxMonthFlightRegistration.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        comboBoxMonthFlightRegistration.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Month" }));

        jLabel31.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel31.setText("-");

        comboBoxDayFlightRegistration.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        comboBoxDayFlightRegistration.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Day" }));

        jLabel32.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel32.setText("-");

        comboBoxHourDepartureDateFlightRegistration.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        comboBoxHourDepartureDateFlightRegistration.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hour" }));

        jLabel33.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel33.setText("-");

        comboBoxMinuteDepartureDateFlightRegistration.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        comboBoxMinuteDepartureDateFlightRegistration.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Minute" }));

        comboBoxHourArrivalLocationFlightRegistration.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        comboBoxHourArrivalLocationFlightRegistration.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hour" }));

        jLabel34.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel34.setText("-");

        comboBoxMinuteArrivalFlightRegistration.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        comboBoxMinuteArrivalFlightRegistration.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Minute" }));

        jLabel35.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel35.setText("-");

        comboBoxHourScaleLocationFlightRegistration.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        comboBoxHourScaleLocationFlightRegistration.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hour" }));

        comboBoxMinuteScaleLocationFlightRegistration.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        comboBoxMinuteScaleLocationFlightRegistration.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Minute" }));

        btnCreateFlightRegistration.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        btnCreateFlightRegistration.setText("Create");
        btnCreateFlightRegistration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateFlightRegistrationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(comboBoxScaleLocationFlightRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(comboBoxArrivalLocationFlightRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addGap(46, 46, 46)
                        .addComponent(comboBoxDepartureLocationFlightRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtIDFlightRegistration)
                            .addComponent(comboBoxPlaneFlightRegistration, 0, 130, Short.MAX_VALUE))))
                .addGap(45, 45, 45)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29)
                    .addComponent(jLabel27))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtYearFlightRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(comboBoxMonthFlightRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(comboBoxDayFlightRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(comboBoxHourDepartureDateFlightRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(comboBoxMinuteDepartureDateFlightRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(comboBoxHourArrivalLocationFlightRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(comboBoxMinuteArrivalFlightRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(comboBoxHourScaleLocationFlightRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(comboBoxMinuteScaleLocationFlightRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCreateFlightRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(530, 530, 530))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel22))
                    .addComponent(txtIDFlightRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(comboBoxPlaneFlightRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboBoxHourDepartureDateFlightRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32)
                    .addComponent(jLabel33)
                    .addComponent(comboBoxMinuteDepartureDateFlightRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel24)
                                .addComponent(comboBoxDepartureLocationFlightRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel29))
                            .addComponent(txtYearFlightRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBoxMonthFlightRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30)
                            .addComponent(jLabel31)
                            .addComponent(comboBoxDayFlightRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel25)
                                .addComponent(comboBoxArrivalLocationFlightRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel28))
                            .addComponent(comboBoxHourArrivalLocationFlightRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel34)
                            .addComponent(comboBoxMinuteArrivalFlightRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(comboBoxHourScaleLocationFlightRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel27))
                            .addComponent(jLabel35)
                            .addComponent(comboBoxMinuteScaleLocationFlightRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel26)
                                .addComponent(comboBoxScaleLocationFlightRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                .addComponent(btnCreateFlightRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );

        jTabbedPane1.addTab("Flight registration", jPanel4);

        jLabel36.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel36.setText("ID:");

        txtIDUpdate.setEditable(false);
        txtIDUpdate.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        txtIDUpdate.setEnabled(false);

        jLabel37.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel37.setText("First Name:");

        txtFirstNameUpdate.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel38.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel38.setText("Last Name:");

        txtLastNameUpdate.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel39.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel39.setText("Birthdate:");

        txtBirthDateUpdate.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        comboBoxMonthUpdate.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        comboBoxMonthUpdate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Month" }));
        comboBoxMonthUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxMonthUpdateActionPerformed(evt);
            }
        });

        comboBoxDayUpdate.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        comboBoxDayUpdate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Day" }));

        txtPhoneUpdate.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel40.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel40.setText("-");

        txtPhoneCodeUpdate.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        txtPhoneCodeUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhoneCodeUpdateActionPerformed(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel41.setText("+");

        jLabel42.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel42.setText("Phone:");

        jLabel43.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel43.setText("Country:");

        txtCountryUpdate.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        btnUpdate.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel36)
                                .addGap(108, 108, 108)
                                .addComponent(txtIDUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel38)
                                .addGap(43, 43, 43)
                                .addComponent(txtLastNameUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel39)
                                .addGap(55, 55, 55)
                                .addComponent(txtBirthDateUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(comboBoxMonthUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(comboBoxDayUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel42)
                                .addGap(56, 56, 56)
                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(txtPhoneCodeUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(txtPhoneUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel43)
                                .addGap(63, 63, 63)
                                .addComponent(txtCountryUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel37)
                                .addGap(41, 41, 41)
                                .addComponent(txtFirstNameUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(507, 507, 507)
                        .addComponent(btnUpdate)))
                .addContainerGap(586, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36)
                    .addComponent(txtIDUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel37)
                    .addComponent(txtFirstNameUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel38)
                    .addComponent(txtLastNameUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel39)
                    .addComponent(txtBirthDateUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxMonthUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxDayUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel42)
                    .addComponent(jLabel41)
                    .addComponent(txtPhoneCodeUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40)
                    .addComponent(txtPhoneUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel43)
                    .addComponent(txtCountryUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(btnUpdate)
                .addGap(113, 113, 113))
        );

        jTabbedPane1.addTab("Update info", jPanel5);

        txtIDAdd.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        txtIDAdd.setEnabled(false);

        jLabel44.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel44.setText("ID:");

        jLabel45.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel45.setText("Flight:");

        comboBoxFlightAdd.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        comboBoxFlightAdd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Flight" }));

        btnAdd.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel44)
                    .addComponent(jLabel45))
                .addGap(79, 79, 79)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboBoxFlightAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIDAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(860, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(509, 509, 509))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel44))
                    .addComponent(txtIDAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(comboBoxFlightAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 288, Short.MAX_VALUE)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85))
        );

        jTabbedPane1.addTab("Add to flight", jPanel6);

        tableShowMyFlights.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        tableShowMyFlights.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Departure Date", "Arrival Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableShowMyFlights);

        btnRefreshShowMyFlights.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        btnRefreshShowMyFlights.setText("Refresh");
        btnRefreshShowMyFlights.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshShowMyFlightsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(269, 269, 269)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(323, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRefreshShowMyFlights)
                .addGap(527, 527, 527))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(btnRefreshShowMyFlights)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Show my flights", jPanel7);

        tableShowAllPassengers.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        tableShowAllPassengers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Birthdate", "Age", "Phone", "Country", "Num Flight"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tableShowAllPassengers);

        btnRefreshShowAllPassengers.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        btnRefreshShowAllPassengers.setText("Refresh");
        btnRefreshShowAllPassengers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshShowAllPassengersActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(489, 489, 489)
                        .addComponent(btnRefreshShowAllPassengers))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1078, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(72, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRefreshShowAllPassengers)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Show all passengers", jPanel8);

        tableShowAllFlights.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        tableShowAllFlights.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Departure Airport ID", "Arrival Airport ID", "Scale Airport ID", "Departure Date", "Arrival Date", "Plane ID", "Number Passengers"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tableShowAllFlights);

        btnRefreshShowAllFlights.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        btnRefreshShowAllFlights.setText("Refresh");
        btnRefreshShowAllFlights.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshShowAllFlightsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(521, 521, 521)
                        .addComponent(btnRefreshShowAllFlights)))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRefreshShowAllFlights)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Show all flights", jPanel9);

        btnRefreshShowAllPlanes.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        btnRefreshShowAllPlanes.setText("Refresh");
        btnRefreshShowAllPlanes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshShowAllPlanesActionPerformed(evt);
            }
        });

        tableShowAllPlanes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Brand", "Model", "Max Capacity", "Airline", "Number Flights"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tableShowAllPlanes);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(508, 508, 508)
                        .addComponent(btnRefreshShowAllPlanes))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 816, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(221, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(btnRefreshShowAllPlanes)
                .addGap(17, 17, 17))
        );

        jTabbedPane1.addTab("Show all planes", jPanel10);

        tableShowAllLocations.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Airport ID", "Airport Name", "City", "Country"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tableShowAllLocations);

        btnRefreshShowAllLocation.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        btnRefreshShowAllLocation.setText("Refresh");
        btnRefreshShowAllLocation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshShowAllLocationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(508, 508, 508)
                        .addComponent(btnRefreshShowAllLocation))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(226, 226, 226)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 652, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(304, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btnRefreshShowAllLocation)
                .addGap(17, 17, 17))
        );

        jTabbedPane1.addTab("Show all locations", jPanel11);

        comboBoxHourDeayFlight.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        comboBoxHourDeayFlight.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hour" }));

        jLabel46.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel46.setText("Hours:");

        jLabel47.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel47.setText("ID:");

        comboBoxIDDelayFlight.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        comboBoxIDDelayFlight.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID" }));

        jLabel48.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel48.setText("Minutes:");

        comboBoxMinuteDelayFlight.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        comboBoxMinuteDelayFlight.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Minute" }));

        btnDelayDelayFlight.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        btnDelayDelayFlight.setText("Delay");
        btnDelayDelayFlight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelayDelayFlightActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel48)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(comboBoxMinuteDelayFlight, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel47)
                            .addComponent(jLabel46))
                        .addGap(79, 79, 79)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboBoxHourDeayFlight, 0, 138, Short.MAX_VALUE)
                            .addComponent(comboBoxIDDelayFlight, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(820, 820, 820))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDelayDelayFlight)
                .addGap(531, 531, 531))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(comboBoxIDDelayFlight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(comboBoxHourDeayFlight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(comboBoxMinuteDelayFlight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 307, Short.MAX_VALUE)
                .addComponent(btnDelayDelayFlight)
                .addGap(33, 33, 33))
        );

        jTabbedPane1.addTab("Delay flight", jPanel12);

        panelRound1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 1150, 620));

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1150, Short.MAX_VALUE)
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        panelRound1.add(panelRound3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 660, 1150, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void panelRound2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelRound2MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_panelRound2MousePressed

    private void panelRound2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelRound2MouseDragged
        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);
    }//GEN-LAST:event_panelRound2MouseDragged

    private void administratorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_administratorActionPerformed

        for (int i = 1; i < jTabbedPane1.getTabCount(); i++) {
                jTabbedPane1.setEnabledAt(i, true);
        }
        jTabbedPane1.setEnabledAt(5, false);
        jTabbedPane1.setEnabledAt(6, false);
        jTabbedPane1.setEnabledAt(7, false);
    }//GEN-LAST:event_administratorActionPerformed

    private void userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userActionPerformed

        for (int i = 1; i < jTabbedPane1.getTabCount(); i++) {

            jTabbedPane1.setEnabledAt(i, false);

        }
        jTabbedPane1.setEnabledAt(9, true);
        jTabbedPane1.setEnabledAt(5, true);
        jTabbedPane1.setEnabledAt(6, true);
        jTabbedPane1.setEnabledAt(7, true);
        jTabbedPane1.setEnabledAt(11, true);
    }//GEN-LAST:event_userActionPerformed

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        // TODO add your handling code here:
     
            // Obtener y validar campos
            String id = txtIDRegistration.getText().trim();
            String firstname = txtFirstNameRegistration.getText().trim();
            String lastname = txtLastNameRegistration.getText().trim();
            String year = txtBirthdateRegistration.getText().trim();
            String month = comboBoxMonthRegistration.getSelectedItem().toString().trim();
            String day = comboBoxDayRegistration.getSelectedItem().toString().trim();
            String phoneCode = txtPhoneCodeRegistration.getText().trim();
            String phone = txtPhoneRegistration.getText().trim();
            String country = txtCountryRegistration.getText().trim();

            // Construir fecha
            String birthDate = year + "-" + month + "-" + day;
            
            // Llamar al controlador
            Response response = PassengerController.createPassenger(id, firstname, lastname, birthDate, phoneCode, phone, country);

            // Mostrar mensajes según status
            if (response.getStatus() >= 500){
                JOptionPane.showMessageDialog(this, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
            } else if (response.getStatus() >= 400) {
                JOptionPane.showMessageDialog(this, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, response.getMessage(), "Success " + response.getStatus(), JOptionPane.INFORMATION_MESSAGE);
                this.userSelect.addItem("" + id);
                txtIDRegistration.setText("");
                txtFirstNameRegistration.setText("");
                txtLastNameRegistration.setText("");
                txtBirthdateRegistration.setText(""); // si tienes un campo año
                comboBoxMonthRegistration.setSelectedIndex(0); // primer mes
                comboBoxDayRegistration.setSelectedIndex(0);   // primer día
                txtPhoneCodeRegistration.setText("");
                txtPhoneRegistration.setText("");
                txtCountryRegistration.setText("");
            }

    }//GEN-LAST:event_btnRegisterActionPerformed

    private void btnCreateAirplaneRegistrationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateAirplaneRegistrationActionPerformed
        // TODO add your handling code here:
        String id = txtIDAirplaneRegistrarion.getText();
        String brand = txtBrandAirplaneRegistration.getText();
        String model = txtModelAirplaneRegistration.getText();
        String maxCapacity = txtMaxCapacityAirplaneRegistration.getText();
        String airline = txtAirlineAirplaneRegistration.getText();
        
        Response response = PlaneController.createPlane(id, brand, model, maxCapacity, airline);
        
        if (response.getStatus() >= 500) {
                JOptionPane.showMessageDialog(this, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
            } else if (response.getStatus() >= 400) {
                JOptionPane.showMessageDialog(this, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, response.getMessage(), "Success " + response.getStatus(), JOptionPane.INFORMATION_MESSAGE);
                this.comboBoxPlaneFlightRegistration.addItem(id);
                txtIDAirplaneRegistrarion.setText("");
                txtBrandAirplaneRegistration.setText("");
                txtModelAirplaneRegistration.setText("");
                txtMaxCapacityAirplaneRegistration.setText("");
                txtAirlineAirplaneRegistration.setText("");
            }
    }//GEN-LAST:event_btnCreateAirplaneRegistrationActionPerformed

    private void btnCreateLocationRegistrationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateLocationRegistrationActionPerformed
        // TODO add your handling code here:
        
            String id = txtAirportIDLocationRegistration.getText().trim();
            String name = txtAirportNameLocationRegistration.getText().trim();
            String city = txtAirportCityLocationRegistration.getText().trim();
            String country = txtAirportCountryLocationRegistration.getText().trim();
            String latitude = txtAirportLatitudeLocationRegistration.getText().trim();
            String longitude = txtAirportLongitudeLocationRegistration.getText().trim();

            Response response = LocationController.createLocation(id, name, city, country, latitude, longitude);

            if (response.getStatus() >= 500) {
                JOptionPane.showMessageDialog(this, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
            } else if (response.getStatus() >= 400) {
                JOptionPane.showMessageDialog(this, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, response.getMessage(), "Success " + response.getStatus(), JOptionPane.INFORMATION_MESSAGE);

                // Solo agregas a los comboBoxes si fue exitoso
                comboBoxDepartureLocationFlightRegistration.addItem(id);
                comboBoxArrivalLocationFlightRegistration.addItem(id);
                comboBoxScaleLocationFlightRegistration.addItem(id);

                txtAirportIDLocationRegistration.setText("");
                txtAirportNameLocationRegistration.setText("");
                txtAirportCityLocationRegistration.setText("");
                txtAirportCountryLocationRegistration.setText("");
                txtAirportLatitudeLocationRegistration.setText("");
                txtAirportLongitudeLocationRegistration.setText("");
            }

        
    }//GEN-LAST:event_btnCreateLocationRegistrationActionPerformed

    private void btnCreateFlightRegistrationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateFlightRegistrationActionPerformed
        // TODO add your handling code here:
        String id = txtIDFlightRegistration.getText().trim();
        String planeId = comboBoxPlaneFlightRegistration.getSelectedItem().toString();
        String departureLocationId = comboBoxDepartureLocationFlightRegistration.getSelectedItem().toString();
        String arrivalLocationId = comboBoxArrivalLocationFlightRegistration.getSelectedItem().toString();
        String scaleLocationId = comboBoxScaleLocationFlightRegistration.getSelectedItem().toString(); // puede ser ""

        String year = txtYearFlightRegistration.getText().trim();
        String month = comboBoxMonthFlightRegistration.getSelectedItem().toString();
        String day = comboBoxDayFlightRegistration.getSelectedItem().toString();
        String hour = comboBoxHourDepartureDateFlightRegistration.getSelectedItem().toString();
        String minutes = comboBoxMinuteDepartureDateFlightRegistration.getSelectedItem().toString();

        // convertir a formato yyyy-MM-ddTHH:mm:ss como String
        String departureDateStr = String.format("%s-%s-%s", year, month, day);
        String departureTimeStr = String.format("%s:%s", hour, minutes);

        String hoursDurationArrival = comboBoxHourArrivalLocationFlightRegistration.getSelectedItem().toString();
        String minutesDurationArrival = comboBoxMinuteArrivalFlightRegistration.getSelectedItem().toString();
        String hoursDurationScale = comboBoxHourScaleLocationFlightRegistration.getSelectedItem().toString();
        String minutesDurationScale = comboBoxMinuteScaleLocationFlightRegistration.getSelectedItem().toString();

        Response response = FlightController.createFlight(
            id, planeId, departureLocationId, arrivalLocationId, scaleLocationId,
            departureDateStr, departureTimeStr, hoursDurationArrival, minutesDurationArrival,
            hoursDurationScale, minutesDurationScale
        );
        
        if (response.getStatus() >= 500) {
        JOptionPane.showMessageDialog(this, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(this, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, response.getMessage(), "Success", JOptionPane.INFORMATION_MESSAGE);
            // Agregar ID al combo de asignación de vuelos
            comboBoxFlightAdd.addItem(id);
            
            txtIDFlightRegistration.setText("");
            txtYearFlightRegistration.setText("");

            comboBoxPlaneFlightRegistration.setSelectedIndex(0);
            comboBoxDepartureLocationFlightRegistration.setSelectedIndex(0);
            comboBoxArrivalLocationFlightRegistration.setSelectedIndex(0);
            comboBoxScaleLocationFlightRegistration.setSelectedIndex(0);

            comboBoxMonthFlightRegistration.setSelectedIndex(0);
            comboBoxDayFlightRegistration.setSelectedIndex(0);
            comboBoxHourDepartureDateFlightRegistration.setSelectedIndex(0);
            comboBoxMinuteDepartureDateFlightRegistration.setSelectedIndex(0);

            comboBoxHourArrivalLocationFlightRegistration.setSelectedIndex(0);
            comboBoxMinuteArrivalFlightRegistration.setSelectedIndex(0);
            comboBoxHourScaleLocationFlightRegistration.setSelectedIndex(0);
            comboBoxMinuteScaleLocationFlightRegistration.setSelectedIndex(0);
        }
        ShowJsonComboBox.loadFlights(comboBoxIDDelayFlight);

    }//GEN-LAST:event_btnCreateFlightRegistrationActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        String id = txtIDUpdate.getText().trim();
        String firstname = txtFirstNameUpdate.getText().trim();
        String lastname = txtLastNameUpdate.getText().trim();
        String year = txtBirthDateUpdate.getText().trim();
        String month = comboBoxMonthUpdate.getSelectedItem().toString().trim();
        String day = comboBoxDayUpdate.getSelectedItem().toString().trim();
        String phoneCode = txtPhoneCodeUpdate.getText().trim();
        String phone = txtPhoneUpdate.getText().trim();
        String country = txtCountryUpdate.getText().trim();
        

        String birthDate = year + "-" + month + "-" + day;
        
        Response response = PassengerController.updatePassenger(id, firstname, lastname, year, month, day, phoneCode, phone, country );

        if (response.getStatus() >= 500) {
                JOptionPane.showMessageDialog(this, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
            } else if (response.getStatus() >= 400) {
                JOptionPane.showMessageDialog(this, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, response.getMessage(), "Success " + response.getStatus(), JOptionPane.INFORMATION_MESSAGE);

                txtIDUpdate.setText("");
                txtFirstNameUpdate.setText("");
                txtLastNameUpdate.setText("");
                txtBirthDateUpdate.setText("");
                comboBoxMonthUpdate.setSelectedIndex(0);
                comboBoxDayUpdate.setSelectedIndex(0);
                txtPhoneCodeUpdate.setText("");
                txtPhoneUpdate.setText("");
                txtCountryUpdate.setText("");
            }

    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        String passengerId = txtIDAdd.getText();
        String flightId = comboBoxFlightAdd.getSelectedItem().toString();

        Response response = FlightController.addPassengerToFlight(passengerId, flightId);

        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(this, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(this, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, response.getMessage(), "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDelayDelayFlightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelayDelayFlightActionPerformed
        String selectedFlightId = "";
        selectedFlightId = comboBoxIDDelayFlight.getSelectedItem().toString();
        String delayHoursStr = comboBoxHourDeayFlight.getSelectedItem().toString();
        String delayMinutesStr = comboBoxMinuteDelayFlight.getSelectedItem().toString();
        if (delayHoursStr.equalsIgnoreCase("Hour")) delayHoursStr = "0";
        if (delayMinutesStr.equalsIgnoreCase("Minute")) delayMinutesStr = "0";
        Response controllerResponse = FlightController.delayFlight(selectedFlightId, delayHoursStr, delayMinutesStr);
        if (controllerResponse.getStatus() >= 400) {
        JOptionPane.showMessageDialog(this, controllerResponse.getMessage(), "Operation Failed - Status: " + controllerResponse.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, controllerResponse.getMessage(), "Operation Successful - Status: " + controllerResponse.getStatus(), JOptionPane.INFORMATION_MESSAGE);
            if (controllerResponse.getStatus() < 300) {
            comboBoxIDDelayFlight.setSelectedIndex(0);
            comboBoxHourDeayFlight.setSelectedIndex(0);
            comboBoxMinuteDelayFlight.setSelectedIndex(0);
            }
        }
    }//GEN-LAST:event_btnDelayDelayFlightActionPerformed

    private void btnRefreshShowMyFlightsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshShowMyFlightsActionPerformed
        // TODO add your handling code here:
        String selected = userSelect.getSelectedItem().toString();

        Response response = FlightTableController.updateMyFlightsTable(selected, (DefaultTableModel) tableShowMyFlights.getModel());

        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(this, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(this, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, response.getMessage(), "Success", JOptionPane.INFORMATION_MESSAGE);
        }
//        long passengerId = Long.parseLong(userSelect.getItemAt(userSelect.getSelectedIndex()));
//
//        Passenger passenger = null;
//        for (Passenger p : this.passengers) {
//            if (p.getId() == passengerId) {
//                passenger = p;
//            }
//        }
//
//        ArrayList<Flight> flights = passenger.getFlights();
//        DefaultTableModel model = (DefaultTableModel) tableShowMyFlights.getModel();
//        model.setRowCount(0);
//        for (Flight flight : flights) {
//            model.addRow(new Object[]{flight.getId(), flight.getDepartureDate(), flight.calculateArrivalDate()});
//        }
    }//GEN-LAST:event_btnRefreshShowMyFlightsActionPerformed

    private void btnRefreshShowAllPassengersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshShowAllPassengersActionPerformed
        // TODO add your handling code here:
        Response response = PassengerTableController.updatePassengersTable((DefaultTableModel) tableShowAllPassengers.getModel());

        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);
        }
//        for (Passenger passenger : this.passengers) {
//            model.addRow(new Object[]{passenger.getId(), passenger.getFullname(), passenger.getBirthDate(), passenger.calculateAge(), passenger.generateFullPhone(), passenger.getCountry(), passenger.getNumFlights()});
//        }
    }//GEN-LAST:event_btnRefreshShowAllPassengersActionPerformed

    private void btnRefreshShowAllFlightsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshShowAllFlightsActionPerformed
        // TODO add your handling code here:
        Response response = FlightTableController.updateFlightsTable((DefaultTableModel) tableShowAllFlights.getModel());

        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);
        }
//        DefaultTableModel model = (DefaultTableModel) tableShowAllFlights.getModel();
//        model.setRowCount(0);
//        for (Flight flight : this.flights) {
//            model.addRow(new Object[]{flight.getId(), flight.getDepartureLocation().getAirportId(), flight.getArrivalLocation().getAirportId(), (flight.getScaleLocation() == null ? "-" : flight.getScaleLocation().getAirportId()), flight.getDepartureDate(), flight.calculateArrivalDate(), flight.getPlane().getId(), flight.getNumPassengers()});
//        }
    }//GEN-LAST:event_btnRefreshShowAllFlightsActionPerformed

    private void btnRefreshShowAllPlanesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshShowAllPlanesActionPerformed
        // TODO add your handling code here:
        Response response = PlaneTableController.updatePlanesTable((DefaultTableModel) tableShowAllPlanes.getModel());

        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnRefreshShowAllPlanesActionPerformed

    private void btnRefreshShowAllLocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshShowAllLocationActionPerformed
        // TODO add your handling code here:
        Response response = LocationTableController.updateLocationsTable((DefaultTableModel) tableShowAllLocations.getModel());

        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnRefreshShowAllLocationActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    private void userSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userSelectActionPerformed
        try {
            String id = userSelect.getSelectedItem().toString();
            if (! id.equals(userSelect.getItemAt(0))) {
                txtIDUpdate.setText(id);
                txtIDAdd.setText(id);
            }
            else{
                txtIDUpdate.setText("");
                txtIDAdd.setText("");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_userSelectActionPerformed

    private void txtPhoneCodeUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhoneCodeUpdateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhoneCodeUpdateActionPerformed

    private void comboBoxMonthUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxMonthUpdateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxMonthUpdateActionPerformed

    private void txtYearFlightRegistrationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtYearFlightRegistrationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtYearFlightRegistrationActionPerformed

    private void comboBoxScaleLocationFlightRegistrationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxScaleLocationFlightRegistrationActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxScaleLocationFlightRegistrationActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton administrator;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCreateAirplaneRegistration;
    private javax.swing.JButton btnCreateFlightRegistration;
    private javax.swing.JButton btnCreateLocationRegistration;
    private javax.swing.JButton btnDelayDelayFlight;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnRefreshShowAllFlights;
    private javax.swing.JButton btnRefreshShowAllLocation;
    private javax.swing.JButton btnRefreshShowAllPassengers;
    private javax.swing.JButton btnRefreshShowAllPlanes;
    private javax.swing.JButton btnRefreshShowMyFlights;
    private javax.swing.JButton btnRegister;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> comboBoxArrivalLocationFlightRegistration;
    private javax.swing.JComboBox<String> comboBoxDayFlightRegistration;
    private javax.swing.JComboBox<String> comboBoxDayRegistration;
    private javax.swing.JComboBox<String> comboBoxDayUpdate;
    private javax.swing.JComboBox<String> comboBoxDepartureLocationFlightRegistration;
    private javax.swing.JComboBox<String> comboBoxFlightAdd;
    private javax.swing.JComboBox<String> comboBoxHourArrivalLocationFlightRegistration;
    private javax.swing.JComboBox<String> comboBoxHourDeayFlight;
    private javax.swing.JComboBox<String> comboBoxHourDepartureDateFlightRegistration;
    private javax.swing.JComboBox<String> comboBoxHourScaleLocationFlightRegistration;
    private javax.swing.JComboBox<String> comboBoxIDDelayFlight;
    private javax.swing.JComboBox<String> comboBoxMinuteArrivalFlightRegistration;
    private javax.swing.JComboBox<String> comboBoxMinuteDelayFlight;
    private javax.swing.JComboBox<String> comboBoxMinuteDepartureDateFlightRegistration;
    private javax.swing.JComboBox<String> comboBoxMinuteScaleLocationFlightRegistration;
    private javax.swing.JComboBox<String> comboBoxMonthFlightRegistration;
    private javax.swing.JComboBox<String> comboBoxMonthRegistration;
    private javax.swing.JComboBox<String> comboBoxMonthUpdate;
    private javax.swing.JComboBox<String> comboBoxPlaneFlightRegistration;
    private javax.swing.JComboBox<String> comboBoxScaleLocationFlightRegistration;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private core.views.PanelRound panelRound1;
    private core.views.PanelRound panelRound2;
    private core.views.PanelRound panelRound3;
    private javax.swing.JTable tableShowAllFlights;
    private javax.swing.JTable tableShowAllLocations;
    private javax.swing.JTable tableShowAllPassengers;
    private javax.swing.JTable tableShowAllPlanes;
    private javax.swing.JTable tableShowMyFlights;
    private javax.swing.JTextField txtAirlineAirplaneRegistration;
    private javax.swing.JTextField txtAirportCityLocationRegistration;
    private javax.swing.JTextField txtAirportCountryLocationRegistration;
    private javax.swing.JTextField txtAirportIDLocationRegistration;
    private javax.swing.JTextField txtAirportLatitudeLocationRegistration;
    private javax.swing.JTextField txtAirportLongitudeLocationRegistration;
    private javax.swing.JTextField txtAirportNameLocationRegistration;
    private javax.swing.JTextField txtBirthDateUpdate;
    private javax.swing.JTextField txtBirthdateRegistration;
    private javax.swing.JTextField txtBrandAirplaneRegistration;
    private javax.swing.JTextField txtCountryRegistration;
    private javax.swing.JTextField txtCountryUpdate;
    private javax.swing.JTextField txtFirstNameRegistration;
    private javax.swing.JTextField txtFirstNameUpdate;
    private javax.swing.JTextField txtIDAdd;
    private javax.swing.JTextField txtIDAirplaneRegistrarion;
    private javax.swing.JTextField txtIDFlightRegistration;
    private javax.swing.JTextField txtIDRegistration;
    private javax.swing.JTextField txtIDUpdate;
    private javax.swing.JTextField txtLastNameRegistration;
    private javax.swing.JTextField txtLastNameUpdate;
    private javax.swing.JTextField txtMaxCapacityAirplaneRegistration;
    private javax.swing.JTextField txtModelAirplaneRegistration;
    private javax.swing.JTextField txtPhoneCodeRegistration;
    private javax.swing.JTextField txtPhoneCodeUpdate;
    private javax.swing.JTextField txtPhoneRegistration;
    private javax.swing.JTextField txtPhoneUpdate;
    private javax.swing.JTextField txtYearFlightRegistration;
    private javax.swing.JRadioButton user;
    private javax.swing.JComboBox<String> userSelect;
    // End of variables declaration//GEN-END:variables
}
