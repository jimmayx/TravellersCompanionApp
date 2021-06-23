package op.mobile.app.dev.simjd1.travelling.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import op.mobile.app.dev.simjd1.travelling.helpers.ServiceInstance
import op.mobile.app.dev.simjd1.travelling.helpers.ServiceStatus
import op.mobile.app.dev.simjd1.travelling.models.Country

class HomeViewModel : ViewModel() {

    private val url = "https://gist.github.com/jimmayx/a97a3613922e9551114ee344634ce924/"

    private val _status = MutableLiveData<ServiceStatus>()
    val status: LiveData<ServiceStatus> get() = _status

    private val _response = MutableLiveData<List<Country>>()
    val response: LiveData<List<Country>> get() = _response

    init {
        viewModelScope.launch {
            _status.value = ServiceStatus.LOADING
            try {
                _response.value = ServiceInstance(url).retrofitCountryService.getResponse()
                _status.value = ServiceStatus.COMPLETE
            } catch (e: Exception) {
                _response.value = ArrayList()
                _status.value = ServiceStatus.ERROR
            }
        }
    }
}