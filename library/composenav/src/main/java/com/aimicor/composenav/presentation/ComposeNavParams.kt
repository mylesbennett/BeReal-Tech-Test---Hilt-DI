package com.aimicor.composenav.presentation

import androidx.lifecycle.SavedStateHandle
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString

fun String.withParam(param: String): String = "$this/{$param}"

inline fun <reified T> String.putParam(
    value: T
): String = "$this/${Json.encodeToString(value)}"

inline fun <reified T> SavedStateHandle.getParam(
    name: String
): T? = get<String>(name)?.let<String, T> { Json.decodeFromString(it) }
