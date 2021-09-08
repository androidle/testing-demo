package com.leevinapp.testingdemo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.leevinapp.testingdemo.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var githubApiService: GithubApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as DemoApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        githubApiService.testFetch().enqueue(object : Callback<List<GithubRepoResponse>> {
            override fun onResponse(call: Call<List<GithubRepoResponse>>, response: Response<List<GithubRepoResponse>>) {
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

        binding.openMainFragment.setOnClickListener {
            startActivity(Intent(this, SingleFragmentActivity::class.java))
        }
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
}
