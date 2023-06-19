package com.example.mydemomvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.mydemomvvm.ViewModel.MyViewModel
import com.example.mydemomvvm.ViewModel.MyViewModelFactory
import com.example.mydemomvvm.adapter.RvAdapter
import com.example.mydemomvvm.model.Article

class MainActivity : AppCompatActivity() {
    private var viewManager = LinearLayoutManager(this)
    private lateinit var viewModel: MyViewModel
    private lateinit var mainrecycler: RecyclerView
    private lateinit var but: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainrecycler = findViewById(R.id.recycler)
        val application = requireNotNull(this).application
        val factory = MyViewModelFactory()
        viewModel = ViewModelProvider(this,factory).get(MyViewModel::class.java)
        but = findViewById(R.id.button)
        but.setOnClickListener {
            addData()
        }

        initialiseAdapter()
observeData()
    }
    private fun initialiseAdapter(){
        mainrecycler.layoutManager = viewManager
        mainrecycler.adapter= RvAdapter(this,viewModel, ArrayList<Article>())

        observeData()
    }

    private fun observeData(){
        viewModel.newlist.observe(this, Observer{
            Log.i("okokp",it.toString())
            mainrecycler.adapter= RvAdapter(this,viewModel, it)
        })
    }
    private fun addData(){
        var txtplce = findViewById<EditText>(R.id.titletxt)
        var title=txtplce.text.toString()
        if(title.isNullOrBlank()){
            Toast.makeText(this,"Enter value!", Toast.LENGTH_LONG).show()
        }else{
            var blog= Article(title)
            viewModel.add(blog)
            txtplce.text.clear()
            mainrecycler.adapter?.notifyDataSetChanged()
        }

    }
}