package com.aydinkaya.saporiveloce.data.repo

import com.aydinkaya.saporiveloce.data.datasource.YemeklerDataSource
import com.aydinkaya.saporiveloce.data.entity.CRUDCevap
import com.aydinkaya.saporiveloce.data.entity.SepettekiYemeklerCevap
import com.aydinkaya.saporiveloce.data.entity.Yemekler


class YemeklerRepository(var yds: YemeklerDataSource) {

    suspend fun yemekleriYukle(): List<Yemekler> = yds.yemekleriYukle()

    suspend fun kaydet(yemek_adi:String,yemek_resim_adi:String,yemek_fiyat:Int,yemek_siparis_adet:Int,kullanici_adi:String) =
        yds.kaydet(yemek_adi, yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)

    suspend fun sepettekiYemekleriGetir(kullanici_adi: String): SepettekiYemeklerCevap? = yds.sepettekiYemekleriGetir(kullanici_adi)

    suspend fun sil(sepet_yemek_id: Int, kullanici_adi: String): CRUDCevap {
        return yds.sil(sepet_yemek_id, kullanici_adi)
    }

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

    fun getYemekAciklama(yemekId: Int): String {
        return yemekAciklamalar[yemekId] ?: "No description available."
    }
}
