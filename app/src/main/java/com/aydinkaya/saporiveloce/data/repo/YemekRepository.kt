package com.aydinkaya.saporiveloce.data.repo

import android.util.Log
import com.aydinkaya.saporiveloce.data.datasource.YemekDataSource
import com.aydinkaya.saporiveloce.data.entity.CRUDCevap
import com.aydinkaya.saporiveloce.data.entity.SepetYemek
import com.aydinkaya.saporiveloce.data.entity.Yemek
import javax.inject.Inject

class YemekRepository @Inject constructor(private val yemekDataSource: YemekDataSource) {

    private val yemekAciklamalar = mapOf(
        1 to "250 ml. 120 calories.\nA refreshing and traditional drink.",
        2 to "150 g. 400 calories.\nLayered phyllo dough and rich pistachio traditional dessert.",
        3 to "330 ml. 150 calories.\nOrange-flavored carbonated soft drink.",
        4 to "200 g. 300 calories.\nDelicious grilled salmon.",
        5 to "180 g. 250 calories.\nHealthy and flavorful grilled chicken.",
        6 to "200 g. 450 calories.\nCrispy and syrupy kadayif dessert.",
        7 to "200 ml. 2 calories.\nFreshly brewed coffee from roasted beans.",
        8 to "150 g. 350 calories.\nGrilled meatballs enriched with savory spices.",
        9 to "250 g. 500 calories.\nLayered and flavorful lasagna.",
        10 to "250 g. 450 calories.\nRich and saucy pasta dish.",
        11 to "500 g. 800 calories.\nThin-crust pizza loaded with toppings.",
        12 to "500 ml. 0 calories.\nPure and clean water, essential with every meal.",
        13 to "200 g. 250 calories.\nDelicious rice pudding topped with cinnamon.",
        14 to "200 g. 350 calories.\nFamous Italian dessert with cream and coffee flavors, tiramisu."
    )

    suspend fun tumYemekleriGetir(): List<Yemek> {
        return try {
            val response = yemekDataSource.tumYemekleriGetir()
            Log.d("YemekRepository", "Tüm yemekler başarıyla alındı: ${response.yemekler.size} öğe")
            response.yemekler
        } catch (e: Exception) {
            Log.e("YemekRepository", "Yemekleri alırken hata oluştu: ${e.message}")
            emptyList()
        }
    }

    suspend fun sepeteYemekEkle(sepetYemek: SepetYemek): CRUDCevap {
        return try {
            val response = yemekDataSource.sepeteYemekEkle(
                sepetYemek.yemek_adi,
                sepetYemek.yemek_resim_adi,
                sepetYemek.yemek_fiyat,
                sepetYemek.yemek_siparis_adet,
                sepetYemek.kullanici_adi
            )
            Log.d("YemekRepository", "${sepetYemek.yemek_adi} sepete eklendi.")
            response
        } catch (e: Exception) {
            Log.e("YemekRepository", "Sepete ekleme hatası: ${e.message}")
            CRUDCevap(success = 0, message = "Yemek sepete eklenemedi: ${e.message}")
        }
    }

    suspend fun tumSepetYemekleriGetir(): List<SepetYemek> {
        return try {
            val response = yemekDataSource.tumSepetYemekleriGetir()
            Log.d("YemekRepository", "Sepet verileri alındı: ${response.size} öğe")
            response
        } catch (e: Exception) {
            Log.e("YemekRepository", "Sepet verileri alınamadı: ${e.message}")
            emptyList()
        }
    }

    suspend fun sepettenYemekSil(sepetYemekId: Int, kullaniciAdi: String): CRUDCevap {
        return try {
            val response = yemekDataSource.sepettenYemekSil(sepetYemekId, kullaniciAdi)
            Log.d("YemekRepository", "Sepetten yemek başarıyla silindi.")
            response
        } catch (e: Exception) {
            Log.e("YemekRepository", "Sepetten silme hatası: ${e.message}")
            CRUDCevap(success = 0, message = "Yemek sepetten silinemedi: ${e.message}")
        }
    }
}