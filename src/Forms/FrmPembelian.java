/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrmPembelian.java
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
public final class FrmPembelian extends javax.swing.JInternalFrame {

    private DefaultTableModel model;
    public FrmPembelian() {
        initComponents();
        loadcombodata();
        model=new DefaultTableModel();
        tblPembelian.setModel(model);
        model.addColumn("No. Transaksi");
        model.addColumn("Tgl. Transaksi");
        model.addColumn("Kode Supplier");  
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
    
    try {        
        TxtKodeSupplier.removeAllItems();
            Connection c=ClassDB.getkoneksi();
            Statement s= c.createStatement();
            String sql="Select * from supplier";
            ResultSet r=s.executeQuery(sql);
            
            while (r.next()) {
                TxtKodeSupplier.addItem(r.getString("kd_supplier"));
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
            String sql="Select * from pembelian where no_transaksi='" + this.TxtNoTransaksi.getText() + "'";
            ResultSet r=s.executeQuery(sql);
            
            while (r.next()) {
                Object[] o=new Object[7];
                o[0]=r.getString("no_transaksi");
                o[1]=r.getString("tgl_transaksi");
                o[2]=r.getString("kd_supplier");                
                o[3]=r.getString("kd_barang");
                o[4]=r.getString("harga");
                o[5]=r.getString("jumlah");                
                o[6]=r.getString("totalharga");
                
                model.addRow(o);
            }
            r.close();
            s.close();
            
        }catch(SQLException e) {
            System.out.println("Terjadi kesalahan");
        }
    }
public void updatebarang(String jml,String kode) {
    String sql1="Update barang Set stok=stok + ? where kd_barang=?";
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
    String KodeSup=(String) this.TxtKodeSupplier.getSelectedItem();
    String KodeBar=(String) this.TxtKodeBarang.getSelectedItem();
    String Harga=this.TxtHarga.getText();
    String Jml=this.TxtJumlah.getText();
    String total=this.TxtTotalHarga.getText();
        try {
            String sql1="Select * from pembelian where no_transaksi='" + kode + "' AND kd_barang='" + KodeBar + "' AND kd_supplier='" + KodeSup + "'";
            Connection c=ClassDB.getkoneksi();            
            PreparedStatement st=(PreparedStatement) c.prepareStatement(sql1);
            ResultSet rs=st.executeQuery(sql1);
            if (rs.next())
            {
            String sql = "UPDATE pembelian Set jumlah=jumlah + ?,totalharga= totalharga + ? where no_transaksi='" + kode + "' AND kd_barang='" + KodeBar + "' AND kd_supplier='" + KodeSup + "'";
            PreparedStatement p2=(PreparedStatement) c.prepareStatement(sql);
            
            p2.setString(1, Jml);
            p2.setString(2, total);           
            p2.executeUpdate();
            p2.close();
            this.updatebarang(Jml, KodeBar);
            
            }else{
            String sql = "Insert into pembelian values (?,?,?,?,?,?,?)";
            PreparedStatement p=(PreparedStatement) c.prepareStatement(sql);
            p.setString(1, kode);
            p.setDate(2,  new java.sql.Date(tgl.getTime()));
            p.setString(3, KodeSup);
            p.setString(4, KodeBar);                        
            p.setString(5, Harga);                        
            p.setString(6, Jml);                        
            p.setString(7, total);                        
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
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblPembelian = new javax.swing.JTable();
        panelWarnaTransparan21 = new Tampilan.PanelWarnaTransparan2();
        TxtNoTransaksi = new javax.swing.JTextField();
        TxtKodeBarang = new javax.swing.JComboBox();
        TxtTglTransaksi = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        TxtKodeSupplier = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        TxtHarga = new javax.swing.JTextField();
        TxtJumlah = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        TxtTotalHarga = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        panelWarnaTransparan22 = new Tampilan.PanelWarnaTransparan2();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        setTitle("TRANSAKSI PEMBELIAN");

        tblPembelian.setModel(new javax.swing.table.DefaultTableModel(
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
        tblPembelian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPembelianMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPembelian);

        panelWarnaTransparan21.setBackground(new java.awt.Color(204, 204, 204));

        TxtNoTransaksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtNoTransaksiKeyPressed(evt);
            }
        });

        TxtKodeBarang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        TxtKodeBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtKodeBarangActionPerformed(evt);
            }
        });

        jLabel2.setText("Kode Supplier");

        jLabel1.setText("Tgl Transaksi");

        TxtKodeSupplier.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("No Transaksi");

        jLabel4.setText("Kode Barang");

        jLabel7.setText("Total Harga");

        TxtHarga.setEditable(false);

        TxtJumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtJumlahKeyReleased(evt);
            }
        });

        jLabel5.setText("Harga");

        jLabel6.setText("Jumlah");

        TxtTotalHarga.setEditable(false);

        javax.swing.GroupLayout panelWarnaTransparan21Layout = new javax.swing.GroupLayout(panelWarnaTransparan21);
        panelWarnaTransparan21.setLayout(panelWarnaTransparan21Layout);
        panelWarnaTransparan21Layout.setHorizontalGroup(
            panelWarnaTransparan21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelWarnaTransparan21Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelWarnaTransparan21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelWarnaTransparan21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(panelWarnaTransparan21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TxtKodeSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelWarnaTransparan21Layout.createSequentialGroup()
                        .addGroup(panelWarnaTransparan21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtNoTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtTglTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48)
                        .addGroup(panelWarnaTransparan21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelWarnaTransparan21Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28))
                            .addGroup(panelWarnaTransparan21Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(10, 10, 10)
                                .addComponent(TxtKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(60, 60, 60)
                .addGroup(panelWarnaTransparan21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(panelWarnaTransparan21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TxtTotalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(86, 86, 86))
        );
        panelWarnaTransparan21Layout.setVerticalGroup(
            panelWarnaTransparan21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelWarnaTransparan21Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelWarnaTransparan21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelWarnaTransparan21Layout.createSequentialGroup()
                        .addGroup(panelWarnaTransparan21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelWarnaTransparan21Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(TxtKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelWarnaTransparan21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelWarnaTransparan21Layout.createSequentialGroup()
                        .addGroup(panelWarnaTransparan21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelWarnaTransparan21Layout.createSequentialGroup()
                                .addGroup(panelWarnaTransparan21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TxtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelWarnaTransparan21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TxtTotalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelWarnaTransparan21Layout.createSequentialGroup()
                                .addGroup(panelWarnaTransparan21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TxtNoTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelWarnaTransparan21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TxtTglTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelWarnaTransparan21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TxtKodeSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(51, 51, 51))
        );

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/New.png"))); // NOI18N
        jButton3.setText("New");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/OK.png"))); // NOI18N
        jButton4.setText("Add =>");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tampilan/Close.png"))); // NOI18N
        jButton5.setText("Exit");
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        panelWarnaTransparan22.setBackground(new java.awt.Color(204, 204, 204));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setText("TRANSAKSI PEMBELIAN");

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
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(9, 9, 9)
                        .addComponent(jButton4)
                        .addGap(549, 549, 549)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 827, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(72, Short.MAX_VALUE))
            .addComponent(panelWarnaTransparan22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelWarnaTransparan21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelWarnaTransparan22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelWarnaTransparan21, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            String sql = "Select harga from barang WHERE kd_barang=?";
            PreparedStatement p=(PreparedStatement) c.prepareStatement(sql);
            p.setString(1, nm);
            ResultSet result=p.executeQuery();
            result.next();
            TxtHarga.setText(result.getString("harga"));
           
            
            }catch(SQLException e){
            System.out.println(e);        
         }
    }//GEN-LAST:event_TxtKodeBarangActionPerformed

    private void TxtJumlahKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtJumlahKeyReleased
        String qtys=TxtJumlah.getText();       
       String totals=TxtHarga.getText();
        double qtysd=Double.parseDouble(qtys);
        double totalsd=Double.parseDouble(totals);
        DecimalFormat df = new DecimalFormat("#.##");
        TxtTotalHarga.setText(df.format(qtysd * totalsd));
    }//GEN-LAST:event_TxtJumlahKeyReleased

    private void tblPembelianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPembelianMouseClicked
        int i=tblPembelian.getSelectedRow();
        
        if(i==-1)
        {
            return;
        }
        String kode=(String) model.getValueAt(i, 0);        
        String kodesup=(String) model.getValueAt(i, 2);        
        String kodebar=(String) model.getValueAt(i, 3);
        String jml=(String) model.getValueAt(i, 5);
        
        String sql1="Update barang Set stok=stok - ? where kd_barang=?";
        String sql="Delete from pembelian where no_transaksi=? AND kd_barang=? AND kd_supplier=?";
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
            st2.setString(3, kodesup);
            st2.executeUpdate();
            st2.close();
            
    }catch(SQLException e) {
        System.out.println(e);
    }finally{
        loadData();
    }
    }//GEN-LAST:event_tblPembelianMouseClicked

    private void TxtNoTransaksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtNoTransaksiKeyPressed
        loadData();
    }//GEN-LAST:event_TxtNoTransaksiKeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        TambahData();
        loadData();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TxtHarga;
    private javax.swing.JTextField TxtJumlah;
    private javax.swing.JComboBox TxtKodeBarang;
    private javax.swing.JComboBox TxtKodeSupplier;
    private javax.swing.JTextField TxtNoTransaksi;
    private com.toedter.calendar.JDateChooser TxtTglTransaksi;
    private javax.swing.JTextField TxtTotalHarga;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private Tampilan.PanelWarnaTransparan2 panelWarnaTransparan21;
    private Tampilan.PanelWarnaTransparan2 panelWarnaTransparan22;
    private javax.swing.JTable tblPembelian;
    // End of variables declaration//GEN-END:variables
}
