package op.mobile.app.dev.simjd1.travelling.helpers

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceInstance(private val url: String) {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val retrofitCountryService: ICountry by lazy {
        retrofit.create(ICountry::class.java)
    }

    val retrofitYandexService: IYandex by lazy {
        retrofit.create(IYandex::class.java)
    }
}