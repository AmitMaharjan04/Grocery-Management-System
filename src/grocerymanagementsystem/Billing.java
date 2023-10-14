/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grocerymanagementsystem;

import connector.DatabaseConnection;
import java.awt.print.PrinterException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
/**
 *
 * @author SuMiT
 */
public class Billing extends javax.swing.JFrame {
    public int finalTotal=0;
    /**
     * Creates new form Billing
     */
    Date date;
    String username;
    Connection con=null;
    Statement stmt=null;
    PreparedStatement pstmt=null;
    ResultSet rs=null;
    
    public Billing(String uname) {
        initComponents();
       username=uname;
        SimpleDateFormat dFormat=new SimpleDateFormat("yyyy-MM-dd");
         date=new Date();
        jLabel5.setText(dFormat.format(date));
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now=LocalDateTime.now();
        jLabel7.setText(dtf.format(now));
        ProductDetails();
    }
    public Billing(){
        initComponents();
        
    }
    
     public void ProductDetails(){
    try{  
            con=DatabaseConnection.getCon();
            stmt=con.createStatement();
            rs=stmt.executeQuery("select * from product");
//            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            while(rs.next()){
            String id=String.valueOf(rs.getInt("id"));
            String name=String.valueOf(rs.getString("name"));
            String rate=String.valueOf(rs.getInt("rate"));
            String quantity=String.valueOf(rs.getInt("quantity"));
            String category=String.valueOf(rs.getString("category"));
            String tableData[]={id,name,rate,quantity,category};
            DefaultTableModel tbModel=(DefaultTableModel)jTable1.getModel();
            tbModel.addRow(tableData);
            tableHeader2();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
     
    public void QuantityReduce(){
        //need to do for multiple tables as many producta and quantity is reduced
        DefaultTableModel tbModel=(DefaultTableModel)jTable2.getModel();
       int n=tbModel.getRowCount();
       String soldQuantity[]=new String[100];
       for(int i=0;i<n;i++){
            soldQuantity[i]=(tbModel.getValueAt(i,3)).toString();
       }
       String productName[]=new String[100];
       for(int i=0;i<n;i++){
            productName[i]=(tbModel.getValueAt(i,1)).toString();
       }
       
        for(int i=0;i<n;i++){
        String query="UPDATE product set quantity=quantity-? where name=?";
         
        try{
          Connection con=DatabaseConnection.getCon();
          PreparedStatement pstmt=con.prepareStatement(query);
          pstmt.setInt(1,Integer.parseInt(soldQuantity[i]));
          pstmt.setString(2,productName[i]);
          pstmt.executeUpdate();
          pstmt.close();
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        }
    }
    public void Logs(){
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String currentDate=dateFormat.format(date);
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now=LocalDateTime.now();
        String currentTime=dtf.format(now);
       
        String query="INSERT into logs(username,date,time,total) VALUES(?,?,?,?)";
        try{
          Connection con=DatabaseConnection.getCon();
          PreparedStatement pstmt=con.prepareStatement(query);
          pstmt.setString(1,"admin");
          pstmt.setString(2, currentDate);
          pstmt.setString(3, currentTime);
          pstmt.setString(4, finalTotal1);
          int rs=pstmt.executeUpdate();
          pstmt.close();
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void Sales(){
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String currentDate=dateFormat.format(date);
        
       DefaultTableModel tbModel=(DefaultTableModel)jTable2.getModel();
       int n=tbModel.getRowCount();
       String soldQuantity[]=new String[100];
       for(int i=0;i<n;i++){
            soldQuantity[i]=(tbModel.getValueAt(i,3)).toString();
       }
       String productName[]=new String[100];
       for(int i=0;i<n;i++){
            productName[i]=(tbModel.getValueAt(i,1)).toString();
       }
       
       for(int i=0;i<n;i++){
//       String sql="select *from sales where product_name=?";
String query="insert into sales(product_name,sold_quantity,date) values(?,?,?)";
       try{
          Connection con=DatabaseConnection.getCon();
          PreparedStatement pstmt=con.prepareStatement(query);
        //  pstmt.setString(1,productName[i]);
//          ResultSet rs=pstmt.executeQuery();
          //if(rs.next()==false){
//             String query="insert into sales(product_name,sold_quantity,date) values(?,?,?)";
           //  pstmt=con.prepareStatement(query);
                pstmt.setString(1,productName[i]);
                pstmt.setString(2, soldQuantity[i]);
                pstmt.setString(3, currentDate);
                pstmt.executeUpdate();
                pstmt.close();
                con.close();
          }catch(Exception e){
            e.printStackTrace();
        }
//          else{
//               String query="update sales set sold_quantity=sold_quantity+? where product_name=?";
//               pstmt=con.prepareStatement(query);
//                pstmt.setInt(1,Integer.parseInt(soldQuantity[i]));
//                pstmt.setString(2,productName[i]);
//                pstmt.executeUpdate();
//                pstmt.close();
//          }
//            con.close();
//        }catch(Exception e){
//            e.printStackTrace();
//        }
       }
    }
    
    public void billGenerate(){
        JTextArea textArea=new JTextArea();
        DefaultTableModel tbModel=(DefaultTableModel)jTable2.getModel();
        textArea.setText(textArea.getText()+"\t                                 SHOP RECEIPT\n\n");
        textArea.setText(textArea.getText()+"\t                                    R. STORE\n");
        textArea.setText(textArea.getText()+"\t                             Kalimati, Kathmandu\n");
        textArea.setText(textArea.getText()+"\t                                    01-4444444\n\n\n");
        textArea.setText(textArea.getText()+"Date: "+jLabel5.getText()+" on "+jLabel7.getText()+"\n");
//        textArea.setText(textArea.getText()+"Cashier: "+username+"\n");
        textArea.setText(textArea.getText()+"Cashier: admin\n");
        textArea.setText(textArea.getText()+"----------------------------------------------------------------------------------------------------------------------\n");
        textArea.setText(textArea.getText()+"No."+"\tPRODUCT"+"\t\tPRICE"+"\tQUANTITY"+"\tTOTAL\n");
//        textArea.setText(textArea.getText()+"--------------------------------------------------------------\n");
        textArea.setText(textArea.getText()+"----------------------------------------------------------------------------------------------------------------------\n");
        for(int i=0;i<tbModel.getRowCount();i++){
            String num=tbModel.getValueAt(i,0).toString();
            String name=tbModel.getValueAt(i,1).toString();
            String price=tbModel.getValueAt(i,2).toString();
            String quantity=tbModel.getValueAt(i,3).toString();
            String total=tbModel.getValueAt(i,4).toString();
            if(name.length()>10){
                            textArea.setText(textArea.getText()+num+"\t"+name+"\t"+price+"\t"+quantity+"\t"+total+"\n");

            }else{
                            textArea.setText(textArea.getText()+num+"\t"+name+"\t\t"+price+"\t"+quantity+"\t"+total+"\n");

            }
        }  
        textArea.setText(textArea.getText()+"----------------------------------------------------------------------------------------------------------------------\n");
        textArea.setText(textArea.getText()+"Total                                                                                                               "+jTextField6.getText()+"\n");
        textArea.setText(textArea.getText()+"Discount                                                                                                          0.00\n");
        int total=Integer.parseInt(jTextField6.getText());
        int discount=0;
        int gTotal=total+discount;
        textArea.setText(textArea.getText()+"Grand Total                                                                                                    "+gTotal+"\n");
         textArea.setText(textArea.getText()+"----------------------------------------------------------------------------------------------------------------------\n");
        
        textArea.setText(textArea.getText()+"Cash Received                                                                                               "+jTextField8.getText()+"\n");
        textArea.setText(textArea.getText()+"Cash Returned                                                                                               "+jTextField7.getText()+"\n\n");
         textArea.setText(textArea.getText()+"----------------------------------------------------------------------------------------------------------------------\n");
        
       textArea.setText(textArea.getText()+"\n                                                   Thank you for visiting us\n");
         textArea.setText(textArea.getText()+"                                                       Have A Nice Day\n");
       try {
            // TODO add your handling code here:
            textArea.print();
        } catch (PrinterException ex) {
            Logger.getLogger(Billing.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        QuantityReduce();
        Logs();
        Sales();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(0, 51, 55));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Close_30px.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(51, 0, 55));

        jButton9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Logout_60px.png"))); // NOI18N
        jButton9.setText("Logout");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/undo_52px.png"))); // NOI18N
        jButton10.setText("Go Back");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jButton9)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton10)
                .addGap(48, 48, 48)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 48)); // NOI18N
        jLabel1.setText("BIlling System");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cash_receipt_80px.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("Date");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setText("jLabel3");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setText("Time");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setText("jLabel3");

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel8.setText("Product Details");

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel14.setText("Product Name ");

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel15.setText("Quantity");

        jTextField2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jButton1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel10.setText("Calculation Details");

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel11.setText("Total");

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel12.setText("Paid Amount");

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel17.setText("Return Amount");

        jTextField6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jTextField7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jTextField8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton2.setText("Print");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton4.setText("Reset");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Product Name", "Price", "Quantity", "Category"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Num", "Product Name", "Price", "Quantity", "Total"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setText("Filter by");

        jComboBox1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Convenience", "Fats and oils", "Legumes", "Household", "Sauce and soups", "Snacks", "Beverages", "Dairy" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton5.setText("Search");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton6.setText("Reset");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7))
                .addGap(102, 102, 102))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel15)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1)
                            .addComponent(jLabel14))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 37, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(240, 240, 240)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(70, 70, 70)
                                                .addComponent(jButton4))
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(131, 131, 131)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(jButton5)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator3)
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(66, 66, 66)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(453, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6)))
                            .addComponent(jLabel1))))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5))
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(89, 89, 89))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jButton4))
                .addGap(29, 29, 29))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(425, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel4))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        int exit=JOptionPane.showConfirmDialog(null,"Do you want to exit application","Select",JOptionPane.YES_NO_OPTION);
        if(exit==0){
            System.exit(0);
        }
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        new Login().setVisible(true);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        new HomePage().setVisible(true);
    }//GEN-LAST:event_jButton10ActionPerformed
 public void tableHeader(){
       TableColumn col1=jTable2.getColumnModel().getColumn(0);
       col1.setPreferredWidth(90);
       TableColumn col2=jTable2.getColumnModel().getColumn(1);
       col2.setPreferredWidth(190);
       TableColumn col3=jTable2.getColumnModel().getColumn(2);
       col3.setPreferredWidth(90);
       TableColumn col4=jTable2.getColumnModel().getColumn(3);
       col4.setPreferredWidth(90);
       TableColumn col5=jTable2.getColumnModel().getColumn(4);
       col5.setPreferredWidth(90);
   }
 public void tableHeader2(){
       TableColumn col1=jTable1.getColumnModel().getColumn(0);
       col1.setPreferredWidth(60);
       TableColumn col2=jTable1.getColumnModel().getColumn(1);
       col2.setPreferredWidth(180);
       TableColumn col3=jTable1.getColumnModel().getColumn(2);
       col3.setPreferredWidth(90);
       TableColumn col4=jTable1.getColumnModel().getColumn(3);
       col4.setPreferredWidth(90);
       TableColumn col5=jTable1.getColumnModel().getColumn(4);
       col5.setPreferredWidth(130);
   }
    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
