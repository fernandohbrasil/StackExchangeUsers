package com.fernandohbrasil.stackexchange.ui

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.fernandohbrasil.stackexchange.R
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.containsString
import org.junit.Rule
import org.junit.Test

const val NAME_TEST = "Fernando Henrique Brasil"

class ListUsersFragmentTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun listUsersFragmentTest() {
        onView(
            allOf(
                withId(R.id.etName),
                isDisplayed()
            )
        ).perform(replaceText(NAME_TEST), closeSoftKeyboard())

        onView(
            allOf(
                withId(R.id.btSearch),
                isDisplayed()
            )
        ).perform(click())

        Thread.sleep(5000)

        val recyclerView = onView(
            allOf(
                withId(R.id.rvUsers)
            )
        )

        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        Thread.sleep(5000)

        onView(withId(R.id.tvUserName))
            .check(matches(withText(containsString(NAME_TEST))))
    }
}