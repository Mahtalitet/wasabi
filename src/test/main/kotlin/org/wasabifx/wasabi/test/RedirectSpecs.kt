package org.wasabifx.wasabi.test

import org.junit.Test as spec
import kotlin.test.assertEquals
import org.wasabifx.wasabi.protocol.http.StatusCodes

class RedirectSpecs: TestServerContext() {

    @spec fun redirect_should_set_status_code_to_found_and_location_header_to_new_location() {

        TestServer.appServer.get("/redirect", {
            response.redirect("http://www.google.com")
        })

        val response = get("http://localhost:${TestServer.definedPort}/redirect", hashMapOf())

        assertEquals(StatusCodes.Found.code, response.statusCode)
        assertEquals("http://www.google.com", response.headers.filter { it.name == "Location" }.first().value)


    }
}