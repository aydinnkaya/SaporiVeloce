package com.aydinkaya.saporiveloce.viewmodel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aydinkaya.saporiveloce.data.entity.SepetYemekler
import com.aydinkaya.saporiveloce.data.entity.SepettekiYemeklerCevap
import com.aydinkaya.saporiveloce.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SepetViewModel @Inject constructor(private val yrepo: YemeklerRepository) : ViewModel() {

    var sepettekiYemeklerListesi = MutableLiveData<List<SepetYemekler>>()
    private val kullanici_adi = "aydinkaya"

    init {
        sepettekiYemekleriGetir(kullanici_adi)
    }

    fun sil(sepet_yemek_id: Int, kullanici_adi: String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = yrepo.sil(sepet_yemek_id, kullanici_adi)
                if (response.success == 1) {
                    Log.d("SepetViewModel", "Item deleted successfully.")
                    sepettekiYemekleriGetir(kullanici_adi)
                } else {
                    Log.e("SepetViewModel", "Failed to delete item: ${response.message}")
                }
            } catch (e: Exception) {
                Log.e("SepetViewModel", "Error: ${e.message}")
            }
        }
    }

    fun sepettekiYemekleriGetir(kullanici_adi: String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val yemeklerCevap: SepettekiYemeklerCevap? = yrepo.sepettekiYemekleriGetir(kullanici_adi)

                Log.d("SepetViewModel", "Yemekler yanıtı: $yemeklerCevap")

                if (yemeklerCevap != null && yemeklerCevap.sepet_yemekler != null) {
                    sepettekiYemeklerListesi.value = yemeklerCevap.sepet_yemekler
                    Log.d("SepetViewModel", "Liste güncellendi: ${sepettekiYemeklerListesi.value}")
                    if (yemeklerCevap.sepet_yemekler.isEmpty()) {
                        Log.d("SepetViewModel", "Sepet boş!")
                    }
                } else {
                    Log.d("SepetViewModel", "Yemekler listesi boş veya null!")
                    sepettekiYemeklerListesi.value = emptyList()
                }
            } catch (e: Exception) {
                Log.e("SepetViewModel", "Hata: ${e.message}")
            }
        }
    }
}
