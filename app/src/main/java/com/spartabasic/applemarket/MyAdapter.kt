package com.spartabasic.applemarket

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.spartabasic.applemarket.databinding.ItemProductBinding

class MyAdapter(private val mItems: List<MyItem>) : RecyclerView.Adapter<MyAdapter.Holder>() {

    interface ItemClick {
        fun onClick(view : View, position : Int)
    }

    var itemClick : ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(myItem = mItems[position])

        holder.itemView.setOnClickListener {  //클릭이벤트추가부분
            itemClick?.onClick(it, position)
        }

    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    class Holder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(myItem: MyItem) {
            binding.image.setImageResource(myItem.image)
            binding.name.text = myItem.name
            binding.address.text = myItem.address
            binding.price.text = myItem.price + "원"
        }
    }
}