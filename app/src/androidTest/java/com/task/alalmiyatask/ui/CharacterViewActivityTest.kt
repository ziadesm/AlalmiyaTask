package com.task.alalmiyatask.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.task.alalmiyatask.R
import org.junit.Rule
import org.junit.Test

class CharacterViewActivityTest {

    @get: Rule
    val activityScenario = ActivityScenarioRule(CharacterViewActivity::class.java)

    @Test
    fun make_sure_that_activity_is_running() {
        onView(withId(R.id.rv_character_items)).check(matches(isDisplayed()))
    }

    @Test
    fun check_visibility_progress_text_loading() {

        onView(withId(R.id.loading_screen_progress_bar)).check(matches(isDisplayed()))
        onView(withId(R.id.loading_screen_text)).check(matches(isDisplayed()))

        onView(withId(R.id.loading_screen_progress_bar)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun check_visibility_loading_text_string() {

        onView(withId(R.id.loading_screen_text)).check(matches(withText(R.string.loading_screen_text)))

//        onView(withId(R.id.loading_screen_text)).check(matches(withText(R.string.error_handling_network)))
    }
}