package com.aydinkaya.saporiveloce.retrofit

import com.aydinkaya.saporiveloce.data.entity.CRUDCevap
import com.aydinkaya.saporiveloce.data.entity.SepetYemek
import com.aydinkaya.saporiveloce.data.entity.YemekResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface YemekDao {

    @GET("tumYemekleriGetir.php")
    suspend fun tumYemekleriGetir(): Response<YemekResponse>  // Response ile sarıldı

    @POST("sepeteYemekEkle.php")
    @FormUrlEncoded
    suspend fun sepeteYemekEkle(
        @Field("yemek_adi") yemekAdi: String,
        @Field("yemek_resim_adi") yemekResimAdi: String,
        @Field("yemek_fiyat") yemekFiyat: Int,
        @Field("yemek_siparis_adet") yemekSiparisAdet: Int,
        @Field("kullanici_adi") kullaniciAdi: String
    ): Response<CRUDCevap>  // Response ile sarıldı

    @POST("sepettenYemekSil.php")
    @FormUrlEncoded
    suspend fun sepettenYemekSil(
        @Field("sepet_yemek_id") sepetYemekId: Int,
        @Field("kullanici_adi") kullaniciAdi: String
    ): Response<CRUDCevap>  // Response ile sarıldı

    @GET("tumSepetYemekleriGetir.php")
    suspend fun tumSepetYemekleriGetir(): Response<List<SepetYemek>>  // Response ile sarıldı
}
