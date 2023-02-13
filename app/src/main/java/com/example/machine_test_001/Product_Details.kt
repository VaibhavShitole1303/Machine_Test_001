package com.example.machine_test_001

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.machine_test_001.databinding.ActivityMainBinding
import com.example.machine_test_001.databinding.ActivityProductDetailsBinding

class Product_Details : AppCompatActivity(
) {
    private lateinit var binding: ActivityProductDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityProductDetailsBinding.inflate(layoutInflater)
        getData()
        setContentView(binding.root)


    }
    private fun getData()
    {
        var intent=intent
        var product=intent.getSerializableExtra("product") as Product
        binding.title.text=product.title
        binding.brand.text=product.brand
        var a: String =product.thumbnail
        var c:Int=a.toInt()
        binding.thumbnail.setImageResource(c)
        binding.price.text=product.price.toString()
    }
}