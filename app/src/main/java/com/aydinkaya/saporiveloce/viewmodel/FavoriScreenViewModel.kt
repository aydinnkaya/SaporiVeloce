package com.aydinkaya.saporiveloce.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.aydinkaya.saporiveloce.data.entity.Yemekler

class FavoriScreenViewModel : ViewModel() {
    private val _favoriYemekler = mutableStateListOf<Yemekler>()
    val favoriYemekler: List<Yemekler> get() = _favoriYemekler


    fun favoriEkle(yemek: Yemekler) {
        if (!_favoriYemekler.contains(yemek)) {
            _favoriYemekler.add(yemek)
        }
    }


    fun favoriCikar(yemek: Yemekler) {
        _favoriYemekler.remove(yemek)
    }


    fun isFavori(yemek: Yemekler): Boolean {
        return _favoriYemekler.contains(yemek)
    }
}
