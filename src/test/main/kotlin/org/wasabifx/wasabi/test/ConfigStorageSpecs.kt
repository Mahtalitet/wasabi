package org.wasabifx.wasabi.test

import org.wasabifx.wasabi.app.AppConfiguration
import org.wasabifx.wasabi.configuration.ConfigurationStorage
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertTrue
import org.junit.Test as spec

class ConfigStorageSpecs {

    @spec fun loading_a_valid_configuration_file_should_correctly_load_all_values() {

        val configurationStorage = ConfigurationStorage()


        val configuration = configurationStorage.loadFromFile("testData${File.separatorChar}production.json")

        assertEquals(configuration.port, 5000)
        assertEquals(configuration.welcomeMessage, "Welcome to Wasabi!")
        assertEquals(configuration.enableLogging, true)

    }

    @spec fun loading_a_non_existent_configuration_file_should_throw_invalid_configuration_exception() {

        val configurationStorage = ConfigurationStorage()

        val exception = assertFails({ configurationStorage.loadFromFile("non_existing_file") })


        assertEquals("Configuration file does not exist", exception.message)
    }

    @spec fun loading_an_invalid_configuration_with_invalid_property_should_throw_invalid_configuration_exception_with_name_of_invalid_property() {

        val configurationStorage = ConfigurationStorage()

        val exception = assertFails({ configurationStorage.loadFromFile("testData${File.separatorChar}production_bad_property.json")})

        assertTrue(exception.message!!.contains("Invalid property in configuration file: Unrecognized field \"invalid_property\" (class org.wasabifx.wasabi.app.AppConfiguration), not marked as ignorable"))
    }

    @spec fun loading_an_invalid_configuration_with_invalid_json_should_throw_invalid_configuration_exception() {

        val configurationStorage = ConfigurationStorage()

        val exception = assertFails({ configurationStorage.loadFromFile("testData${File.separatorChar}production_bad_json.json")})

        assertEquals("Invalid JSON in configuration file: [Source: testData${File.separatorChar}production_bad_json.json; line: 2, column: 6]", exception.message)
    }

    @spec fun saving_a_configuration_to_file_should_save_it_correctly() {

        val configurationStorage = ConfigurationStorage()


        val file = File.createTempFile("configuration", ".json")
        configurationStorage.saveToFile(AppConfiguration(), file.absolutePath)

        file.readText()

        assertTrue(file.exists())

    }
}