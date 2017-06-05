package it.liceoarzignano.vote

import it.liceoarzignano.vote.ui.SelectorForm

object Main {

    @JvmStatic fun main(args: Array<String>) {
        var parent = false
        args.forEach { if (it == "--parent") parent = true }
        SelectorForm(parent)
    }
}