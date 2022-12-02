package com.arabin.albertsonsacronymstest

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.arabin.albertsonsacronymstest.fragments.EnterMacroFragment
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AlbertSonsTestTestCase {

    @Test
    fun testNavOptionIsworkingFine() {
        assertEquals(
            androidx.navigation.ui.R.anim.nav_default_enter_anim,
            EnterMacroFragment.getNavigationOptions()?.enterAnim
        )
    }

}