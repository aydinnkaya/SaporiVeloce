package com.aydinkaya.saporiveloce.data.datasource

import android.util.Log
import com.aydinkaya.saporiveloce.data.entity.CRUDCevap
import com.aydinkaya.saporiveloce.data.entity.SepettekiYemeklerCevap
import com.aydinkaya.saporiveloce.data.entity.Yemekler
import com.example.graduationproject.retrofit.YemeklerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class YemeklerDataSource (var ydao: YemeklerDao){

    suspend fun yemekleriYukle() : List<Yemekler> = withContext(Dispatchers.IO){
        return@withContext ydao.yemekleriYukle().yemekler
    }

    suspend fun kaydet(yemek_adi:String,yemek_resim_adi:String,yemek_fiyat:Int,yemek_siparis_adet:Int,kullanici_adi:String){
        ydao.kaydet(yemek_adi, yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)
    }

    suspend fun sepettekiYemekleriGetir(kullanici_adi: String): SepettekiYemeklerCevap? = withContext(Dispatchers.IO) {
        try {
            val response = ydao.sepettekiYemekleriGetir(kullanici_adi)
            if (response == null) {
                Log.e("YemeklerDataSource", "Boş yanıt alındı!")
            }
            return@withContext response
        } catch (e: Exception) {
            Log.e("YemeklerDataSource", "Hata: ${e.message}")
            return@withContext null
        }
    }


    suspend fun sil(sepet_yemek_id: Int, kullanici_adi: String): CRUDCevap {
        return withContext(Dispatchers.IO) {
            ydao.sil(sepet_yemek_id, kullanici_adi)
        }
    }




}