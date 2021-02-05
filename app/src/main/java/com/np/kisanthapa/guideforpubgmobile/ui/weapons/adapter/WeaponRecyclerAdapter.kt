package com.np.kisanthapa.guideforpubgmobile.ui.weapons.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.np.kisanthapa.guideforpubgmobile.R
import com.np.kisanthapa.guideforpubgmobile.ui.weapons.model.WeaponModelClass
import com.np.kisanthapa.guideforpubgmobile.utility.UtilityObject
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_single_weapon.view.*

class WeaponRecyclerAdapter(private val context: Context) :
    RecyclerView.Adapter<WeaponRecyclerAdapter.WeaponViewHolder>() {


    private var weaponDataList: List<WeaponModelClass> = emptyList()


    // Click listener
    interface WeaponItemClickCallback {
        fun onWeaponItemClick(weaponData: WeaponModelClass)
    }

    private lateinit var weaponItemClickCallback: WeaponItemClickCallback
    fun setOnWeaponItemClickListener(weaponClickCallback: WeaponItemClickCallback) {
        weaponItemClickCallback = weaponClickCallback
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = WeaponViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_single_weapon, parent, false)
    )

    internal fun setWeaponData(data: List<WeaponModelClass>) {
        weaponDataList = data
        notifyDataSetChanged()
    }

    override fun getItemCount() = weaponDataList.size

    override fun onBindViewHolder(holder: WeaponViewHolder, position: Int) =
        holder.bindView(context, weaponDataList[position], weaponItemClickCallback)


    class WeaponViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val weaponConstraintLayout = view.weaponConstraintLayout
        private val weaponImageView = view.weaponImageView
        private val weaponName = view.weaponName
        private val weaponInfo = view.weaponInfo


        fun bindView(
            context: Context,
            weaponData: WeaponModelClass,
            itemClickCallback: WeaponItemClickCallback
        ) {

            weaponName.text = weaponData.weaponTypeName
            weaponInfo.text = weaponData.weaponTypeInfo

            Picasso.get().load(
                UtilityObject.getDrawableIdByName(
                    context, weaponData.weaponTypeImage
                )
            ).into(weaponImageView)

            // Click listener
            weaponConstraintLayout.setOnClickListener {
                itemClickCallback.onWeaponItemClick(weaponData)
            }

        }

    }


}