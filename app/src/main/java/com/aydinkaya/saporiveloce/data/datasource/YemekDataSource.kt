package com.aydinkaya.saporiveloce.data.datasource

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
                response.body() ?: YemekResponse(yemekler = emptyList(), success = 0)
            } else {
                YemekResponse(yemekler = emptyList(), success = 0)
            }
        } catch (e: Exception) {
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
                response.body() ?: CRUDCevap(success = 0, message = "Unknown error")
            } else {
                CRUDCevap(success = 0, message = "Error adding to cart")
            }
        } catch (e: Exception) {
            CRUDCevap(success = 0, message = "Error adding to cart: ${e.message}")
        }
    }

    suspend fun sepettenYemekSil(sepetYemekId: Int, kullaniciAdi: String): CRUDCevap = withContext(Dispatchers.IO) {
        try {
            val response = yemekDao.sepettenYemekSil(sepetYemekId, kullaniciAdi)
            if (response.isSuccessful) {
                response.body() ?: CRUDCevap(success = 0, message = "Unknown error")
            } else {
                CRUDCevap(success = 0, message = "Error removing from cart")
            }
        } catch (e: Exception) {
            CRUDCevap(success = 0, message = "Error removing from cart: ${e.message}")
        }
    }

    suspend fun tumSepetYemekleriGetir(): List<SepetYemek> = withContext(Dispatchers.IO) {
        try {
            val response = yemekDao.tumSepetYemekleriGetir()
            if (response.isSuccessful) {
                response.body() ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
