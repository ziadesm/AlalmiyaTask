package com.task.alalmiyatask.repo
import com.task.alalmiyatask.db.CharacterDao
import com.task.alalmiyatask.network.CharacterNetworkRequest
import com.task.alalmiyatask.pojo.CharacterCache
import com.task.alalmiyatask.utils.CharacterDataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class MainRepository
@Inject constructor(
    private val retrofit: CharacterNetworkRequest,
    private val dao: CharacterDao
){
    suspend fun getSiteWord(): Flow<CharacterDataState<CharacterCache>> = flow {
        emit(CharacterDataState.Loading)
        try {
            val get = retrofit.getResponseBodyHtml()
            val re = Regex("[^A-Za-z0-9 ]")
            val responseString = re.replace(get.string().toString(), " ")

            dao.insertNewWord(CharacterCache(1, responseString))
            emit(CharacterDataState.Success(CharacterCache(1, responseString)))

        } catch (e: Exception) {
            emit(CharacterDataState.Error(e))
        }
    }
}