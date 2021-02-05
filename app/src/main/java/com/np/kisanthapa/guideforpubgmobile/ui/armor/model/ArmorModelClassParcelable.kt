package com.np.kisanthapa.guideforpubgmobile.ui.armor.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArmorModelClassParcelable(
    val armorId: Int,
    val armorName: String,
    val armorFullName: String,
    val armorLevel: String,
    val armorDescription: String,
    val armorCapacityDescription: String,
    val armorCapacityExtension: Int,
    val armorDamageReduction: Int,
    val armorDurability: Int,
    val armorImageName: String

) : Parcelable {}