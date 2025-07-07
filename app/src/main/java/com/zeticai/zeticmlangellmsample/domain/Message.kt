package com.zeticai.zeticmlangellmsample.domain

data class Message(
    val content: StringBuilder = StringBuilder(),
    val isFromUser: Boolean,
    val timestamp: Long = System.currentTimeMillis()
)