package me.bristermitten.mittenmines.util

import kotlin.Throws
import java.io.IOException
import java.net.URISyntaxException
import java.net.URL
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.spi.FileSystemProvider
import java.nio.file.FileSystemNotFoundException


//Credit https://stackoverflow.com/questions/15713119/java-nio-file-path-for-a-classpath-resource
    @Throws(IOException::class, URISyntaxException::class)
    fun URL.toPath(): Path {
        val uri = toURI()
        val scheme = uri.scheme
        if (scheme == "file") {
            return Paths.get(uri)
        }
        require(scheme == "jar") { "Cannot convert to Path: $uri" }
        for (provider in FileSystemProvider.installedProviders()) {
            if (provider.scheme.equals("jar", ignoreCase = true)) {
                try {
                    provider.getFileSystem(uri)
                } catch (e: FileSystemNotFoundException) {
                    // in this case we need to initialize it first:
                    provider.newFileSystem(uri, emptyMap<String, Any>())
                }
            }
        }
        return Paths.get(uri)
    }
