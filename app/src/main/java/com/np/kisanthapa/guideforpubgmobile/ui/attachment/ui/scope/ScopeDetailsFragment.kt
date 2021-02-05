package com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.scope

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
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.scope.model.ScopeModelParcelable
import com.np.kisanthapa.guideforpubgmobile.utility.UtilityObject
import com.squareup.picasso.Picasso


class ScopeDetailsFragment : Fragment() {


    private lateinit var mScopeImageCardView: CardView
    private lateinit var mScopeImageView: ImageView
    private lateinit var mScopeName: TextView
    private lateinit var mScopeFeature: TextView
    private lateinit var mScopeSummary: TextView

    // For Supported Weapons List
    private lateinit var mScopeSupportedWeaponRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_scope_details, container, false)


        // init views here
        mScopeImageCardView = root.findViewById(R.id.mScopeImageCardView)
        mScopeImageView = root.findViewById(R.id.mScopeImageView)
        mScopeName = root.findViewById(R.id.mScopeName)
        mScopeFeature = root.findViewById(R.id.mScopeFeature)
        mScopeSummary = root.findViewById(R.id.mScopeSummary)


        //Init recycler view here
        mScopeSupportedWeaponRecyclerView =
            root.findViewById(R.id.mScopeSupportedWeaponRecyclerView)



        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val scopeData = ScopeDetailsFragmentArgs.fromBundle(requireArguments()).scopeDataArgs

        setDataInViews(scopeData)

    }

    private fun setDataInViews(scopeData: ScopeModelParcelable) {
        mScopeName.text = scopeData.scopeName
        mScopeSummary.text = scopeData.scopeSummary
        mScopeFeature.text = "Capacity: ${scopeData.scopeCapacity}"

        Picasso.get().load(
            UtilityObject.getDrawableIdByName(
                requireContext(),
                scopeData.scopeImage
            )
        ).into(mScopeImageView)


        // Prepare Supported weapon data here
        val weaponNameList = scopeData.scopeSupportedWeaponName.split("\n")
        val weaponImageList = scopeData.scopeSupportedWeaponImage.split("\n")

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

        mScopeSupportedWeaponRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = supportedWeaponAdapter
        }

    }

}