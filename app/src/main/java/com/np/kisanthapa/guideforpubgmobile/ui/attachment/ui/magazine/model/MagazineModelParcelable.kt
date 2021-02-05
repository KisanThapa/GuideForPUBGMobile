package com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.magazine.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MagazineModelParcelable(
    val magId: Int,
    val magName: String,
    val magSummary: String,
    val magFeature: String,
    val magCapacity: String,
    val magSupportedWeaponName: String,
    val magSupportedWeaponImage: String,
    val magSupportedWeaponDefCap: String,
    val magSupportedWeaponExtCap: String,
    val magImage: String
) : Parcelable