package com.task.alalmiyatask.repo
import android.util.Log
import com.task.alalmiyatask.db.CharacterDao
import com.task.alalmiyatask.network.CharacterNetworkRequest
import com.task.alalmiyatask.pojo.CharacterCache
import com.task.alalmiyatask.remote.NetworkResponse
import com.task.alalmiyatask.utils.CharacterDataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class MainRepository @Inject constructor(
        private val retrofit: CharacterNetworkRequest,
        private val dao: CharacterDao) {

    suspend fun getSiteWord(): Flow<CharacterDataState<CharacterCache>> = flow{
        emit(CharacterDataState.Loading)
        delay(2500)

        when(val get = retrofit.getResponseBodyHtml()) {
            is NetworkResponse.Success -> {
                val re = Regex("[^A-Za-z0-9 ]")
                val responseString = re.replace(get.body.toString(), " ")

                dao.insertNewWord(CharacterCache(1, responseString))
                emit(CharacterDataState.Success(CharacterCache(1, responseString)))
            }
            is NetworkResponse.ApiError -> {
                emit(CharacterDataState.Cancel(get.body.toString()))
            }
            is NetworkResponse.NetworkError -> {
                emit(CharacterDataState.Error(get.error))
            }
            is NetworkResponse.UnknownError -> {
                emit(CharacterDataState.UnknownError(get.error!!))
            }
        }
    }
}
