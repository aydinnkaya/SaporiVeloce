package com.aydinkaya.saporiveloce.data.datasource

import android.util.Log
import com.aydinkaya.saporiveloce.data.entity.CRUDCevap
import com.aydinkaya.saporiveloce.data.entity.SepetYemek
import com.aydinkaya.saporiveloce.data.entity.YemekResponse
import com.aydinkaya.saporiveloce.retrofit.YemekDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class YemekDataSource @Inject constructor(private val yemekDao: YemekDao) {

    suspend fun tumYemekleriGetir(): YemekResponse = withContext(Dispatchers.IO) {
        try {
            val response = yemekDao.tumYemekleriGetir()
            if (response.isSuccessful) {
                Log.d("YemekDataSource", "Yemekler başarıyla getirildi.")
                response.body() ?: YemekResponse(yemekler = emptyList(), success = 0)
            } else {
                Log.e("YemekDataSource", "Yemekler getirilemedi, hata kodu: ${response.code()}")
                YemekResponse(yemekler = emptyList(), success = 0)
            }
        } catch (e: Exception) {
            Log.e("YemekDataSource", "Yemekleri getirirken hata oluştu: ${e.message}")
            YemekResponse(yemekler = emptyList(), success = 0)
        }
    }

    suspend fun sepeteYemekEkle(
        yemekAdi: String,
        yemekResimAdi: String,
        yemekFiyat: Int,
        yemekSiparisAdet: Int,
        kullaniciAdi: String
    ): CRUDCevap = withContext(Dispatchers.IO) {
        try {
            val response = yemekDao.sepeteYemekEkle(yemekAdi, yemekResimAdi, yemekFiyat, yemekSiparisAdet, kullaniciAdi)
            if (response.isSuccessful) {
                Log.d("YemekDataSource", "$yemekAdi sepete başarıyla eklendi.")
                response.body() ?: CRUDCevap(success = 0, message = "Bilinmeyen hata")
            } else {
                Log.e("YemekDataSource", "Sepete ekleme hatası: ${response.code()}")
                CRUDCevap(success = 0, message = "Sepete ekleme hatası")
            }
        } catch (e: Exception) {
            Log.e("YemekDataSource", "Sepete eklerken hata: ${e.message}")
            CRUDCevap(success = 0, message = "Sepete ekleme hatası: ${e.message}")
        }
    }

    suspend fun sepettenYemekSil(sepetYemekId: Int, kullaniciAdi: String): CRUDCevap = withContext(Dispatchers.IO) {
        try {
            val response = yemekDao.sepettenYemekSil(sepetYemekId, kullaniciAdi)
            if (response.isSuccessful) {
                Log.d("YemekDataSource", "Yemek başarıyla sepetten silindi.")
                response.body() ?: CRUDCevap(success = 0, message = "Bilinmeyen hata")
            } else {
                Log.e("YemekDataSource", "Sepetten silme hatası: ${response.code()}")
                CRUDCevap(success = 0, message = "Sepetten silme hatası")
            }
        } catch (e: Exception) {
            Log.e("YemekDataSource", "Sepetten silerken hata: ${e.message}")
            CRUDCevap(success = 0, message = "Sepetten silme hatası: ${e.message}")
        }
    }

    suspend fun tumSepetYemekleriGetir(): List<SepetYemek> = withContext(Dispatchers.IO) {
        try {
            val response = yemekDao.tumSepetYemekleriGetir()
            if (response.isSuccessful) {
                Log.d("YemekDataSource", "Sepet yemekleri başarıyla getirildi.")
                response.body() ?: emptyList()
            } else {
                Log.e("YemekDataSource", "Sepet yemekleri getirilemedi, hata kodu: ${response.code()}")
                emptyList()
            }
        } catch (e: Exception) {
            Log.e("YemekDataSource", "Sepet yemeklerini getirirken hata: ${e.message}")
            emptyList()
        }
    }
}

