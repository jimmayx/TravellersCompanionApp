package op.mobile.app.dev.simjd1.travelling.ui.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import op.mobile.app.dev.simjd1.travelling.models.Country

@Suppress("UNCHECKED_CAST")
class GameViewModelFactory(
    private val country: Country,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java))
            return GameViewModel(country) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}