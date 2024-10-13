package com.example.graduationproject.views.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aydinkaya.saporiveloce.data.entity.Yemekler
import com.aydinkaya.saporiveloce.data.repo.YemeklerRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(var yemeklerRepository: YemeklerRepository) : ViewModel() {

    var yemeklerListesi = MutableLiveData<List<Yemekler>>()

    init {
        yemekleriYukle()
    }

    fun yemekleriYukle() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val yemekler = yemeklerRepository.yemekleriYukle()
                yemeklerListesi.value = yemekler
                Log.d("AnasayfaViewModel", "Yemekler başarıyla yüklendi: ${yemekler.size} öğe")
            } catch (e: Exception) {
                Log.e("AnasayfaViewModel", "Yemekler yüklenirken hata: ${e.message}", e)
            }
        }
    }
    fun getYemekAciklama(yemekId: Int): String {
        return yemeklerRepository.getYemekAciklama(yemekId)
    }
}
