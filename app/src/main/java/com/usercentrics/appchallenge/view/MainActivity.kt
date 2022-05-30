package com.usercentrics.appchallenge.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.usercentrics.appchallenge.R
import com.usercentrics.appchallenge.databinding.ActivityMainBinding
import com.usercentrics.sdk.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private var banner: UsercentricsBanner? = null
    private lateinit var binding: ActivityMainBinding

    // Lazy injected MainViewModel
    private val mainViewModel: MainViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        /*
            checking  Usercentrics is ready or not
         */
        Usercentrics.isReady(onSuccess = {
            binding.consentButton.isEnabled = true
            binding.progressBar.visibility = View.GONE
        }, onFailure = {
            // Handle error
            it.printStackTrace()
            binding.consentButton.isEnabled = false
            binding.progressBar.visibility = View.GONE
        })

        /*
            observing the total cost from view  model
         */
        mainViewModel.getConsentScore().observe(this) { data ->
            data?.let {
                binding.consentScore.text = data
            }
        }
    }

    fun showBannerButtonClicked(view: View) {
        showBanner()
    }

    private fun showBanner(
        layout: UsercentricsLayout = UsercentricsLayout.Popup(PopupPosition.BOTTOM),
        settings: BannerSettings? = null
    ) {
        banner = UsercentricsBanner(this, settings).also {
            it.showFirstLayer(
                layout = layout,
                callback = ::handleUserResponse
            )
        }
    }

    private fun handleUserResponse(userResponse: UsercentricsConsentUserResponse?) {
        userResponse ?: return
        getCMPData(userResponse)

    }

    private fun getCMPData(userResponse: UsercentricsConsentUserResponse) {
        val data = Usercentrics.instance.getCMPData()
        mainViewModel.calculateServiceCost(userResponse, data)
    }

}