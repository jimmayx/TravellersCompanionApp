package op.mobile.app.dev.simjd1.travelling.models

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

data class Attractions(
    val latLng: LatLng,
    val name: String,
    val city: String,
    val country: String
) : ClusterItem {
    override fun getPosition(): LatLng = latLng
    override fun getTitle(): String = name
    override fun getSnippet(): String = ""
}