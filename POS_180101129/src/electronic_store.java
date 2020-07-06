
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author user
 */
public final class electronic_store extends javax.swing.JFrame {

    /**
     * Creates new form electronic_store
     */
    private final DefaultTableModel TransactionTable, CustomerTable, ProductTable, UserTable, SelectedProductTable;

    public electronic_store() {
        initComponents();

        jPanel6.setVisible(false);
        jMenuBar1.setVisible(false);
        jTabbedPane1.setVisible(false);
        jPanel5.setVisible(true);

        TransactionTable = new DefaultTableModel();
        jTable1.setModel(TransactionTable);
        TransactionTable.addColumn("Transaction Code");
        TransactionTable.addColumn("Date");
        TransactionTable.addColumn("Time");
        TransactionTable.addColumn("Customer Id");
        TransactionTable.addColumn("Name");
        TransactionTable.addColumn("Amount");
        TransactionTable.addColumn("Price");

        ProductTable = new DefaultTableModel();
        jTable2.setModel(ProductTable);
        ProductTable.addColumn("Product Code");
        ProductTable.addColumn("Name");
        ProductTable.addColumn("Category");
        ProductTable.addColumn("Amount");
        ProductTable.addColumn("Price");

        CustomerTable = new DefaultTableModel();
        jTable3.setModel(CustomerTable);
        CustomerTable.addColumn("Customer ID");
        CustomerTable.addColumn("Name");
        CustomerTable.addColumn("Address");
        CustomerTable.addColumn("Phone");
        CustomerTable.addColumn("Email");

        UserTable = new DefaultTableModel();
        jTable4.setModel(UserTable);
        UserTable.addColumn("User ID");
        UserTable.addColumn("Name");
        UserTable.addColumn("Password");
        UserTable.addColumn("Category");
        UserTable.addColumn("Adrress");
        UserTable.addColumn("Phone");
        UserTable.addColumn("Email");

        SelectedProductTable = new DefaultTableModel();
        jTable5.setModel(SelectedProductTable);
        SelectedProductTable.addColumn("Product Code");
        SelectedProductTable.addColumn("Name");
        SelectedProductTable.addColumn("Amount");
        SelectedProductTable.addColumn("Price");

        GetTransactionData();
        GetProductData();
        GetCustomerData();
        GetUserData();
        GetSelectedProductData();

        jTextField1.setText("TR000" + String.valueOf(jTable1.getRowCount() + 1));
        jTextField5.setText("PR000" + String.valueOf(jTable2.getRowCount() + 1));
        jTextField9.setText("CU000" + String.valueOf(jTable3.getRowCount() + 1));
        jTextField14.setText("US000" + String.valueOf(jTable4.getRowCount() + 1));
    }

