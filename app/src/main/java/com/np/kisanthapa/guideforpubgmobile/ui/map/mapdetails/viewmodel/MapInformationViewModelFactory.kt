package com.np.kisanthapa.guideforpubgmobile.ui.map.mapdetails.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MapInformationViewModelFactory(private val application: Application, private val mapID: Int) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return if (modelClass.isAssignableFrom(MapInformationViewModel::class.java)) {
            MapInformationViewModel(
                application,
                mapID
            ) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }

    }

}