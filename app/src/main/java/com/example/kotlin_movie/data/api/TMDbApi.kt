import com.example.kotlin_movie.data.models.Movie
import com.example.kotlin_movie.data.models.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDbApi {
    // Requête GET pour récupérer les films populaires
    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String): Call<MovieResponse>

    // Requête GET pour récupérer les détails d'un film spécifique
    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Call<Movie>
}