//         String name=jTextField2.getText();
//        try{
//            Connection con=DatabaseConnection.getCon();
//            Statement stmt=con.createStatement();
//            ResultSet rs=stmt.executeQuery("select * from product where name='"+name+"'");
//            if(rs.next()){
//                jTextField1.setText(rs.getString(1));
//                jTextField3.setText(rs.getString(3));
//                jTextField4.setText("1");
//                jTextField5.setText(rs.getString(5));
//            }else{
//                jTextField1.setText("");
//                jTextField3.setText("");
//                jTextField4.setText("");
//                jTextField5.setText("");
//            }
//        }catch(Exception e){
//            JOptionPane.showMessageDialog(null, e); 
//        }
    }//GEN-LAST:event_jTextField2ActionPerformed

    int i=0;
    String finalTotal1;
    int total;
    // added variables to put into Logs.java
        String productName;
        int soldQuantity;
       int id=0;
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
      // int price=Integer.parseInt(jTextField3.getText());
      DefaultTableModel tbModel1=(DefaultTableModel)jTable1.getModel();
      int myIndex=jTable1.getSelectedRow();
       int price=Integer.valueOf(tbModel1.getValueAt(myIndex,2).toString());
        int quantity=Integer.parseInt(jTextField4.getText());
        total=price*quantity;
        String product=jTextField2.getText();
        id=id+1;
        String tableData[]={""+id,product,""+price,""+quantity,""+total};
        DefaultTableModel tbModel=(DefaultTableModel)jTable2.getModel();
        tbModel.addRow(tableData);
        tableHeader();
        finalTotal=finalTotal+total;
        finalTotal1=String.valueOf(finalTotal);
        jTextField6.setText(finalTotal1);
        
        
