package com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.grip.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GripModelParcelable(
    val gripId: Int,
    val gripName: String,
    val gripSummary: String,
    val gripCapacity: String,
    val gripFeature: String,
    val gripSupportedWeaponName: String,
    val gripSupportedWeaponImage: String,
    val gripImage: String
) : Parcelable