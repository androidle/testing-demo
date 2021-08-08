package com.leevinapp.testingdemo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.leevinapp.testingdemo.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = RetrofitProvider(getBaseUrl(), OkHttpProvider.getOkHttpClient()).getRetrofit()
        val githubApi = retrofit.create(GithubApi::class.java)

        githubApi.testFetch().enqueue(object : Callback<GithubResponse> {
            override fun onResponse(call: Call<GithubResponse>, response: Response<GithubResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val result = response.body()!!
                    showSuccessDataState(result)
                } else {
                    showEmptyDataState()
                }
            }

            override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                showErrorState()
            }
        })

    }

    private fun showSuccessDataState(body: GithubResponse) {
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
        return (applicationContext as DemoApplication).getBaseUrl()
    }
}