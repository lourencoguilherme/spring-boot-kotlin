package com.guimtlo.digitalinovationone.exceptions

open class StandardError(val timeStamp: Long, val status: Int, val code: String, val message: String, val path: String)