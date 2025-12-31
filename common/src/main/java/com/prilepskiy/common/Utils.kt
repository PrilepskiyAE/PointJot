package com.prilepskiy.common

import android.content.Context
import android.net.Uri
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.io.File


fun <T> emitFlow(action: suspend () -> T) = flow { emit(action.invoke()) }


fun <T> Flow<T>.subscribe(
    scope: CoroutineScope,
    success: suspend (value: T) -> Unit,
    error: suspend (Throwable) -> Unit = { },
    onStart: suspend () -> Unit = { },
    complete: () -> Unit = { }
) = scope.launch {
    subscribe(
        success = { success.invoke(it) },
        error = { error.invoke(it) },
        onStart = { onStart.invoke() }
    )
}.apply { invokeOnCompletion { complete.invoke() } }

suspend fun <T> Flow<T>.subscribe(
    success: suspend (value: T) -> Unit,
    error: suspend (Throwable) -> Unit = { },
    onStart: (suspend () -> Unit)? = null,
    onEnd: (suspend () -> Unit)? = null,
) {
    onStart?.invoke()
    try {
        collect {
            success.invoke(it)
            onEnd?.invoke()
        }
    } catch (throwable: Throwable) {
        error.invoke(throwable)
        onEnd?.invoke()
        throwable.printStackTrace()
    }
}


fun copyImageToPrivateStorage(
    context: Context,
    sourceUri: Uri
): String? {
    return try {

        val imagesDir = File(context.filesDir, "images")
        if (!imagesDir.exists()) {
            imagesDir.mkdirs()
        }

        val fileName = "img_${System.currentTimeMillis()}.jpg"
        val destFile = File(imagesDir, fileName)

        context.contentResolver.openInputStream(sourceUri)?.use { input ->
            destFile.outputStream().use { output ->
                input.copyTo(output)
            }
        }

        destFile.absolutePath
    } catch (e: Exception) {
        Log.e("CopyImage", "Failed to copy image", e)
        null
    }
}


fun deleteImageFromPrivateStorage(
    localPath: String
): Boolean {
    return try {
        val file = File(localPath)
        if (!file.exists() || !file.isFile) return false
        val isDeleted = file.delete()
        isDeleted
    } catch (e: Exception) {
        false
    }
}

fun saveImageAndUpdateDb(context: Context, uri: Uri, saveUri: (path: String?) -> Unit,) {
    val localPath = copyImageToPrivateStorage(context, uri)
    if (localPath != null) {
        saveUri(localPath)
    }
}