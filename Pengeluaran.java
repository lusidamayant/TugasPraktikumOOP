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
public class Pengeluaran extends Kas {
    public int no_pengeluaran;
    String jenis_pengeluaran;
    public int total_saldo;
    public int sisa_saldo;
     int no;
    String jenis;
    
    public Pengeluaran(String nama, String tanggal,String sumberkas, String keterangan, String tipe_mutasi, int nominal, int no, String jenis, int total_saldo, int sisa_saldo) {
        super(nama, tanggal, keterangan,sumberkas, tipe_mutasi, nominal);
        this.no_pengeluaran = no;
        this.jenis_pengeluaran = jenis;
        this.total_saldo = total_saldo;
        this.sisa_saldo = sisa_saldo;
        this.nominal=nominal;
    }  

    public int setno(){
      return no_pengeluaran;  
    } 
     public void getno(int no){
       this.no_pengeluaran=no;  
    } 
        
    public int gettotalsaldo() {
        return total_saldo;
    }
    public void settotalsaldo(int totalsaldo) {
        this.total_saldo = totalsaldo;
    }
     public int getsisasaldo(int sisasaldo) {
        return sisa_saldo;
    }
    public int sisa_saldo() {
        sisa_saldo = gettotalsaldo() - nominal;
        return sisa_saldo;
    }
     
    
}

   




  

    

