package com.aydinkaya.saporiveloce.data.repo

import com.aydinkaya.saporiveloce.data.datasource.YemekDataSource
import com.aydinkaya.saporiveloce.data.entity.CRUDCevap
import com.aydinkaya.saporiveloce.data.entity.SepetYemek
import com.aydinkaya.saporiveloce.data.entity.Yemek
import javax.inject.Inject

class YemekRepository @Inject constructor(private val yemekDataSource: YemekDataSource) {

    suspend fun tumYemekleriGetir(): List<Yemek> {
        return try {
            val response = yemekDataSource.tumYemekleriGetir()
            response.yemekler
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun sepeteYemekEkle(sepetYemek: SepetYemek): CRUDCevap {
        return yemekDataSource.sepeteYemekEkle(
            sepetYemek.yemek_adi,
            sepetYemek.yemek_resim_adi,
            sepetYemek.yemek_fiyat,
            sepetYemek.yemek_siparis_adet,
            sepetYemek.kullanici_adi
        )
    }

    suspend fun sepettenYemekSil(sepetYemekId: Int, kullaniciAdi: String): CRUDCevap {
        return yemekDataSource.sepettenYemekSil(sepetYemekId, kullaniciAdi)
    }

    suspend fun tumSepetYemekleriGetir(): List<SepetYemek> {
        return yemekDataSource.tumSepetYemekleriGetir()
    }
}

