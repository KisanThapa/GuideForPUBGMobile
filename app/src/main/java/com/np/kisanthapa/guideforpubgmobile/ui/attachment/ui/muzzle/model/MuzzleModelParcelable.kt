package com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.muzzle.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MuzzleModelParcelable(
    val muzzleId: Int,
    val muzzleName: String,
    val muzzleSummary: String,
    val muzzleFeature: String,
    val muzzleSupportedWeaponName: String,
    val muzzleSupportedWeaponImage: String,
    val muzzleImage: String
) : Parcelable