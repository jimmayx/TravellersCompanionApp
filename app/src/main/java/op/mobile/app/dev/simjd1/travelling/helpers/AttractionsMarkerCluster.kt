package op.mobile.app.dev.simjd1.travelling.helpers

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.ClusterManager.OnClusterClickListener
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import com.google.maps.android.ui.IconGenerator
import op.mobile.app.dev.simjd1.travelling.R
import op.mobile.app.dev.simjd1.travelling.databinding.ClusterMarkerBinding
import op.mobile.app.dev.simjd1.travelling.databinding.ClusterMarkerInfoWindowBinding
import op.mobile.app.dev.simjd1.travelling.models.Attractions

class AttractionsMarkerCluster(
    context: Context,
    private val map: GoogleMap,
    clusterManager: ClusterManager<Attractions>
) :
    DefaultClusterRenderer<Attractions>(context, map, clusterManager),
    OnClusterClickListener<Attractions>,
    OnInfoWindowClickListener {

    private var layoutInflater: LayoutInflater = LayoutInflater.from(context)
    private val iconGen = IconGenerator(context)
    private var binding: ClusterMarkerBinding = DataBindingUtil.inflate(
        layoutInflater,
        R.layout.cluster_marker,
        null,
        false
    )

    init {
        val drawable: Drawable? = ContextCompat.getDrawable(context, android.R.color.transparent)
        iconGen.setBackground(drawable)
        iconGen.setContentView(binding.root)

        clusterManager.setOnClusterClickListener(this)
        clusterManager.markerCollection.setOnInfoWindowAdapter(InfoWindowAdapter())

        map.setInfoWindowAdapter(clusterManager.markerManager)
        map.setOnInfoWindowClickListener(this)
        map.setOnCameraIdleListener(clusterManager)
        map.setOnMarkerClickListener(clusterManager)
    }

    override fun onBeforeClusterItemRendered(item: Attractions, markerOptions: MarkerOptions) {
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
    }

    override fun onBeforeClusterRendered(
        cluster: Cluster<Attractions>,
        markerOptions: MarkerOptions
    ) {
        binding.tvCluster.text = cluster.size.toString()

        val icon: Bitmap = iconGen.makeIcon()
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon))
    }

    override fun onClusterItemRendered(clusterItem: Attractions, marker: Marker) {
        marker.tag = clusterItem
    }

    override fun onClusterClick(cluster: Cluster<Attractions>): Boolean {
        val builder = LatLngBounds.Builder()
        for (data: Attractions in cluster.items)
            builder.include(data.position)

        try {
            map.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 100))
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return true
    }

    override fun onInfoWindowClick(marker: Marker) {}

    inner class InfoWindowAdapter : GoogleMap.InfoWindowAdapter {
        private val binding: ClusterMarkerInfoWindowBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.cluster_marker_info_window,
            null,
            false
        )

        override fun getInfoWindow(marker: Marker): View {
            val attractions: Attractions = marker.tag as Attractions
            binding.tvName.text = attractions.name
            binding.tvCity.text = attractions.city
            return binding.root
        }

        override fun getInfoContents(marker: Marker): View? {
            return null
        }
    }
}