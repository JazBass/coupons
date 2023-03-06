package com.jazbass.coupons.mainModule.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.jazbass.coupons.R
import com.jazbass.coupons.common.entities.CouponEntity
import com.jazbass.coupons.common.utils.hideKeyword
import com.jazbass.coupons.databinding.ActivityMainBinding
import com.jazbass.coupons.mainModule.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViewModel()
        setUpObservers()
        setUpButtons()
    }

    private fun setUpViewModel() {
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    private fun setUpObservers() {
        mainViewModel.getResult().observe(this) { coupon ->
            if (coupon == null) {
                with(binding) {
                    tilDescription.hint = getString(R.string.main_hint_description)
                    tilDescription.isEnabled = true
                    btnCreate.visibility = View.VISIBLE
                }
            } else {
                with(binding) {
                    etDescription.setText(coupon.description)
                    val status = getString(
                        if (coupon.isActive) R.string.main_hint_active
                        else R.string.main_hint_description
                    )
                    tilDescription.hint = status
                    tilDescription.isEnabled = false
                    btnCreate.visibility = if (coupon.isActive) View.GONE else View.VISIBLE
                }
            }
        }
        mainViewModel.getSnackbarMsg().observe(this) { msg ->
            Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun setUpButtons() {
        binding.btnConsult.setOnClickListener {
            mainViewModel.consultCouponBybCode(binding.etCoupon.text.toString())
            hideKeyword(this, binding.root)
        }
        binding.btnCreate.setOnClickListener {
            val coupon = CouponEntity(
                code = binding.etCoupon.text.toString(),
                description = binding.etDescription.text.toString()
            )
            mainViewModel.saveCoupon(coupon)
            hideKeyword(this, binding.root)
        }
    }
}