    @SuppressWarnings("empty-statement")
    public void GetTransactionData() {
        TransactionTable.getDataVector().removeAllElements();;
        TransactionTable.fireTableDataChanged();

        try {
            Statement stat = (Statement) db_connection.getconnection().createStatement();
            String sql = "SELECT * FROM transaction";
            ResultSet res = stat.executeQuery(sql);

            while (res.next()) {
                Object[] obj = new Object[7];
                obj[0] = res.getString("tcode");
                obj[1] = res.getString("tdate");
                obj[2] = res.getString("ttime");
                obj[3] = res.getString("cid");
                obj[4] = res.getString("cname");
                obj[5] = res.getString("tamount");
                obj[6] = res.getString("tprice");

                TransactionTable.addRow(obj);
            }
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }

    @SuppressWarnings("empty-statement")
    public void GetProductData() {
        ProductTable.getDataVector().removeAllElements();;
        ProductTable.fireTableDataChanged();
        jComboBox4.removeAllItems();

        try {
            Statement stat = (Statement) db_connection.getconnection().createStatement();
            String sql = "SELECT * FROM product";
            ResultSet res = stat.executeQuery(sql);

            while (res.next()) {
                Object[] obj = new Object[5];
                obj[0] = res.getString("pcode");
                obj[1] = res.getString("pname");
                obj[2] = res.getString("pcategory");
                obj[3] = res.getString("pamount");
                obj[4] = res.getString("pprice");

                ProductTable.addRow(obj);
                jComboBox4.addItem(obj[0].toString());
            }
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }

    @SuppressWarnings("empty-statement")
    public void GetCustomerData() {
        CustomerTable.getDataVector().removeAllElements();;
        CustomerTable.fireTableDataChanged();
        jComboBox1.removeAllItems();

        try {
            Statement stat = (Statement) db_connection.getconnection().createStatement();
            String sql = "SELECT * FROM customer";
            ResultSet res = stat.executeQuery(sql);

            while (res.next()) {
                Object[] obj = new Object[5];
                obj[0] = res.getString("cid");
                obj[1] = res.getString("cname");
                obj[2] = res.getString("caddress");
                obj[3] = res.getString("cphone");
                obj[4] = res.getString("cemail");

                CustomerTable.addRow(obj);
                jComboBox1.addItem(obj[0].toString());
            }
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }

    @SuppressWarnings("empty-statement")
    public void GetUserData() {
        UserTable.getDataVector().removeAllElements();;
        UserTable.fireTableDataChanged();

        try {
            Statement stat = (Statement) db_connection.getconnection().createStatement();
            String sql = "SELECT * FROM user";
            ResultSet res = stat.executeQuery(sql);

            while (res.next()) {
                Object[] obj = new Object[7];
                obj[0] = res.getString("uid");
                obj[1] = res.getString("uname");
                obj[2] = res.getString("upassword");
                obj[3] = res.getString("ucategory");
                obj[4] = res.getString("uaddress");
                obj[5] = res.getString("uphone");
                obj[6] = res.getString("uemail");

                UserTable.addRow(obj);
            }
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }

    @SuppressWarnings("empty-statement")
    public void GetSelectedProductData() {
        SelectedProductTable.getDataVector().removeAllElements();;
        SelectedProductTable.fireTableDataChanged();

        try {
            Statement stat = (Statement) db_connection.getconnection().createStatement();
            String sql = "SELECT * FROM product_selected WHERE tcode = '" + jTextField1.getText() + "'";
            ResultSet res = stat.executeQuery(sql);

            while (res.next()) {
                Object[] obj = new Object[4];
                obj[0] = res.getString("pcode");
                obj[1] = res.getString("pname");
                obj[2] = res.getString("psamount");
                obj[3] = res.getString("psprice");

                SelectedProductTable.addRow(obj);
            }
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, err.getMessage());
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        jTextField19 = new javax.swing.JTextField();
        jComboBox3 = new javax.swing.JComboBox<>();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jTextField20 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton22 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jTextField21 = new javax.swing.JTextField();
        jTextField22 = new javax.swing.JTextField();
        jTextField23 = new javax.swing.JTextField();
        jTextField24 = new javax.swing.JTextField();
        jTextField25 = new javax.swing.JTextField();
        jTextField26 = new javax.swing.JTextField();
        jTextField27 = new javax.swing.JTextField();
        jTextField28 = new javax.swing.JTextField();
        jComboBox4 = new javax.swing.JComboBox<>();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Electronic Store");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("TRANSACTION");

        jLabel2.setText("Transaction Code");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Costumer");

        jLabel4.setText("Costumer Id");

        jLabel5.setText("Name");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Product");

        jLabel7.setText("Amount");

        jLabel8.setText("Price");

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(240, 240, 245));
        jTextField1.setFocusable(false);
        jTextField1.setRequestFocusEnabled(false);
        jTextField1.setVerifyInputWhenFocusTarget(false);

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(240, 240, 245));
        jTextField2.setFocusable(false);
        jTextField2.setRequestFocusEnabled(false);
        jTextField2.setVerifyInputWhenFocusTarget(false);

        jTextField3.setEditable(false);
        jTextField3.setBackground(new java.awt.Color(240, 240, 245));
        jTextField3.setFocusable(false);
        jTextField3.setRequestFocusEnabled(false);
        jTextField3.setVerifyInputWhenFocusTarget(false);

        jTextField4.setEditable(false);
        jTextField4.setBackground(new java.awt.Color(240, 240, 245));
        jTextField4.setFocusable(false);
        jTextField4.setRequestFocusEnabled(false);
        jTextField4.setVerifyInputWhenFocusTarget(false);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton1.setText("...");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Save");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Cancel");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Select");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Delete");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Clear");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel8))
                                .addComponent(jLabel6)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jButton1))
                                .addComponent(jLabel5)
                                .addComponent(jLabel4)
                                .addComponent(jLabel3)
                                .addComponent(jTextField2)
                                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(33, 33, 33))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton3)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Transaction", jPanel1);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("PRODUCT");

        jLabel10.setText("Product Code");

        jLabel11.setText("Name");

        jLabel12.setText("Category");

        jLabel13.setText("Amount");

        jLabel14.setText("Price");

        jTextField5.setEditable(false);
        jTextField5.setBackground(new java.awt.Color(240, 240, 245));
        jTextField5.setFocusable(false);
        jTextField5.setRequestFocusEnabled(false);
        jTextField5.setVerifyInputWhenFocusTarget(false);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Clock", "Fan", "Radio", "Refrigerator", "Television" }));

        jButton7.setText("Save");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Cancel");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("Edit");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("Delete");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setText("Clear");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton11))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField6, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jButton7)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jButton8))
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jTextField7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                            .addComponent(jTextField8, javax.swing.GroupLayout.Alignment.LEADING))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton8)
                            .addComponent(jButton7)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9)
                    .addComponent(jButton10)
                    .addComponent(jButton11))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Product", jPanel2);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("CUSTOMER");

        jLabel16.setText("Customer Id");

        jLabel17.setText("Name");

        jLabel18.setText("Address");

        jLabel19.setText("Phone");

        jLabel20.setText("Email");

        jTextField9.setEditable(false);
        jTextField9.setBackground(new java.awt.Color(240, 240, 245));
        jTextField9.setFocusable(false);
        jTextField9.setRequestFocusEnabled(false);
        jTextField9.setVerifyInputWhenFocusTarget(false);

        jButton12.setText("Save");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setText("Cancel");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setText("Edit");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setText("Delete");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setText("Clear");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTextField12, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTextField11, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTextField10, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTextField13)
                        .addComponent(jTextField9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton13)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton16))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton13)
                            .addComponent(jButton12)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton14)
                    .addComponent(jButton15)
                    .addComponent(jButton16))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Customer", jPanel3);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setText("USER");

        jLabel22.setText("User Id");

        jLabel23.setText("Name");

        jLabel24.setText("Password");

        jLabel25.setText("Category");

        jLabel26.setText("Address");

        jLabel27.setText("Phone");

        jLabel28.setText("Email");

        jTextField14.setEditable(false);
        jTextField14.setBackground(new java.awt.Color(240, 240, 245));
        jTextField14.setFocusable(false);
        jTextField14.setRequestFocusEnabled(false);
        jTextField14.setVerifyInputWhenFocusTarget(false);

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "User" }));

        jButton17.setText("Save");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setText("Cancel");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton19.setText("Edit");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton20.setText("Delete");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton21.setText("Clear");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jTable4);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jButton17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton18))
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel28)
                                .addComponent(jLabel27)
                                .addComponent(jLabel22)
                                .addComponent(jLabel23)
                                .addComponent(jLabel25)
                                .addComponent(jLabel26)
                                .addComponent(jLabel24)
                                .addComponent(jComboBox3, 0, 250, Short.MAX_VALUE)
                                .addComponent(jTextField16)
                                .addComponent(jTextField15)
                                .addComponent(jTextField14)
                                .addComponent(jTextField17)
                                .addComponent(jTextField18)
                                .addComponent(jTextField19)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jButton19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton21)))))
                .addGap(33, 33, 33))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton18)
                            .addComponent(jButton17)))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton19)
                    .addComponent(jButton20)
                    .addComponent(jButton21))
                .addGap(33, 33, 33))
        );

        jTabbedPane1.addTab("User", jPanel4);

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("LOGIN");

        jLabel30.setText("Username");

        jLabel31.setText("Password");

        jButton22.setText("Login");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addComponent(jLabel30))
                        .addGap(147, 147, 147))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                            .addComponent(jPasswordField1)
                            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField20))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel29)
                .addGap(18, 18, 18)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton22)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel32.setText("PRODUCT");

        jLabel33.setText("Transaction Code");

        jLabel34.setText("Customer Id");

        jLabel35.setText("Name");

        jLabel36.setText("Poduct Code");

        jLabel37.setText("Name");

        jLabel38.setText("Stock");

        jLabel39.setText("Price");

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel40.setText("BUY");

        jLabel41.setText("Amount");

        jLabel42.setText("Total Price");

        jTextField21.setEditable(false);
        jTextField21.setBackground(new java.awt.Color(240, 240, 245));
        jTextField21.setFocusable(false);
        jTextField21.setRequestFocusEnabled(false);
        jTextField21.setVerifyInputWhenFocusTarget(false);

        jTextField22.setEditable(false);
        jTextField22.setBackground(new java.awt.Color(240, 240, 245));
        jTextField22.setFocusable(false);
        jTextField22.setRequestFocusEnabled(false);
        jTextField22.setVerifyInputWhenFocusTarget(false);

        jTextField23.setEditable(false);
        jTextField23.setBackground(new java.awt.Color(240, 240, 245));
        jTextField23.setFocusable(false);
        jTextField23.setRequestFocusEnabled(false);
        jTextField23.setVerifyInputWhenFocusTarget(false);

        jTextField24.setEditable(false);
        jTextField24.setBackground(new java.awt.Color(240, 240, 245));
        jTextField24.setFocusable(false);
        jTextField24.setRequestFocusEnabled(false);
        jTextField24.setVerifyInputWhenFocusTarget(false);

        jTextField25.setEditable(false);
        jTextField25.setBackground(new java.awt.Color(240, 240, 245));
        jTextField25.setFocusable(false);
        jTextField25.setRequestFocusEnabled(false);
        jTextField25.setVerifyInputWhenFocusTarget(false);

        jTextField26.setEditable(false);
        jTextField26.setBackground(new java.awt.Color(240, 240, 245));
        jTextField26.setFocusable(false);
        jTextField26.setRequestFocusEnabled(false);
        jTextField26.setVerifyInputWhenFocusTarget(false);

        jTextField27.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField27CaretUpdate(evt);
            }
        });

        jTextField28.setEditable(false);
        jTextField28.setBackground(new java.awt.Color(240, 240, 245));
        jTextField28.setFocusable(false);
        jTextField28.setRequestFocusEnabled(false);
        jTextField28.setVerifyInputWhenFocusTarget(false);

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        jButton23.setText("Add");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jButton24.setText("Cancel");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jButton25.setText("Print");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        jButton26.setText("Delete");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        jButton27.setText("Clear");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        jButton28.setText("Back");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(jTable5);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton28))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(33, 33, 33)
                                        .addComponent(jLabel38)
                                        .addGap(29, 29, 29))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel39)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jButton23)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton24))
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel40)
                                        .addComponent(jLabel36)
                                        .addComponent(jLabel33)
                                        .addComponent(jLabel32)
                                        .addComponent(jLabel34)
                                        .addComponent(jLabel35)
                                        .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel37)
                                        .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                            .addComponent(jLabel41)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel42))
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                            .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(29, 29, 29)))
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton26)
                            .addComponent(jButton27)
                            .addComponent(jButton28)
                            .addComponent(jButton25)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel35)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel36)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel38)
                            .addComponent(jLabel39))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel40)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel41)
                            .addComponent(jLabel42))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton23)
                            .addComponent(jButton24))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu1.setText("File");

        jMenu3.setText("New");

        jMenuItem3.setText("Customer");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuItem4.setText("Product");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuItem5.setText("Transaction");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuItem6.setText("User");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenu1.add(jMenu3);

        jMenu4.setText("Print");

        jMenuItem7.setText("Customer");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem7);

        jMenuItem8.setText("Product");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem8);

        jMenuItem9.setText("Transaction");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem9);

        jMenuItem10.setText("User");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem10);

        jMenu1.add(jMenu4);
        jMenu1.add(jSeparator1);

        jMenuItem1.setText("Logout");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);
        jMenu1.add(jSeparator2);

        jMenuItem2.setText("Exit");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Help");

        jMenuItem11.setText("About");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem11);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        try {
            JasperPrint jp = JasperFillManager.fillReport(getClass().getResourceAsStream("customer.jasper"), null, db_connection.getconnection());
            JasperExportManager.exportReportToPdfFile(jp, "customer.pdf");
        } catch (JRException e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        try {
            JasperPrint jp = JasperFillManager.fillReport(getClass().getResourceAsStream("product.jasper"), null, db_connection.getconnection());
            JasperExportManager.exportReportToPdfFile(jp, "product.pdf");
        } catch (JRException e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        try {
            JasperPrint jp = JasperFillManager.fillReport(getClass().getResourceAsStream("transaction.jasper"), null, db_connection.getconnection());
            JasperExportManager.exportReportToPdfFile(jp, "transaction.pdf");
        } catch (JRException e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        try {
            JasperPrint jp = JasperFillManager.fillReport(getClass().getResourceAsStream("user.jasper"), null, db_connection.getconnection());
            JasperExportManager.exportReportToPdfFile(jp, "user.pdf");
        } catch (JRException e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        jMenuBar1.setVisible(false);
        jTabbedPane1.setVisible(false);
        jPanel5.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        JOptionPane.showConfirmDialog(null, "Created by Muhamad Alwi Kurniawan", "About", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        try {
            Statement stat = (Statement) db_connection.getconnection().createStatement();
            String sql = "SELECT * FROM customer WHERE cid = '" + jComboBox1.getSelectedItem() + "'";
            ResultSet res = stat.executeQuery(sql);

            while (res.next()) {
                jTextField2.setText(res.getString("cname"));
            }
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jTextField21.setText(jTextField1.getText());
        if ("".equals(jComboBox1.getSelectedItem().toString())) {
            jTextField22.setText("");
        } else {
            jTextField22.setText(jComboBox1.getSelectedItem().toString());
        }
        jTextField23.setText(jTextField2.getText());
        jLabel36.setVisible(true);
        jLabel37.setVisible(true);
        jLabel38.setVisible(true);
        jLabel39.setVisible(true);
        jLabel40.setVisible(true);
        jLabel41.setVisible(true);
        jLabel42.setVisible(true);
        jTextField24.setVisible(true);
        jTextField25.setVisible(true);
        jTextField26.setVisible(true);
        jTextField27.setVisible(true);
        jTextField28.setVisible(true);
        jComboBox4.setVisible(true);
        jButton23.setVisible(true);
        jButton24.setVisible(true);
        jButton25.setVisible(false);
        jButton28.setText("Back");
        jMenuBar1.setVisible(false);
        jTabbedPane1.setVisible(false);
        GetSelectedProductData();
        jPanel6.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();
            DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
            Date time = new Date();
            String sql = "INSERT INTO transaction VALUE ('" + jTextField1.getText() + "', '" + dateFormat.format(date) + "', '" + timeFormat.format(time) + "', '" + jComboBox1.getSelectedItem() + "', '" + jTextField2.getText() + "', '" + jTextField3.getText() + "', '" + jTextField4.getText() + "')";
            db_connection.getconnection().prepareStatement(sql).executeUpdate();
            GetTransactionData();
            jTextField1.setText("TR000" + String.valueOf(jTable1.getRowCount() + 1));
            jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
            jComboBox1.setSelectedIndex(0);
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        jTextField1.setText("TR000" + String.valueOf(jTable1.getRowCount() + 1));
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jComboBox1.setSelectedIndex(0);
    }//GEN-LAST:event_jButton3ActionPerformed

    @SuppressWarnings("empty-statement")
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (jTable1.getSelectedRow() > -1) {
            SelectedProductTable.getDataVector().removeAllElements();;
            SelectedProductTable.fireTableDataChanged();
            try {
                Statement stat = (Statement) db_connection.getconnection().createStatement();
                String sql = "SELECT * FROM product_selected WHERE tcode = '" + jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString() + "'";
                ResultSet res = stat.executeQuery(sql);

                while (res.next()) {
                    Object[] obj = new Object[4];
                    obj[0] = res.getString("pcode");
                    obj[1] = res.getString("pname");
                    obj[2] = res.getString("psamount");
                    obj[3] = res.getString("psprice");

                    SelectedProductTable.addRow(obj);
                }
            } catch (SQLException err) {
                JOptionPane.showMessageDialog(null, err.getMessage());
            }
            jTextField21.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
            jTextField22.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString());
            jTextField23.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString());
            jLabel36.setVisible(false);
            jLabel37.setVisible(false);
            jLabel38.setVisible(false);
            jLabel39.setVisible(false);
            jLabel40.setVisible(false);
            jLabel41.setVisible(false);
            jLabel42.setVisible(false);
            jTextField24.setVisible(false);
            jTextField25.setVisible(false);
            jTextField26.setVisible(false);
            jTextField27.setVisible(false);
            jTextField28.setVisible(false);
            jComboBox4.setVisible(false);
            jButton23.setVisible(false);
            jButton24.setVisible(false);
            jButton25.setVisible(true);
            jButton28.setText("Close");
            jMenuBar1.setVisible(false);
            jTabbedPane1.setVisible(false);
            jPanel6.setVisible(true);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (jTable1.getSelectedRow() > -1) {
            String select = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
            if (JOptionPane.showConfirmDialog(null, "Are you sure to delete " + select + "'s data ?", "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                try {
                    String sql = "DELETE FROM transaction WHERE tcode = '" + select + "'";
                    db_connection.getconnection().prepareStatement(sql).executeUpdate();
                    sql = "DELETE FROM product_selected WHERE tcode = '" + select + "'";
                    db_connection.getconnection().prepareStatement(sql).executeUpdate();
                    GetTransactionData();
                    GetSelectedProductData();
                    jTextField1.setText("TR000" + String.valueOf(jTable1.getRowCount() + 1));
                    jTextField2.setText("");
                    jTextField3.setText("");
                    jTextField4.setText("");
                    jComboBox1.setSelectedIndex(0);
                } catch (SQLException err) {
                    JOptionPane.showMessageDialog(null, err.getMessage());
                }
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Are you sure to clear transaction data ?", "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try {
                String sql = "DELETE FROM transaction";
                db_connection.getconnection().prepareStatement(sql).executeUpdate();
                sql = "DELETE FROM product_selected";
                db_connection.getconnection().prepareStatement(sql).executeUpdate();
                GetTransactionData();
                GetSelectedProductData();
                jTextField1.setText("TR000" + String.valueOf(jTable1.getRowCount() + 1));
                jTextField2.setText("");
                jTextField3.setText("");
                jTextField4.setText("");
                jComboBox1.setSelectedIndex(0);
            } catch (SQLException err) {
                JOptionPane.showMessageDialog(null, err.getMessage());
            }
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if ("Save".equals(jButton7.getText())) {
            try {
                String sql = "INSERT INTO product VALUE ('" + jTextField5.getText() + "', '" + jTextField6.getText() + "', '" + jComboBox2.getSelectedItem() + "', '" + jTextField7.getText() + "', '" + jTextField8.getText() + "')";
                db_connection.getconnection().prepareStatement(sql).executeUpdate();
                GetProductData();
                jTextField5.setText("PR000" + String.valueOf(jTable2.getRowCount() + 1));
                jTextField6.setText("");
                jTextField7.setText("");
                jTextField8.setText("");
                jComboBox2.setSelectedIndex(0);
                jButton7.setText("Save");
            } catch (SQLException err) {
                JOptionPane.showMessageDialog(null, err.getMessage());
            }
        } else {
            if (JOptionPane.showConfirmDialog(null, "Are you sure to update " + jTextField5.getText() + "'s data ?", "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                try {
                    String sql = "UPDATE product SET pcode = '" + jTextField5.getText() + "', pname = '" + jTextField6.getText() + "', pcategory = '" + jComboBox2.getSelectedItem() + "', pamount = '" + jTextField7.getText() + "', pprice = '" + jTextField8.getText() + "' WHERE pcode = '" + jTextField5.getText() + "'";
                    db_connection.getconnection().prepareStatement(sql).executeUpdate();
                    GetProductData();
                    jTextField5.setText("PR000" + String.valueOf(jTable2.getRowCount() + 1));
                    jTextField6.setText("");
                    jTextField7.setText("");
                    jTextField8.setText("");
                    jComboBox2.setSelectedIndex(0);
                    jButton7.setText("Save");
                } catch (SQLException err) {
                    JOptionPane.showMessageDialog(null, err.getMessage());
                }
            }
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        jTextField5.setText("PR000" + String.valueOf(jTable2.getRowCount() + 1));
        jTextField6.setText("");
        jTextField7.setText("");
        jTextField8.setText("");
        jComboBox2.setSelectedIndex(0);
        jButton7.setText("Save");
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        if (jTable2.getSelectedRow() > -1) {
            jTextField5.setText(jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString());
            jTextField6.setText(jTable2.getValueAt(jTable2.getSelectedRow(), 1).toString());
            jComboBox2.setSelectedItem(jTable2.getValueAt(jTable2.getSelectedRow(), 2).toString());
            jTextField7.setText(jTable2.getValueAt(jTable2.getSelectedRow(), 3).toString());
            jTextField8.setText(jTable2.getValueAt(jTable2.getSelectedRow(), 4).toString());
            jButton7.setText("Update");
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        if (jTable2.getSelectedRow() > -1) {
            String select = jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString();
            if (JOptionPane.showConfirmDialog(null, "Are you sure to delete " + select + "'s data ?", "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                try {
                    String sql = "DELETE FROM product WHERE pcode = '" + select + "'";
                    db_connection.getconnection().prepareStatement(sql).executeUpdate();
                    GetProductData();
                    jTextField5.setText("PR000" + String.valueOf(jTable2.getRowCount() + 1));
                    jTextField6.setText("");
                    jTextField7.setText("");
                    jTextField8.setText("");
                    jComboBox2.setSelectedIndex(0);
                    jButton7.setText("Save");
                } catch (SQLException err) {
                    JOptionPane.showMessageDialog(null, err.getMessage());
                }
            }
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Are you sure to clear product data ?", "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try {
                String sql = "DELETE FROM product";
                db_connection.getconnection().prepareStatement(sql).executeUpdate();
                GetProductData();
                jTextField5.setText("PR000" + String.valueOf(jTable2.getRowCount() + 1));
                jTextField6.setText("");
                jTextField7.setText("");
                jTextField8.setText("");
                jComboBox2.setSelectedIndex(0);
                jButton7.setText("Save");
            } catch (SQLException err) {
                JOptionPane.showMessageDialog(null, err.getMessage());
            }
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        if ("Save".equals(jButton12.getText())) {
            try {
                String sql = "INSERT INTO customer VALUE ('" + jTextField9.getText() + "', '" + jTextField10.getText() + "', '" + jTextField11.getText() + "', '" + jTextField12.getText() + "', '" + jTextField13.getText() + "')";
                db_connection.getconnection().prepareStatement(sql).executeUpdate();
                GetCustomerData();
                jTextField9.setText("CU000" + String.valueOf(jTable3.getRowCount() + 1));
                jTextField10.setText("");
                jTextField11.setText("");
                jTextField12.setText("");
                jTextField13.setText("");
                jButton12.setText("Save");
            } catch (SQLException err) {
                JOptionPane.showMessageDialog(null, err.getMessage());
            }
        } else {
            if (JOptionPane.showConfirmDialog(null, "Are you sure to update " + jTextField9.getText() + "'s data ?", "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                try {
                    String sql = "UPDATE customer SET cid = '" + jTextField9.getText() + "', cname = '" + jTextField10.getText() + "', caddress = '" + jTextField11.getText() + "', cphone = '" + jTextField12.getText() + "', cemail = '" + jTextField13.getText() + "' WHERE cid = '" + jTextField9.getText() + "'";
                    db_connection.getconnection().prepareStatement(sql).executeUpdate();
                    GetCustomerData();
                    jTextField9.setText("CU000" + String.valueOf(jTable3.getRowCount() + 1));
                    jTextField10.setText("");
                    jTextField11.setText("");
                    jTextField12.setText("");
                    jTextField13.setText("");
                    jButton12.setText("Save");
                } catch (SQLException err) {
                    JOptionPane.showMessageDialog(null, err.getMessage());
                }
            }
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        jTextField9.setText("CU000" + String.valueOf(jTable3.getRowCount() + 1));
        jTextField10.setText("");
        jTextField11.setText("");
        jTextField12.setText("");
        jTextField13.setText("");
        jButton12.setText("Save");
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        if (jTable3.getSelectedRow() > -1) {
            jTextField9.setText(jTable3.getValueAt(jTable3.getSelectedRow(), 0).toString());
            jTextField10.setText(jTable3.getValueAt(jTable3.getSelectedRow(), 1).toString());
            jTextField11.setText(jTable3.getValueAt(jTable3.getSelectedRow(), 2).toString());
            jTextField12.setText(jTable3.getValueAt(jTable3.getSelectedRow(), 3).toString());
            jTextField13.setText(jTable3.getValueAt(jTable3.getSelectedRow(), 4).toString());
            jButton12.setText("Update");
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        if (jTable3.getSelectedRow() > -1) {
            String select = jTable3.getValueAt(jTable3.getSelectedRow(), 0).toString();
            if (JOptionPane.showConfirmDialog(null, "Are you sure to delete " + select + "'s data ?", "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                try {
                    String sql = "DELETE FROM customer WHERE cid = '" + select + "'";
                    db_connection.getconnection().prepareStatement(sql).executeUpdate();
                    GetCustomerData();
                    jTextField9.setText("CU000" + String.valueOf(jTable3.getRowCount() + 1));
                    jTextField10.setText("");
                    jTextField11.setText("");
                    jTextField12.setText("");
                    jTextField13.setText("");
                    jButton12.setText("Save");
                } catch (SQLException err) {
                    JOptionPane.showMessageDialog(null, err.getMessage());
                }
            }
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Are you sure to clear customer data ?", "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try {
                String sql = "DELETE FROM customer";
                db_connection.getconnection().prepareStatement(sql).executeUpdate();
                GetCustomerData();
                jTextField9.setText("CU000" + String.valueOf(jTable3.getRowCount() + 1));
                jTextField10.setText("");
                jTextField11.setText("");
                jTextField12.setText("");
                jTextField13.setText("");
                jButton12.setText("Save");
            } catch (SQLException err) {
                JOptionPane.showMessageDialog(null, err.getMessage());
            }
        }
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        if ("Save".equals(jButton17.getText())) {
            try {
                String sql = "INSERT INTO user VALUE ('" + jTextField14.getText() + "', '" + jTextField15.getText() + "', '" + jTextField16.getText() + "', '" + jComboBox3.getSelectedItem() + "', '" + jTextField17.getText() + "', '" + jTextField18.getText() + "', '" + jTextField19.getText() + "')";
                db_connection.getconnection().prepareStatement(sql).executeUpdate();
                GetUserData();
                jTextField14.setText("US000" + String.valueOf(jTable4.getRowCount() + 1));
                jTextField15.setText("");
                jTextField16.setText("");
                jTextField17.setText("");
                jTextField18.setText("");
                jTextField19.setText("");
                jComboBox3.setSelectedIndex(0);
                jButton17.setText("Save");
            } catch (SQLException err) {
                JOptionPane.showMessageDialog(null, err.getMessage());
            }
        } else {
            if (JOptionPane.showConfirmDialog(null, "Are you sure to update " + jTextField14.getText() + "'s data ?", "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                try {
                    String sql = "UPDATE user SET uid = '" + jTextField14.getText() + "', uname = '" + jTextField15.getText() + "', upassword = '" + jTextField16.getText() + "', ucategory = '" + jComboBox3.getSelectedItem() + "', uaddress = '" + jTextField17.getText() + "', uphone = '" + jTextField18.getText() + "', uemail = '" + jTextField19.getText() + "' WHERE uid = '" + jTextField14.getText() + "'";
                    db_connection.getconnection().prepareStatement(sql).executeUpdate();
                    GetUserData();
                    jTextField14.setText("US000" + String.valueOf(jTable4.getRowCount() + 1));
                    jTextField15.setText("");
                    jTextField16.setText("");
                    jTextField17.setText("");
                    jTextField18.setText("");
                    jTextField19.setText("");
                    jComboBox3.setSelectedIndex(0);
                    jButton17.setText("Save");
                } catch (SQLException err) {
                    JOptionPane.showMessageDialog(null, err.getMessage());
                }
            }
        }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        jTextField14.setText("US000" + String.valueOf(jTable4.getRowCount() + 1));
        jTextField15.setText("");
        jTextField16.setText("");
        jTextField17.setText("");
        jTextField18.setText("");
        jTextField19.setText("");
        jButton17.setText("Save");
        jComboBox3.setSelectedIndex(0);
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        if (jTable4.getSelectedRow() > -1) {
            jTextField14.setText(jTable4.getValueAt(jTable4.getSelectedRow(), 0).toString());
            jTextField15.setText(jTable4.getValueAt(jTable4.getSelectedRow(), 1).toString());
            jTextField16.setText(jTable4.getValueAt(jTable4.getSelectedRow(), 2).toString());
            jComboBox3.setSelectedItem(jTable4.getValueAt(jTable4.getSelectedRow(), 3).toString());
            jTextField17.setText(jTable4.getValueAt(jTable4.getSelectedRow(), 4).toString());
            jTextField18.setText(jTable4.getValueAt(jTable4.getSelectedRow(), 5).toString());
            jTextField19.setText(jTable4.getValueAt(jTable4.getSelectedRow(), 6).toString());
            jButton17.setText("Update");
        }
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        if (jTable4.getSelectedRow() > -1) {
            String select = jTable4.getValueAt(jTable4.getSelectedRow(), 0).toString();
            if (JOptionPane.showConfirmDialog(null, "Are you sure to delete " + select + "'s data ?", "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                try {
                    String sql = "DELETE FROM user WHERE uid = '" + select + "'";
                    db_connection.getconnection().prepareStatement(sql).executeUpdate();
                    GetUserData();
                    jTextField14.setText("US000" + String.valueOf(jTable4.getRowCount() + 1));
                    jTextField15.setText("");
                    jTextField16.setText("");
                    jTextField17.setText("");
                    jTextField18.setText("");
                    jTextField19.setText("");
                    jButton17.setText("Save");
                    jComboBox3.setSelectedIndex(0);
                } catch (SQLException err) {
                    JOptionPane.showMessageDialog(null, err.getMessage());
                }
            }
        }
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Are you sure to clear user data ?", "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try {
                String sql = "DELETE FROM user";
                db_connection.getconnection().prepareStatement(sql).executeUpdate();
                GetUserData();
                jTextField14.setText("US000" + String.valueOf(jTable4.getRowCount() + 1));
                jTextField15.setText("");
                jTextField16.setText("");
                jTextField17.setText("");
                jTextField18.setText("");
                jTextField19.setText("");
                jButton17.setText("Save");
                jComboBox3.setSelectedIndex(0);
            } catch (SQLException err) {
                JOptionPane.showMessageDialog(null, err.getMessage());
            }
        }
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        try {
            String username = "";
            String password = "";
            String category = "";
            String sql = "SELECT * FROM user WHERE uname = '" + jTextField20.getText() + "' AND upassword = '" + jPasswordField1.getText() + "'";
            ResultSet res = db_connection.getconnection().createStatement().executeQuery(sql);
            while (res.next()) {
                username = res.getString("uname");
                password = res.getString("upassword");
                category = res.getString("ucategory");
            }
            if ("".equals(username) & "".equals(password)) {
                JOptionPane.showConfirmDialog(null, "Please check your username or password and try again", "Information", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
            } else {
                if ("Admin".equals(category)) {
                    jMenuItem6.setVisible(true);
                    jMenuItem10.setVisible(true);
                    jTabbedPane1.setEnabledAt(3, true);
                } else {
                    jMenuItem6.setVisible(false);
                    jMenuItem10.setVisible(false);
                    jTabbedPane1.setEnabledAt(3, false);
                }
                jMenuBar1.setVisible(true);
                jTabbedPane1.setVisible(true);
                jPanel5.setVisible(false);
                jTextField20.setText("");
                jPasswordField1.setText("");
            }
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        if (jTable5.getSelectedRow() > -1) {
            String select = jTable5.getValueAt(jTable5.getSelectedRow(), 0).toString();
            if (JOptionPane.showConfirmDialog(null, "Are you sure to delete " + select + "'s data ?", "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                try {
                    String sql = "DELETE FROM product_selected WHERE tcode = '" + jTextField21.getText() + "' AND pcode = '" + select + "'";
                    db_connection.getconnection().prepareStatement(sql).executeUpdate();
                    GetSelectedProductData();
                    jTextField24.setText("");
                    jTextField25.setText("");
                    jTextField26.setText("");
                    jTextField27.setText("");
                    jTextField28.setText("");
                    jComboBox4.setSelectedIndex(0);
                } catch (SQLException err) {
                    JOptionPane.showMessageDialog(null, err.getMessage());
                }
            }
        }
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        String select = jTextField21.getText();
        if (JOptionPane.showConfirmDialog(null, "Are you sure to clear selected products data ?", "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try {
                String sql = "DELETE FROM product_selected WHERE tcode = '" + select + "'";
                db_connection.getconnection().prepareStatement(sql).executeUpdate();
                GetSelectedProductData();
                jTextField24.setText("");
                jTextField25.setText("");
                jTextField26.setText("");
                jTextField27.setText("");
                jTextField28.setText("");
                jComboBox4.setSelectedIndex(0);
            } catch (SQLException err) {
                JOptionPane.showMessageDialog(null, err.getMessage());
            }
        }
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        if ("Back".equals(jButton28.getText())) {
            int a = 0;
            int p = 0;
            for (int i = 0; i < jTable5.getRowCount(); i++) {
                a = a + Integer.valueOf(jTable5.getValueAt(i, 2).toString());
                p = p + Integer.valueOf(jTable5.getValueAt(i, 3).toString());
            }
            if (a <= 0 & p <= 0) {
                jTextField3.setText("");
                jTextField4.setText("");
            } else {
                jTextField3.setText(String.valueOf(a));
                jTextField4.setText(String.valueOf(p));
            }
        }
        jMenuBar1.setVisible(true);
        jTabbedPane1.setVisible(true);
        jPanel6.setVisible(false);
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        jTextField27.setText("");
        jTextField28.setText("");
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        String search = "";
        try {
            Statement stat = (Statement) db_connection.getconnection().createStatement();
            String sql = "SELECT * FROM product_selected WHERE tcode = '" + jTextField21.getText() + "' AND pcode = '" + jComboBox4.getSelectedItem().toString() + "'";
            ResultSet res = stat.executeQuery(sql);

            while (res.next()) {
                search = res.getString("pcode");
            }
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
        if (search.equals(jComboBox4.getSelectedItem().toString())) {
            try {
                String sql = "UPDATE product_selected SET psamount = '" + jTextField27.getText() + "', psprice = '" + jTextField28.getText() + "' WHERE tcode = '" + jTextField21.getText() + "' AND pcode = '" + jComboBox4.getSelectedItem().toString() + "'";
                db_connection.getconnection().prepareStatement(sql).executeUpdate();
                GetSelectedProductData();
                jTextField24.setText("");
                jTextField25.setText("");
                jTextField26.setText("");
                jTextField27.setText("");
                jTextField28.setText("");
                jComboBox4.setSelectedIndex(0);
            } catch (SQLException err) {
                JOptionPane.showMessageDialog(null, err.getMessage());
            }
        } else {
            try {
                String sql = "INSERT INTO product_selected VALUE ('" + jTextField21.getText() + "', '" + jComboBox4.getSelectedItem().toString() + "', '" + jTextField24.getText() + "', '" + jTextField27.getText() + "', '" + jTextField28.getText() + "')";
                db_connection.getconnection().prepareStatement(sql).executeUpdate();
                GetSelectedProductData();
                jTextField24.setText("");
                jTextField25.setText("");
                jTextField26.setText("");
                jTextField27.setText("");
                jTextField28.setText("");
                jComboBox4.setSelectedIndex(0);
            } catch (SQLException err) {
                JOptionPane.showMessageDialog(null, err.getMessage());
            }
        }
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jTextField27CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField27CaretUpdate
        int a = Integer.valueOf(jTextField25.getText());
        int b = Integer.valueOf(jTextField26.getText());
        int c = Integer.valueOf(jTextField27.getText());
        if (c <= a) {
            jTextField28.setText(String.valueOf(b * c));
        } else {
            jTextField28.setText("");
        }
    }//GEN-LAST:event_jTextField27CaretUpdate

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        try {
            Statement stat = (Statement) db_connection.getconnection().createStatement();
            String sql = "SELECT * FROM product WHERE pcode = '" + jComboBox4.getSelectedItem() + "'";
            ResultSet res = stat.executeQuery(sql);

            while (res.next()) {
                jTextField24.setText(res.getString("pname"));
                jTextField25.setText(res.getString("pamount"));
                jTextField26.setText(res.getString("pprice"));
            }
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        try {
            HashMap map = new HashMap();
            map.put("trcode", jTextField21.getText());
            JasperPrint jp = JasperFillManager.fillReport(getClass().getResourceAsStream("product_selected.jasper"), map, db_connection.getconnection());
            JasperExportManager.exportReportToPdfFile(jp, "product_selected.pdf");
        } catch (JRException e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_jButton25ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(electronic_store.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new electronic_store().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