//      if(jTextField2.getText().isEmpty() || jTextField4.getText().isEmpty()){
//           JOptionPane.showMessageDialog(this, "Missing information");
//       }else{
//           i++;
//           if(i==1){
//               jTextArea1.setText(jTextArea1.getText()+"\t----------------BILLING SYSTEM----------------\n"+"NUM\t      PRODUCT\t       PRICE\t     QUANTITY\t      TOTAL\n"+i+"\t      "+jTextField2.getText()+"\t     "+jTextField3.getText()+"\t     "+jTextField4.getText()+"\t       "+total);
//           }else{
//               
//               jTextArea1.setText(jTextArea1.getText()+"\n"+i+"\t      "+jTextField2.getText()+"\t     "+jTextField3.getText()+"\t     "+jTextField4.getText()+"\t       "+total);
//           }
//           //test
//          
//           
//        productName=jTextField2.getText();
//        soldQuantity=Integer.parseInt(jTextField4.getText());
//       }
//       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
        String paidAmount=jTextField8.getText();
        int a=Integer.parseInt(paidAmount);
        finalTotal=a-finalTotal;
        String finalTotal1=String.valueOf(finalTotal);
        jTextField7.setText(finalTotal1);
        jTextField7.setEditable(false);
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        new Billing().setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
//         jTextArea1.setText(jTextArea1.getText()+"\n--------------------------------------------------");
//        jTextArea1.setText(jTextArea1.getText()+"\nThank you for visiting us");
        billGenerate();
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField6ActionPerformed
    int price;
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        DefaultTableModel tbModel=(DefaultTableModel)jTable1.getModel();
         int myIndex=jTable1.getSelectedRow();
         jTextField2.setText(tbModel.getValueAt(myIndex,1).toString());
