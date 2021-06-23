package op.mobile.app.dev.simjd1.travelling.ui.results

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import op.mobile.app.dev.simjd1.travelling.models.Country

@Suppress("UNCHECKED_CAST")
class ResultsViewModelFactory(private val finalScore: Int, private val country: Country) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultsViewModel::class.java))
            return ResultsViewModel(finalScore, country) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}