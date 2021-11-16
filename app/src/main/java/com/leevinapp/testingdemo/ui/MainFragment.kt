package com.leevinapp.testingdemo.ui

import android.app.Dialog
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.leevinapp.journeylib.JourneyFeature
import com.leevinapp.testingdemo.R
import com.leevinapp.testingdemo.common.Resource
import com.leevinapp.testingdemo.databinding.FragmentMainBinding
import com.leevinapp.testingdemo.repository.model.GithubRepo
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    @Inject
    lateinit var dialog: Dialog

    @Inject
    lateinit var resourcesGlobal: Resources

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        observerViewModel()
    }

    private fun initData() {
        viewModel.fetchData()
    }

    private fun observerViewModel() {
        viewModel.userDataResult.observe(
            viewLifecycleOwner,
            { result ->
                when (result) {
                    is Resource.Success -> {
                        if (result.data.isNotEmpty()) {
                            showSuccessDataState(result.data)
                        } else {
                            showEmptyDataState()
                        }
                    }
                    is Resource.Error -> {
                        showErrorState()
                    }
                }
            }
        )
    }

    private fun showSuccessDataState(body: List<GithubRepo>) {
        binding.textviewSuccess.visibility = View.VISIBLE
        binding.textviewSuccess.text = body.toString()
        binding.progressBar.visibility = View.GONE
        binding.textview.visibility = View.GONE
    }

    private fun showEmptyDataState() {
        binding.textviewSuccess.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
        binding.textview.visibility = View.VISIBLE
        binding.textview.text = getString(R.string.no_data_message)
    }

    private fun showErrorState() {
        binding.textviewSuccess.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
        binding.textview.visibility = View.VISIBLE
        binding.textview.text = getString(R.string.error_message)
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
