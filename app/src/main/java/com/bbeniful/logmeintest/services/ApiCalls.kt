package com.bbeniful.logmeintest.services

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.io.UnsupportedEncodingException

class ApiCalls {

    companion object {
        fun getRequest(
            context: Context,
            url: String,
            completionHandler: (response: String?, error: JSONObject?) -> Unit
        ) {
            val queue = Volley.newRequestQueue(context)
            val stringRequest = StringRequest(
                Request.Method.GET, url,
                { response ->
                    if (response != null) {
                        val responseJSON = JSONObject()
                        responseJSON.put("data", response)
                        completionHandler(response, null)
                    }
                },
                { error ->
                    var body = ""
                    val statusCode = error.networkResponse.statusCode
                    if (error.networkResponse.data != null) {
                        try {
                            body = String(error.networkResponse.data, Charsets.UTF_8)
                        } catch (e: UnsupportedEncodingException) {
                            e.printStackTrace()
                        }
                    }
                    val errorJson = JSONObject()
                    errorJson.put("statusCode", statusCode)
                    errorJson.put("errorMessage", body)

                    completionHandler(null, errorJson)
                })

            queue.add(stringRequest)

        }
    }
}