package com.example.anime.data

import com.example.anime.model.FakeAnime
import com.example.anime.model.FakeAnimeDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class AnimeRepository {

    private val fakeAnime = mutableListOf<FakeAnime>()

    init {
        if (fakeAnime.isEmpty()) {
            FakeAnimeDataSource.dummyAnime.forEach {
                fakeAnime.add(FakeAnime(it, 0))
            }
        }
    }

    fun getAllAnime(): Flow<List<FakeAnime>> {
        return flowOf(fakeAnime)
    }

    fun getAnimeById(animeId: Long): FakeAnime {
        return fakeAnime.first {
            it.anime.id == animeId
        }
    }

    fun updateAnime(animeId: Long, newCountValue: Int): Flow<Boolean> {
        val index = fakeAnime.indexOfFirst { it.anime.id == animeId }
        val result = if (index >= 0) {
            val fake = fakeAnime[index]
            fakeAnime[index] = fake.copy(anime = fake.anime, count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getAddedAnime(): Flow<List<FakeAnime>> {
        return getAllAnime()
            .map { fakeAnime ->
                fakeAnime.filter { fakeAnime ->
                    fakeAnime.count != 0
                }
            }
    }

    companion object {
        @Volatile
        private var instance: AnimeRepository? = null

        fun getInstance(): AnimeRepository =
            instance ?: synchronized(this) {
                AnimeRepository().apply {
                    instance = this
                }
            }
    }
}