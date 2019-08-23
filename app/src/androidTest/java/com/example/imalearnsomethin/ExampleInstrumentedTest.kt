package com.example.imalearnsomethin

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Matchers.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class ExampleInstrumentedTest {

    private lateinit var firstName : String
    private lateinit var lastName : String

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Before
    fun initInputText(){
        firstName = "Testy"
        lastName = "McTesterton"
    }

    @Test
    fun validInputPassesToDisplayMessageActivity() {
        onView(withId(R.id.firstName)).perform(typeText(firstName))
        onView(withId(R.id.lastName)).perform(typeText(lastName), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.send)).perform(click())

        onView(withId(R.id.textView)).check(matches(withText("$firstName $lastName")))
    }

    @Test
    fun missingFirstNameFailsValidation() {
        onView(withId(R.id.lastName)).perform(typeText(lastName), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.send)).perform(click())

        onView(withId(R.id.firstNameValidation)).check(matches(isDisplayed()))
        onView(withId(R.id.lastNameValidation)).check(matches(not(isDisplayed())))
        onView(withId(R.id.firstNameValidation)).check(matches(withText("*First Name Required")))
    }

    @Test
    fun missingLastNameFailsValidation() {
        onView(withId(R.id.firstName)).perform(typeText(firstName), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.send)).perform(click())

        onView(withId(R.id.lastNameValidation)).check(matches(isDisplayed()))
        onView(withId(R.id.firstNameValidation)).check(matches(not(isDisplayed())))
        onView(withId(R.id.lastNameValidation)).check(matches(withText("*Last Name Required")))
    }

    @Test
    fun displaysContentFromRequest() {
        onView(withId(R.id.request)).perform(click())

        onView(withId(R.id.response)).check(matches(withText(containsString("Example Domain"))))
    }
}
