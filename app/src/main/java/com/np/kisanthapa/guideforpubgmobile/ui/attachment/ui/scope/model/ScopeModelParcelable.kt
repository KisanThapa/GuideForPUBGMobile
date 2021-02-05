package com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.scope.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ScopeModelParcelable(
    val scopeId: Int,
    val scopeName: String,
    val scopeSummary: String,
    val scopeCapacity: String,
    val scopeSupportedWeaponName: String,
    val scopeSupportedWeaponImage: String,
    val scopeImage: String
) : Parcelable