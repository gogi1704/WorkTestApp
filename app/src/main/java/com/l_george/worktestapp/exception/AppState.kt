package com.l_george.worktestapp.exception

data class AppState (
    val exception:AppException? = null,
    val isLoad:Boolean = false
)