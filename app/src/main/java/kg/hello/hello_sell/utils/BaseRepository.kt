package kg.hello.hello_sell.utils

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.ConnectException
import java.net.SocketTimeoutException

abstract class BaseRepository {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.success(apiCall.invoke())
            }catch (e: Exception){
                e.printStackTrace()
                Log.e(TAG, "safeApiCall: NetworkResponse Failure ${this.javaClass.name}", e)
                Resource.error(e.localizedMessage ?: "no message", null)
            }catch (throwable: Throwable) {
                throwable.printStackTrace()
                Resource.error(throwable.localizedMessage ?: "no message", null)
            }
        }
    }

    companion object {
        private const val TAG = "BaseRepository"
    }
}