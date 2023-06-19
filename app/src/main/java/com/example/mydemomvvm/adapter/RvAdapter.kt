package com.example.mydemomvvm.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.mydemomvvm.R
import com.example.mydemomvvm.ViewModel.MyViewModel
import com.example.mydemomvvm.databinding.ItemViewBinding
import com.example.mydemomvvm.model.Article
import org.w3c.dom.Text

class RvAdapter(val context: Context, val viewModel: MyViewModel, val arrayList: ArrayList<Article>): RecyclerView.Adapter<RvAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvAdapter.MyViewHolder {

        var root = ItemViewBinding.inflate( LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(root)
    }

    override fun onBindViewHolder(holder:RvAdapter.MyViewHolder, position: Int) {
        holder.bind(arrayList.get(position))
    }

    override fun getItemCount(): Int {
        if(arrayList.size==0){
            Toast.makeText(context,"List is empty", Toast.LENGTH_LONG).show()
        }
        return arrayList.size    }

    inner class MyViewHolder(private val binding: ItemViewBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(article: Article){
            binding.title.text = article.title
            binding.delete.setOnClickListener {
                viewModel.remove(article)
                notifyItemRemoved(arrayList.indexOf(article))
            }
        }
    }

}