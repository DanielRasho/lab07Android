package com.raywenderlich.rwnews.ui

import android.util.Log
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.raywenderlich.rwnews.di.ActivityModule
import com.raywenderlich.rwnews.ui.list.NewsListFragment
import com.raywenderlich.rwnews.ui.navigation.NavigationHelper
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import dagger.hilt.android.testing.UninstallModules
import fakes.FakeNavigationHelper
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode
import org.robolectric.RobolectricTestRunner
import com.raywenderlich.rwnews.R

@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(RobolectricTestRunner::class)
@LooperMode(LooperMode.Mode.PAUSED)
@UninstallModules(ActivityModule::class)
class MainActivityTest {

    @get:Rule(order = 0)
    var hiltAndroidRule = HiltAndroidRule(this)

    @get:Rule(order = 1) // 2
    var activityScenarioRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java) // 1

    @Before
    fun setUp() {
        hiltAndroidRule.inject()
    }

    @BindValue
    @JvmField
    val navigator : NavigationHelper = FakeNavigationHelper()

    @Test
    fun whenMainActivityLaunchedNavigationHelperIsInvokedForFragment() {
        activityScenarioRule.scenario // 1
        val fakeHelper = navigator as FakeNavigationHelper // 2
        with(fakeHelper.replaceRequests[0]) {
            assertEquals(anchorId, R.id.anchor) // 3
            assertTrue(fragment is NewsListFragment)
            assertTrue(backStack == null)
        }
    }
}