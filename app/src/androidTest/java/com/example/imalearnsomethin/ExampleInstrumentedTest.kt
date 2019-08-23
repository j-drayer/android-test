package com.example.imalearnsomethin

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
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
    fun testingInstrumentedStyle() {
        onView(withId(R.id.firstName)).perform(typeText(firstName))
        onView(withId(R.id.lastName)).perform(typeText(lastName), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.send)).perform(click())

        onView(withId(R.id.textView)).check(matches(withText("$firstName $lastName")))
    }

    //
//    @Test
//    fun useAppContext() {
//        // Context of the app under test.
//        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
//        assertEquals("com.example.imalearnsomethin", appContext.packageName)
//    }
}
