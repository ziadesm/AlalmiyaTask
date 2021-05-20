package com.task.alalmiyatask.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
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

    }

    private fun observeSiteWordResult() {
        viewModel.dataState.observe(this, { dataState ->
            when(dataState) {
                is CharacterDataState.Loading -> {
                    showAndHideProgress(true, "")
                }
                is CharacterDataState.Error -> {
                    viewModel.setStateEvent(MainStateEvent.Nothing)
                    observeDataCache()
                }
                is CharacterDataState.Success -> {
                    charAdapter.submitList(CharacterValidation.getCharacterValidationList(dataState.data.characters_site))
                    showAndHideProgress(false, "")
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
            loading_screen_progress_bar.visibility = VISIBLE
            loading_screen_text.visibility = VISIBLE
        } else if (!show){
            loading_screen_progress_bar.visibility = GONE
            loading_screen_text.visibility = GONE
        } else if (text != "") {
            loading_screen_progress_bar.visibility = GONE
            loading_screen_text.visibility = VISIBLE
            loading_screen_text.text = getString(R.string.error_handling_network)
        }
    }

    private fun setupRecyclerView() {
        rv_character_items.apply {
            adapter = charAdapter
            layoutManager = GridLayoutManager(this@CharacterViewActivity, 2)
        }
    }
}