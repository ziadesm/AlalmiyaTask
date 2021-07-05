package com.task.alalmiyatask.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.task.alalmiyatask.R
import com.task.alalmiyatask.adapter.CharacterAdapter
import com.task.alalmiyatask.utils.CharacterDataState
import com.task.alalmiyatask.utils.CharacterValidation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_character_view.*

@AndroidEntryPoint
class CharacterViewActivity : AppCompatActivity() {

    private val viewModel: CharacterViewModel by viewModels()
    private val charAdapter: CharacterAdapter by lazy { CharacterAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_view)

        viewModel.setStateEvent(MainStateEvent.GetSiteWord)
        setupRecyclerView()
        observeSiteWordResult()

        cancel.setOnClickListener{
            viewModel.setStateEvent(MainStateEvent.Cancel)
        }
    }

    private fun observeSiteWordResult() {
        viewModel.dataState.observe(this, { dataState ->
            when(dataState) {
                is CharacterDataState.Loading -> {
                    Toast.makeText(this, "Loading", LENGTH_LONG).show()
                    showAndHideProgress(true, "")
                }
                is CharacterDataState.Error -> {
                    Toast.makeText(this, "Error", LENGTH_LONG).show()
                    viewModel.setStateEvent(MainStateEvent.Nothing)
                    observeDataCache()
                }
                is CharacterDataState.Success -> {
                    Toast.makeText(this, "Success", LENGTH_LONG).show()
                    charAdapter.submitList(CharacterValidation.getCharacterValidationList(dataState.data.characters_site))
                    showAndHideProgress(false, "")
                }
                is CharacterDataState.Cancel -> {
                    Toast.makeText(this, "Canceled", LENGTH_LONG).show()
                }
                is CharacterDataState.UnknownError -> {
                    Toast.makeText(this, "Unknown Error", LENGTH_LONG).show()
                }
            }
        })
    }

    private fun observeDataCache() {
        viewModel.characterList.observe(this@CharacterViewActivity, {
            if (it != null && it.isNotEmpty()) {
                charAdapter.submitList(it)
                showAndHideProgress(false, "")
            } else {
                showAndHideProgress(true, "Error, No backup data in your cache")
            }
        })
    }

    private fun showAndHideProgress(show: Boolean, text: String) {
        if (show && text == "") {
            flipVisibility(loading_screen_progress_bar, loading_screen_text)
        } else if (!show){
            flipVisibility(loading_screen_progress_bar, loading_screen_text)
        } else if (text != "") {
            flipVisibility(loading_screen_progress_bar, loading_screen_text)
            loading_screen_text.text = getString(R.string.error_handling_network)
        }
    }

    private fun setupRecyclerView() {
        rv_character_items.apply {
            adapter = charAdapter
            layoutManager = GridLayoutManager(this@CharacterViewActivity, 2)
        }
    }

    private fun flipVisibility(vararg views: View) {
        for (view in views) {
            if (view.visibility == VISIBLE) view.visibility = GONE
            else view.visibility = VISIBLE
        }
    }
}