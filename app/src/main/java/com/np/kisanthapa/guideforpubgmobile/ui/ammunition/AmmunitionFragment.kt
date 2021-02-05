package com.np.kisanthapa.guideforpubgmobile.ui.ammunition

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.np.kisanthapa.guideforpubgmobile.R
import com.np.kisanthapa.guideforpubgmobile.ui.ammunition.adapter.AmmunitionRecyclerAdapter
import com.np.kisanthapa.guideforpubgmobile.ui.ammunition.model.AmmunitionModel
import com.np.kisanthapa.guideforpubgmobile.ui.ammunition.model.AmmunitionModelParcelable
import com.np.kisanthapa.guideforpubgmobile.ui.ammunition.viewmodel.AmmunitionViewModel

class AmmunitionFragment : Fragment() {

    companion object {
        fun newInstance() = AmmunitionFragment()
    }

    // View Model
    private lateinit var viewModel: AmmunitionViewModel

    // Recycler View
    private lateinit var mAmmunitionRecyclerView: RecyclerView

    // Adapter
    private lateinit var mAmmunitionRecyclerAdapter: AmmunitionRecyclerAdapter

    // navController
    private lateinit var mNavController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.ammunition_fragment, container, false)

        // Init View Model here
        viewModel = ViewModelProvider(this).get(AmmunitionViewModel::class.java)

        // Init Recycler view here
        mAmmunitionRecyclerView = root.findViewById(R.id.mAmmunitionRecyclerView)


        // Init Recycler View
        initRecyclerView()

        // Observe data here
        viewModel.ammunitionList.observe(viewLifecycleOwner, Observer {
            it?.let {
                mAmmunitionRecyclerAdapter.setAmmunitionData(it)
            }
        })

        return root
    }

    private fun initRecyclerView() {

        val viewManager = GridLayoutManager(requireContext(), 2)
        mAmmunitionRecyclerAdapter = AmmunitionRecyclerAdapter(requireContext())

        mAmmunitionRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = mAmmunitionRecyclerAdapter
        }

        mAmmunitionRecyclerAdapter.setOnAmmunitionItemClickListener(object :
            AmmunitionRecyclerAdapter.AmmunitionItemClickCallback {
            override fun onItemClick(ammunitionModel: AmmunitionModel) {

                val ammParcelable: AmmunitionModelParcelable =
                    AmmunitionModelParcelable(
                        ammunitionModel.amId,
                        ammunitionModel.amName,
                        ammunitionModel.amSummary,
                        ammunitionModel.amCapacity,
                        ammunitionModel.amFeature,
                        ammunitionModel.amSupportedWeaponName,
                        ammunitionModel.amSupportedWeaponImages,
                        ammunitionModel.amImageName
                    )

                mNavController.navigate(
                    AmmunitionFragmentDirections
                        .actionNavAmmunitionToDetailsAmmunitionFragment(
                            ammParcelable,
                            ammunitionModel.amName
                        )
                )


            }

        })

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mNavController = Navigation.findNavController(view)

    }

}
