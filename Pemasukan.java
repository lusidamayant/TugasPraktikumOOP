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

/**
 *
 * @author Lusi Damayanti
 */
public class Pemasukan extends Kas{
    public int no_pemasukan;
    String jenis_pemasukan;
    public int total_saldo;
    private int sisa_saldo;
    
   public Pemasukan(String nama, String tanggal,String sumberkas, String keterangan, String tipe_mutasi, int nominal, int no, String jenis, int total_saldo, int sisa_saldo) {
        super(nama, tanggal, keterangan,sumberkas, tipe_mutasi, nominal);
        this.no_pemasukan = no;
        this.jenis_pemasukan = jenis;
        this.total_saldo = total_saldo;
        this.sisa_saldo = sisa_saldo;
        this.nominal = nominal;
    }
 
    public int setno(){
      return no_pemasukan;  
    } 
     public void getno(int no){
      this.no_pemasukan=no; 
    } 
            
    public int getsisasaldo() {
        return sisa_saldo;
    }
    
    public void setsisasaldo(int sisasaldo) {
        this.sisa_saldo = sisasaldo;      
    }   
    
   
    public int total_saldo() {
        total_saldo = getsisasaldo() + nominal;
        return total_saldo;
    }
    
}



   






