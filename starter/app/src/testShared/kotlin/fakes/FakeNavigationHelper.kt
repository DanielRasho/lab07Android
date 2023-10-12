package fakes

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.raywenderlich.rwnews.ui.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import androidx.fragment.app.Fragment
import com.raywenderlich.rwnews.ui.navigation.NavigationHelper

class FakeNavigationHelper : NavigationHelper {

    data class NavigationInput(
        val anchorId: Int,
        val fragment: Fragment,
        val backStack: String?
    )

    val replaceRequests = mutableListOf<NavigationInput>()

    override fun replace(anchorId: Int, fragment: Fragment, backStack: String?) {
        replaceRequests.add(NavigationInput(anchorId, fragment, backStack))
    }
}
