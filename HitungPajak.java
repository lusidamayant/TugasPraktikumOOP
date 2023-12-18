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
public class HitungPajak extends Kas {
    int pajak = 0;
    
    HitungPajak(String nama, String tanggal,String sumberkas, String keterangan, String tipe_mutasi, int nominal){
         super(nama, tanggal, keterangan,sumberkas, tipe_mutasi, nominal);
    }
    //override
    
    @Override
     public int hitungPajak(){
        int hasilPajak = nominal * pajak / 100;
        return hasilPajak;
    }
    @Override
    public int hitungTotal(){
        int total = hitungPajak() + nominal;
        return total;
    }
   
}
