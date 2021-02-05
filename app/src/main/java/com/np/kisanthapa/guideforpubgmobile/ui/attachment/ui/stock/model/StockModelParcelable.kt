package com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.stock.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StockModelParcelable(
    val stockId: Int,
    val stockName: String,
    val stockSummary: String,
    val stockFeature: String,
    val stockSupportedWeaponName: String,
    val stockSupportedWeaponImage: String,
    val stockImage: String
) : Parcelable