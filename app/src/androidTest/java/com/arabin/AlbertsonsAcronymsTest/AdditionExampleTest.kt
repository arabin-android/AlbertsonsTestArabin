package com.arabin.AlbertsonsAcronymsTest

import com.arabin.AlbertsonsAcronymsTest.fragments.EnterMacroFragment
import org.junit.Test


class AlbersonsTestCase {

    @Test
    fun testNavOptionIsworkingFine(): Boolean{
        return androidx.navigation.ui.R.anim.nav_default_enter_anim == EnterMacroFragment.getNavigationOptions()?.enterAnim
    }

}