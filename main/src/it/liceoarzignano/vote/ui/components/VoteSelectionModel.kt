package it.liceoarzignano.vote.ui.components

import javax.swing.*

class VoteSelectionModel(val list: JList<String>, val max: Int) : DefaultListSelectionModel() {

    override fun addSelectionInterval(p0: Int, p1: Int) {
        val length = list.selectedIndices.size
        var pEnd = p1

        if (length >= max) {
            return
        }
        if (p1 - p0 >= max - length) {
            pEnd = p0 + max - 1 - length
        }
        if (pEnd < p1) {
            return
        }

        super.addSelectionInterval(p0, pEnd)
    }

    override fun setSelectionInterval(p0: Int, p1: Int) {
        var pEnd = p1

        if (p1 - p0 >= max) {
            pEnd = p0 + max - 1
        }

        if (super.isSelectedIndex(p0)) {
            removeSelectionInterval(p0, pEnd)
        } else {
            addSelectionInterval(p0, pEnd)
        }
    }
}
