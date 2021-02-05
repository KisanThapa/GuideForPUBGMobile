package com.np.kisanthapa.guideforpubgmobile.ui.map.mapdetails.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PopularDropViewModelFactory(val application: Application, val mapId: Int) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(PopularDropViewModel::class.java)) {
            PopularDropViewModel(
                application,
                mapId
            ) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }

    }

}