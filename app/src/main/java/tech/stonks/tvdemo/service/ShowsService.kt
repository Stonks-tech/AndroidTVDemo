package tech.stonks.tvdemo.service

import tech.stonks.tvdemo.model.ShowModel
private const val videoUrl = "https://storage.googleapis.com/video-streaming-tests/demo-video.mp4"

class ShowsService {
    fun getShows(): List<ShowModel> {
        return listOf(
            ShowModel(
                id = "1",
                title = "The Witcher",
                imageUrl = "https://www.themoviedb.org/t/p/w1066_and_h600_bestv2/foGkPxpw9h8zln81j63mix5B7m8.jpg",
                videoUrl = videoUrl
            ),
            ShowModel(
                id = "2",
                title = "Breaking Bad",
                imageUrl = "https://www.themoviedb.org/t/p/w1066_and_h600_bestv2/9faGSFi5jam6pDWGNd0p8JcJgXQ.jpg",
                videoUrl = videoUrl
            ),
            ShowModel(
                id = "3",
                title = "Bojack Horseman",
                imageUrl = "https://www.themoviedb.org/t/p/w1066_and_h600_bestv2/qFYDJUIFh8zgEDy3EvnHwhgOl0S.jpg",
                videoUrl = videoUrl
            ),
            ShowModel(
                id = "4",
                title = "Outlander",
                imageUrl = "https://www.themoviedb.org/t/p/w1066_and_h600_bestv2/nf3Vlxm3C9U1aKUUQHmKFZmxPSc.jpg",
                videoUrl = videoUrl
            ),
        )
    }

    fun getMovies(): List<ShowModel> {
        return listOf(
            ShowModel(
                id = "5",
                title = "Gladiator",
                imageUrl = "https://www.themoviedb.org/t/p/w1066_and_h600_bestv2/7PBXKqTDCsSZb3GATn94DfXoAGx.jpg",
                videoUrl = videoUrl
            ),
            ShowModel(
                id = "6",
                title = "BraveHeart",
                imageUrl = "https://www.themoviedb.org/t/p/w1066_and_h600_bestv2/vXhTr0cN54R0TbSD9iG5cuAEWcT.jpg",
                videoUrl = videoUrl
            ),
        )
    }

    fun get(id: String): ShowModel {
        val list = getShows().toMutableList()
        list.addAll(getMovies())
        return list.find { it.id == id }!!
    }
}
