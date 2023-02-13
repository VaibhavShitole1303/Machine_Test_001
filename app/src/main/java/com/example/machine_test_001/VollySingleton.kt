package com.example.machine_test_001

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

object VollySingleton {
    var volleyRequestQueue:RequestQueue?=null
    fun initiallizeRequestQueue(context: Context){
        volleyRequestQueue=Volley.newRequestQueue(context)
    }
}