package op.mobile.app.dev.simjd1.travelling


import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import it.xabaras.android.espresso.recyclerviewchildactions.RecyclerViewChildActions
import it.xabaras.android.espresso.recyclerviewchildactions.RecyclerViewChildActions.Companion.childOfViewAtPositionWithMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import java.lang.Error

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class UITests {
    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_home_app_name_text() {
        onView(withId(R.id.tv_appName))
            .check(matches(withText("Traveller's Companion App")))
    }

    @Test
    fun test_recyclerview_output_name() {
        Thread.sleep(5000)
        onView(withId(R.id.rv_country))
            .check(matches(childOfViewAtPositionWithMatcher(
                        R.id.tv_name, 1, withText("Name: Australia")
            )))
    }

    @Test
    fun test_phrases() {
        Thread.sleep(5000)
        onView(withId(R.id.rv_country))
            .perform(click())
        onView(withId(R.id.btn_phrases))
            .perform(click())
        onView(withId(R.id.tv_phrases_intro))
            .check(matches(withText("These are a handful of common colloquial phrases from Sweden")))
    }

    @Test
    fun test_quiz_question() {
        Thread.sleep(5000)
        onView(withId(R.id.rv_country))
            .perform(click())
        onView(withId(R.id.btn_takeQuiz))
            .perform(click())
        onView(withId(R.id.tv_question))
            .check(matches(withText("Which is the local currency in Sweden?")))
    }

    @Test
    fun test_quiz_answer() {
        Thread.sleep(5000)
        onView(withId(R.id.rv_country))
            .perform(click())
        onView(withId(R.id.btn_takeQuiz))
            .perform(click())
        onView(withId(R.id.btn_ans1))
            .check(matches(withText("Swedish Crowns")))
    }

    @Test
    fun test_quiz_correct_answer() {
        Thread.sleep(5000)
        onView(withId(R.id.rv_country))
            .perform(click())
        onView(withId(R.id.btn_takeQuiz))
            .perform(click())
        onView(withId(R.id.btn_ans1))
            .perform(click())
        onView(withId(R.id.tv_score))
            .check(matches(withText("1")))
    }

    @Test
    fun test_quiz_incorrect_answer() {
        Thread.sleep(5000)
        onView(withId(R.id.rv_country))
            .perform(click())
        onView(withId(R.id.btn_takeQuiz))
            .perform(click())
        onView(withId(R.id.btn_ans2))
            .perform(click())
        onView(withId(R.id.tv_score))
            .check(matches(withText("0")))
    }

    @Test
    fun test_quiz_final_score_1() {
        Thread.sleep(5000)
        onView(withId(R.id.rv_country))
            .perform(click())
        onView(withId(R.id.btn_takeQuiz))
            .perform(click())
        onView(withId(R.id.btn_ans1))
            .perform(click())
        onView(withId(R.id.btn_ans2))
            .perform(click())
        onView(withId(R.id.btn_ans2))
            .perform(click())
        onView(withId(R.id.btn_ans2))
            .perform(click())
        onView(withId(R.id.btn_ans2))
            .perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.tv_final_score))
            .check(matches(withText("You Scored: 1")))
    }

    @Test
    fun test_quiz_final_score_2() {
        Thread.sleep(5000)
        onView(withId(R.id.rv_country))
            .perform(click())
        onView(withId(R.id.btn_takeQuiz))
            .perform(click())
        onView(withId(R.id.btn_ans1))
            .perform(click())
        onView(withId(R.id.btn_ans1))
            .perform(click())
        onView(withId(R.id.btn_ans1))
            .perform(click())
        onView(withId(R.id.btn_ans1))
            .perform(click())
        onView(withId(R.id.btn_ans1))
            .perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.tv_final_score))
            .check(matches(withText("You Scored: 5")))
    }

    @Test
    fun test_quiz_results_to_dashboard() {
        Thread.sleep(5000)
        onView(withId(R.id.rv_country))
            .perform(click())
        onView(withId(R.id.btn_takeQuiz))
            .perform(click())
        onView(withId(R.id.btn_ans1))
            .perform(click())
        onView(withId(R.id.btn_ans1))
            .perform(click())
        onView(withId(R.id.btn_ans1))
            .perform(click())
        onView(withId(R.id.btn_ans1))
            .perform(click())
        onView(withId(R.id.btn_ans1))
            .perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.btn_finish))
            .perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.tv_welcoming))
            .check(matches(withText("Welcome to the Traveller's Companion App!")))
    }

    @Test
    fun test_translation_english_to_english() {
        Thread.sleep(5000)
        onView(withId(R.id.rv_country))
            .perform(RecyclerViewChildActions.actionOnChild(click(), R.id.tv_name))
        Thread.sleep(2000)
        onView(withId(R.id.btn_translate))
            .perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.et_translation_text))
            .perform(typeText("Hello"))
        onView(withId(R.id.btn_translate_this))
            .perform(click())
        Thread.sleep(6000)
        onView(withId(R.id.tv_translation))
            .check(matches(withText("Hello")))
    }
    @Test
    fun test_translation_danish_to_english() {
        Thread.sleep(5000)
        onView(withId(R.id.rv_country))
            .perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.btn_translate))
            .perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.et_translation_text))
            .perform(typeText("Hello"))
        onView(withId(R.id.btn_translate_this))
            .perform(click())
        Thread.sleep(6000)
        onView(withId(R.id.tv_translation))
            .check(matches(withText("Hej")))
    }
    @Test
    fun test_home_goes_home() {
        Thread.sleep(5000)
        onView(withId(R.id.rv_country))
            .perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.btn_translate))
            .perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.nav_view))
            .perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.rv_country))
            .check(matches(childOfViewAtPositionWithMatcher(
                R.id.tv_name, 1, withText("Name: Australia")
            )))
    }
    @Test
    fun test_back_dialog_one_press() {
        Thread.sleep(5000)
        onView(withId(R.id.rv_country))
            .perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.btn_translate))
            .perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.et_translation_text))
            .perform(ViewActions.pressBack())
        Thread.sleep(2000)
        onView(withId(R.id.btn_translate))
            .perform(ViewActions.pressBack())
        Thread.sleep(2000)
        onView(withId(R.id.rv_country))
            .perform(ViewActions.pressBack())
        onView(withId(R.id.rv_country))
            .check(matches(childOfViewAtPositionWithMatcher(
                R.id.tv_name, 1, withText("Name: Australia")
            )))
    }
    @Test
    fun test_back_dialog_two_presses() {
        Thread.sleep(5000)
        onView(withId(R.id.rv_country))
            .perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.btn_translate))
            .perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.et_translation_text))
            .perform(ViewActions.pressBack())
        Thread.sleep(2000)
        onView(withId(R.id.btn_translate))
            .perform(ViewActions.pressBack())
        Thread.sleep(2000)
        try {
            Espresso.pressBackUnconditionally()
        }
        catch (e: Error){

        }
        assertEquals(activityRule.scenario.state,  Lifecycle.State.RESUMED)
    }

}