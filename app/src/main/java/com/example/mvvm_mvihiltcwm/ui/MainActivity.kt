package com.example.mvvm_mvihiltcwm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.mvvm_mvihiltcwm.R
import com.example.mvvm_mvihiltcwm.model.Blog
import com.example.mvvm_mvihiltcwm.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG: String = "MainActivity"

    private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetBlogEvents) // intent
    }


    private fun subscribeObservers() {
        viewModel.dataState.observe(this, Observer { dataState ->

            when(dataState) {

                is DataState.Success<List<Blog>> -> {
                    displayProgressBar(false)
                    appendAndShowBlogTitles(dataState.data)

                }

                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message)
                }

                is DataState.Loading -> {
                    displayProgressBar(true)
                }

            }
        })
    }

    private fun displayError(message: String?) {
        if(message!=null) text.text = message
        else text.text = "Unknown Error"
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        progress_bar.visibility = if(isDisplayed) View.VISIBLE else View.GONE
    }

    private fun appendAndShowBlogTitles(blogs: List<Blog>) {
        val sb = StringBuilder()

        for(blog in blogs) {
            sb.append(blog.title)
        }

        text.text = sb.toString()
    }

}