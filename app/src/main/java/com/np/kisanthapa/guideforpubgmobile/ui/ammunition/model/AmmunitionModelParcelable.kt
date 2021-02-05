package com.np.kisanthapa.guideforpubgmobile.ui.ammunition.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AmmunitionModelParcelable(
    val amId: Int,
    val amName: String,
    val amSummary: String,
    val amCapacity: String,
    val amFeature: String,
    val amSupportedWeaponName: String,
    val amSupportedWeaponImages: String,
    val amImageName: String
) : Parcelable {}