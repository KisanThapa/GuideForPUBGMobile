package com.np.kisanthapa.guideforpubgmobile.ui.helmet

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
import com.np.kisanthapa.guideforpubgmobile.ui.helmet.adapter.HelmetRecyclerAdapter
import com.np.kisanthapa.guideforpubgmobile.ui.helmet.model.HelmetModelClass
import com.np.kisanthapa.guideforpubgmobile.ui.helmet.model.HelmetModelClassParcelable

class HelmetFragment : Fragment() {

    companion object {
        fun newInstance() = HelmetFragment()
    }

    private lateinit var mHelmetViewModel: HelmetViewModel
    private lateinit var mHelmetRecyclerAdapter: HelmetRecyclerAdapter

    //Recycler view for helmet list
    private lateinit var mHelmetRecyclerView: RecyclerView

    // Navigation Controller for further Navigation
    // and passing arguments to further
    private lateinit var mNavController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.helmet_fragment, container, false)

        //Init recycler view here
        mHelmetRecyclerView = root.findViewById(R.id.mHelmetRecyclerView)

        //Initialize recycler view and its design here
        initRecyclerView()

        //View Model
        mHelmetViewModel = ViewModelProvider(this).get(HelmetViewModel::class.java)

        //Observe data in view model
        mHelmetViewModel.helmetList.observe(viewLifecycleOwner, Observer {
            it?.let {
                mHelmetRecyclerAdapter.setHelmetData(it)
            }
        })

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mNavController = Navigation.findNavController(view)

    }

    private fun initRecyclerView() {

        // Define view layout of recycler view here
        val viewManager = GridLayoutManager(requireContext(), 2)

        //Init Adapter here
        mHelmetRecyclerAdapter = HelmetRecyclerAdapter(requireContext())

        //Set other parameters of recycler view here
        mHelmetRecyclerView.apply {
            setHasFixedSize(true)
            // use a grid layout manager which is defined above
            layoutManager = viewManager
            // specify an viewAdapter
            adapter = mHelmetRecyclerAdapter
        }

        // Handle actions after user clicked the single item here
        mHelmetRecyclerAdapter.setOnHelmetItemClickListener(object :
            HelmetRecyclerAdapter.HelmetItemClickCallback {
            override fun onItemClick(helmetModel: HelmetModelClass) {

                val helmetParcelable: HelmetModelClassParcelable =
                    HelmetModelClassParcelable(
                        helmetModel.helmetId,
                        helmetModel.helmetName,
                        helmetModel.helmetFullName,
                        helmetModel.helmetLevel,
                        helmetModel.helmetDescription,
                        helmetModel.helmetDurability,
                        helmetModel.helmetDamageReduction,
                        helmetModel.helmetImageName
                    )

                val action = HelmetFragmentDirections.actionNavHelmetToDetailsHelmetFragment(
                    helmetParcelable,
                    helmetModel.helmetName
                )

                mNavController.navigate(action)
            }

        })
    }
}
