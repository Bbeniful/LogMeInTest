package com.bbeniful.logmeintest.manager

import android.content.Context
import android.widget.Toast
import com.bbeniful.logmeintest.models.Error

object ErrorHandler {

    fun getError(context: Context, error: Error?) {
        Toast.makeText(
            context,
            "Error code: ${error?.statusCode} error message: " + error?.errorBody,
            Toast.LENGTH_SHORT
        ).show()


    }
}