package com.bbeniful.logmeintest.models

import org.json.JSONArray

data class WorldID(
    var woeid: String? = ""
) {
    constructor(jsonArray: JSONArray?) : this() {
        if (jsonArray == null) return
        val firstObject = jsonArray.getJSONObject(0) ?: return
        this.woeid = firstObject.getString("woeid")
    }
}