package com.chathum.assignment03miniproject.model

data class Task(
    val id: String = java.util.UUID.randomUUID().toString(),
    var title: String,
    var description: String,
    var isCompleted: Boolean = false
)