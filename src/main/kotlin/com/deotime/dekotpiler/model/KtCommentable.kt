package com.deotime.dekotpiler.model

import com.deotime.dekotpiler.coding.Codable
import com.deotime.dekotpiler.coding.CodeBuilder
import com.deotime.dekotpiler.util.backing

interface KtCommentable : Codable {


    companion object {

        fun CodeBuilder.writeComments(
            commentable: KtCommentable, /*temp until ctx receivers are fixed*/
            fullLine: Boolean = false,
        ) {
            commentable.comments.forEach {
                if (fullLine) +"// $it"
                else +" /*$it*/"
                if (fullLine) newline()
            }
        }

        fun KtCommentable.comment(comment: String) {
            comments += comment
        }

        val KtCommentable.comments by backing { mutableListOf<String>() }
    }
}

