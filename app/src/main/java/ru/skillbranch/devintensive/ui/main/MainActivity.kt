package ru.skillbranch.devintensive.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.ui.adapters.ChatAdapter
import ru.skillbranch.devintensive.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var toolbar: Toolbar
    private lateinit var rvChat: RecyclerView
    private lateinit var adapter: ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolabr()
        initViews()
        initViewModels()
    }

    private fun initViews() {
        rvChat = findViewById(R.id.rv_chat_list)
        adapter = ChatAdapter()
        rvChat.adapter = adapter
    }

    private fun initViewModels() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    private fun initToolabr() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }
}
