package com.l_george.worktestapp.exception

sealed class AppException : RuntimeException() {
}

class NetworkException: AppException()
class ApiException: AppException()
class AuthException(): AppException()
class UnknownException: AppException()