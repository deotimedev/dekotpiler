package com.deotime.dekotpiler.model

import com.deotime.dekotpiler.coding.CodeBuilder
import com.deotime.dekotpiler.util.backing.backing

interface KtCommentable {

    companion object {

        context (CodeBuilder)
        fun KtCommentable.writeComments() {
            comments.forEach {
                // todo comment logic
            }
        }

        val KtCommentable.comments by backing { mutableListOf<String>() }
    }
}

