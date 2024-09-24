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
        yemekDao.tumYemekleriGetir()
    }

    suspend fun sepeteYemekEkle(
        yemekAdi: String,
        yemekResimAdi: String,
        yemekFiyat: Int,
        yemekSiparisAdet: Int,
        kullaniciAdi: String
    ): CRUDCevap = withContext(Dispatchers.IO) {
        yemekDao.sepeteYemekEkle(yemekAdi, yemekResimAdi, yemekFiyat, yemekSiparisAdet, kullaniciAdi)
    }

    suspend fun sepettenYemekSil(sepetYemekId: Int, kullaniciAdi: String): CRUDCevap = withContext(Dispatchers.IO) {
        yemekDao.sepettenYemekSil(sepetYemekId, kullaniciAdi)
    }

    suspend fun tumSepetYemekleriGetir(): List<SepetYemek> = withContext(Dispatchers.IO) {
        yemekDao.tumSepetYemekleriGetir()
    }
}

