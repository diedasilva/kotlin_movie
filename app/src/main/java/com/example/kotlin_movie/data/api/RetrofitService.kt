import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    // Crée une instance de Retrofit avec la configuration de base
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Crée une instance de l'interface TMDbApi à partir de Retrofit
    val tmdbApi: TMDbApi by lazy {
        retrofit.create(TMDbApi::class.java)
    }
}
