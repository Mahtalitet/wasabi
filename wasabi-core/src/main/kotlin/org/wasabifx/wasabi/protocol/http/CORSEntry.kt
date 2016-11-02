package org.wasabifx.wasabi.protocol.http

import io.netty.handler.codec.http.HttpMethod

class CORSEntry(val path: String = "*",
                       val origins: String = "*",
                       val methods: Set<HttpMethod>? = CORSEntry.ALL_AVAILABLE_METHODS,
                       val headers: String = "Origin, X-Requested-With, Content-Type, Accept",
                       val credentials: String = "",
                       val preflightMaxAge: String = "") {

        companion object {
            val NO_METHODS = emptySet<HttpMethod>()
            val ALL_AVAILABLE_METHODS = null
        }

}