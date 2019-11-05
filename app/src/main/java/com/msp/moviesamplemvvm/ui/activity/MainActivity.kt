package com.msp.moviesamplemvvm.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.msp.moviesamplemvvm.R
import com.msp.moviesamplemvvm.databinding.ActivityMainBinding
import com.msp.moviesamplemvvm.ui.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.mainVM = viewModel
        binding.lifecycleOwner = this
    }
}
