package op.mobile.app.dev.simjd1.travelling.ui.phrases

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import op.mobile.app.dev.simjd1.travelling.models.Country

@Suppress("UNCHECKED_CAST")
class PhrasesViewModelFactory(
    private val country: Country,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PhrasesViewModel::class.java))
            return PhrasesViewModel(country) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}