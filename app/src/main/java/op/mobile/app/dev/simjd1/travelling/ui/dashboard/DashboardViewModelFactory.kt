package op.mobile.app.dev.simjd1.travelling.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import op.mobile.app.dev.simjd1.travelling.models.Country

@Suppress("UNCHECKED_CAST")
class DashboardViewModelFactory(
    private val country: Country,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashboardViewModel::class.java))
            return DashboardViewModel(country) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}