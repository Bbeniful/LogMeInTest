package com.bbeniful.logmeintest.models

import org.json.JSONObject

data class Error(
    var statusCode: Int = 0,
    var errorBody: String = ""
) {

    constructor(errorObject: JSONObject?) : this() {
        if (errorObject == null) return
        if (errorObject.has("statusCode"))
            this.statusCode = errorObject.getInt("statusCode")
        if (errorObject.has("errorMessage"))
            this.errorBody = errorObject.getString("errorMessage")
    }
}