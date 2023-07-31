package com.example.getmoview.domain.model

import com.example.getmoview.common.Resources
import kotlinx.coroutines.flow.Flow

class DefaultPaginator<Key, MovieDtoItem>(
    private val initialKeys: Key,
    private inline val onLoadUpdate: (Boolean) -> Unit,
    private inline val onRequest: (nextKey: Key) -> Flow<Resources<List<MovieDtoItem>>>,
    private inline val getNextKey: suspend (List<MovieDtoItem>) -> Key,
    private inline val onError: suspend (Throwable?) -> Unit,
    private inline val onSuccess: suspend (items: List<MovieDtoItem>, newKey: Key) -> Unit
) : Paginator<Key, MovieDtoItem> {

    private var currentKey = initialKeys
    private var isMakingRequest = false
    override suspend fun loadNextItem() {
        if (isMakingRequest) return

        isMakingRequest = true
        onLoadUpdate(true)

        try {
            onRequest(currentKey).collect { result ->
                when (result) {
                    is Resources.IsLoading -> {}

                    is Resources.Success -> {
                        currentKey = getNextKey(result.data ?: emptyList())
                        onSuccess(result.data ?: emptyList(), currentKey)
                    }

                    is Resources.Error -> {
                        onError(Throwable(result.message))
                    }
                }
            }
        } catch (e: Exception) {
            onError(e)
        } finally {
            isMakingRequest = false
            onLoadUpdate(false)
        }
    }

    override suspend fun reset() {
        currentKey = initialKeys
    }
}