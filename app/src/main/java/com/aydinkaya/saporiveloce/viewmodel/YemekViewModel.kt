package com.aydinkaya.saporiveloce.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aydinkaya.saporiveloce.data.entity.SepetYemek
import com.aydinkaya.saporiveloce.data.entity.Yemek
import com.aydinkaya.saporiveloce.data.repo.YemekRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import android.util.Log

@HiltViewModel
open class YemekViewModel @Inject constructor(private val repository: YemekRepository) : ViewModel() {

    private val _yemekListesi = MutableLiveData<List<Yemek>>(emptyList())
    val yemekListesi: LiveData<List<Yemek>> get() = _yemekListesi

    private val _cartItems = MutableLiveData<List<SepetYemek>>(emptyList())
    val cartItems: LiveData<List<SepetYemek>> get() = _cartItems

    private val _toplamFiyat = MutableLiveData<Double>(0.0)
    val toplamFiyat: LiveData<Double> get() = _toplamFiyat

    private val _toplamUrunSayisi = MutableLiveData<Int>(0)
    val toplamUrunSayisi: LiveData<Int> get() = _toplamUrunSayisi

    private val _yukleniyor = MutableLiveData<Boolean>()
    val yukleniyor: LiveData<Boolean> get() = _yukleniyor

    private val _hataMesaji = MutableLiveData<String?>(null)
    val hataMesaji: LiveData<String?> get() = _hataMesaji

    private val _yemekAciklama = MutableLiveData<String?>()
    val yemekAciklama: LiveData<String?> get() = _yemekAciklama

    init {
        yemekleriGetir()
        tumSepetYemekleriniGetir() // Sepeti başlangıçta yüklüyoruz
    }

    fun sepeteYemekEkle(yemek: SepetYemek) {
        val currentCartItems = _cartItems.value?.toMutableList() ?: mutableListOf()
        val existingItem = currentCartItems.find { it.yemek_adi == yemek.yemek_adi }

        if (existingItem != null) {
            val updatedItem = existingItem.copy(yemek_siparis_adet = existingItem.yemek_siparis_adet + 1)
            currentCartItems[currentCartItems.indexOf(existingItem)] = updatedItem
        } else {
            currentCartItems.add(yemek)
        }

        _cartItems.value = currentCartItems
        Log.d("YemekViewModel", "Sepet güncellendi: ${_cartItems.value?.size} öğe var.")

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.sepeteYemekEkle(yemek)
                withContext(Dispatchers.Main) {
                    if (response.success == 1) {
                        Log.d("API", "Yemek başarıyla sepete eklendi: ${yemek.yemek_adi}")
                        tumSepetYemekleriniGetir() // Sepet güncelleme
                    } else {
                        _hataMesaji.value = "Yemek sepete eklenemedi."
                        Log.e("API", "Yemek sepete eklenemedi: ${response.message}")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _hataMesaji.value = "Sepete ekleme sırasında hata oluştu: ${e.message}"
                    Log.e("API", "Sepete ekleme sırasında hata: ${e.message}")
                }
            }
        }
    }

    fun tumSepetYemekleriniGetir() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val sepetYemekler = repository.tumSepetYemekleriGetir()
                withContext(Dispatchers.Main) {
                    _cartItems.value = sepetYemekler
                    Log.d("YemekViewModel", "Sepet verileri güncellendi: ${sepetYemekler.size} öğe var.")
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _hataMesaji.value = "Sepet yüklenemedi: ${e.message}"
                    Log.e("YemekViewModel", "Sepet yüklenemedi: ${e.message}")
                }
            }
        }
    }

    fun toplamFiyat(): Int {
        return _cartItems.value?.sumOf { it.yemek_fiyat * it.yemek_siparis_adet } ?: 0
    }

    fun updateCartQuantity(sepetYemek: SepetYemek, newQuantity: Int) {
        val updatedList = _cartItems.value?.map {
            if (it == sepetYemek) it.copy(yemek_siparis_adet = newQuantity) else it
        } ?: emptyList()
        _cartItems.value = updatedList
    }

    private fun yemekleriGetir() {
        viewModelScope.launch(Dispatchers.IO) {
            _yukleniyor.postValue(true)
            try {
                val yemekler = repository.tumYemekleriGetir()
                withContext(Dispatchers.Main) {
                    _yemekListesi.value = yemekler
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _yemekListesi.value = emptyList()
                    _hataMesaji.value = "Yemekler yüklenemedi: ${e.message}"
                    Log.e("API", "Yemekler yüklenemedi: ${e.message}")
                }
            } finally {
                withContext(Dispatchers.Main) {
                    _yukleniyor.value = false
                }
            }
        }
    }

    fun sepettenYemekSil(sepetYemek: SepetYemek) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.sepettenYemekSil(sepetYemek.sepet_yemek_id, sepetYemek.kullanici_adi)
                withContext(Dispatchers.Main) {
                    if (response.success == 1) {
                        tumSepetYemekleriniGetir() // Silindikten sonra sepet güncelleme
                        Log.d("API", "Yemek başarıyla sepetten silindi.")
                    } else {
                        _hataMesaji.value = "Yemek sepetten silinemedi."
                        Log.e("API", "Yemek sepetten silinemedi: ${response.message}")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _hataMesaji.value = "Sepetten silerken hata oluştu: ${e.message}"
                    Log.e("API", "Sepetten silerken hata: ${e.message}")
                }
            }
        }
    }

    fun yemekAciklamasiniGetir(yemekId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val yemek = _yemekListesi.value?.find { it.yemek_id == yemekId }
            withContext(Dispatchers.Main) {
                _yemekAciklama.value = yemek?.yemek_aciklama ?: "Açıklama yok"
            }
        }
    }

    fun hataMesajiniTemizle() {
        _hataMesaji.value = null
    }
}
