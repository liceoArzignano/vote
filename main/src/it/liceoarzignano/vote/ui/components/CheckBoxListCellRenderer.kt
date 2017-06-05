package it.liceoarzignano.vote.ui.components

import javax.swing.*
import java.awt.*

class CheckBoxListCellRenderer : JCheckBox(), ListCellRenderer<Any> {

    override fun getListCellRendererComponent(list: JList<*>, value: Any?, index: Int,
                                              isSelected: Boolean, hasFocus: Boolean): Component {
        componentOrientation = list.componentOrientation
        font = list.font
        foreground = list.foreground
        background = list.background
        isEnabled = list.isEnabled
        text = value?.toString() ?: ""
        font = Font(UIManager.getDefaults().getFont("Label.font").name, Font.PLAIN, 24)
        this.isSelected = isSelected

        return this
    }
}
