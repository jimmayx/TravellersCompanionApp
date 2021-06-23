package op.mobile.app.dev.simjd1.travelling.ui.results

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import op.mobile.app.dev.simjd1.travelling.models.Country

class ResultsViewModel(finalScore: Int, country: Country) : ViewModel() {
    val country: Country = country

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int> get() = _score

    init {
        _score.value = finalScore
    }

}