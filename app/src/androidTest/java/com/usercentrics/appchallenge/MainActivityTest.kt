package com.usercentrics.appchallenge

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.usercentrics.appchallenge.view.MainActivity
import org.junit.Rule
import org.junit.Test


class MainActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun check_Consent_Button_Is_Displayed() {
        onView(withId(R.id.consent_button))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun check_Consent_Button_Clicked_Open_Banner() {
        onView(withId(R.id.consent_button)).perform(ViewActions.click())
        Thread.sleep(2000)
        onView(ViewMatchers.withText("Privacy Settings"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}