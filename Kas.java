  /*
 * To change this license header , choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugasrumah10;
import exception.*;
import cobainterface.*;
import bab7.*;
import TUGASBAB1.*;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Lusi Damayanti
 */
public class Kas implements kalkulasiKeuangan{
    String nama;
    String Tanggal;
    String Keterangan,sumberkas;
    String Tipe_mutasi;
    public int nominal, pajak, nominalPajak, totalNominal, total_saldo;
    String nilai_matauang;

    
     // Konstruktor dengan parameter
    public Kas(String nama,String Tanggal,String sumberkas,String keterangan,String Tipe_mutasi,int nominal){
        this.nama=nama;
        this.Tanggal=Tanggal;
        this.Keterangan=keterangan;
        this.sumberkas=sumberkas;
        this.nominal=nominal;
        this.Tipe_mutasi=Tipe_mutasi;
        this.pajak = 0;
        this.nominalPajak = 0;
        this.totalNominal = nominal;
    }
    
    // Metode mengembalikan nilai dari variabel
    public int getNominal() {
        return nominal;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }
   // Metode
    String hasilNama() {
        return nama;
    }

    String hasilTanggal() {
        return Tanggal;
    }

    String hasilKeterangan() {
        return Keterangan;
    }
     String hasilSumberkas() {
        return sumberkas;
    }

    String hasilMutasi() {
        return Tipe_mutasi;
    }
    // Metode overloading 
    String cetakBuku(){
        String hasil = "";
        hasil += "Nama : "+nama+" \n";
        hasil += "Tanggal : "+Tanggal+" \n";
        hasil += "Sumber Kas : "+sumberkas+" \n";
        hasil += "Keterangan : "+Keterangan+" \n";
        hasil += "Nominal : "+nominal+" \n";
        hasil += "Tipe Mutasi : "+Tipe_mutasi+" \n";
        return hasil;
    }
    
    String cetakBuku(int pajak){
        String hasil = "";
        hasil += "Nama : "+nama+" \n";
        hasil += "Tanggal : "+Tanggal+" \n";
        hasil += "Sumber Kas : "+sumberkas+" \n";
        hasil += "Tipe Mutasi : "+Tipe_mutasi+" \n";
        hasil += "Keterangan : "+Keterangan+" \n";
        hasil += "Nominal : "+nominal+" \n";
        HitungPajak hitPajak = new HitungPajak(nama, Tanggal,sumberkas, Tipe_mutasi, Keterangan, this.nominal);
        hitPajak.pajak = pajak;
        nominalPajak = hitPajak.hitungPajak();
        totalNominal = hitPajak.hitungTotal();
        hasil += "Pajak : "+pajak+"% \n";
        hasil += "Nominal Pajak : "+String.valueOf(nominalPajak)+" \n";
        hasil += "Total Nominal: "+String.valueOf(totalNominal)+" \n";
        return hasil;
    }
    
    int hitungPajak(){
        return 0;
    }

    int hitungTotal(){
        return nominal;
    }
    
    // mengembalikan objek getdatalist
    List getDataList(){
        List data = new ArrayList<>();
        data.add(nama);
        data.add(Tanggal);
        data.add(sumberkas);
        data.add(Keterangan);
        data.add(nominal);
        data.add(Tipe_mutasi);
        data.add(pajak);
        data.add(nominalPajak);
        data.add(totalNominal);
        
        return data;
    }

    @Override
    public int hitSaldo(String tipe, int nominal, int saldo) {
        tipe = tipe.toLowerCase();
        if(tipe.equals("pemasukan")){
            total_saldo = saldo + nominal;
        }else{
            total_saldo = saldo - nominal;
        }
        
        return total_saldo;
    }
    
 }
   

   

        
    



