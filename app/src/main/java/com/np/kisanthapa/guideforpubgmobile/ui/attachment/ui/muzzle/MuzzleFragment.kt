package com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.muzzle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.np.kisanthapa.guideforpubgmobile.R
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.muzzle.adapter.MuzzleRecyclerAdapter
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.muzzle.model.MuzzleModel
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.muzzle.model.MuzzleModelParcelable
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.scope.ScopeFragmentDirections
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.scope.adapter.ScopeRecyclerAdapter
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.scope.model.ScopeModel
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.scope.model.ScopeModelParcelable

class MuzzleFragment : Fragment() {

    companion object {
        fun newInstance() = MuzzleFragment()
    }

    private lateinit var viewModel: MuzzleViewModel


    // Recycler view
    private lateinit var mMuzzleRecyclerView: RecyclerView

    // Adapter
    private lateinit var mMuzzleRecyclerAdapter: MuzzleRecyclerAdapter

    // Nav Controller
    private lateinit var mNavController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.muzzle_fragment, container, false)

        mMuzzleRecyclerView = root.findViewById(R.id.mMuzzleRecyclerView)

        // View Model
        viewModel = ViewModelProvider(this).get(MuzzleViewModel::class.java)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Init nav controller here
        mNavController = Navigation.findNavController(view)

        initRecyclerView()


        viewModel.muzzleList.observe(viewLifecycleOwner, Observer {
            it?.let {
                mMuzzleRecyclerAdapter.setMuzzleData(it)
            }
        })

    }

    private fun initRecyclerView() {

        mMuzzleRecyclerAdapter = MuzzleRecyclerAdapter(requireContext())
        val viewManager = GridLayoutManager(requireContext(), 2)

        mMuzzleRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = mMuzzleRecyclerAdapter
        }

        // Click handling
        mMuzzleRecyclerAdapter.onSetMuzzleItemClickListener(object :
            MuzzleRecyclerAdapter.MuzzleItemClickCallback {
            override fun onItemClick(muzzleModel: MuzzleModel) {

                val muzzleParcelable = MuzzleModelParcelable(
                    muzzleModel.muzzleId,
                    muzzleModel.muzzleName,
                    muzzleModel.muzzleSummary,
                    muzzleModel.muzzleFeature,
                    muzzleModel.muzzleSupportedWeaponName,
                    muzzleModel.muzzleSupportedWeaponImage,
                    muzzleModel.muzzleImage
                )

                mNavController.navigate(
                    MuzzleFragmentDirections.actionMuzzleFragmentToMuzzleDetailsFragment(
                        muzzleParcelable,
                        muzzleModel.muzzleName
                    )
                )

            }

        })

    }

}