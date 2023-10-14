/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grocerymanagementsystem;

import connector.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SuMiT
 */
public class Sales extends javax.swing.JFrame {

    /**
     * Creates new form Sales
     */
    Connection con=null;
    public Sales() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(0, 51, 55));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Close_30px.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
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
            .addComponent(jButton9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton10)
                .addGap(68, 68, 68)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Transactions ");

        jTable1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S. No", "Product Name", "Sold Quantity", "Date"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("Select to see high sales");

        jButton1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Today", "Yesterday", "This Month", "Past Month", "This Year" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(323, 323, 323)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(jButton1))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(98, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jButton1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel5))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        int exit=JOptionPane.showConfirmDialog(null,"Do you want to exit application","Select",JOptionPane.YES_NO_OPTION);
        if(exit==0){
            System.exit(0);
        }
    }//GEN-LAST:event_jLabel5MouseClicked

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String sale = jComboBox1.getSelectedItem().toString();
         if(sale.equals("Today")==true){
                today();
         }
         if(sale.equals("Yesterday")==true){
                yesterday();
         }
         if(sale.equals("This Month")==true){
            thisMonth();
         }
         if(sale.equals("Past Month")==true){
            pastMonth();
         }
         if(sale.equals("This Year")==true){
            thisYear();
         }
       /* 
        DefaultTableModel tbModel=(DefaultTableModel)jTable1.getModel();
        if(sale.equals("Today")==true){
            int id=0;
//            String query="select * from sales where date=? order by sold_quantity asc";
        String query="select product_name,sum(sold_quantity) as sold_quantity,date from sales where date=? group by product_name order by sold_quantity desc";
            Date d=new Date();
            SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
            String date1=dateFormat.format(d);
            try{
                con=DatabaseConnection.getCon();
                PreparedStatement pstmt=con.prepareStatement(query);
                pstmt.setString(1,date1);
                ResultSet rs=pstmt.executeQuery();
                tbModel.setRowCount(0);
                while(rs.next()){
                    id++;
                    String name=String.valueOf(rs.getString("product_name"));
                    String quantity=String.valueOf(rs.getInt("sold_quantity"));
                    String date=String.valueOf(rs.getDate("date"));

                    String tableData[]={String.valueOf(id),name,quantity,date};
                    tbModel.addRow(tableData);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }*/
    }//GEN-LAST:event_jButton1ActionPerformed
    public void today(){
         DefaultTableModel tbModel=(DefaultTableModel)jTable1.getModel();
        int id=0;
//            String query="select * from sales where date=? order by sold_quantity asc";
        String query="select product_name,sum(sold_quantity) as sold_quantity,date from sales where date=? group by product_name order by sold_quantity desc";
            Date d=new Date();
            SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
            String date1=dateFormat.format(d);
            try{
                con=DatabaseConnection.getCon();
                PreparedStatement pstmt=con.prepareStatement(query);
                pstmt.setString(1,date1);
                ResultSet rs=pstmt.executeQuery();
                tbModel.setRowCount(0);
                while(rs.next()){
                    id++;
                    String name=String.valueOf(rs.getString("product_name"));
                    String quantity=String.valueOf(rs.getInt("sold_quantity"));
                    String date=String.valueOf(rs.getDate("date"));

                    String tableData[]={String.valueOf(id),name,quantity,date};
                    tbModel.addRow(tableData);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        
    }
    public void yesterday(){
    DefaultTableModel tbModel=(DefaultTableModel)jTable1.getModel();
            int id=0;
        String query="select product_name,sum(sold_quantity) as sold_quantity,date from sales where date=? group by product_name order by sold_quantity desc";
             Date d=new Date();
             SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
             String day=dayFormat.format(d);
         int day1=Integer.parseInt(day);
         day1--;
         String date="2022-08-".concat(String.valueOf(day1));
            try{  
            con=DatabaseConnection.getCon();
            PreparedStatement pstmt=con.prepareStatement(query);
            pstmt.setString(1,date);
            ResultSet rs=pstmt.executeQuery();
            tbModel.setRowCount(0);
            while(rs.next()){
                id++;
                    String name=String.valueOf(rs.getString("product_name"));
                    String quantity=String.valueOf(rs.getInt("sold_quantity"));
                    String date1=String.valueOf(rs.getDate("date"));

                    String tableData[]={String.valueOf(id),name,quantity,date1};
                    tbModel.addRow(tableData);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            
    }
    public void thisMonth(){
    DefaultTableModel tbModel=(DefaultTableModel)jTable1.getModel();
            int id=0;
        String query="select product_name,sum(sold_quantity) as sold_quantity,date from sales where date between ? and ? group by product_name order by sold_quantity desc";
       
             Date d=new Date();
             SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
             String currentMonth=monthFormat.format(d);
         String currentDate="2022-".concat(String.valueOf(currentMonth)).concat("-31");
         String date="2022-".concat(String.valueOf(currentMonth)).concat("-01");
            
            try{  
            con=DatabaseConnection.getCon();
            PreparedStatement pstmt=con.prepareStatement(query);
            pstmt.setString(1,date);
            pstmt.setString(2,currentDate);
            ResultSet rs=pstmt.executeQuery();
            tbModel.setRowCount(0);
            while(rs.next()){
                 id++;
                    String name=String.valueOf(rs.getString("product_name"));
                    String quantity=String.valueOf(rs.getInt("sold_quantity"));
                    String date1=String.valueOf(rs.getDate("date"));

                    String tableData[]={String.valueOf(id),name,quantity,date1};
                    tbModel.addRow(tableData);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
    }
    public void pastMonth(){
    DefaultTableModel tbModel=(DefaultTableModel)jTable1.getModel();
            int id=0;
        String query="select product_name,sum(sold_quantity) as sold_quantity,date from sales where date between ? and ? group by product_name order by sold_quantity desc";
             Date d=new Date();
             SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
             String currentMonth=monthFormat.format(d);
         int month1=Integer.parseInt(currentMonth);
         month1--;
         String currentDate="2022-0".concat(String.valueOf(month1)).concat("-31");
         String date="2022-0".concat(String.valueOf(month1)).concat("-01");
            
            try{  
            con=DatabaseConnection.getCon();
            PreparedStatement pstmt=con.prepareStatement(query);
            pstmt.setString(1,date);
            pstmt.setString(2,currentDate);
            ResultSet rs=pstmt.executeQuery();
            tbModel.setRowCount(0);
            while(rs.next()){
                 id++;
                    String name=String.valueOf(rs.getString("product_name"));
                    String quantity=String.valueOf(rs.getInt("sold_quantity"));
                    String date1=String.valueOf(rs.getDate("date"));

                    String tableData[]={String.valueOf(id),name,quantity,date1};
                    tbModel.addRow(tableData);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
    }
    public void thisYear(){
    DefaultTableModel tbModel=(DefaultTableModel)jTable1.getModel();
            int id=0;
        String query="select product_name,sum(sold_quantity) as sold_quantity,date from sales where date between ? and ? group by product_name order by sold_quantity desc";
       
             Date d=new Date();
             SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
             String year=yearFormat.format(d);
         String currentDate="".concat(String.valueOf(year)).concat("-12-31");
         String date="".concat(String.valueOf(year)).concat("-01-01");
            try{  
            con=DatabaseConnection.getCon();
            PreparedStatement pstmt=con.prepareStatement(query);
            pstmt.setString(1,date);
            pstmt.setString(2,currentDate);
            ResultSet rs=pstmt.executeQuery();
            tbModel.setRowCount(0);
            while(rs.next()){
                   id++;
                    String name=String.valueOf(rs.getString("product_name"));
                    String quantity=String.valueOf(rs.getInt("sold_quantity"));
                    String date1=String.valueOf(rs.getDate("date"));

                    String tableData[]={String.valueOf(id),name,quantity,date1};
                    tbModel.addRow(tableData);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
    }
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

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
            java.util.logging.Logger.getLogger(Sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sales().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
