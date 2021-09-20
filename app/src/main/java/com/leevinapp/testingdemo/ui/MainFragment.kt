package com.leevinapp.testingdemo.ui

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.leevinapp.testingdemo.R
import com.leevinapp.testingdemo.common.Resource
import com.leevinapp.testingdemo.common.ViewModelFactory
import com.leevinapp.testingdemo.databinding.FragmentMainBinding
import com.leevinapp.testingdemo.repository.model.GithubRepo
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import timber.log.Timber
import javax.inject.Inject

class MainFragment : DaggerFragment() {

    private lateinit var binding: FragmentMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var dialog: Dialog

    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        /**
         * compile error for params required fragment that is not from androidx libs when using AndroidSupportInjection
         * Fixed by AndroidSupportInjection has migrate to androidx need implement dagger-android-support
         */
        AndroidSupportInjection.inject(this)
    }

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
        Timber.e("=====viewModelFactory=====$viewModelFactory")
        Timber.e("=====viewModel=====$viewModel")
        Timber.e("=====dialog=====$dialog")
        // todo should navigation via activity
        binding.buttonOther.setOnClickListener {
            activity?.apply {
                supportFragmentManager.beginTransaction()
                    .add(R.id.container, OtherFragment.newInstance(), OtherFragment::class.java.canonicalName)
                    .commit()
            }
        }
    }

    private fun initData() {
        viewModel.fetchData()
    }

    private fun observerViewModel() {
        with(viewModel) {
            userDataResult.observe(
                viewLifecycleOwner,
                Observer { result ->
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
