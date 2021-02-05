package com.np.kisanthapa.guideforpubgmobile.ui.consumable.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ConsumableModelClassParcelable(
    val consumableId: Int,
    val consumableName: String,
    val consumableType: String,
    val consumableDescription: String,
    val consumableFeature: String,
    val consumableImageName: String

) : Parcelable {}
