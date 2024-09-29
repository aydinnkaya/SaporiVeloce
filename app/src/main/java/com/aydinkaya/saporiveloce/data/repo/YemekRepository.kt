package com.aydinkaya.saporiveloce.data.repo

import com.aydinkaya.saporiveloce.data.datasource.YemekDataSource
import com.aydinkaya.saporiveloce.data.entity.CRUDCevap
import com.aydinkaya.saporiveloce.data.entity.SepetYemek
import com.aydinkaya.saporiveloce.data.entity.Yemek
import javax.inject.Inject

class YemekRepository @Inject constructor(private val yemekDataSource: YemekDataSource) {

    private val yemekAciklamalar = mapOf(
        1 to "250 ml.\n120 calories.\nAyran is a refreshing, slightly salty traditional yogurt-based drink, perfect for cooling down on hot days and balancing rich meals.",
        2 to "150 g.\n400 calories.\nBaklava is a rich, sweet pastry made of layers of filo filled with chopped nuts, sweetened and held together by syrup or honey, perfect for dessert lovers.",
        3 to "330 ml.\n150 calories.\nFanta is a fruit-flavored soft drink with a vibrant orange color and a crisp, refreshing citrus taste, ideal for pairing with any meal.",
        4 to "200 g.\n300 calories.\nGrilled salmon is a healthy, protein-packed dish rich in omega-3 fatty acids, offering a smoky flavor from the grill and a tender texture.",
        5 to "180 g.\n250 calories.\nGrilled chicken breast, marinated with herbs and spices, provides a lean and nutritious protein source, cooked to juicy perfection over an open flame.",
        6 to "200 g.\n450 calories.\nKadayif is a traditional Turkish dessert made from thin shredded dough, filled with nuts and soaked in syrup, offering a crispy texture and sweet flavor.",
        7 to "200 ml.\n2 calories.\nFreshly brewed coffee made from premium roasted beans, offering a rich and bold flavor to kickstart your day or finish a meal.",
        8 to "150 g.\n350 calories.\nGrilled meatballs (köfte) are juicy, seasoned with a blend of spices, and cooked over an open grill, giving them a smoky and savory taste.",
        9 to "250 g.\n500 calories.\nLasagna is a layered pasta dish with rich meat sauce, creamy béchamel, and melted cheese, providing a hearty and comforting meal.",
        10 to "250 g.\n450 calories.\nPasta is a classic Italian dish with a variety of sauces, cooked to al dente perfection, offering a satisfying and flavorful experience.",
        11 to "500 g.\n800 calories.\nPizza is a thin-crust Italian classic topped with mozzarella, tomato sauce, and your choice of toppings, baked to crispy perfection.",
        12 to "500 ml.\n0 calories.\nPure and fresh water, essential for hydration, pairs perfectly with any meal, ensuring you stay refreshed and healthy.",
        13 to "200 g.\n250 calories.\nRice pudding (Sütlaç) is a creamy Turkish dessert made with rice, milk, and sugar, lightly flavored with vanilla and topped with cinnamon.",
        14 to "200 g.\n350 calories.\nTiramisu is a decadent Italian dessert with layers of coffee-soaked ladyfingers and mascarpone cream, finished with a dusting of cocoa powder."
    )



    suspend fun tumYemekleriGetir(): List<Yemek> {
        return try {
            val response = yemekDataSource.tumYemekleriGetir()


            response.yemekler.map { yemek ->
                yemek.copy(yemek_aciklama = yemekAciklamalar[yemek.yemek_id] ?: "Açıklama yok")
            }
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

