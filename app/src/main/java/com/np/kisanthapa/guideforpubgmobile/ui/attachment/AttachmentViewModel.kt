package com.np.kisanthapa.guideforpubgmobile.ui.attachment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.model.AttachmentModelClass

class AttachmentViewModel(application: Application) : AndroidViewModel(application) {

    private val _attachmentDataList = MutableLiveData<List<AttachmentModelClass>>()
        .apply {
            value = createAttachmentData()
        }


    val mapAttachmentList: LiveData<List<AttachmentModelClass>> = _attachmentDataList

    private fun createAttachmentData(): List<AttachmentModelClass> {

        return listOf(
            AttachmentModelClass(
                1,
                "Scopes",
                "Eyesight for better Aim",
                "item_attachment_scope"
            ), AttachmentModelClass(
                2,
                "Magazines",
                "For more ammunition",
                "item_attachment_magazine"
            ), AttachmentModelClass(
                3,
                "Muzzles",
                "For less recoil",
                "item_attachment_muzzle"
            ), AttachmentModelClass(
                4,
                "Grips",
                "For better grips and less recoil",
                "item_attachment_grip"
            ), AttachmentModelClass(
                5,
                "Stocks",
                "For better weapon stability",
                "item_attachment_stock"
            )
        )
    }

}
