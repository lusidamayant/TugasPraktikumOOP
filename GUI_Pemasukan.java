/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugasrumah10;

import exception.*;
import cobainterface.*;
import bab7.*;
import TUGASBAB1.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lusi Damayanti
 */
public class GUI_Pemasukan extends javax.swing.JFrame {

    int no_pemasukan;
    String jenis_pemasukan,tanggal, pilih, keterangan;
    int total_saldo, nominsl, saldo, itemPemasukan;
    int sisa_saldo;
    
    /**
     * Creates new form GUI_Pemasukan
     */
    public GUI_Pemasukan() {
        initComponents();
        tampil();
        getSaldo();
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        txt_saldo.setText("Saldo : "+String.valueOf(formatter.format((saldo))));
    }
    public Connection conn;

    public void refresh() {
        new GUI_Pemasukan().setVisible(true);
        this.setVisible(false);
    }

    public void koneksi() throws SQLException {
        try {
            conn = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/oop_kas?user=root&password=");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GUI_Pemasukan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            Logger.getLogger(GUI_Pemasukan.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception es) {
            Logger.getLogger(GUI_Pemasukan.class.getName()).log(Level.SEVERE, null, es);
        }
    }
    
    public void getSaldo(){
        try{
            koneksi();
            String sql = "SELECT * FROM setting where nama = 'saldo'";
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery(sql);
            while(res.next()){
                saldo = Integer.parseInt(res.getString("nilai"));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    public void tampil() {
        DefaultTableModel tabelhead = new DefaultTableModel();
        tabelhead.addColumn("No Pemasukan");
        tabelhead.addColumn("Jenis Pemasukan");
        tabelhead.addColumn("Tanggal");
        tabelhead.addColumn("Keterangan");
        tabelhead.addColumn("Sisa Saldo");
        tabelhead.addColumn("Nominal Pemasukan");
        tabelhead.addColumn("Total Saldo");

        try {
            koneksi();
            String sql = "SELECT * FROM laporan_pemasukan";
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery(sql);
            while (res.next()) {
                tabelhead.addRow(new Object[]{res.getString("no_pemasukan"), res.getString("jenis_pemasukan"), res.getString("tanggal"), res.getString("keterangan"), res.getString("sisa_saldo"), res.getString("Nominal_pemasukan"), res.getString("total_saldo")});
            }
            tabelpemasukan.setModel(tabelhead);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void insert() {
        int no = Integer.parseInt(txtno.getText());
        String tanggal = txttanggal.getText();
        String keterangan = txtket.getText();
        int nominal = Integer.parseInt(txtnominal.getText());
        int sisa_saldo = total_saldo;
        String jenisText = "";
        String selectedjenis = (String) cmb_pilih.getSelectedItem();
        if (selectedjenis.equals("Anggota")) {
            jenisText = "Anggota";
        } else if (selectedjenis.equals("Manager")) {
            jenisText = "Manager";
        } else if (selectedjenis.equals("Sekertaris")) {
            jenisText = "Sekertaris";
        }
        try {
            koneksi();
            Statement statement = conn.createStatement();
            sisa_saldo = saldo;
            total_saldo = saldo+nominal;
            statement.executeUpdate("INSERT INTO laporan_pemasukan (no_pemasukan , jenis_pemasukan, tanggal,keterangan, sisa_saldo, nominal_pemasukan, total_saldo) "
                    + "VALUES('" + no + "','"+ jenisText+"','"+ tanggal + "','" + keterangan +"','"+ sisa_saldo+"','" + nominal + "','" +total_saldo+"')");
            statement.executeUpdate("UPDATE setting SET nilai = "+total_saldo+" where nama = 'saldo'");
            statement.close();
            JOptionPane.showMessageDialog(null, "Berhasil Memasukan Data pemasukan!" + "\n" + no);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        refresh();
    }

    public void delete() {
        int ok = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin akan menghapus data ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            try {
                int newSaldo = saldo - Integer.parseInt(txtnominal.getText());
                String query = "UPDATE setting SET nilai = '"+newSaldo+"' where nama = 'saldo'";
                Statement stat = conn.createStatement();
                stat.executeUpdate(query);
                String sql = "DELETE FROM laporan_pemasukan WHERE no_pemasukan='" + txtno.getText() + "'";
                java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil di hapus");
                batal();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data gagal di hapus");
            }
        }
        refresh();
    }

    public void batal() {
        txtno.setText("");
        txttanggal.setText("");
        txtket.setText("");
        txtnominal.setText("");
    }

    void itempilih() {
        txtno.setText(String.valueOf(no_pemasukan));
        cmb_pilih.setSelectedItem(itemPemasukan);
        txttanggal.setText(String.valueOf(tanggal));
        txtket.setText(keterangan);
        txtnominal.setText(String.valueOf(nominsl));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtno = new javax.swing.JTextField();
        txttanggal = new javax.swing.JTextField();
        txtket = new javax.swing.JTextField();
        txtnominal = new javax.swing.JTextField();
        cmb_pilih = new javax.swing.JComboBox<>();
        btnsimpan = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelpemasukan = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        txt_saldo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Pemasukan Kas");

        jLabel2.setText("No Pemasukan ");

        jLabel3.setText("Jenis Pemasukan");

        jLabel4.setText("Tanggal");

        jLabel5.setText("Keterangan");

        jLabel7.setText("Nominal Pemasukan");

        cmb_pilih.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Anggota", "Manager", "Sekertaris" }));
        cmb_pilih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_pilihActionPerformed(evt);
            }
        });

        btnsimpan.setText("Simpan");
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });

        tabelpemasukan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "no_pemasukan", "jenis_pemasukan", "tanggal", "keterangan", "sisa_saldo", "nominal_pemasukan", "total_saldo"
            }
        ));
        tabelpemasukan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelpemasukanMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelpemasukan);

        jButton1.setText("Hapus");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txt_saldo.setText("Saldo : ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                            .addComponent(txtnominal, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtket, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txttanggal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmb_pilih, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnsimpan)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 775, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_saldo)
                        .addGap(190, 190, 190))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txt_saldo)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cmb_pilih, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txttanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtnominal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnsimpan)
                            .addComponent(jButton1)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(79, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        // TODO add your handling code here:
        // Membuat objek Pemasukan dengan parameter
//    Tampilan.setText("");
//    // Membuat objek Pemasukan dengan parameter
//    int no = Integer.parseInt(txtno.getText());
//    String tanggal = txttanggal.getText();
//    String keterangan = txtket.getText();
//    int nominal = Integer.parseInt(txtnominal.getText());
//    int sisa_saldo = Integer.parseInt(txtsisa.getText());
//    String jenisText = "";
//    String selectedjenis = (String) cmb_pilih.getSelectedItem();
//    if (selectedjenis.equals("Anggota")) {
//        jenisText = "Anggota";
//    } else if (selectedjenis.equals("Manager")) {
//        jenisText = "Manager";
//    } else if (selectedjenis.equals("Sekertaris")) {
//        jenisText = "Sekertaris";
//    }
//
//    Pemasukan masuk = new Pemasukan("", tanggal, "", keterangan, "masuk", nominal, no, jenisText, 0, sisa_saldo); 
//    // Menampilkan informasi pemasukan
//
//    Tampilan.append("\n\t Pemasukan Kas \n");
//    Tampilan.append("------------------------------------------------------\n");
//    Tampilan.append("No Pemasukan  : " + masuk.no_pemasukan + "\n");
//    Tampilan.append("Jenis Pemasukan : " + masuk.jenis_pemasukan + "\n");
//    Tampilan.append("Tanggal : " + masuk.Tanggal + "\n");
//    Tampilan.append("Keterangan : " + masuk.Keterangan + "\n");
//    Tampilan.append("Sisa Saldo Rp: " + masuk.getsisasaldo() + "\n");
//    Tampilan.append("Nominal Pemasukan Rp: " + masuk.nominal + "\n");
//    Tampilan.append("Total Saldo Rp: " + masuk.total_saldo()+ "\n"); 
        // int total_saldo = masuk.total_saldo()
        //txttotal.setText(Integer.toString(total_saldo));
//
//    
//
//
//
        insert();
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void tabelpemasukanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelpemasukanMouseClicked
        // TODO add your handling code here:
        int tabel = tabelpemasukan.getSelectedRow();
        no_pemasukan = Integer.parseInt(tabelpemasukan.getValueAt(tabel, 0).toString());
        jenis_pemasukan = tabelpemasukan.getValueAt(tabel, 1).toString();
        if(jenis_pemasukan.equalsIgnoreCase("anggota")){
            itemPemasukan = 1;
        }else if(jenis_pemasukan.equalsIgnoreCase("sekretaris")){
            itemPemasukan = 2;
        }else{
            itemPemasukan = 3;
        }
        tanggal = tabelpemasukan.getValueAt(tabel, 2).toString();
        keterangan = tabelpemasukan.getValueAt(tabel, 3).toString();
        nominsl = Integer.parseInt(tabelpemasukan.getValueAt(tabel, 5).toString());

        itempilih();

    }//GEN-LAST:event_tabelpemasukanMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cmb_pilihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_pilihActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_pilihActionPerformed

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
            java.util.logging.Logger.getLogger(GUI_Pemasukan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_Pemasukan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_Pemasukan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_Pemasukan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold
        //</editor-fold
        //</editor-fold
        //</editor-fold
        //</editor-fold
        //</editor-fold
        //</editor-fold
        //</editor-fold
        //</editor-fold
        //</editor-fold
        //</editor-fold
        //</editor-fold
        //</editor-fold
        //</editor-fold
        //</editor-fold
        //</editor-fold

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_Pemasukan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnsimpan;
    private javax.swing.JComboBox<String> cmb_pilih;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabelpemasukan;
    private javax.swing.JLabel txt_saldo;
    private javax.swing.JTextField txtket;
    private javax.swing.JTextField txtno;
    private javax.swing.JTextField txtnominal;
    private javax.swing.JTextField txttanggal;
    // End of variables declaration//GEN-END:variables

    private int total_saldo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
