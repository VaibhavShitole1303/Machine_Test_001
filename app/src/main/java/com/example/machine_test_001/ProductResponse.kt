package com.example.machine_test_001

import com.google.gson.annotations.SerializedName

class ProductResponse(
    @SerializedName("products")
    var products:ArrayList<Product>
) {
    override fun toString(): String {
        return super.toString()
    }
}