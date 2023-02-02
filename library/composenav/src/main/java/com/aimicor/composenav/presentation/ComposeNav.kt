package com.aimicor.composenav.presentation

import android.app.Activity
import android.content.Context
import androidx.navigation.NavController

fun NavController.goBack(context: Context) {
    if(!popBackStack()) (context as? Activity)?.finish()
}
