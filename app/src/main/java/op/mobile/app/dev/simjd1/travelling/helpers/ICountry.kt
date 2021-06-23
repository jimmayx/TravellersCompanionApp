package op.mobile.app.dev.simjd1.travelling.helpers

import op.mobile.app.dev.simjd1.travelling.models.Country
import retrofit2.http.GET

interface ICountry {
    @GET("raw")
    suspend fun getResponse(): List<Country>
}