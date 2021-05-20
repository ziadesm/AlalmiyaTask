package com.task.alalmiyatask.ui
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.task.alalmiyatask.db.CharacterDao
import com.task.alalmiyatask.pojo.CharacterCache
import com.task.alalmiyatask.pojo.CharacterModel
import com.task.alalmiyatask.repo.MainRepository
import com.task.alalmiyatask.utils.CharacterDataState
import com.task.alalmiyatask.utils.CharacterValidation
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class CharacterViewModel
@ViewModelInject constructor(
    private val repo: MainRepository,
    private val db: CharacterDao,
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel(){
    val characterList: MutableLiveData<List<CharacterModel>> = MutableLiveData()
    private val _dataState: MutableLiveData<CharacterDataState<CharacterCache>> = MutableLiveData()

    val dataState: LiveData<CharacterDataState<CharacterCache>>
        get() = _dataState

    fun setStateEvent(state: MainStateEvent){
        viewModelScope.launch {
            when(state) {
                is MainStateEvent.GetSiteWord -> {
                    repo.getSiteWord().onEach {
                        _dataState.value = it
                    }.launchIn(viewModelScope)
                }
                is MainStateEvent.Nothing -> {
                    val siteWord = db.getSiteWord()
                    if (siteWord != null && siteWord.characters_site != "") characterList.value = CharacterValidation.getCharacterValidationList(siteWord.characters_site)
                }
            }
        }
    }
}

sealed class MainStateEvent{
    object GetSiteWord: MainStateEvent()
    object Nothing: MainStateEvent()
}