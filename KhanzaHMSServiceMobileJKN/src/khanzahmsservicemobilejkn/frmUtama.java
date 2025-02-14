/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanzahmsservicemobilejkn;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.ApiMobileJKN;
import fungsi.koneksiDB;
import fungsi.sekuel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import javax.swing.Timer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

/**
 *
 * @author windiartonugroho
 */
public class frmUtama extends javax.swing.JFrame {
    private  Properties prop = new Properties();
    private  Connection koneksi=koneksiDB.condb();
    private  sekuel Sequel=new sekuel();
    private  String requestJson,URL="",kodeppk=Sequel.cariIsi("select kode_ppk from setting"),utc="",link="",datajam="",
              nol_jam = "",nol_menit = "",nol_detik = "",jam="",menit="",detik="";
    private  ApiMobileJKN api=new ApiMobileJKN();
    private  HttpHeaders headers;
    private  HttpEntity requestEntity;
    private  ObjectMapper mapper= new ObjectMapper();
    private  JsonNode root;
    private  JsonNode nameNode;
    private  JsonNode response;
    private  PreparedStatement ps;
    private  ResultSet rs;
    private  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private  Date parsedDate;

    /**
     * Creates new form frmUtama
     */
    public frmUtama() {
        initComponents();
        try {
            link=koneksiDB.URLAPIMOBILEJKN();
        } catch (Exception e) {
            System.out.println("E : "+e);
        }
        
        this.setSize(390,340);
        
        jam();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        TeksArea = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SIMKES Khanza Service Mobile JKN");

        TeksArea.setColumns(20);
        TeksArea.setRows(5);
        jScrollPane1.setViewportView(TeksArea);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jButton1.setText("Keluar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(frmUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmUtama().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea TeksArea;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    private void jam(){
        ActionListener taskPerformer = new ActionListener(){
            private int nilai_jam;
            private int nilai_menit;
            private int nilai_detik;
            public void actionPerformed(ActionEvent e) {
                nol_jam = "";
                nol_menit = "";
                nol_detik = "";
                Date now = Calendar.getInstance().getTime();
                // Mengambil nilaj JAM, MENIT, dan DETIK Sekarang
                nilai_jam = now.getHours();
                nilai_menit = now.getMinutes();
                nilai_detik = now.getSeconds();
                // Jika nilai JAM lebih kecil dari 10 (hanya 1 digit)
                if (nilai_jam <= 9) {
                    // Tambahkan "0" didepannya
                    nol_jam = "0";
                }
                // Jika nilai MENIT lebih kecil dari 10 (hanya 1 digit)
                if (nilai_menit <= 9) {
                    // Tambahkan "0" didepannya
                    nol_menit = "0";
                }
                // Jika nilai DETIK lebih kecil dari 10 (hanya 1 digit)
                if (nilai_detik <= 9) {
                    // Tambahkan "0" didepannya
                    nol_detik = "0";
                }
                // Membuat String JAM, MENIT, DETIK
                jam = nol_jam + Integer.toString(nilai_jam);
                menit = nol_menit + Integer.toString(nilai_menit);
                detik = nol_detik + Integer.toString(nilai_detik);
                if(detik.equals("01")&&((nilai_menit%3)==0)){
                    if(jam.equals("01")&&menit.equals("01")&&detik.equals("01")){
                        TeksArea.setText("");
                    }
                    
                    try {
                        koneksi=koneksiDB.condb();
                        TeksArea.append("Menjalankan WS tambah antrian Mobile JKN BPJS\n");
                        ps=koneksi.prepareStatement(
                                "SELECT referensi_mobilejkn_bpjs.nobooking,referensi_mobilejkn_bpjs.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,referensi_mobilejkn_bpjs.nohp,referensi_mobilejkn_bpjs.nomorkartu,"+
                                "referensi_mobilejkn_bpjs.nik,referensi_mobilejkn_bpjs.tanggalperiksa,poliklinik.nm_poli,dokter.nm_dokter,referensi_mobilejkn_bpjs.jampraktek,"+
                                "referensi_mobilejkn_bpjs.jeniskunjungan,referensi_mobilejkn_bpjs.nomorreferensi,referensi_mobilejkn_bpjs.status,referensi_mobilejkn_bpjs.validasi,"+
                                "referensi_mobilejkn_bpjs.kodepoli,referensi_mobilejkn_bpjs.pasienbaru,referensi_mobilejkn_bpjs.kodedokter,referensi_mobilejkn_bpjs.jampraktek,"+
                                "referensi_mobilejkn_bpjs.nomorantrean,referensi_mobilejkn_bpjs.angkaantrean,referensi_mobilejkn_bpjs.estimasidilayani,referensi_mobilejkn_bpjs.sisakuotajkn,"+
                                "referensi_mobilejkn_bpjs.kuotajkn,referensi_mobilejkn_bpjs.sisakuotanonjkn,referensi_mobilejkn_bpjs.kuotanonjkn "+
                                "FROM referensi_mobilejkn_bpjs INNER JOIN reg_periksa ON referensi_mobilejkn_bpjs.no_rawat=reg_periksa.no_rawat "+
                                "INNER JOIN pasien ON reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                                "INNER JOIN poliklinik ON reg_periksa.kd_poli=poliklinik.kd_poli "+
                                "INNER JOIN dokter ON reg_periksa.kd_dokter=dokter.kd_dokter "+
                                "WHERE referensi_mobilejkn_bpjs.statuskirim='Belum' and referensi_mobilejkn_bpjs.tanggalperiksa=current_date() "+
                                "order by referensi_mobilejkn_bpjs.tanggalperiksa");
                        try {
                            rs=ps.executeQuery();
                            while(rs.next()){
                                try {     
                                    headers = new HttpHeaders();
                                    headers.setContentType(MediaType.APPLICATION_JSON);
                                    headers.add("x-cons-id",koneksiDB.CONSIDAPIMOBILEJKN());
                                    utc=String.valueOf(api.GetUTCdatetimeAsString());
                                    headers.add("x-timestamp",utc);
                                    headers.add("x-signature",api.getHmac(utc));
                                    headers.add("user_key",koneksiDB.USERKEYAPIMOBILEJKN());
                                    requestJson ="{" +
                                                    "\"kodebooking\": \""+rs.getString("nobooking")+"\"," +
                                                    "\"jenispasien\": \"JKN\"," +
                                                    "\"nomorkartu\": \""+rs.getString("nomorkartu")+"\"," +
                                                    "\"nik\": \""+rs.getString("nik")+"\"," +
                                                    "\"nohp\": \""+rs.getString("nohp")+"\"," +
                                                    "\"kodepoli\": \""+rs.getString("kodepoli")+"\"," +
                                                    "\"namapoli\": \""+rs.getString("nm_poli")+"\"," +
                                                    "\"pasienbaru\": "+rs.getString("pasienbaru")+"," +
                                                    "\"norm\": \""+rs.getString("no_rkm_medis")+"\"," +
                                                    "\"tanggalperiksa\": \""+rs.getString("tanggalperiksa")+"\"," +
                                                    "\"kodedokter\": "+rs.getString("kodedokter")+"," +
                                                    "\"namadokter\": \""+rs.getString("nm_dokter")+"\"," +
                                                    "\"jampraktek\": \""+rs.getString("jampraktek")+"\"," +
                                                    "\"jeniskunjungan\": "+rs.getString("jeniskunjungan").substring(0,1)+"," +
                                                    "\"nomorreferensi\": \""+rs.getString("nomorreferensi")+"\"," +
                                                    "\"nomorantrean\": \""+rs.getString("nomorantrean")+"\"," +
                                                    "\"angkaantrean\": "+Integer.parseInt(rs.getString("angkaantrean"))+"," +
                                                    "\"estimasidilayani\": "+rs.getString("estimasidilayani")+"," +
                                                    "\"sisakuotajkn\": "+rs.getString("sisakuotajkn")+"," +
                                                    "\"kuotajkn\": "+rs.getString("kuotajkn")+"," +
                                                    "\"sisakuotanonjkn\": "+rs.getString("sisakuotanonjkn")+"," +
                                                    "\"kuotanonjkn\": "+rs.getString("kuotanonjkn")+"," +
                                                    "\"keterangan\": \"Peserta harap 30 menit lebih awal guna pencatatan administrasi.\"" +
                                                "}";
                                    TeksArea.append("JSON : "+requestJson+"\n");
                                    requestEntity = new HttpEntity(requestJson,headers);
                                    URL = link+"/antrean/add";	
                                    System.out.println("URL : "+URL);
                                    //System.out.println(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                                    root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                                    nameNode = root.path("metadata");
                                    if(nameNode.path("code").asText().equals("200")){
                                        Sequel.queryu2("update referensi_mobilejkn_bpjs set statuskirim='Sudah' where nomorreferensi='"+rs.getString("nomorreferensi")+"'");
                                    }  
                                    TeksArea.append("respon WS BPJS : "+nameNode.path("code").asText()+" "+nameNode.path("message").asText()+"\n");
                                }catch (Exception ex) {
                                    System.out.println("Notifikasi Bridging : "+ex);
                                }
                            }
                        } catch (Exception ex) {
                            System.out.println("Notif Ketersediaan : "+ex);
                        } finally{
                            if(rs!=null){
                                rs.close();
                            }
                            if(ps!=null){
                                ps.close();
                            }
                        }
                        
                        TeksArea.append("Menjalankan WS batal antrian Mobile JKN BPJS\n");
                        ps=koneksi.prepareStatement(
                                "SELECT * FROM referensi_mobilejkn_bpjs_batal where statuskirim='Belum' and tanggalbatal=current_date()");
                        try {
                            rs=ps.executeQuery();
                            while(rs.next()){
                                try {     
                                    headers = new HttpHeaders();
                                    headers.setContentType(MediaType.APPLICATION_JSON);
                                    headers.add("x-cons-id",koneksiDB.CONSIDAPIMOBILEJKN());
                                    utc=String.valueOf(api.GetUTCdatetimeAsString());
                                    headers.add("x-timestamp",utc);
                                    headers.add("x-signature",api.getHmac(utc));
                                    headers.add("user_key",koneksiDB.USERKEYAPIMOBILEJKN());
                                    requestJson ="{" +
                                                     "\"kodebooking\": \""+rs.getString("nobooking")+"\"," +
                                                     "\"keterangan\": \""+rs.getString("keterangan")+"\"" +
                                                  "}";
                                    TeksArea.append("JSON : "+requestJson+"\n");
                                    requestEntity = new HttpEntity(requestJson,headers);
                                    URL = link+"/antrean/batal";	
                                    System.out.println("URL : "+URL);
                                    //System.out.println(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                                    root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                                    nameNode = root.path("metadata");
                                    if(nameNode.path("code").asText().equals("200")){
                                        Sequel.queryu2("update referensi_mobilejkn_bpjs_batal set statuskirim='Sudah' where nomorreferensi='"+rs.getString("nomorreferensi")+"'");
                                    }  
                                    TeksArea.append("respon WS BPJS : "+nameNode.path("code").asText()+" "+nameNode.path("message").asText()+"\n");
                                }catch (Exception ex) {
                                    System.out.println("Notifikasi Bridging : "+ex);
                                }
                            }
                        } catch (Exception ex) {
                            System.out.println("Notif Ketersediaan : "+ex);
                        } finally{
                            if(rs!=null){
                                rs.close();
                            }
                            if(ps!=null){
                                ps.close();
                            }
                        }
                        
                        ps=koneksi.prepareStatement(
                                "SELECT referensi_mobilejkn_bpjs.nobooking,referensi_mobilejkn_bpjs.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,referensi_mobilejkn_bpjs.nohp,referensi_mobilejkn_bpjs.nomorkartu,"+
                                "referensi_mobilejkn_bpjs.nik,referensi_mobilejkn_bpjs.tanggalperiksa,poliklinik.nm_poli,dokter.nm_dokter,referensi_mobilejkn_bpjs.jampraktek,"+
                                "referensi_mobilejkn_bpjs.jeniskunjungan,referensi_mobilejkn_bpjs.nomorreferensi,referensi_mobilejkn_bpjs.status,referensi_mobilejkn_bpjs.validasi,"+
                                "referensi_mobilejkn_bpjs.kodepoli,referensi_mobilejkn_bpjs.pasienbaru,referensi_mobilejkn_bpjs.kodedokter,referensi_mobilejkn_bpjs.jampraktek,"+
                                "referensi_mobilejkn_bpjs.nomorantrean,referensi_mobilejkn_bpjs.angkaantrean,referensi_mobilejkn_bpjs.estimasidilayani,referensi_mobilejkn_bpjs.sisakuotajkn,"+
                                "referensi_mobilejkn_bpjs.kuotajkn,referensi_mobilejkn_bpjs.sisakuotanonjkn,referensi_mobilejkn_bpjs.kuotanonjkn "+
                                "FROM referensi_mobilejkn_bpjs INNER JOIN reg_periksa ON referensi_mobilejkn_bpjs.no_rawat=reg_periksa.no_rawat "+
                                "INNER JOIN pasien ON reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                                "INNER JOIN poliklinik ON reg_periksa.kd_poli=poliklinik.kd_poli "+
                                "INNER JOIN dokter ON reg_periksa.kd_dokter=dokter.kd_dokter "+
                                "WHERE referensi_mobilejkn_bpjs.statuskirim='Sudah' and referensi_mobilejkn_bpjs.tanggalperiksa=current_date() "+
                                "order by referensi_mobilejkn_bpjs.tanggalperiksa");
                        try {
                            rs=ps.executeQuery();
                            while(rs.next()){
                                datajam=Sequel.cariIsi("select dikirim from mutasi_berkas where no_rawat=? and dikirim<>'0000-00-00 00:00:00'",rs.getString("no_rawat"));
                                if(!datajam.equals("")){
                                    if(Sequel.menyimpantf2("referensi_mobilejkn_bpjs_taskid","?,?,?","task id",3,new String[]{rs.getString("no_rawat"),"3",datajam})==true){
                                        parsedDate = dateFormat.parse(datajam);
                                        try {     
                                            TeksArea.append("Menjalankan WS taskid mulai tunggu poli Mobile JKN BPJS\n");
                                            headers = new HttpHeaders();
                                            headers.setContentType(MediaType.APPLICATION_JSON);
                                            headers.add("x-cons-id",koneksiDB.CONSIDAPIMOBILEJKN());
                                            utc=String.valueOf(api.GetUTCdatetimeAsString());
                                            headers.add("x-timestamp",utc);
                                            headers.add("x-signature",api.getHmac(utc));
                                            headers.add("user_key",koneksiDB.USERKEYAPIMOBILEJKN());
                                            requestJson ="{" +
                                                             "\"kodebooking\": \""+rs.getString("nobooking")+"\"," +
                                                             "\"taskid\": \"3\"," +
                                                             "\"waktu\": \""+parsedDate.getTime()+"\"" +
                                                          "}";
                                            TeksArea.append("JSON : "+requestJson+"\n");
                                            requestEntity = new HttpEntity(requestJson,headers);
                                            URL = link+"/antrean/updatewaktu";	
                                            System.out.println("URL : "+URL);
                                            //System.out.println(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                                            root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                                            nameNode = root.path("metadata");
                                            if(!nameNode.path("code").asText().equals("200")){
                                                Sequel.queryu2("delete from referensi_mobilejkn_bpjs_taskid where taskid='3' and no_rawat='"+rs.getString("no_rawat")+"'");
                                            }  
                                            TeksArea.append("respon WS BPJS : "+nameNode.path("code").asText()+" "+nameNode.path("message").asText()+"\n");
                                        }catch (Exception ex) {
                                            System.out.println("Notifikasi Bridging : "+ex);
                                        }
                                    }
                                }
                                
                                datajam=Sequel.cariIsi("select diterima from mutasi_berkas where no_rawat=? and diterima<>'0000-00-00 00:00:00'",rs.getString("no_rawat"));
                                if(!datajam.equals("")){
                                    if(Sequel.menyimpantf2("referensi_mobilejkn_bpjs_taskid","?,?,?","task id",3,new String[]{rs.getString("no_rawat"),"4",datajam})==true){
                                        parsedDate = dateFormat.parse(datajam);
                                        try {     
                                            TeksArea.append("Menjalankan WS taskid mulai pelayanan poli Mobile JKN BPJS\n");
                                            headers = new HttpHeaders();
                                            headers.setContentType(MediaType.APPLICATION_JSON);
                                            headers.add("x-cons-id",koneksiDB.CONSIDAPIMOBILEJKN());
                                            utc=String.valueOf(api.GetUTCdatetimeAsString());
                                            headers.add("x-timestamp",utc);
                                            headers.add("x-signature",api.getHmac(utc));
                                            headers.add("user_key",koneksiDB.USERKEYAPIMOBILEJKN());
                                            requestJson ="{" +
                                                             "\"kodebooking\": \""+rs.getString("nobooking")+"\"," +
                                                             "\"taskid\": \"4\"," +
                                                             "\"waktu\": \""+parsedDate.getTime()+"\"" +
                                                          "}";
                                            TeksArea.append("JSON : "+requestJson+"\n");
                                            requestEntity = new HttpEntity(requestJson,headers);
                                            URL = link+"/antrean/updatewaktu";	
                                            System.out.println("URL : "+URL);
                                            //System.out.println(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                                            root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                                            nameNode = root.path("metadata");
                                            if(!nameNode.path("code").asText().equals("200")){
                                                Sequel.queryu2("delete from referensi_mobilejkn_bpjs_taskid where taskid='4' and no_rawat='"+rs.getString("no_rawat")+"'");
                                            }   
                                            TeksArea.append("respon WS BPJS : "+nameNode.path("code").asText()+" "+nameNode.path("message").asText()+"\n");
                                        }catch (Exception ex) {
                                            System.out.println("Notifikasi Bridging : "+ex);
                                        }
                                    }
                                }
                                
                                datajam=Sequel.cariIsi("select concat(tgl_perawatan,' ',jam_rawat) from pemeriksaan_ralan where no_rawat=?",rs.getString("no_rawat"));
                                if(!datajam.equals("")){
                                    if(Sequel.menyimpantf2("referensi_mobilejkn_bpjs_taskid","?,?,?","task id",3,new String[]{rs.getString("no_rawat"),"5",datajam})==true){
                                        parsedDate = dateFormat.parse(datajam);
                                        try {     
                                            TeksArea.append("Menjalankan WS taskid selesai pelayanan poli Mobile JKN BPJS\n");
                                            headers = new HttpHeaders();
                                            headers.setContentType(MediaType.APPLICATION_JSON);
                                            headers.add("x-cons-id",koneksiDB.CONSIDAPIMOBILEJKN());
                                            utc=String.valueOf(api.GetUTCdatetimeAsString());
                                            headers.add("x-timestamp",utc);
                                            headers.add("x-signature",api.getHmac(utc));
                                            headers.add("user_key",koneksiDB.USERKEYAPIMOBILEJKN());
                                            requestJson ="{" +
                                                             "\"kodebooking\": \""+rs.getString("nobooking")+"\"," +
                                                             "\"taskid\": \"5\"," +
                                                             "\"waktu\": \""+parsedDate.getTime()+"\"" +
                                                          "}";
                                            TeksArea.append("JSON : "+requestJson+"\n");
                                            requestEntity = new HttpEntity(requestJson,headers);
                                            URL = link+"/antrean/updatewaktu";	
                                            System.out.println("URL : "+URL);
                                            //System.out.println(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                                            root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                                            nameNode = root.path("metadata");
                                            if(!nameNode.path("code").asText().equals("200")){
                                                Sequel.queryu2("delete from referensi_mobilejkn_bpjs_taskid where taskid='5' and no_rawat='"+rs.getString("no_rawat")+"'");
                                            }  
                                            TeksArea.append("respon WS BPJS : "+nameNode.path("code").asText()+" "+nameNode.path("message").asText()+"\n");
                                        }catch (Exception ex) {
                                            System.out.println("Notifikasi Bridging : "+ex);
                                        }
                                    }
                                }
                            }
                        } catch (Exception ex) {
                            System.out.println("Notif Ketersediaan : "+ex);
                        } finally{
                            if(rs!=null){
                                rs.close();
                            }
                            if(ps!=null){
                                ps.close();
                            }
                        }
                        TeksArea.append("Proses update selesai\n");
                    } catch (Exception ez) {
                        System.out.println("Notif : "+ez);
                    }
                }
            }
        };
        // Timer
        new Timer(1000, taskPerformer).start();
    }
}
