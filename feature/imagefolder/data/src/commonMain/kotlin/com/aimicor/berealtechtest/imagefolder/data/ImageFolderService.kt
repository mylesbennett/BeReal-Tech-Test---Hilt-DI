package com.aimicor.berealtechtest.imagefolder.data

import com.aimicor.httpnetwork.data.getKtorClient
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BasicAuthCredentials
import io.ktor.client.plugins.auth.providers.basic
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType

internal class ImageFolderService(
    private val baseClient: HttpClient = getKtorClient()
) {
    private var userName: String = ""
    private var passWord: String = ""

    private val client: HttpClient by lazy {
        baseClient.config {
            install(Auth) {
                basic {
                    credentials {
                        @Suppress("DEPRECATION")
                        BasicAuthCredentials(userName, passWord)
                    }
                }
            }
        }
    }

    suspend fun loginAndGetFolder(
        username: String,
        password: String
    ): ImageFolderLoginResponse {
        userName = username
        passWord = password
        return client.get("$BASE_URL$ME") {
            contentType(ContentType.Application.Json)
        }.body()
    }

    suspend fun getFolder(id: String): List<ImageFolderResponse> =
        client.get("$BASE_URL$ITEMS/$id") {
            contentType(ContentType.Application.Json)
        }.body()

    suspend fun getImage(id: String): ByteArray =
        client.get("$BASE_URL$ITEMS/$id/$DATA") {
            contentType(ContentType.Application.OctetStream)
        }.body()

    private companion object {
        const val BASE_URL = "http://163.172.147.216:8080/"
        const val ME = "me"
        const val ITEMS = "items"
        const val DATA = "data"
    }
}
