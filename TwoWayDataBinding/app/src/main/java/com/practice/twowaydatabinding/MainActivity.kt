package com.practice.twowaydatabinding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.practice.twowaydatabinding.databinding.ActivityMainBinding
import com.practice.twowaydatabinding.ui.theme.TwoWayDataBindingTheme

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    val loginViewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        activityMainBinding.lifecycleOwner = this
        activityMainBinding.viewModel = loginViewModel
        loginViewModel.user.observe(this, Observer { user ->
            activityMainBinding.user = user
        })

    }
}