/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugasrumah10;

import exception.*;
import cobainterface.*;
import bab7.*;
import coba.*;
import TUGASBAB1.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Lusi Damayanti
 */
public class GUI_KAS extends javax.swing.JFrame {

    private int total_saldo = 50000000;

    /**
     * Creates new form GUI_Kasku
     */
    public GUI_KAS() {
        initComponents();
        txtPajak.setEnabled(false);
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);
        
        txtSaldo.setText("Saldo : "+kursIndonesia.format(total_saldo));
        tampil();
        

    }
    public Connection conn;
    
     public void refresh() {
        new GUI_KAS().setVisible(true);
        this.setVisible(false);
    }
     String nama;
    String Tanggal;
    String Keterangan,sumberkas;
    String Tipe_mutasi;
    int nominal, pajak, nominalPajak, totalNominal,id;
    String nilai_matauang;
         public void koneksi()throws SQLException{
        try {
            conn = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/oop_kas?user=root&password=");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GUI_KAS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            Logger.getLogger(GUI_KAS.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception es) {
            Logger.getLogger(GUI_KAS.class.getName()).log(Level.SEVERE, null, es);
        }
         }
         public void tampil() {
        DefaultTableModel tabelhead = new DefaultTableModel();
        tabelhead.addColumn("id ");
        tabelhead.addColumn("Nama ");
        tabelhead.addColumn("Tanggal");
        tabelhead.addColumn("Sumber Kas");
        tabelhead.addColumn("Ketrangan");
        tabelhead.addColumn("Nominal");
        tabelhead.addColumn("Tipe_Mutasi");
        tabelhead.addColumn("Pajak");
        tabelhead.addColumn("Total Nominal");
        try {
            koneksi();
            String sql = "SELECT * FROM kas";
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery(sql);
            while (res.next()) {
                tabelhead.addRow(new Object[]{res.getString("id"),res.getString("Nama"), res.getString("Tanggal"), res.getString("Sumber_Kas"), res.getString("Keterangan"), res.getString("Nominal"), res.getString("Tipe_Mutasi"), res.getString("Pajak"),res.getString("Total_Nominal"),});
            }
            tabelkas.setModel(tabelhead);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "BELUM TERKONEKSI");
        }
    }
    public void batal() {
        txtnama.setText("");
        txtket.setText("");
        txtnominal.setText("");
        txttanggal.setText("");
        txtsumber.setText("");
        buttonGroup1.clearSelection();
        cekPajak.setSelected(false);
        txtPajak.setText("");
        txtPajak.setEnabled(false);
    }
    public void insert() {
       // int pajak = 0;
        String nama = txtnama.getText();
     String tanggal = txttanggal.getText();
      String keterangan = txtket.getText();
       String sumberkas = txtsumber.getText();
       String pajak = txtPajak.getText();
       int totalNominal = total_saldo;
      int nominal = Integer.parseInt(txtnominal.getText());
        String Tipe_mutasi = "";
        if(radiobtnpem.isSelected()){
            Tipe_mutasi = radiobtnpem.getText();
        } else {
            Tipe_mutasi = radiobtnpeng.getText();
        }
        try {
            koneksi();
//            total_saldo = saldo + nominal;
            Statement statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO kas (Nama, Tanggal,Sumber_Kas, Keterangan, Nominal,Tipe_Mutasi,Pajak,Total_Nominal)"
                    + "VALUES('" + nama + "','" + tanggal + "','" + sumberkas + "','" + keterangan + "','" + nominal + "','" + Tipe_mutasi + "',"+ pajak+"',"+totalNominal+ "')");
            statement.close();
            JOptionPane.showMessageDialog(null, "Berhasil Memasukan Data Kas!" + "\n" + nama);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan Input: " );
        }
        refresh();
} 
    public void delete() {
        int ok = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin akan menghapus data ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            try {
              String sql = "DELETE FROM tb_Kas WHERE nama='" + txtnama.getText() + "'";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil di hapus");
                batal();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data gagal di hapus");
            }
        }
        refresh();
    }
    public void itempilih() {
        txtnama.setText(nama);
        txttanggal.setText(Tanggal);
        txtsumber.setText(sumberkas);
        txtket.setText(Keterangan);
        txtnominal.setText(Integer.toString(nominal));
        txtPajak.setText(Integer.toString(pajak));
        
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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtnama = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txttanggal = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtket = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        radiobtnpem = new javax.swing.JRadioButton();
        radiobtnpeng = new javax.swing.JRadioButton();
        txtnominal = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtPajak = new javax.swing.JTextField();
        cekPajak = new javax.swing.JCheckBox();
        txtsumber = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelkas = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnbatal = new javax.swing.JButton();
        BtnClose = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();
        btnsimpan = new javax.swing.JButton();
        txtSaldo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setText("Kas ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(511, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(321, 321, 321))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jLabel2.setText("Nama");

        jLabel3.setText("Tanggal");

        jLabel4.setText("Keterangan");

        jLabel5.setText("Tipe Mutasi");

        buttonGroup1.add(radiobtnpem);
        radiobtnpem.setText("Pemasukan");
        radiobtnpem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radiobtnpemActionPerformed(evt);
            }
        });

        buttonGroup1.add(radiobtnpeng);
        radiobtnpeng.setText("Pengeluaran");
        radiobtnpeng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radiobtnpengActionPerformed(evt);
            }
        });

        jLabel6.setText("Nominal");

        txtPajak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPajakActionPerformed(evt);
            }
        });

        cekPajak.setText("Pajak (%)");
        cekPajak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cekPajakActionPerformed(evt);
            }
        });

        jLabel7.setText("Sumber Kas ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtnama)
                    .addComponent(txttanggal)
                    .addComponent(txtket)
                    .addComponent(txtnominal)
                    .addComponent(txtPajak)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(371, 371, 371))
                    .addComponent(txtsumber)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cekPajak)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(radiobtnpem)
                                .addGap(18, 18, 18)
                                .addComponent(radiobtnpeng))
                            .addComponent(jLabel7))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel2)
                .addGap(2, 2, 2)
                .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txttanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtsumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtnominal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radiobtnpeng)
                    .addComponent(radiobtnpem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cekPajak)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPajak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        tabelkas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nama ", "Tanggal", "Sumber Kas", "Keterangan", "Nominal", "Jenis Mutasi", "Pajak (Rp)", "Total Nominal"
            }
        ));
        tabelkas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelkasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelkas);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 913, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        btnbatal.setText("Batal");
        btnbatal.setMinimumSize(new java.awt.Dimension(105, 35));
        btnbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbatalActionPerformed(evt);
            }
        });

        BtnClose.setText("Close");
        BtnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseActionPerformed(evt);
            }
        });

        btnhapus.setText("Hapus");
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });

        btnsimpan.setText("Simpan");
        btnsimpan.setMinimumSize(new java.awt.Dimension(105, 35));
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(btnsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(btnhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BtnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnbatal, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnbatal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnClose)
                    .addComponent(btnhapus)
                    .addComponent(btnsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        txtSaldo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtSaldo.setText("Saldo : ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtSaldo)
                        .addGap(17, 17, 17)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void radiobtnpemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radiobtnpemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radiobtnpemActionPerformed

    private void radiobtnpengActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radiobtnpengActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radiobtnpengActionPerformed

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        // TODO add your handling code here:
        insert();
//        String nama = txtnama.getText();
//        String tanggal = txttanggal.getText();
//        String keterangan = txtket.getText();
//        String sumberkas = txtsumber.getText();
//        int nominal = Integer.parseInt(txtnominal.getText());
//        String Tipe_mutasi = "";
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMMddHHmmss");
//        LocalDateTime now = LocalDateTime.now();
//        Random r = new Random();
//        int no = r.nextInt(5);
//        Kas kas = new Kas(nama, tanggal, sumberkas, keterangan, Tipe_mutasi, nominal);
//        kas.total_saldo = total_saldo;
//
//        if (radiobtnpem.isSelected()) {
//            Tipe_mutasi = radiobtnpem.getText();
//             //upcast
//            kas = new Pemasukan(nama, tanggal,  keterangan, sumberkas,Tipe_mutasi, nominal, no, "", total_saldo, total_saldo + kas.totalNominal);
//            //downcast
//            Pemasukan pemasukan = (Pemasukan) kas;
//            if (cekPajak.isSelected()) {
//                kas.pajak = Integer.parseInt(txtPajak.getText());
//                kas.cetakBuku(Integer.parseInt(txtPajak.getText()));
//            }
////             total_saldo = total_saldo + kas.totalNominal;
//              total_saldo = kas.hitSaldo("pemasukan", nominal, total_saldo);
//        } else {
//            Tipe_mutasi = radiobtnpeng.getText();
//            //upcast
//            kas = new Pengeluaran(nama, tanggal, keterangan,sumberkas, Tipe_mutasi, nominal, no, "", total_saldo, total_saldo - kas.totalNominal);
//            //downcast
//            Pengeluaran pengeluaran = (Pengeluaran) kas;
//            if (cekPajak.isSelected()) {
//                kas.pajak = Integer.parseInt(txtPajak.getText());
//                kas.cetakBuku(Integer.parseInt(txtPajak.getText()));
//            }
////            total_saldo = total_saldo - kas.totalNominal;
//              total_saldo = kas.hitSaldo("pengeluaran", nominal, total_saldo);
//        }
//        
//        DefaultTableModel model = (DefaultTableModel) tabelkas.getModel();
//        List data = new ArrayList<>();
//        
// kas.cetakBuku();
//        data = kas.getDataList();
//        model.addRow(data.toArray());
//        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
//        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
//
//        formatRp.setCurrencySymbol("Rp. ");
//        formatRp.setMonetaryDecimalSeparator(',');
//        formatRp.setGroupingSeparator('.');
//
//        kursIndonesia.setDecimalFormatSymbols(formatRp);
        
//        txtSaldo.setText("Saldo : "+kursIndonesia.format(kas.total_saldo));
        
       
         //masukkan method koneksi()
//    }
//    public void refresh() {
//        new GUI_KAS().setVisible(true);
//        this.setVisible(false);
//    }
//
//    String nama;
//    String Tanggal;
//    String Keterangan,sumberkas;
//    String Tipe_mutasi;
//    int nominal, pajak, nominalPajak, totalNominal;
//    String nilai_matauang;
//         public void koneksi()throws SQLException{
//        try {
//            conn = null;
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            conn = DriverManager.getConnection("jdbc:mysql://localhost/OOPuser=root&password=");
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(GUI_KAS.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException e) {
//            Logger.getLogger(GUI_KAS.class.getName()).log(Level.SEVERE, null, e);
//        } catch (Exception es) {
//            Logger.getLogger(GUI_KAS.class.getName()).log(Level.SEVERE, null, es);
//        }
//         }
//    public void tampil() {
//        DefaultTableModel tabelhead = new DefaultTableModel();
//        tabelhead.addColumn("Nama ");
//        tabelhead.addColumn("Tanggal");
//        tabelhead.addColumn("Sumber Kas");
//        tabelhead.addColumn("Ketrangan");
//        tabelhead.addColumn("Nominal");
//        tabelhead.addColumn("Tipe_Mutasi");
//        tabelhead.addColumn("Pajak");
//        try {
//            koneksi();
//            String sql = "SELECT * FROM nama";
//            Statement stat = conn.createStatement();
//            ResultSet res = stat.executeQuery(sql);
//            while (res.next()) {
//                tabelhead.addRow(new Object[]{res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8), res.getString(9), res.getString(10),});
//            }
//            tabelkas.setModel(tabelhead);
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "BELUM TERKONEKSI");
//        }
//    }
//   public void batal() {
//        txtnama.setText("");
//        txtket.setText("");
//        txtnominal.setText("");
//        txttanggal.setText("");
//        txtsumber.setText("");
//        buttonGroup1.clearSelection();
//        cekPajak.setSelected(false);
//        txtPajak.setText("");
//        txtPajak.setEnabled(false);
//    }
//public void insert() {
//        String nama = txtnama.getText();
//     String tanggal = txttanggal.getText();
//      String keterangan = txtket.getText();
//       String sumberkas = txtsumber.getText();
//      int nominal = Integer.parseInt(txtnominal.getText());
//        String Tipe_mutasi = "";
//        if(radiobtnpem.isSelected()){
//            Tipe_mutasi = radiobtnpem.getText();
//        } else {
//            Tipe_mutasi = radiobtnpeng.getText();
//        }
//        try {
//            koneksi();
//            Statement statement = conn.createStatement();
//            statement.executeUpdate("INSERT INTO tb_mahasiswa (nim, nama,jk, prodi, th_angkatan,alamat)"
//                    + "VALUES('" + nama + "','" + tanggal + "','" + sumberkas + "','" + keterangan + "','" + nominal + "','" + Tipe_mutasi + "',"+ pajak+ "')");
//            statement.close();
//            JOptionPane.showMessageDialog(null, "Berhasil Memasukan Data Mahasiswa!" + "\n" + nama);
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan Input!");
//        }
//        refresh();
//}
//
//
//
//
//
////public void update() {
////          String nama = txtnama.getText();
////     String tanggal = txttanggal.getText();
////       String sumberkas = txtsumber.getText();
////        String keterangan = txtket.getText();
////      int nominal = Integer.parseInt(txtnominal.getText());
////        String Tipe_mutasi = "";
////        if(radiobtnpem.isSelected()){
////            Tipe_mutasi = radiobtnpem.getText();
////        } else {
////            Tipe_mutasi = radiobtnpeng.getText();
////        }
////        try {
////            Statement statement = conn.createStatement();
////            statement.executeUpdate("UPDATE tb_mahasiswa SET nama='" + nama + "'," + "tanggal='" + tanggal + "',sumber kas='" + sumberkas + "',Keterangann='" + keterangan + "'nominal="+nominal+"tipe mutasi="+Tipe_mutasi+"' WHERE nama = '" + nama + "'");
////            statement.close();
////            conn.close();
////            JOptionPane.showMessageDialog(null, "Update Data Mahasiswa Berhasil!");
////        } catch (Exception e) {
////            System.out.println("Error : " + e);
////        }
////        refresh();
////}
//    public void delete() {
//        int ok = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin akan menghapus data ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
//        if (ok == 0) {
//            try {
//                String sql = "DELETE FROM tb_kas WHERE nama='" + txtnama.getText() + "'";
//                java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
//                stmt.executeUpdate();
//                JOptionPane.showMessageDialog(null, "Data Berhasil di hapus");
//                batal();
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, "Data gagal di hapus");
//            }
//            refresh();
//        }
//        
 
        
        
    
    


    


    }//GEN-LAST:event_btnsimpanActionPerformed

    private void btnbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbatalActionPerformed
        // TODO add your handling code here:
        batal();
    }//GEN-LAST:event_btnbatalActionPerformed

    private void cekPajakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cekPajakActionPerformed
        // TODO add your handling code here:

        if (cekPajak.isSelected()) {
            txtPajak.setEnabled(true);
        } else {
            txtPajak.setEnabled(false);
        }
    }//GEN-LAST:event_cekPajakActionPerformed

    void clear() {
        txtnama.setText("");
        txtket.setText("");
        txtnominal.setText("");
        txttanggal.setText("");
        txtsumber.setText("");
        buttonGroup1.clearSelection();
        cekPajak.setSelected(false);
        txtPajak.setText("");
        txtPajak.setEnabled(false);
    }

    private void txtPajakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPajakActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPajakActionPerformed

    private void BtnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_BtnCloseActionPerformed

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
        // TODO add your handling code here:
//        DefaultTableModel dataModel = (DefaultTableModel) tabelkas.getModel();
//        int rowCount = dataModel.getRowCount();
//        while (rowCount > 0) {
//            dataModel.removeRow(rowCount - 1);
//            rowCount = dataModel.getRowCount();
//        }
    delete();
    }//GEN-LAST:event_btnhapusActionPerformed

    private void tabelkasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelkasMouseClicked
        // TODO add your handling code here:
        int tabel = tabelkas.getSelectedRow();
        nama = tabelkas.getValueAt(tabel, 0).toString();
        Tanggal = tabelkas.getValueAt(tabel, 1).toString();
        sumberkas = tabelkas.getValueAt(tabel, 2).toString();
        Keterangan = tabelkas.getValueAt(tabel, 3).toString();
        nominal = Integer.parseInt(tabelkas.getValueAt(tabel, 4).toString());
        Tipe_mutasi = tabelkas.getValueAt(tabel, 5).toString();
        pajak = Integer.parseInt(tabelkas.getValueAt(tabel, 6).toString());
        itempilih();

    }//GEN-LAST:event_tabelkasMouseClicked

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
            java.util.logging.Logger.getLogger(GUI_KAS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_KAS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_KAS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_KAS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_KAS().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnClose;
    private javax.swing.JButton btnbatal;
    private javax.swing.JButton btnhapus;
    private javax.swing.JButton btnsimpan;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox cekPajak;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton radiobtnpem;
    private javax.swing.JRadioButton radiobtnpeng;
    private javax.swing.JTable tabelkas;
    private javax.swing.JTextField txtPajak;
    private javax.swing.JLabel txtSaldo;
    private javax.swing.JTextField txtket;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtnominal;
    private javax.swing.JTextField txtsumber;
    private javax.swing.JTextField txttanggal;
    // End of variables declaration//GEN-END:variables
}