//         price=Integer.valueOf(tbModel.getValueAt(myIndex,3).toString());
         jTextField4.setText("");
    }//GEN-LAST:event_jTable1MouseClicked

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        String catName = jComboBox1.getSelectedItem().toString();
        String text="All";
        DefaultTableModel tbModel=(DefaultTableModel)jTable1.getModel();
        if(catName.equals(text)){
            String query="select * from product";
            try{
                con=DatabaseConnection.getCon();
                PreparedStatement pstmt=con.prepareStatement(query);
                ResultSet rs=pstmt.executeQuery();
                tbModel.setRowCount(0);
               
                while(rs.next()){
                    String id=String.valueOf(rs.getInt("id"));
                    String name=String.valueOf(rs.getString("name"));
                    String rate=String.valueOf(rs.getInt("rate"));
                    String quantity=String.valueOf(rs.getInt("quantity"));
                    String category=String.valueOf(rs.getString("category"));
                    
                    String tableData[]={id,name,rate,quantity,category};
                    tbModel.addRow(tableData);;
                    tableHeader2();
                }

            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            String query="select * from product where category=?";
            try{
                con=DatabaseConnection.getCon();
                PreparedStatement pstmt=con.prepareStatement(query);
                pstmt.setString(1, catName);
                ResultSet rs=pstmt.executeQuery();
                tbModel.setRowCount(0);
               
                while(rs.next()){
                    String id=String.valueOf(rs.getInt("id"));
                    String name=String.valueOf(rs.getString("name"));
                    String rate=String.valueOf(rs.getInt("rate"));
                    String quantity=String.valueOf(rs.getInt("quantity"));
                    String category=String.valueOf(rs.getString("category"));
                    
                    String tableData[]={id,name,rate,quantity,category};
                    tbModel.addRow(tableData);;
                    tableHeader2();
                }

            }catch(Exception e){
                e.printStackTrace();
            }
        }
//        if(catName=="none"){
//            ProductDetails();
//        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        jTextField2.setText("");
        jTextField4.setText("");
    }//GEN-LAST:event_jButton6ActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Billing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Billing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Billing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Billing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Billing().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    // End of variables declaration//GEN-END:variables
}
