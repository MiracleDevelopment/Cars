package com.sevenpeakssoftware.patipan.shared

fun Throwable.getResultFailed(): ResultFailed = ResultFailed(this)
