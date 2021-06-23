package op.mobile.app.dev.simjd1.travelling.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Country(
    val name: String?,
    val capital: String?,
    val flagImg: String?,
    val langCode: String?,
    val phrases: List<String>?,
    val quiz: @RawValue List<Quiz>?
) : Parcelable
