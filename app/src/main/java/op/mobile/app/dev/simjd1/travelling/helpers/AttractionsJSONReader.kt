package op.mobile.app.dev.simjd1.travelling.helpers

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import op.mobile.app.dev.simjd1.travelling.R
import op.mobile.app.dev.simjd1.travelling.models.Attractions
import java.io.InputStream
import java.io.InputStreamReader

class AttractionsJSONReader(private val context: Context) {
    private val inputStream: InputStream
        get() = context.resources.openRawResource(R.raw.data)

    fun read(): List<Attractions> {
        val itemType = object : TypeToken<List<AttractionsJSONResponse>>() {}.type
        val reader = InputStreamReader(inputStream)
        return Gson().fromJson<List<AttractionsJSONResponse>>(reader, itemType).map {
            it.toAttractions()
        }
    }
}
