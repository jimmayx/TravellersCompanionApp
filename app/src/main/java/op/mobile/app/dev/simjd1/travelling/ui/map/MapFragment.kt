package op.mobile.app.dev.simjd1.travelling.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.maps.android.clustering.ClusterManager
import op.mobile.app.dev.simjd1.travelling.R
import op.mobile.app.dev.simjd1.travelling.helpers.AttractionsJSONReader
import op.mobile.app.dev.simjd1.travelling.helpers.AttractionsMarkerCluster
import op.mobile.app.dev.simjd1.travelling.models.Attractions

class MapFragment : Fragment(), OnMapReadyCallback {
    private lateinit var map: GoogleMap
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_maps, container, false)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

        return view
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val attractions = AttractionsJSONReader(activity?.applicationContext!!).read()
        val clusterManager: ClusterManager<Attractions> =
            ClusterManager(activity?.applicationContext!!, googleMap)
        val markerCluster =
            AttractionsMarkerCluster(activity?.applicationContext!!, googleMap, clusterManager)
        clusterManager.renderer = markerCluster
        clusterManager.addItems(attractions)
        clusterManager.cluster()
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(attractions[0].position, 10f))
    }
}