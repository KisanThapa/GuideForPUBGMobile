package com.np.kisanthapa.guideforpubgmobile.ui.backpack.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BackPackModelClassParcelable(
    val backPackId: Int,
    val backPackName: String?,
    val backPackFullName: String?,
    val backPackLevel: String?,
    val backPackDescription: String?,
    val backPackCapacity: Int?,
    val backPackImageName: String?,
    val backPackVariants: String?

) : Parcelable {}