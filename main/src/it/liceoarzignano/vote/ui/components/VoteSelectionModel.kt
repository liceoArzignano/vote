package it.liceoarzignano.vote.ui.components

import javax.swing.*

class VoteSelectionModel : DefaultListSelectionModel() {

    override fun setSelectionInterval(p0: Int, p1: Int) {
        if (super.isSelectedIndex(p0)) {
            removeSelectionInterval(p0, p1)
        } else {
            addSelectionInterval(p0, p1)
        }
    }
}
