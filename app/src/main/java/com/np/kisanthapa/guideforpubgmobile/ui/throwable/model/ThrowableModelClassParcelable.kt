package com.np.kisanthapa.guideforpubgmobile.ui.throwable.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ThrowableModelClassParcelable(
    val throwableId: Int,
    val throwableName: String,
    val throwableFullName: String,
    val throwableDescription: String,
    val throwableUsageInstruction: String,
    val throwableCapacity: Int,
    val throwablePickupDelay: Int,
    val throwableReadyDelay: Int,
    val throwableImageName: String

) : Parcelable {}