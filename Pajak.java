/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.oke
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
public abstract class Pajak extends Kas{

    public Pajak(String nama, String Tanggal, String keterangan,String sumberkas, String Tipe_mutasi, int nominal) {
        super(nama, Tanggal, keterangan,sumberkas, Tipe_mutasi, nominal);
    }
    
    // isi abstarck class
    abstract int hitungPajak();
    abstract int hitungTotal();
    
}
