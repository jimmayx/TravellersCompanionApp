package op.mobile.app.dev.simjd1.travelling.ui.phrases


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import op.mobile.app.dev.simjd1.travelling.models.Country

class PhrasesViewModel(_country: Country) : ViewModel() {

    var country: Country = _country

    private val _phrase1 = MutableLiveData<String>()
    val phrase1: LiveData<String> get() = _phrase1

    private val _phrase2 = MutableLiveData<String>()
    val phrase2: LiveData<String> get() = _phrase2

    private val _phrase3 = MutableLiveData<String>()
    val phrase3: LiveData<String> get() = _phrase3

    private val _phrase4 = MutableLiveData<String>()
    val phrase4: LiveData<String> get() = _phrase4

    private val _phrase5 = MutableLiveData<String>()
    val phrase5: LiveData<String> get() = _phrase5

    private val _countryName = MutableLiveData<String>()
    val countryName: LiveData<String> get() = _countryName


    init {
        loadPhrases()
        _countryName.value = country.name.toString()
    }

    private fun loadPhrases() {
        _phrase1.value = country.phrases?.get(0).toString()
        _phrase2.value = country.phrases?.get(1).toString()
        _phrase3.value = country.phrases?.get(2).toString()
        _phrase4.value = country.phrases?.get(3).toString()
        _phrase5.value = country.phrases?.get(4).toString()
    }

}
