package com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.magazine.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.np.kisanthapa.guideforpubgmobile.R
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.magazine.model.MagSupportedWeaponModel
import com.np.kisanthapa.guideforpubgmobile.utility.UtilityObject
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_mag_supported_weapon_layout.view.*


class MagSupportedWeaponAdapter(val context: Context) :
    RecyclerView.Adapter<MagSupportedWeaponAdapter.SupportedWWeaponViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SupportedWWeaponViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_mag_supported_weapon_layout, parent, false)
        )

    private var supportedWeaponDataList = emptyList<MagSupportedWeaponModel>()
    internal fun setSupportedWeaponData(data: List<MagSupportedWeaponModel>) {
        supportedWeaponDataList = data
        notifyDataSetChanged()
    }

    override fun getItemCount() = supportedWeaponDataList.size

    override fun onBindViewHolder(holder: SupportedWWeaponViewHolder, position: Int) {
        holder.bindView(context, supportedWeaponDataList[position])
    }

    class SupportedWWeaponViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val supportedWeaponCount = view.supportedWeaponCount
        private val supportedWeaponImage = view.supportedWeaponImage
        private val supportedWeaponName = view.supportedWeaponName
        private val supportedWeaponMagDefCap = view.supportedWeaponMagDefCap
        private val supportedWeaponMagExtCap = view.supportedWeaponMagExtCap

        fun bindView(context: Context, supportedWeaponModel: MagSupportedWeaponModel) {
            supportedWeaponCount.text = "${supportedWeaponModel.weaponCount}."
            supportedWeaponName.text = supportedWeaponModel.weaponName
            supportedWeaponMagDefCap.text = supportedWeaponModel.magDefCap
            supportedWeaponMagExtCap.text = supportedWeaponModel.magExtCap

            try {
                Picasso.get().load(
                    UtilityObject.getDrawableIdByName(
                        context,
                        supportedWeaponModel.weaponImageName
                    )
                )
                    .into(supportedWeaponImage)
            } catch (e: Exception) {
                supportedWeaponImage.setImageResource(R.drawable.ic_image_error_placeholder)
            }
        }
    }
}