package com.arabin.albertsonsacronymstest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.arabin.albertsonsacronymstest.adapter.MainAdapter
import com.arabin.albertsonsacronymstest.databinding.FragmentDetailsMacroBinding
import com.arabin.albertsonsacronymstest.retrofit.viewmodel.ShareDataViewModel

/**
 * An example full-screen fragment that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class DetailsMacroFragment : Fragment() {

    private var fragmentDetailsBinding: FragmentDetailsMacroBinding? = null
    private lateinit var shareDataViewModel: ShareDataViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentDetailsBinding = FragmentDetailsMacroBinding.inflate(inflater, container, false)
        return fragmentDetailsBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shareDataViewModel = requireActivity().run {
            ViewModelProvider(
                requireActivity(),
                defaultViewModelProviderFactory
            )[ShareDataViewModel::class.java]
        }

        shareDataViewModel.response.observe(viewLifecycleOwner) { resp ->
            fragmentDetailsBinding?.apply {
                subMacro.apply {
                    adapter = MainAdapter(resp)
                }
            }
        }
    }
}