package com.deotime.dekotpiler.ui.impl

import com.deotime.dekotpiler.ui.FileSelector
import com.sun.javafx.util.Logging
import javafx.application.Platform
import javafx.stage.FileChooser
import kotlinx.coroutines.CompletableDeferred
import org.koin.core.annotation.Single
import java.io.File

@Single
internal class FileSelectorImpl : FileSelector {

    override suspend fun selectFile(
        prompt: String,
        description: String,
        vararg allowed: String,
    ): File? {
        val selector = FileChooser().apply {
            title = prompt
            extensionFilters += FileChooser.ExtensionFilter(description, *allowed)
        }

        val defer = CompletableDeferred<File?>()
        Logging.getJavaFXLogger().disableLogging()
        Platform.startup { }
        Platform.runLater {
            defer.complete(selector.showOpenDialog(null))
            Platform.exit()
        }
        return defer.await()
    }

}