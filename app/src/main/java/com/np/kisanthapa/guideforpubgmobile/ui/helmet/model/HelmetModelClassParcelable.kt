package com.np.kisanthapa.guideforpubgmobile.ui.helmet.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HelmetModelClassParcelable(
    val helmetId: Int,
    val helmetName: String?,
    val helmetFullName: String?,
    val helmetLevel: String?,
    val helmetDescription: String?,
    val helmetDurability: Int,
    val helmetDamageReduction: Int,
    val helmetImageName: String?
) : Parcelable {
}