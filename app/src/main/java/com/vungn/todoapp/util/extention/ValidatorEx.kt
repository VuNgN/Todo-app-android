package com.vungn.todoapp.util.extention

object ValidatorEx {
    fun String.isValidName() = this.isNotBlank()

    fun String.isValidRepeat() = this.isNotBlank()

    fun String.isValidDesc() = this.isNotBlank()

    fun String.isValidDueDate() = this.isNotBlank()
}