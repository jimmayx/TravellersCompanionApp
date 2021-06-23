package op.mobile.app.dev.simjd1.travelling.helpers

import com.google.android.gms.maps.model.LatLng
import op.mobile.app.dev.simjd1.travelling.models.Attractions

data class AttractionsJSONResponse(
    val name: String,
    val city: String,
    val location: Location,
    val country: String
) {
    data class Location(
        val latitude: Double,
        val longitude: Double
    )
}

fun AttractionsJSONResponse.toAttractions() = Attractions(
    name = name,
    city = city,
    latLng = LatLng(location.latitude, location.longitude),
    country = country
)