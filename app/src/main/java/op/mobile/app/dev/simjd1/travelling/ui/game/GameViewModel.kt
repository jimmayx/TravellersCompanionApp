package op.mobile.app.dev.simjd1.travelling.ui.game


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import op.mobile.app.dev.simjd1.travelling.models.Country

class GameViewModel(_country: Country) : ViewModel() {

    var country: Country = _country

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int> get() = _score

    private val _isEnd = MutableLiveData<Boolean>()
    val isEnd: LiveData<Boolean> get() = _isEnd

    private val _question = MutableLiveData<String>()
    val question: LiveData<String> get() = _question

    private val _answer1 = MutableLiveData<String>()
    val answer1: LiveData<String> get() = _answer1

    private val _answer2 = MutableLiveData<String>()
    val answer2: LiveData<String> get() = _answer2

    private val _answer3 = MutableLiveData<String>()
    val answer3: LiveData<String> get() = _answer3

    private val _answer4 = MutableLiveData<String>()
    val answer4: LiveData<String> get() = _answer4

    private var questionNumber: Int = 0


    init {
        _score.value = 0
        _isEnd.value = false
        nextQuestion()
    }

    private fun nextQuestion() {
        if (questionNumber > 4)
            onEnd()
        else {
            _question.value = country.quiz?.get(questionNumber)?.question.toString()
            _answer1.value = country.quiz?.get(questionNumber)?.answer?.get(0).toString()
            _answer2.value = country.quiz?.get(questionNumber)?.answer?.get(1).toString()
            _answer3.value = country.quiz?.get(questionNumber)?.answer?.get(2).toString()
            _answer4.value = country.quiz?.get(questionNumber)?.answer?.get(3).toString()
            questionNumber += 1
        }
    }

    fun checkAnswer(text: String) {
        if (text == answer1.value) {
            _score.value = _score.value?.plus(1)
            nextQuestion()
        } else {
            nextQuestion()
        }
    }

    private fun onEnd() {
        _isEnd.value = true
    }

    fun onEndComplete() {
        _isEnd.value = false
    }
}
