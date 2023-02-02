package com.aimicor.berealtechtest.imagefolder.presentation.login

import com.aimicor.udfmvi.presentation.Event

internal sealed class LoginEvent : Event {
    data class Login(val userName: String, val password: String): LoginEvent()
}
