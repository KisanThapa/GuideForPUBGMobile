package com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.muzzle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.np.kisanthapa.guideforpubgmobile.R
import com.np.kisanthapa.guideforpubgmobile.adapter.SupportedWeaponAdapter
import com.np.kisanthapa.guideforpubgmobile.adapter.SupportedWeaponModel
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.muzzle.model.MuzzleModelParcelable
import com.np.kisanthapa.guideforpubgmobile.utility.UtilityObject
import com.squareup.picasso.Picasso


class MuzzleDetailsFragment : Fragment() {


    private lateinit var mMuzzleImageCardView: CardView
    private lateinit var mMuzzleImageView: ImageView
    private lateinit var mMuzzleName: TextView
    private lateinit var mMuzzleFeature: TextView
    private lateinit var mMuzzleSummary: TextView

    // For Supported Weapons List
    private lateinit var mMuzzleSupportedWeaponRecyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_muzzle_details, container, false)

        // init views here
        mMuzzleImageCardView = root.findViewById(R.id.mMuzzleImageCardView)
        mMuzzleImageView = root.findViewById(R.id.mMuzzleImageView)
        mMuzzleName = root.findViewById(R.id.mMuzzleName)
        mMuzzleFeature = root.findViewById(R.id.mMuzzleFeature)
        mMuzzleSummary = root.findViewById(R.id.mMuzzleSummary)


        //Init recycler view here
        mMuzzleSupportedWeaponRecyclerView =
            root.findViewById(R.id.mMuzzleSupportedWeaponRecyclerView)

        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val muzzleData = MuzzleDetailsFragmentArgs.fromBundle(requireArguments()).muzzleDataArgs

        setDataInViews(muzzleData)

    }

    private fun setDataInViews(muzzleData: MuzzleModelParcelable) {

        mMuzzleName.text = muzzleData.muzzleName
        mMuzzleSummary.text = muzzleData.muzzleSummary
        mMuzzleFeature.text = muzzleData.muzzleFeature

        try {
            Picasso.get().load(
                UtilityObject.getDrawableIdByName(
                    requireContext(),
                    muzzleData.muzzleImage
                )
            ).into(mMuzzleImageView)

        } catch (e: Exception) {
            mMuzzleImageView.setImageResource(R.drawable.ic_image_error_placeholder)
        }


        // Prepare Supported weapon data here
        val weaponNameList = muzzleData.muzzleSupportedWeaponName.split("\n")
        val weaponImageList = muzzleData.muzzleSupportedWeaponImage.split("\n")

        val supportedWeaponDataList = mutableListOf<SupportedWeaponModel>()

        for ((index, value) in weaponNameList.withIndex()) {

            val weaponData =
                SupportedWeaponModel(
                    index + 1,
                    value,
                    weaponImageList[index]
                )
            supportedWeaponDataList.add(weaponData)
        }
        // Init Recycler View here
        initRecyclerView(supportedWeaponDataList)

    }

    private fun initRecyclerView(supportedWeaponDataList: MutableList<SupportedWeaponModel>) {

        val supportedWeaponAdapter =
            SupportedWeaponAdapter(
                requireContext()
            )
        supportedWeaponAdapter.setSupportedWeaponData(supportedWeaponDataList)

        val viewManager = LinearLayoutManager(requireContext())
        viewManager.orientation = LinearLayoutManager.VERTICAL

        mMuzzleSupportedWeaponRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = supportedWeaponAdapter
        }

    }

}