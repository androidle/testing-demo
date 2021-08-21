package com.leevinapp.testingdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.test.espresso.idling.CountingIdlingResource
import com.leevinapp.testingdemo.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : Fragment() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val retrofit =
            RetrofitProvider(getBaseUrl(), OkHttpProvider.getOkHttpClient()).getRetrofit()
        val githubApi = retrofit.create(GithubApi::class.java)


        githubApi.testFetch().enqueue(object : Callback<List<GithubRepoResponse>> {
            override fun onResponse(
                call: Call<List<GithubRepoResponse>>,
                response: Response<List<GithubRepoResponse>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    val result = response.body()!!
                    if (result.isEmpty()) {
                        showEmptyDataState()
                    } else {
                        showSuccessDataState(result)
                    }
                } else {
                    showErrorState()
                }
            }

            override fun onFailure(call: Call<List<GithubRepoResponse>>, t: Throwable) {
                showErrorState()
            }
        })
    }

    private fun showSuccessDataState(body: List<GithubRepoResponse>) {
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

    private fun getBaseUrl(): String {
        return (activity?.applicationContext as BaseUrlProvider).getBaseUrl()
    }
}