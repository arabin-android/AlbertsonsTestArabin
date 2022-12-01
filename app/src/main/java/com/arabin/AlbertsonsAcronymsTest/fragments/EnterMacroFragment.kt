package com.arabin.AlbertsonsAcronymsTest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.arabin.AlbertsonsAcronymsTest.R
import com.arabin.AlbertsonsAcronymsTest.databinding.EntermacroFragmentBinding
import com.arabin.AlbertsonsAcronymsTest.retrofit.RestAPIStatus
import com.arabin.AlbertsonsAcronymsTest.retrofit.viewmodel.ResponseViewModel
import com.arabin.AlbertsonsAcronymsTest.retrofit.viewmodel.ShareDataViewModel


/**
 * An example full-screen fragment that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class EnterMacroFragment : Fragment() {


    private lateinit var responseViewModel : ResponseViewModel
    private lateinit var shareDataViewModel: ShareDataViewModel


    private var enterMacroFragmentBinding: EntermacroFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        enterMacroFragmentBinding = EntermacroFragmentBinding.inflate(inflater, container, false)
        return enterMacroFragmentBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        responseViewModel = requireActivity().run {
            ViewModelProvider(requireActivity(), defaultViewModelProviderFactory).get(ResponseViewModel::class.java)
        }
        shareDataViewModel = requireActivity().run {
            ViewModelProvider(requireActivity(), defaultViewModelProviderFactory).get(ShareDataViewModel::class.java)
        }
    }

    override fun onResume() {
        super.onResume()

        val macroText = enterMacroFragmentBinding?.macroText?.text?:""
        val fullForm = enterMacroFragmentBinding?.fullForm?.text?:""

        enterMacroFragmentBinding?.submit?.setOnClickListener {
            if (macroText.isNotBlank() || fullForm.isNotBlank()){
                responseViewModel.init()
                responseViewModel.getAcronym(macroText.toString(),fullForm.toString()
                ).observe(this) {
                    when (it.status) {
                        RestAPIStatus.SUCCESS -> {
                            val data = it.data
                            data?.let { it1 -> shareDataViewModel.setResponse(it1) }
                            val navController = Navigation.findNavController(requireActivity(),
                                R.id.nav_host_fragment)
                            navController.navigate(R.id.details_fragment, null, getNavigationOptions())
                        }
                        RestAPIStatus.ERROR -> {
                            Toast.makeText(requireContext(), "NetworkError", Toast.LENGTH_LONG).show()
                        }
                        RestAPIStatus.LOADING -> {
                            Toast.makeText(requireContext(), "Loading please wait...", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }else{
                Toast.makeText(requireContext(), "Enter a macro of full form", Toast.LENGTH_LONG).show()
            }
        }
    }


    companion object{
        fun getNavigationOptions(): NavOptions? {
            return NavOptions.Builder()
                .setLaunchSingleTop(true) // Used to prevent multiple copies of the same destination
                .setEnterAnim(androidx.navigation.ui.R.anim.nav_default_enter_anim)
                .setExitAnim(androidx.navigation.ui.R.anim.nav_default_exit_anim)
                .setPopEnterAnim(androidx.navigation.ui.R.anim.nav_default_pop_enter_anim)
                .setPopExitAnim(androidx.navigation.ui.R.anim.nav_default_pop_exit_anim)
                .build()
        }
    }

}