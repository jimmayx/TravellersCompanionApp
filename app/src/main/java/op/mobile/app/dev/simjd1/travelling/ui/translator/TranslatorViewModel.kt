package op.mobile.app.dev.simjd1.travelling.ui.translator

import android.text.Editable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import op.mobile.app.dev.simjd1.travelling.BuildConfig
import op.mobile.app.dev.simjd1.travelling.R
import op.mobile.app.dev.simjd1.travelling.helpers.ServiceInstance
import op.mobile.app.dev.simjd1.travelling.helpers.ServiceStatus
import op.mobile.app.dev.simjd1.travelling.models.Country
import op.mobile.app.dev.simjd1.travelling.models.Yandex

class TranslatorViewModel(_country: Country) : ViewModel() {

    private val baseURL = "https://translate.yandex.net/api/v1.5/tr.json/"
    private val apiKey = BuildConfig.YANDEX_API_KEY

    var country: Country = _country

    private val _translateText = MutableLiveData<String>()
    val translateText: LiveData<String> get() = _translateText

    private val _status = MutableLiveData<ServiceStatus>()
    val status: LiveData<ServiceStatus> get() = _status

    private val _response = MutableLiveData<Yandex>()
    val response: LiveData<Yandex> get() = _response

    fun getResponse(text: String?) {
        viewModelScope.launch {
            _status.value = ServiceStatus.LOADING
            try {
                _response.value = ServiceInstance(baseURL).retrofitYandexService.getResponse(
                    apiKey, text!!, "en-${country.langCode}"
                )
                _status.value = ServiceStatus.COMPLETE
            } catch (e: Exception) {
                _status.value = ServiceStatus.ERROR
            }
        }
    }

    fun setTranslateText(editable: Editable) {
        _translateText.value = editable.toString()
    }
}

