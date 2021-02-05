package com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.scope

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
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.scope.adapter.ScopeRecyclerAdapter
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.scope.model.ScopeModel
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.scope.model.ScopeModelParcelable

class ScopeFragment : Fragment() {

    companion object {
        fun newInstance() = ScopeFragment()
    }

    // View Model
    private lateinit var viewModel: ScopeViewModel

    // Recycler view
    private lateinit var mScopeRecyclerView: RecyclerView

    // Adapter
    private lateinit var mScopeRecyclerAdapter: ScopeRecyclerAdapter

    // Nav Controller
    private lateinit var mNavController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.scope_fragment, container, false)

        mScopeRecyclerView = root.findViewById(R.id.mScopeRecyclerView)

        // View model
        viewModel = ViewModelProvider(this).get(ScopeViewModel::class.java)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mNavController = Navigation.findNavController(view)

        initRecyclerView()

        // Observe data here
        viewModel.scopeList.observe(viewLifecycleOwner, Observer {
            it?.let {
                mScopeRecyclerAdapter.setScopeData(it)
            }
        })

    }

    private fun initRecyclerView() {
        mScopeRecyclerAdapter = ScopeRecyclerAdapter(requireContext())

        val viewManager = GridLayoutManager(requireContext(), 2)

        mScopeRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = mScopeRecyclerAdapter
        }

        // Click handling
        mScopeRecyclerAdapter.onSetScopeItemClickListener(object :
            ScopeRecyclerAdapter.ScopeItemClickCallback {

            override fun inItemClick(scopeModel: ScopeModel) {

                val scopeModelParcelable: ScopeModelParcelable = ScopeModelParcelable(
                    scopeModel.scopeId,
                    scopeModel.scopeName,
                    scopeModel.scopeSummary,
                    scopeModel.scopeCapacity,
                    scopeModel.scopeSupportedWeaponName,
                    scopeModel.scopeSupportedWeaponImage,
                    scopeModel.scopeImage
                )

                mNavController.navigate(
                    ScopeFragmentDirections.actionScopeFragmentToScopeDetailsFragment(
                        scopeModelParcelable,
                        scopeModel.scopeName
                    )
                )

            }

        })

    }

}
