package com.example.mydemomvvm.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mydemomvvm.model.Article

class MyViewModel: ViewModel() {
    var lst = MutableLiveData<ArrayList<Article>>()
    var newlist = arrayListOf<Article>()

    fun add(blog: Article){
        newlist.add(blog)
        lst.value=newlist
    }

    fun remove(blog: Article){
        newlist.remove(blog)
        lst.value=newlist
    }
}