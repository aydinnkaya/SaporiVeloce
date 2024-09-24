package com.aydinkaya.saporiveloce.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aydinkaya.saporiveloce.data.entity.CRUDCevap
import com.aydinkaya.saporiveloce.data.entity.SepetYemek
import com.aydinkaya.saporiveloce.data.entity.Yemek
import com.aydinkaya.saporiveloce.data.repo.YemekRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YemekViewModel @Inject constructor(
    private val yemekRepository: YemekRepository
) : ViewModel() {

    private val _yemekList = MutableLiveData<List<Yemek>>()
    val yemekList: LiveData<List<Yemek>> get() = _yemekList

    private val _crudCevap = MutableLiveData<CRUDCevap>()
    val crudCevap: LiveData<CRUDCevap> get() = _crudCevap

    private val _sepetYemekler = MutableLiveData<List<SepetYemek>>() // Cart items LiveData
    val sepetYemekler: LiveData<List<SepetYemek>> get() = _sepetYemekler

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        fetchYemekler()
    }

    private fun fetchYemekler() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val yemekler = yemekRepository.tumYemekleriGetir()
                _yemekList.value = yemekler
            } catch (e: Exception) {
                Log.e("YemekViewModel", "Yemekler alınamadı: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun fetchSepetYemekler() {  // Fetches cart items from repository
        viewModelScope.launch {
            try {
                val sepetItems = yemekRepository.tumSepetYemekleriGetir()
                _sepetYemekler.value = sepetItems
            } catch (e: Exception) {
                Log.e("YemekViewModel", "Sepet yemekler alınamadı: ${e.message}")
            }
        }
    }

    fun sepeteYemekEkle(sepetYemek: SepetYemek) {
        viewModelScope.launch {
            try {
                val cevap = yemekRepository.sepeteYemekEkle(sepetYemek)
                _crudCevap.value = cevap
            } catch (e: Exception) {
                Log.e("YemekViewModel", "Sepete yemek eklenemedi: ${e.message}")
            }
        }
    }

    fun sepettenYemekSil(sepetYemekId: Int, kullaniciAdi: String) {
        viewModelScope.launch {
            try {
                val cevap = yemekRepository.sepettenYemekSil(sepetYemekId, kullaniciAdi)
                _crudCevap.value = cevap
            } catch (e: Exception) {
                Log.e("YemekViewModel", "Yemek silinemedi: ${e.message}")
            }
        }
    }
}
