/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package kasirapp;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.lang.String;


/**
 *
 * @author NITRO 5
 */
public class Main extends javax.swing.JFrame {
    
    NumberFormat nf = NumberFormat.getNumberInstance(new Locale("in", "ID"));
    DefaultTableModel tbl = new DefaultTableModel();
    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        setTabel();
        setWarna();
    }
    
    public void setWarna(){
        background.setBackground(Warna.INSTANCE.putih());
        header.setBackground(Warna.INSTANCE.biruSpaceKadet());
        panelMenu.setBackground(Warna.INSTANCE.biruBlackCoral());
        panelKasir.setBackground(Warna.INSTANCE.silver());
        panelListData.setBackground(Warna.INSTANCE.silver());
        panelStok.setBackground(Warna.INSTANCE.silver());
        btnExit.setBackground(Warna.INSTANCE.biruBlackCoral());
        labelJudul.setForeground(Warna.INSTANCE.putih());
        labelMenu.setForeground(Warna.INSTANCE.putih());
        labelKasir.setForeground(Warna.INSTANCE.putih());
        labelListData.setForeground(Warna.INSTANCE.putih());
        labelStok.setForeground(Warna.INSTANCE.putih());
        labelExit.setForeground(Warna.INSTANCE.putih());
        menuKasir.setBackground(Warna.INSTANCE.biruBlackCoral());
        menuListData.setBackground(Warna.INSTANCE.biruBlackCoral());
        menuStokBarang.setBackground(Warna.INSTANCE.biruBlackCoral());
        btnCari.setBackground(Warna.INSTANCE.biruBlackCoral());
        btnTambah.setBackground(Warna.INSTANCE.biruBlackCoral());
        btnCetak.setBackground(Warna.INSTANCE.orenMandarin());
        btnRefresh.setBackground(Warna.INSTANCE.biruBlackCoral());
        btnSimpan.setBackground(Warna.INSTANCE.orenMandarin());
        btnHapus.setBackground(Warna.INSTANCE.orenMandarin());
        btnHapusBelanja.setBackground(Warna.INSTANCE.merahImperial());
        btnUpdateStok.setBackground(Warna.INSTANCE.biruBlackCoral());
        fieldTotal.setBackground(Warna.INSTANCE.putih());
    }
    
    public void setTabel(){
        listBelanja.getTableHeader().setFont(new Font("Poppins Medium", 4, 14));
        listBelanja.getTableHeader().setOpaque(false);
        listBelanja.setRowHeight(30);
        listData.getTableHeader().setFont(new Font("Poppins Medium", 4, 14));
        listData.getTableHeader().setOpaque(false);
        listData.setRowHeight(30);
    }
    
    //---------------------------------------------------
    //----------------fungsi database-------------------
    
    public void simpanData(){
        try{
            String sql = "INSERT INTO tb_barang  VALUES ('" + sbidBarang.getText() + "','" + sbNamaBarang.getText() + "','" + sbStok.getText() + "','" + sbHarga.getText() + "')";
            Connection konek = (Connection)Koneksi.getKoneksi();
            PreparedStatement pst = konek.prepareStatement(sql);
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Berhasil disimpan");
            
            tampilkanData();
            formKosong();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Gagal disimpan " + e.getMessage());
        }
    }
    
    public void hapusData(){
        try{
            String sql = "DELETE FROM tb_barang WHERE id_barang ='" + idCari.getText() + "'";
            Connection konek = (Connection)Koneksi.getKoneksi();
            PreparedStatement pst = konek.prepareStatement(sql);
            pst.execute();
        
            tampilkanData();
            
            JOptionPane.showMessageDialog(null, "Berhasil dihapus");
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void cariData(){
      
        DefaultTableModel tbl = new DefaultTableModel();

        tbl.addColumn("ID Barang");
        tbl.addColumn("Nama Barang");
        tbl.addColumn("Stok");
        tbl.addColumn("Harga");
             
        try{
            String sql = "SELECT * FROM tb_barang WHERE id_barang ='" + idCari.getText() + "'";
            Connection konek = (Connection)Koneksi.getKoneksi();
            Statement st = konek.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                tbl.addRow(new Object[]{
                    rs.getString("id_barang"),
                    rs.getString("nama_barang"),
                    rs.getString("stok_barang"),
                    rs.getString("harga_barang")
                }); 
                
            }
            listData.setModel(tbl);
        }catch(Exception e){
            
        }
    }
    
        public void tampilkanData(){
        
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("ID Barang");
        tbl.addColumn("Nama Barang");
        tbl.addColumn("Stok");
        tbl.addColumn("Harga");
        
        try{
            Statement st = (Statement)Koneksi.getKoneksi().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tb_barang");
            
            while(rs.next()){
                tbl.addRow(new Object[]{
                    rs.getString("id_barang"),
                    rs.getString("nama_barang"),
                    rs.getString("stok_barang"),
                    rs.getString("harga_barang")
                });
                listData.setModel(tbl);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Koneksi error" + e.getMessage());
        }
        
    }
        
    public void tambahStokBarang(){
        try{
            String sql = "INSERT INTO tb_bm (id_barang, jumlah) VALUES ('" + sbidBarang.getText() + "','" + sbStok.getText() + "')";
            Connection konek = (Connection)Koneksi.getKoneksi();
            PreparedStatement pst = konek.prepareStatement(sql);
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Berhasil disimpan");
            
            tampilkanData();
            formKosong();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Gagal Disimpan!" + e.getMessage());
        }
    }
    //-----------------akhir dari fungsi database---------------------
    //----------------------------------------------------------------
        
        
    //----------------------------------------------------------------
    //-----------------------fungsi pembayaran------------------------
    
    public void subTotal(){
        try{
            int subTotal = 0;
            
            subTotal = Integer.parseInt(kHarga.getText()) * Integer.parseInt(kJumlah.getText());
            kSubTotal.setText(String.valueOf(subTotal));
            
        }catch(Exception e){
            
        }
    }
    
    public void penjualan(){
        try{
            for(int i = 0; i < listBelanja.getRowCount(); i++){
                String sql = "INSERT INTO tb_transaksi (id_barang, nama_barang, harga, jumlah_beli, sub_total) VALUES ('" +  listBelanja.getValueAt(i, 0) + "','" + listBelanja.getValueAt(i, 1) + "','" + listBelanja.getValueAt(i, 2) + "','" + listBelanja.getValueAt(i, 3) + "','" + listBelanja.getValueAt(i, 4) + "')";
                Connection konek = (Connection)Koneksi.getKoneksi();
                PreparedStatement pst = konek.prepareStatement(sql);
                pst.execute();

                JOptionPane.showMessageDialog(null, "Berhasil disimpan");
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Gagal Disimpan!" + e.getMessage());
        }
    }    
    
    public void kembalian(){
        try{
            
            int total = 0;
            int bayar = Integer.valueOf(kBayar.getText());
            int kembalian;
            
            
            for(int i = 0; i < listBelanja.getRowCount(); i++){
                int subTotal = Integer.parseInt(listBelanja.getValueAt(i, 4)+"");
                total += subTotal;
            }
            
            kembalian = bayar - total;
            
            kKembali.setText("Rp. " + nf.format(kembalian));
        }catch(Exception e){
            
        }
    }
    //---------------akhir dari fungsi pembayaran----------------
    //-----------------------------------------------------------
    
    
    //-----------------------------------------------------------
    //---------------------fungsi kasir--------------------------
    
    public void tambahBelanja(){
        DefaultTableModel tbl = (DefaultTableModel)listBelanja.getModel();
        
        tbl.addRow(new Object[]{
            kIDBarang.getText(),
            kNamaBarang.getText(),
            kHarga.getText(),
            kJumlah.getText(),
            kSubTotal.getText()
        });
    }
    
    public void setLabel(){
        int total = 0;
        for(int i = 0; i < listBelanja.getRowCount(); i++){
            int subTotal = Integer.parseInt(listBelanja.getValueAt(i, 4)+"");
            total += subTotal;
        }
        labelTagihan.setText("Rp. " + nf.format(total));
    }
 
    public void hapusBelanja(){
        DefaultTableModel tbl = (DefaultTableModel)listBelanja.getModel();
        try{
            tbl.removeRow(listBelanja.getSelectedRow());
            
            int total = 0;
            for(int i = 0; i < listBelanja.getRowCount(); i++){
                int subTotal = Integer.parseInt(listBelanja.getValueAt(i, 4)+"");
                total += subTotal;
            }
            labelTagihan.setText("Rp. " + nf.format(total));
            
            JOptionPane.showMessageDialog(null, "Belanjaan berhasil di hapus"); 
        }catch(Exception e){
            
        }   
    }
    
    public void cariBarang(){
        try{
            String sql = "SELECT * FROM tb_barang WHERE id_barang ='" + kIDBarang.getText() + "'";
            Connection konek = (Connection)Koneksi.getKoneksi();
            Statement st = konek.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                kIDBarang.setText(rs.getString("id_barang"));
                kNamaBarang.setText(rs.getString("nama_barang"));
                kHarga.setText(rs.getString("harga_barang"));
            }
            
        }catch(Exception e){
            
        }
    }
    
    public void formKosong(){
        sbidBarang.setEditable(true);
        sbidBarang.setText("");
        sbNamaBarang.setText("");
        sbStok.setText("");
        sbHarga.setText("");
        
        kIDBarang.setText("");
        kNamaBarang.setText("");
        kHarga.setText("");
        kJumlah.setText("");
        kSubTotal.setText("");
    }    
  

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new kasirapp.PanelTumpul();
        header = new kasirapp.PanelTumpul();
        labelJudul = new javax.swing.JLabel();
        panelMenu = new kasirapp.PanelTumpul();
        labelMenu = new javax.swing.JLabel();
        menuKasir = new kasirapp.PanelTumpul();
        labelKasir = new javax.swing.JLabel();
        menuListData = new kasirapp.PanelTumpul();
        labelListData = new javax.swing.JLabel();
        menuStokBarang = new kasirapp.PanelTumpul();
        labelStok = new javax.swing.JLabel();
        panelMain = new kasirapp.PanelTumpul();
        panelKasir = new kasirapp.PanelTumpul();
        kIDBarang = new javax.swing.JTextField();
        kNamaBarang = new javax.swing.JTextField();
        kHarga = new javax.swing.JTextField();
        kSubTotal = new javax.swing.JTextField();
        labelID = new javax.swing.JLabel();
        labelNamaB = new javax.swing.JLabel();
        labelHarga = new javax.swing.JLabel();
        labelJumlah = new javax.swing.JLabel();
        labelSub = new javax.swing.JLabel();
        kBayar = new javax.swing.JTextField();
        kKembali = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnCari = new kasirapp.PanelTumpul();
        jLabel6 = new javax.swing.JLabel();
        btnTambah = new kasirapp.PanelTumpul();
        jLabel5 = new javax.swing.JLabel();
        btnCetak = new kasirapp.PanelTumpul();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listBelanja = new javax.swing.JTable();
        fieldTotal = new kasirapp.PanelTumpul();
        labelTagihan = new javax.swing.JLabel();
        kJumlah = new javax.swing.JTextField();
        btnHapusBelanja = new kasirapp.PanelTumpul();
        jLabel1 = new javax.swing.JLabel();
        panelListData = new kasirapp.PanelTumpul();
        jScrollPane2 = new javax.swing.JScrollPane();
        listData = new javax.swing.JTable();
        idCari = new javax.swing.JTextField();
        btnRefresh = new kasirapp.PanelTumpul();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnHapus = new kasirapp.PanelTumpul();
        jLabel17 = new javax.swing.JLabel();
        panelStok = new kasirapp.PanelTumpul();
        jLabel10 = new javax.swing.JLabel();
        sbidBarang = new javax.swing.JTextField();
        sbNamaBarang = new javax.swing.JTextField();
        sbStok = new javax.swing.JTextField();
        sbHarga = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnSimpan = new kasirapp.PanelTumpul();
        jLabel15 = new javax.swing.JLabel();
        btnUpdateStok = new kasirapp.PanelTumpul();
        jLabel4 = new javax.swing.JLabel();
        btnExit = new kasirapp.PanelTumpul();
        labelExit = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        background.setBackground(new java.awt.Color(255, 255, 255));

        labelJudul.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 36)); // NOI18N
        labelJudul.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelJudul.setText("KasirApp");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(430, 430, 430)
                .addComponent(labelJudul, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelJudul, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        labelMenu.setFont(new java.awt.Font("Poppins SemiBold", 0, 18)); // NOI18N
        labelMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/shopping-cart.png"))); // NOI18N
        labelMenu.setText("Penjualan");

        menuKasir.setBackground(new java.awt.Color(102, 102, 102));
        menuKasir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuKasirMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuKasirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuKasirMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuKasirMousePressed(evt);
            }
        });

        labelKasir.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        labelKasir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/shopping-cart2.png"))); // NOI18N
        labelKasir.setText("Kasir");

        javax.swing.GroupLayout menuKasirLayout = new javax.swing.GroupLayout(menuKasir);
        menuKasir.setLayout(menuKasirLayout);
        menuKasirLayout.setHorizontalGroup(
            menuKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuKasirLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(labelKasir, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        menuKasirLayout.setVerticalGroup(
            menuKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelKasir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        menuListData.setBackground(new java.awt.Color(102, 102, 102));
        menuListData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuListDataMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuListDataMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuListDataMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuListDataMousePressed(evt);
            }
        });

        labelListData.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        labelListData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/to-do-list.png"))); // NOI18N
        labelListData.setText("List Data");

        javax.swing.GroupLayout menuListDataLayout = new javax.swing.GroupLayout(menuListData);
        menuListData.setLayout(menuListDataLayout);
        menuListDataLayout.setHorizontalGroup(
            menuListDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuListDataLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(labelListData, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        menuListDataLayout.setVerticalGroup(
            menuListDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelListData, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        menuStokBarang.setBackground(new java.awt.Color(102, 102, 102));
        menuStokBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuStokBarangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuStokBarangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuStokBarangMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuStokBarangMousePressed(evt);
            }
        });

        labelStok.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        labelStok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/box.png"))); // NOI18N
        labelStok.setText("Stok Barang");

        javax.swing.GroupLayout menuStokBarangLayout = new javax.swing.GroupLayout(menuStokBarang);
        menuStokBarang.setLayout(menuStokBarangLayout);
        menuStokBarangLayout.setHorizontalGroup(
            menuStokBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuStokBarangLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(labelStok, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );
        menuStokBarangLayout.setVerticalGroup(
            menuStokBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelStok, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMenuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelMenuLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(menuListData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(menuKasir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMenuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(menuStokBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(labelMenu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuKasir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuListData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuStokBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        panelMain.setLayout(new java.awt.CardLayout());

        kIDBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kIDBarangActionPerformed(evt);
            }
        });

        kNamaBarang.setEditable(false);

        kHarga.setEditable(false);

        kSubTotal.setEditable(false);

        labelID.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        labelID.setForeground(new java.awt.Color(0, 0, 0));
        labelID.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelID.setText("ID Barang :  ");

        labelNamaB.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        labelNamaB.setForeground(new java.awt.Color(0, 0, 0));
        labelNamaB.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelNamaB.setText("Nama Barang :  ");

        labelHarga.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        labelHarga.setForeground(new java.awt.Color(0, 0, 0));
        labelHarga.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelHarga.setText("Harga :  ");

        labelJumlah.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        labelJumlah.setForeground(new java.awt.Color(0, 0, 0));
        labelJumlah.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelJumlah.setText("Jumlah :  ");

        labelSub.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        labelSub.setForeground(new java.awt.Color(0, 0, 0));
        labelSub.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelSub.setText("Sub Total :  ");

        kBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kBayarActionPerformed(evt);
            }
        });
        kBayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kBayarKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                kBayarKeyTyped(evt);
            }
        });

        kKembali.setEditable(false);

        jLabel2.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Uang Bayar :  ");

        jLabel3.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Kembali :  ");

        btnCari.setBackground(new java.awt.Color(153, 153, 153));
        btnCari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCariMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCariMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCariMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnCariMousePressed(evt);
            }
        });

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search.png"))); // NOI18N

        javax.swing.GroupLayout btnCariLayout = new javax.swing.GroupLayout(btnCari);
        btnCari.setLayout(btnCariLayout);
        btnCariLayout.setHorizontalGroup(
            btnCariLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnCariLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnCariLayout.setVerticalGroup(
            btnCariLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnCariLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnTambah.setBackground(new java.awt.Color(153, 153, 153));
        btnTambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTambahMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnTambahMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnTambahMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnTambahMousePressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Tambah");

        javax.swing.GroupLayout btnTambahLayout = new javax.swing.GroupLayout(btnTambah);
        btnTambah.setLayout(btnTambahLayout);
        btnTambahLayout.setHorizontalGroup(
            btnTambahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnTambahLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnTambahLayout.setVerticalGroup(
            btnTambahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        btnCetak.setBackground(new java.awt.Color(153, 153, 153));
        btnCetak.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCetakMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCetakMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCetakMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnCetakMousePressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Cetak");

        javax.swing.GroupLayout btnCetakLayout = new javax.swing.GroupLayout(btnCetak);
        btnCetak.setLayout(btnCetakLayout);
        btnCetakLayout.setHorizontalGroup(
            btnCetakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnCetakLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnCetakLayout.setVerticalGroup(
            btnCetakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        listBelanja.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Barang", "Nama Barang", "Harga", "Jumlah", "Sub Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        listBelanja.setIntercellSpacing(new java.awt.Dimension(0, 0));
        listBelanja.setRowHeight(30);
        listBelanja.setShowVerticalLines(false);
        listBelanja.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(listBelanja);
        if (listBelanja.getColumnModel().getColumnCount() > 0) {
            listBelanja.getColumnModel().getColumn(0).setResizable(false);
            listBelanja.getColumnModel().getColumn(1).setResizable(false);
            listBelanja.getColumnModel().getColumn(2).setResizable(false);
            listBelanja.getColumnModel().getColumn(3).setResizable(false);
            listBelanja.getColumnModel().getColumn(4).setResizable(false);
        }

        fieldTotal.setBackground(new java.awt.Color(153, 153, 153));

        labelTagihan.setBackground(new java.awt.Color(0, 0, 0));
        labelTagihan.setFont(new java.awt.Font("Tahoma", 1, 40)); // NOI18N
        labelTagihan.setForeground(new java.awt.Color(0, 0, 0));
        labelTagihan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTagihan.setText("Rp.");

        javax.swing.GroupLayout fieldTotalLayout = new javax.swing.GroupLayout(fieldTotal);
        fieldTotal.setLayout(fieldTotalLayout);
        fieldTotalLayout.setHorizontalGroup(
            fieldTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fieldTotalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTagihan, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                .addContainerGap())
        );
        fieldTotalLayout.setVerticalGroup(
            fieldTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fieldTotalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTagihan, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );

        kJumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                kJumlahKeyTyped(evt);
            }
        });

        btnHapusBelanja.setBackground(new java.awt.Color(153, 153, 153));
        btnHapusBelanja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHapusBelanjaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHapusBelanjaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHapusBelanjaMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnHapusBelanjaMousePressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Hapus");

        javax.swing.GroupLayout btnHapusBelanjaLayout = new javax.swing.GroupLayout(btnHapusBelanja);
        btnHapusBelanja.setLayout(btnHapusBelanjaLayout);
        btnHapusBelanjaLayout.setHorizontalGroup(
            btnHapusBelanjaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnHapusBelanjaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnHapusBelanjaLayout.setVerticalGroup(
            btnHapusBelanjaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelKasirLayout = new javax.swing.GroupLayout(panelKasir);
        panelKasir.setLayout(panelKasirLayout);
        panelKasirLayout.setHorizontalGroup(
            panelKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKasirLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(panelKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelKasirLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 773, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addGroup(panelKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnCetak, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnHapusBelanja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelKasirLayout.createSequentialGroup()
                        .addGroup(panelKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelNamaB, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelID, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSub, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelKasirLayout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(panelKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelKasirLayout.createSequentialGroup()
                                        .addComponent(kIDBarang)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(kNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelKasirLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(kHarga, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                                    .addComponent(kSubTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                                    .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(kJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelKasirLayout.createSequentialGroup()
                                .addGroup(panelKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(kBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(kKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(fieldTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        panelKasirLayout.setVerticalGroup(
            panelKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKasirLayout.createSequentialGroup()
                .addGroup(panelKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelKasirLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(panelKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(kIDBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelID))
                            .addComponent(btnCari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(panelKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(kNamaBarang, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                            .addComponent(labelNamaB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(panelKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(kHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelKasirLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(fieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)))
                .addGroup(panelKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSub, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelKasirLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelKasirLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnHapusBelanja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCetak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57))))
        );

        panelMain.add(panelKasir, "card2");

        listData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Barang", "Nama Barang", "Stok", "Harga"
            }
        ));
        listData.setRowHeight(30);
        listData.setShowVerticalLines(false);
        listData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listDataMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(listData);
        if (listData.getColumnModel().getColumnCount() > 0) {
            listData.getColumnModel().getColumn(0).setMinWidth(170);
            listData.getColumnModel().getColumn(0).setPreferredWidth(170);
            listData.getColumnModel().getColumn(0).setMaxWidth(170);
            listData.getColumnModel().getColumn(1).setMinWidth(360);
            listData.getColumnModel().getColumn(1).setPreferredWidth(360);
            listData.getColumnModel().getColumn(1).setMaxWidth(360);
            listData.getColumnModel().getColumn(2).setMinWidth(125);
            listData.getColumnModel().getColumn(2).setPreferredWidth(125);
            listData.getColumnModel().getColumn(2).setMaxWidth(125);
            listData.getColumnModel().getColumn(3).setMinWidth(300);
            listData.getColumnModel().getColumn(3).setPreferredWidth(300);
            listData.getColumnModel().getColumn(3).setMaxWidth(300);
        }

        idCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idCariActionPerformed(evt);
            }
        });

        btnRefresh.setBackground(new java.awt.Color(153, 153, 153));
        btnRefresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRefreshMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRefreshMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRefreshMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnRefreshMousePressed(evt);
            }
        });

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/refresh.png"))); // NOI18N

        javax.swing.GroupLayout btnRefreshLayout = new javax.swing.GroupLayout(btnRefresh);
        btnRefresh.setLayout(btnRefreshLayout);
        btnRefreshLayout.setHorizontalGroup(
            btnRefreshLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnRefreshLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnRefreshLayout.setVerticalGroup(
            btnRefreshLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnRefreshLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel8.setFont(new java.awt.Font("Poppins Medium", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Cari :");

        btnHapus.setBackground(new java.awt.Color(153, 153, 153));
        btnHapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHapusMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHapusMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHapusMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnHapusMousePressed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Hapus");

        javax.swing.GroupLayout btnHapusLayout = new javax.swing.GroupLayout(btnHapus);
        btnHapus.setLayout(btnHapusLayout);
        btnHapusLayout.setHorizontalGroup(
            btnHapusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnHapusLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnHapusLayout.setVerticalGroup(
            btnHapusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelListDataLayout = new javax.swing.GroupLayout(panelListData);
        panelListData.setLayout(panelListDataLayout);
        panelListDataLayout.setHorizontalGroup(
            panelListDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListDataLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(panelListDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 960, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelListDataLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(idCari, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        panelListDataLayout.setVerticalGroup(
            panelListDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListDataLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(panelListDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelListDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnHapus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelListDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(idCari, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        panelMain.add(panelListData, "card3");

        jLabel10.setFont(new java.awt.Font("Poppins Medium", 0, 24)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Tambah Stok Barang");

        sbidBarang.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        sbidBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sbidBarangActionPerformed(evt);
            }
        });

        sbNamaBarang.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        sbStok.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        sbHarga.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jLabel11.setBackground(new java.awt.Color(0, 0, 0));
        jLabel11.setFont(new java.awt.Font("Poppins Medium", 0, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("ID Barang :  ");

        jLabel12.setBackground(new java.awt.Color(0, 0, 0));
        jLabel12.setFont(new java.awt.Font("Poppins Medium", 0, 18)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Nama Barang :  ");

        jLabel13.setBackground(new java.awt.Color(0, 0, 0));
        jLabel13.setFont(new java.awt.Font("Poppins Medium", 0, 18)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Stok :  ");

        jLabel14.setBackground(new java.awt.Color(0, 0, 0));
        jLabel14.setFont(new java.awt.Font("Poppins Medium", 0, 18)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Harga :  ");

        btnSimpan.setBackground(new java.awt.Color(153, 153, 153));
        btnSimpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSimpanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSimpanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSimpanMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSimpanMousePressed(evt);
            }
        });

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Simpan");

        javax.swing.GroupLayout btnSimpanLayout = new javax.swing.GroupLayout(btnSimpan);
        btnSimpan.setLayout(btnSimpanLayout);
        btnSimpanLayout.setHorizontalGroup(
            btnSimpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSimpanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnSimpanLayout.setVerticalGroup(
            btnSimpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        btnUpdateStok.setBackground(new java.awt.Color(153, 153, 153));
        btnUpdateStok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUpdateStokMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnUpdateStokMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnUpdateStokMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnUpdateStokMousePressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Update");

        javax.swing.GroupLayout btnUpdateStokLayout = new javax.swing.GroupLayout(btnUpdateStok);
        btnUpdateStok.setLayout(btnUpdateStokLayout);
        btnUpdateStokLayout.setHorizontalGroup(
            btnUpdateStokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnUpdateStokLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnUpdateStokLayout.setVerticalGroup(
            btnUpdateStokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelStokLayout = new javax.swing.GroupLayout(panelStok);
        panelStok.setLayout(panelStokLayout);
        panelStokLayout.setHorizontalGroup(
            panelStokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelStokLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelStokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelStokLayout.createSequentialGroup()
                        .addGroup(panelStokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 991, Short.MAX_VALUE)
                            .addGroup(panelStokLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(panelStokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(panelStokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(sbStok, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
                                    .addComponent(sbHarga)
                                    .addComponent(sbNamaBarang)
                                    .addComponent(sbidBarang))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelStokLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnUpdateStok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))))
        );
        panelStokLayout.setVerticalGroup(
            panelStokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelStokLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                .addGap(64, 64, 64)
                .addGroup(panelStokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sbidBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelStokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sbNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelStokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sbStok, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelStokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sbHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(162, 162, 162)
                .addGroup(panelStokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdateStok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        panelMain.add(panelStok, "card4");

        btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExitMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExitMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnExitMousePressed(evt);
            }
        });

        labelExit.setFont(new java.awt.Font("Poppins SemiBold", 0, 18)); // NOI18N
        labelExit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelExit.setText("EXIT");

        javax.swing.GroupLayout btnExitLayout = new javax.swing.GroupLayout(btnExit);
        btnExit.setLayout(btnExitLayout);
        btnExitLayout.setHorizontalGroup(
            btnExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnExitLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelExit, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnExitLayout.setVerticalGroup(
            btnExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelExit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(panelMain, javax.swing.GroupLayout.PREFERRED_SIZE, 1003, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54))
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addComponent(panelMain, javax.swing.GroupLayout.PREFERRED_SIZE, 613, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    //-----------button exit---------------
    private void btnExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseClicked
        System.exit(0);
    }//GEN-LAST:event_btnExitMouseClicked

    private void btnExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseEntered
        btnExit.setBackground(Warna.INSTANCE.warnaHover1());
    }//GEN-LAST:event_btnExitMouseEntered

    private void btnExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseExited
        btnExit.setBackground(Warna.INSTANCE.biruBlackCoral());
    }//GEN-LAST:event_btnExitMouseExited

    private void btnExitMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMousePressed
        btnExit.setBackground(Warna.INSTANCE.warnaClick1());
    }//GEN-LAST:event_btnExitMousePressed

    //---------------button menu-----------------w
    private void menuKasirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuKasirMouseEntered
        menuKasir.setBackground(Warna.INSTANCE.warnaHover1());
    }//GEN-LAST:event_menuKasirMouseEntered

    private void menuKasirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuKasirMouseExited
        menuKasir.setBackground(Warna.INSTANCE.biruBlackCoral());
    }//GEN-LAST:event_menuKasirMouseExited

    private void menuKasirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuKasirMousePressed
        menuKasir.setBackground(Warna.INSTANCE.warnaClick1());
    }//GEN-LAST:event_menuKasirMousePressed

    private void menuListDataMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuListDataMouseEntered
        menuListData.setBackground(Warna.INSTANCE.warnaHover1());
    }//GEN-LAST:event_menuListDataMouseEntered

    private void menuListDataMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuListDataMouseExited
        menuListData.setBackground(Warna.INSTANCE.biruBlackCoral());
    }//GEN-LAST:event_menuListDataMouseExited

    private void menuListDataMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuListDataMousePressed
        menuListData.setBackground(Warna.INSTANCE.warnaClick1());
    }//GEN-LAST:event_menuListDataMousePressed

    private void menuStokBarangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuStokBarangMouseEntered
        menuStokBarang.setBackground(Warna.INSTANCE.warnaHover1());
    }//GEN-LAST:event_menuStokBarangMouseEntered

    private void menuStokBarangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuStokBarangMouseExited
        menuStokBarang.setBackground(Warna.INSTANCE.biruBlackCoral());
    }//GEN-LAST:event_menuStokBarangMouseExited

    private void menuStokBarangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuStokBarangMousePressed
        menuStokBarang.setBackground(Warna.INSTANCE.warnaClick1());
    }//GEN-LAST:event_menuStokBarangMousePressed

    //--------------button kasir------------------
    private void btnCariMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCariMouseEntered
        btnCari.setBackground(Warna.INSTANCE.warnaHover1());
    }//GEN-LAST:event_btnCariMouseEntered

    private void btnCariMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCariMouseExited
        btnCari.setBackground(Warna.INSTANCE.biruBlackCoral());
    }//GEN-LAST:event_btnCariMouseExited

    private void btnCariMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCariMousePressed
        btnCari.setBackground(Warna.INSTANCE.warnaClick1());
    }//GEN-LAST:event_btnCariMousePressed

    private void btnTambahMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTambahMouseEntered
        btnTambah.setBackground(Warna.INSTANCE.warnaHover1());
    }//GEN-LAST:event_btnTambahMouseEntered

    private void btnTambahMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTambahMouseExited
        btnTambah.setBackground(Warna.INSTANCE.biruBlackCoral());
    }//GEN-LAST:event_btnTambahMouseExited

    private void btnTambahMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTambahMousePressed
        btnTambah.setBackground(Warna.INSTANCE.warnaClick1());
    }//GEN-LAST:event_btnTambahMousePressed

    private void btnCetakMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCetakMouseEntered
        btnCetak.setBackground(Warna.INSTANCE.warnaHover2());
    }//GEN-LAST:event_btnCetakMouseEntered

    private void btnCetakMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCetakMouseExited
        btnCetak.setBackground(Warna.INSTANCE.orenMandarin());
    }//GEN-LAST:event_btnCetakMouseExited

    private void btnCetakMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCetakMousePressed
        btnCetak.setBackground(Warna.INSTANCE.warnaClick2());
    }//GEN-LAST:event_btnCetakMousePressed

    private void menuKasirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuKasirMouseClicked
        panelKasir.setVisible(true);
        panelListData.setVisible(false);
        panelStok.setVisible(false);
    }//GEN-LAST:event_menuKasirMouseClicked

    private void menuListDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuListDataMouseClicked
        panelKasir.setVisible(false);
        panelListData.setVisible(true);
        panelStok.setVisible(false);
    }//GEN-LAST:event_menuListDataMouseClicked

    private void btnRefreshMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRefreshMouseEntered
        btnRefresh.setBackground(Warna.INSTANCE.warnaHover1());
    }//GEN-LAST:event_btnRefreshMouseEntered

    private void btnRefreshMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRefreshMouseExited
        btnRefresh.setBackground(Warna.INSTANCE.biruBlackCoral());
    }//GEN-LAST:event_btnRefreshMouseExited

    private void btnRefreshMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRefreshMousePressed
        btnRefresh.setBackground(Warna.INSTANCE.warnaClick1());
    }//GEN-LAST:event_btnRefreshMousePressed

    private void menuStokBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuStokBarangMouseClicked
        panelKasir.setVisible(false);
        panelListData.setVisible(false);
        panelStok.setVisible(true);
    }//GEN-LAST:event_menuStokBarangMouseClicked

    private void sbidBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sbidBarangActionPerformed
        
    }//GEN-LAST:event_sbidBarangActionPerformed

    private void btnSimpanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimpanMouseEntered
        btnSimpan.setBackground(Warna.INSTANCE.warnaHover2());
    }//GEN-LAST:event_btnSimpanMouseEntered

    private void btnSimpanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimpanMouseExited
        btnSimpan.setBackground(Warna.INSTANCE.orenMandarin());
    }//GEN-LAST:event_btnSimpanMouseExited

    private void btnSimpanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimpanMousePressed
        btnSimpan.setBackground(Warna.INSTANCE.warnaClick2());
    }//GEN-LAST:event_btnSimpanMousePressed

    private void btnSimpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimpanMouseClicked
        simpanData();
    }//GEN-LAST:event_btnSimpanMouseClicked

    private void btnHapusMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHapusMouseEntered
        btnHapus.setBackground(Warna.INSTANCE.warnaHover2());
    }//GEN-LAST:event_btnHapusMouseEntered

    private void btnHapusMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHapusMouseExited
        btnHapus.setBackground(Warna.INSTANCE.orenMandarin());
    }//GEN-LAST:event_btnHapusMouseExited

    private void btnHapusMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHapusMousePressed
        btnHapus.setBackground(Warna.INSTANCE.warnaClick2());
    }//GEN-LAST:event_btnHapusMousePressed

    private void btnHapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHapusMouseClicked
        hapusData();
    }//GEN-LAST:event_btnHapusMouseClicked

    private void btnCariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCariMouseClicked
        cariBarang();
    }//GEN-LAST:event_btnCariMouseClicked

    private void btnRefreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRefreshMouseClicked
        tampilkanData();
        idCari.setText("");
    }//GEN-LAST:event_btnRefreshMouseClicked

    private void listDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listDataMouseClicked
        int baris = listData.rowAtPoint(evt.getPoint());
        String idBarang = listData.getValueAt(baris, 0).toString();
        
        idCari.setText(idBarang);
    }//GEN-LAST:event_listDataMouseClicked

    private void btnTambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTambahMouseClicked
        tambahBelanja();
        formKosong();
        setLabel();
    }//GEN-LAST:event_btnTambahMouseClicked

    private void idCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idCariActionPerformed
        cariData();
    }//GEN-LAST:event_idCariActionPerformed

    private void kJumlahKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kJumlahKeyTyped
        subTotal();
    }//GEN-LAST:event_kJumlahKeyTyped

    private void kIDBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kIDBarangActionPerformed
        cariBarang();
    }//GEN-LAST:event_kIDBarangActionPerformed

    private void btnHapusBelanjaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHapusBelanjaMouseClicked
        hapusBelanja();
    }//GEN-LAST:event_btnHapusBelanjaMouseClicked

    private void btnHapusBelanjaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHapusBelanjaMouseEntered
        btnHapusBelanja.setBackground(Warna.INSTANCE.warnaHover3());
    }//GEN-LAST:event_btnHapusBelanjaMouseEntered

    private void btnHapusBelanjaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHapusBelanjaMouseExited
        btnHapusBelanja.setBackground(Warna.INSTANCE.merahImperial());
    }//GEN-LAST:event_btnHapusBelanjaMouseExited

    private void btnHapusBelanjaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHapusBelanjaMousePressed
        btnHapusBelanja.setBackground(Warna.INSTANCE.warnaClick3());
    }//GEN-LAST:event_btnHapusBelanjaMousePressed

    private void kBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kBayarActionPerformed
        kembalian();
    }//GEN-LAST:event_kBayarActionPerformed

    private void btnUpdateStokMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateStokMouseEntered
        btnUpdateStok.setBackground(Warna.INSTANCE.warnaHover1());
    }//GEN-LAST:event_btnUpdateStokMouseEntered

    private void btnUpdateStokMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateStokMouseExited
        btnUpdateStok.setBackground(Warna.INSTANCE.biruBlackCoral());
    }//GEN-LAST:event_btnUpdateStokMouseExited

    private void btnUpdateStokMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateStokMousePressed
        btnUpdateStok.setBackground(Warna.INSTANCE.warnaClick1());
    }//GEN-LAST:event_btnUpdateStokMousePressed

    private void btnUpdateStokMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateStokMouseClicked
        tambahStokBarang();
    }//GEN-LAST:event_btnUpdateStokMouseClicked

    private void btnCetakMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCetakMouseClicked
        penjualan();
    }//GEN-LAST:event_btnCetakMouseClicked

    private void kBayarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kBayarKeyTyped
        
    }//GEN-LAST:event_kBayarKeyTyped

    private void kBayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kBayarKeyPressed
        kembalian();
    }//GEN-LAST:event_kBayarKeyPressed

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private kasirapp.PanelTumpul background;
    private kasirapp.PanelTumpul btnCari;
    private kasirapp.PanelTumpul btnCetak;
    private kasirapp.PanelTumpul btnExit;
    private kasirapp.PanelTumpul btnHapus;
    private kasirapp.PanelTumpul btnHapusBelanja;
    private kasirapp.PanelTumpul btnRefresh;
    private kasirapp.PanelTumpul btnSimpan;
    private kasirapp.PanelTumpul btnTambah;
    private kasirapp.PanelTumpul btnUpdateStok;
    private kasirapp.PanelTumpul fieldTotal;
    private kasirapp.PanelTumpul header;
    private javax.swing.JTextField idCari;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField kBayar;
    private javax.swing.JTextField kHarga;
    private javax.swing.JTextField kIDBarang;
    private javax.swing.JTextField kJumlah;
    private javax.swing.JTextField kKembali;
    private javax.swing.JTextField kNamaBarang;
    private javax.swing.JTextField kSubTotal;
    private javax.swing.JLabel labelExit;
    private javax.swing.JLabel labelHarga;
    private javax.swing.JLabel labelID;
    private javax.swing.JLabel labelJudul;
    private javax.swing.JLabel labelJumlah;
    private javax.swing.JLabel labelKasir;
    private javax.swing.JLabel labelListData;
    private javax.swing.JLabel labelMenu;
    private javax.swing.JLabel labelNamaB;
    private javax.swing.JLabel labelStok;
    private javax.swing.JLabel labelSub;
    private javax.swing.JLabel labelTagihan;
    private javax.swing.JTable listBelanja;
    private javax.swing.JTable listData;
    private kasirapp.PanelTumpul menuKasir;
    private kasirapp.PanelTumpul menuListData;
    private kasirapp.PanelTumpul menuStokBarang;
    private kasirapp.PanelTumpul panelKasir;
    private kasirapp.PanelTumpul panelListData;
    private kasirapp.PanelTumpul panelMain;
    private kasirapp.PanelTumpul panelMenu;
    private kasirapp.PanelTumpul panelStok;
    private javax.swing.JTextField sbHarga;
    private javax.swing.JTextField sbNamaBarang;
    private javax.swing.JTextField sbStok;
    private javax.swing.JTextField sbidBarang;
    // End of variables declaration//GEN-END:variables

    
}
