package me.deo.dekotpiler.file

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Component
import java.io.File
import javax.swing.JFileChooser
import javax.swing.JFrame
import javax.swing.UIManager
import javax.swing.filechooser.FileFilter

@Component
class FileSelectorImpl : FileSelector {

    init {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
    }

    override suspend fun selectFile(
        prompt: String,
        files: Boolean,
        directories: Boolean,
        filter: (File?) -> Boolean
    ): File? {
        val frame = JFrame().apply { isVisible = true }
        frame.defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE
        val selector = JFileChooser().apply {
            fileSelectionMode = when {
                files && directories -> JFileChooser.FILES_AND_DIRECTORIES
                files -> JFileChooser.FILES_ONLY
                directories -> JFileChooser.DIRECTORIES_ONLY
                else -> return null
            }
            dialogTitle = prompt
            fileFilter = object : FileFilter() {
                override fun accept(f: File?) = filter(f)
                override fun getDescription() = "File selector"
            }
        }


        return withContext(Dispatchers.IO + NonCancellable) {
            frame.requestFocus()
            selector.showOpenDialog(frame)
            launch {
                delay(1)
                frame.dispose()
            }
            selector.selectedFile
        }
    }

}