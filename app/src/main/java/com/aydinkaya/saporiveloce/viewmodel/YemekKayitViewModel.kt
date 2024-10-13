package com.aydinkaya.saporiveloce.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.aydinkaya.saporiveloce.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class YemekKayitViewModel @Inject constructor(private val yrepo: YemeklerRepository) : ViewModel() {

    private val kullanici_adi = "aydinkaya"


    fun kaydet(
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: Int,
        yemek_siparis_adet: Int
    ) {
        Log.d("YemekKayitViewModel", "Sending to API: " +
                "Yemek Adı: $yemek_adi, " +
                "Yemek Resim Adı: $yemek_resim_adi, " +
                "Yemek Fiyatı: $yemek_fiyat, " +
                "Sipariş Adet: $yemek_siparis_adet, " +
                "Kullanıcı Adı: $kullanici_adi")

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = yrepo.kaydet(
                    yemek_adi,
                    yemek_resim_adi,
                    yemek_fiyat,
                    yemek_siparis_adet,
                    kullanici_adi
                )
                withContext(Dispatchers.Main) {
                    Log.d("YemekKayitViewModel", "API kaydet çağrısı başarılı: Response: $response")

                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.e("YemekKayitViewModel", "API kaydet çağrısı başarısız: ${e.message}", e)
                }
            }
        }
    }



}
