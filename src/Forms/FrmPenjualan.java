/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Frmpenjualan.java
 *
 * Created on 07 Des 11, 14:13:25
 */
package Forms;

import cls.ClassDB;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author bagong
 */
public final class FrmPenjualan extends javax.swing.JInternalFrame {

    private DefaultTableModel model;
    public FrmPenjualan() {
        initComponents();
        loadcombodata();
        model=new DefaultTableModel();
        tblPenjualan.setModel(model);
        model.addColumn("No. Transaksi");
        model.addColumn("Tgl. Transaksi");  
        model.addColumn("Kode Barang");
        model.addColumn("Harga");
        model.addColumn("Jumlah");
        model.addColumn("Total Harga");
        loadData();
    }
    
public void loadcombodata(){
    try {        
        TxtKodeBarang.removeAllItems();
        
            Connection c=ClassDB.getkoneksi();
            Statement s= c.createStatement();
            String sql="Select * from barang";
            ResultSet r=s.executeQuery(sql);
            
            while (r.next()) {
                TxtKodeBarang.addItem(r.getString("kd_barang"));
            }
            r.close();
            s.close();
        }catch(SQLException e) {
            System.out.println("Terjadi kesalahan");
        }    
    
    
    
}
public void loadData() {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {            
            Connection c=ClassDB.getkoneksi();
            Statement s= c.createStatement();
            String sql="Select * from penjualan where no_transaksi='" + this.TxtNoTransaksi.getText() + "'";
            ResultSet r=s.executeQuery(sql);
            
            while (r.next()) {
                Object[] o=new Object[6];
                o[0]=r.getString("no_transaksi");
                o[1]=r.getString("tgl_transaksi");               
                o[2]=r.getString("kd_barang");
                o[3]=r.getString("harga");
                o[4]=r.getString("jumlah");                
                o[5]=r.getString("totalharga");
                
                model.addRow(o);
            }
            r.close();
            s.close();
            
        }catch(SQLException e) {
            System.out.println("Terjadi kesalahan");
        }
    }
public void updatebarang(String jml,String kode) {
    String sql1="Update barang Set stok=stok - ? where kd_barang=?";
    try {        
    Connection c=ClassDB.getkoneksi();            
            PreparedStatement st=(PreparedStatement) c.prepareStatement(sql1);
            st.setString(1, jml);
            st.setString(2, kode);
            st.executeUpdate();
            st.close();
            
    }catch(SQLException e) {
        System.out.println(e);
    }
}

public void TambahData() {
    String kode=this.TxtNoTransaksi.getText();
    java.util.Date tgl=(java.util.Date) this.TxtTglTransaksi.getDate();

    String KodeBar=(String) this.TxtKodeBarang.getSelectedItem();
    String Harga=this.TxtHarga.getText();
    String Jml=this.TxtJumlah.getText();
    String total=this.TxtTotalHarga.getText();
        try {
            String sql1="Select * from penjualan where no_transaksi='" + kode + "' AND kd_barang='" + KodeBar + "'";
            Connection c=ClassDB.getkoneksi();            
            PreparedStatement st=(PreparedStatement) c.prepareStatement(sql1);
            ResultSet rs=st.executeQuery(sql1);
            if (rs.next())
            {
            String sql = "UPDATE penjualan Set jumlah=jumlah + ?,totalharga= totalharga + ? where no_transaksi='" + kode + "' AND kd_barang='" + KodeBar + "'";
            PreparedStatement p2=(PreparedStatement) c.prepareStatement(sql);
            
            p2.setString(1, Jml);
            p2.setString(2, total);           
            p2.executeUpdate();
            p2.close();
            this.updatebarang(Jml, KodeBar);
            
            }else{
            String sql = "Insert into penjualan values (?,?,?,?,?,?)";
            PreparedStatement p=(PreparedStatement) c.prepareStatement(sql);
            p.setString(1, kode);
            p.setDate(2,  new java.sql.Date(tgl.getTime()));          
            p.setString(3, KodeBar);                        
            p.setString(4, Harga);                        
            p.setString(5, Jml);                        
            p.setString(6, total);                        
            p.executeUpdate();
            p.close();
            this.updatebarang(Jml, KodeBar);
            
            }
        }catch(SQLException e){
            System.out.println(e);
        }finally{
            loadData();
        }
}
public void HapusText() {
    this.TxtNoTransaksi.setText(null);
    this.TxtHarga.setText(null);
    this.TxtJumlah.setText(null);
    this.TxtTotalHarga.setText(null);
    this.txtsub.setText(null);
    this.TxtStok.setText(null);
    this.txtbayar.setText(null);
    this.tblPenjualan.columnRemoved(null);
}
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblPenjualan = new javax.swing.JTable();
        panelWarnaTransparan21 = new Tampilan.PanelWarnaTransparan2();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        TxtNoTransaksi = new javax.swing.JTextField();
        TxtHarga = new javax.swing.JTextField();
        TxtJumlah = new javax.swing.JTextField();
        TxtTotalHarga = new javax.swing.JTextField();
        TxtKodeBarang = new javax.swing.JComboBox();
        TxtStok = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        TxtTglTransaksi = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        txtsub = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtbayar = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        panelWarnaTransparan22 = new Tampilan.PanelWarnaTransparan2();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setTitle("TRANSAKSI PENJUALAN");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblPenjualan.setModel(new javax.swing.table.DefaultTableModel(
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
        tblPenjualan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPenjualanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPenjualan);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 830, 170));

        panelWarnaTransparan21.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setText("Tgl Transaksi");

        jLabel3.setText("No Transaksi");

        jLabel4.setText("Kode Barang");

        jLabel5.setText("Harga");

        jLabel6.setText("Jumlah");

        jLabel7.setText("Total Harga");

        TxtNoTransaksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtNoTransaksiKeyPressed(evt);
            }
        });

        TxtHarga.setEditable(false);

        TxtJumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtJumlahKeyReleased(evt);
            }
        });

        TxtTotalHarga.setEditable(false);

        TxtKodeBarang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        TxtKodeBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtKodeBarangActionPerformed(evt);
            }
        });

        TxtStok.setEditable(false);
        TxtStok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtStokKeyReleased(evt);
            }
        });

        jLabel8.setText("tersedia");

        jLabel9.setText("Sub Total");

        txtsub.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtsub.setText("Rp.");

        jLabel11.setText("Bayar");

        jLabel15.setText("Kembali");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Rp.");

        txtbayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbayarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelWarnaTransparan21Layout = new javax.swing.GroupLayout(panelWarnaTransparan21);
        panelWarnaTransparan21.setLayout(panelWarnaTransparan21Layout);
        panelWarnaTransparan21Layout.setHorizontalGroup(
            panelWarnaTransparan21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelWarnaTransparan21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelWarnaTransparan21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(20, 20, 20)
                .addGroup(panelWarnaTransparan21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelWarnaTransparan21Layout.createSequentialGroup()
                        .addGroup(panelWarnaTransparan21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(TxtKodeBarang, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TxtTglTransaksi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(49, 49, 49)
                        .addGroup(panelWarnaTransparan21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(panelWarnaTransparan21Layout.createSequentialGroup()
                        .addComponent(TxtNoTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)))
                .addGroup(panelWarnaTransparan21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TxtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtTotalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelWarnaTransparan21Layout.createSequentialGroup()
                        .addComponent(TxtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(TxtStok, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(288, 288, 288)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(271, 271, 271)
                .addGroup(panelWarnaTransparan21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelWarnaTransparan21Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtsub, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelWarnaTransparan21Layout.createSequentialGroup()
                        .addGroup(panelWarnaTransparan21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelWarnaTransparan21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtbayar, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelWarnaTransparan21Layout.setVerticalGroup(
            panelWarnaTransparan21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelWarnaTransparan21Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(panelWarnaTransparan21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelWarnaTransparan21Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addGroup(panelWarnaTransparan21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtbayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelWarnaTransparan21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)))
                    .addComponent(txtsub)
                    .addGroup(panelWarnaTransparan21Layout.createSequentialGroup()
                        .addGroup(panelWarnaTransparan21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtNoTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(panelWarnaTransparan21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelWarnaTransparan21Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelWarnaTransparan21Layout.createSequentialGroup()
                                .addComponent(TxtTglTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(TxtKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelWarnaTransparan21Layout.createSequentialGroup()
                        .addGroup(panelWarnaTransparan21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TxtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelWarnaTransparan21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtStok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(panelWarnaTransparan21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtTotalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        getContentPane().add(panelWarnaTransparan21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 900, 130));

        panelWarnaTransparan22.setBackground(new java.awt.Color(204, 204, 204));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setText("TRANSAKSI PENJUALAN");

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/barokah1.png"))); // NOI18N
        jLabel13.setText("jLabel8");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel14.setText("RESTORAN BAROKAH");

        javax.swing.GroupLayout panelWarnaTransparan22Layout = new javax.swing.GroupLayout(panelWarnaTransparan22);
        panelWarnaTransparan22.setLayout(panelWarnaTransparan22Layout);
        panelWarnaTransparan22Layout.setHorizontalGroup(
            panelWarnaTransparan22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelWarnaTransparan22Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelWarnaTransparan22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE)
                    .addGroup(panelWarnaTransparan22Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelWarnaTransparan22Layout.setVerticalGroup(
            panelWarnaTransparan22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelWarnaTransparan22Layout.createSequentialGroup()
                .addGroup(panelWarnaTransparan22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelWarnaTransparan22Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14))
                    .addGroup(panelWarnaTransparan22Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        getContentPane().add(panelWarnaTransparan22, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, -1));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/New.png"))); // NOI18N
        jButton3.setText("New");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, -1, 32));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/OK.png"))); // NOI18N
        jButton4.setText("Add =>");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 280, -1, 32));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tampilan/Close.png"))); // NOI18N
        jButton5.setText("Exit");
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(748, 280, 90, 32));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
HapusText();        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tblPenjualanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPenjualanMouseClicked
        int i=tblPenjualan.getSelectedRow();
        
        if(i==-1)
        {
            return;
        }
        String kode=(String) model.getValueAt(i, 0);        
               
        String kodebar=(String) model.getValueAt(i, 2);
        String jml=(String) model.getValueAt(i, 4);
        
        String sql1="Update barang Set stok=stok + ? where kd_barang=?";
        String sql="Delete from penjualan where no_transaksi=? AND kd_barang=?";
    try {        
    Connection c=ClassDB.getkoneksi();            
            PreparedStatement st=(PreparedStatement) c.prepareStatement(sql1);
            st.setString(1, jml);
            st.setString(2, kodebar);
            st.executeUpdate();
            st.close();
            
            PreparedStatement st2=(PreparedStatement) c.prepareStatement(sql);
            st2.setString(1, kode);
            st2.setString(2, kodebar);
            st2.executeUpdate();
            st2.close();
            
    }catch(SQLException e) {
        System.out.println(e);
    }finally{
        loadData();
    }
    }//GEN-LAST:event_tblPenjualanMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        TambahData();
        loadData();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
         this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void TxtStokKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtStokKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtStokKeyReleased

    private void TxtKodeBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtKodeBarangActionPerformed
        int i=TxtKodeBarang.getSelectedIndex();
        if (i==-1)
        {
            return;
        }
        try {
            String nm=(String) TxtKodeBarang.getSelectedItem();
            Connection c=ClassDB.getkoneksi();
            Statement s= c.createStatement();
            String sql = "Select harga,stok from barang WHERE kd_barang=?";
            PreparedStatement p=(PreparedStatement) c.prepareStatement(sql);
            p.setString(1, nm);
            ResultSet result=p.executeQuery();
            result.next();
            TxtHarga.setText(result.getString("harga"));
            TxtStok.setText(result.getString("stok"));

        }catch(SQLException e){
            System.out.println(e);
        }
    }//GEN-LAST:event_TxtKodeBarangActionPerformed

    private void TxtJumlahKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtJumlahKeyReleased
        int q1=Integer.parseInt(this.TxtJumlah.getText());
        int q2=Integer.parseInt(this.TxtStok.getText());
        if (q1 <= q2)
        {
            String qtys=TxtJumlah.getText();
            String totals=TxtHarga.getText();
            double qtysd=Double.parseDouble(qtys);
            double totalsd=Double.parseDouble(totals);
            DecimalFormat df = new DecimalFormat("#.##");
            TxtTotalHarga.setText(df.format(qtysd * totalsd));
        }else{
            TxtJumlah.setText(null);
        }
    }//GEN-LAST:event_TxtJumlahKeyReleased

    private void TxtNoTransaksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtNoTransaksiKeyPressed
        loadData();
    }//GEN-LAST:event_TxtNoTransaksiKeyPressed

    private void txtbayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbayarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TxtHarga;
    private javax.swing.JTextField TxtJumlah;
    private javax.swing.JComboBox TxtKodeBarang;
    private javax.swing.JTextField TxtNoTransaksi;
    private javax.swing.JTextField TxtStok;
    private com.toedter.calendar.JDateChooser TxtTglTransaksi;
    private javax.swing.JTextField TxtTotalHarga;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private Tampilan.PanelWarnaTransparan2 panelWarnaTransparan21;
    private Tampilan.PanelWarnaTransparan2 panelWarnaTransparan22;
    private javax.swing.JTable tblPenjualan;
    private javax.swing.JTextField txtbayar;
    private javax.swing.JLabel txtsub;
    // End of variables declaration//GEN-END:variables
}
