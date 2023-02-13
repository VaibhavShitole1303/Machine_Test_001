package com.example.machine_test_001

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.example.machine_test_001.databinding.ActivityMainBinding
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
   private  var productAdapter: ProductAdapter?=null
    var products=ArrayList<Product>()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        VollySingleton.initiallizeRequestQueue(this)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
     CoroutineScope(Dispatchers.IO).launch {
         binding.button.setOnClickListener {
             jsonObjectRequest()
         }
     }
    productAdapter= ProductAdapter(products)
        CoroutineScope(Dispatchers.Main).launch {
            initRecyclerView()
        }


    }
private fun initRecyclerView()
{
    binding.recyclerView.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
    binding.recyclerView.adapter=productAdapter
}

    private fun jsonObjectRequest() {
        var volleyJsonObjectRequest=JsonObjectRequest(
            Request.Method.GET,
            "https://dummyjson.com/products",
            null,
            JsonObjectRequestSuccessListener(),
            StringRequestErrorListener()
        )
        VollySingleton.volleyRequestQueue!!.add(volleyJsonObjectRequest)
    }

   inner class StringRequestErrorListener : Response.ErrorListener {
        override fun onErrorResponse(error: VolleyError?) {
            Toast.makeText(this@MainActivity,"$error",Toast.LENGTH_LONG).show()
        }

    }

   inner class JsonObjectRequestSuccessListener : Response.Listener<JSONObject> {
        override fun onResponse(response: JSONObject?) {
            var productResponse=Gson().fromJson<ProductResponse>(
        response.toString(),
                ProductResponse::class.java
            )
         var   productsFromApi=productResponse.products
           products.addAll(productsFromApi)
            productAdapter?.notifyDataSetChanged()
        }

    }

}

