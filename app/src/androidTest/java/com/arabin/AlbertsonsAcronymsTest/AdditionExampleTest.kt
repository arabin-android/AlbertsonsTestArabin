package com.arabin.AlbertsonsAcronymsTest

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.arabin.AlbertsonsAcronymsTest.fragments.EnterMacroFragment
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AlbersonsTestCase {

    @Test
    fun testNavOptionIsworkingFine(){
        assertEquals(androidx.navigation.ui.R.anim.nav_default_enter_anim, EnterMacroFragment.getNavigationOptions()?.enterAnim)
    }

}