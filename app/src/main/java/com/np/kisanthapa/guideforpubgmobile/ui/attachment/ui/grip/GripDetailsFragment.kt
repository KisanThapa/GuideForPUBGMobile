package com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.grip

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.np.kisanthapa.guideforpubgmobile.R
import com.np.kisanthapa.guideforpubgmobile.adapter.SupportedWeaponAdapter
import com.np.kisanthapa.guideforpubgmobile.adapter.SupportedWeaponModel
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.grip.model.GripModelParcelable
import com.np.kisanthapa.guideforpubgmobile.utility.UtilityObject
import com.squareup.picasso.Picasso

class GripDetailsFragment : Fragment() {


    private lateinit var mGripImageCardView: CardView
    private lateinit var mGripImageView: ImageView
    private lateinit var mGripName: TextView
    private lateinit var mGripFeature: TextView
    private lateinit var mGripSummary: TextView

    // For Supported Weapons List
    private lateinit var mGripSupportedWeaponRecyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_grip_details, container, false)


        // init views here
        mGripImageCardView = root.findViewById(R.id.mGripImageCardView)
        mGripImageView = root.findViewById(R.id.mGripImageView)
        mGripName = root.findViewById(R.id.mGripName)
        mGripFeature = root.findViewById(R.id.mGripFeature)
        mGripSummary = root.findViewById(R.id.mGripSummary)


        //Init recycler view here
        mGripSupportedWeaponRecyclerView =
            root.findViewById(R.id.mGripSupportedWeaponRecyclerView)



        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val gripData = GripDetailsFragmentArgs.fromBundle(requireArguments()).gripDataArgs

        setDataInViews(gripData)
    }

    private fun setDataInViews(gripData: GripModelParcelable) {

        mGripName.text = gripData.gripName
        mGripSummary.text = gripData.gripSummary
        mGripFeature.text = gripData.gripFeature

        try {
            Picasso.get().load(
                UtilityObject.getDrawableIdByName(
                    requireContext(),
                    gripData.gripImage
                )
            ).into(mGripImageView)

        } catch (e: Exception) {
            mGripImageView.setImageResource(R.drawable.ic_image_error_placeholder)
        }

        // Prepare Supported weapon data here
        val weaponNameList = gripData.gripSupportedWeaponName.split("\n")
        val weaponImageList = gripData.gripSupportedWeaponImage.split("\n")

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

        mGripSupportedWeaponRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = supportedWeaponAdapter
        }

    }

}