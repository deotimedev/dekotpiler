package com.deotime.dekotpiler.ui.impl

import com.deotime.dekotpiler.ui.FileSelector
import com.sun.javafx.util.Logging
import javafx.application.Platform
import javafx.stage.FileChooser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Component
import java.io.File
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Component
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

        return withContext(Dispatchers.IO) {
            suspendCoroutine { cont ->
                Logging.getJavaFXLogger().disableLogging()
                Platform.startup { }
                Platform.runLater {
                    cont.resume(selector.showOpenDialog(null))
                    Platform.exit()
                }
            }
        }
    }

}