package com.example.machine_test_001

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.machine_test_001.databinding.ProductViewBinding

class ProductAdapter(
   //
    var products:ArrayList<Product>
): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

   inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var productViewBinding:ProductViewBinding=ProductViewBinding.bind(itemView)
        var image1=productViewBinding.thumbnail.setOnClickListener{
            var product=products.get(adapterPosition)
            var intent=Intent(itemView.context,Product_Details::class.java)
            intent.putExtra("product",product)
            itemView.context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        var layoutInflater= LayoutInflater.from(parent.context)
        var productView=layoutInflater.inflate(R.layout.product_view,null)
        return ProductViewHolder(productView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.productViewBinding.title.text="${products[position].title}"
        holder.productViewBinding.description.text="${products[position].description}"
        holder.productViewBinding.price.text="${products[position].price}"
        holder.productViewBinding.brand.text="${products[position].brand}"
      Glide.with(holder.productViewBinding.thumbnail).load(products[position].thumbnail)
            .into(holder.productViewBinding.thumbnail)

        /*holder.itemView.setOnClickListener {
            var intent= Intent(holder.itemView.context,Product_Details::class.java)
            intent.putExtra("products",products)
           holder.itemView.context.startService(intent)
        }*/
    }

    override fun getItemCount(): Int {
        return products.size
    }}