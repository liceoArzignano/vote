package it.liceoarzignano.vote.ui.components

import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.Font
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.UIManager

class VoteFrame(panel: JPanel?): JFrame("Vote") {

    private val FONT_TARGETS = arrayOf("Button.font", "CheckBoxMenuItem.font", "ComboBox.font",
            "Label.font", "List.font", "TextField.font")

    init {
        if (panel == null) {
            throw IllegalArgumentException("Panel cannot be null")
        }

        layout = FlowLayout()
        contentPane = panel
        size = Dimension(600, 300)
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    }

    fun setCustomFont(size: Int) {
        val oldFont = UIManager.getDefaults().getFont("Label.font").name
        val newFont = Font(oldFont, Font.PLAIN, size)
        for (item in FONT_TARGETS) {
            UIManager.getLookAndFeelDefaults().put(item, newFont)
        }
    }

    fun getCustomFont(size: Int): Font {
        return Font(UIManager.getDefaults().getFont("Label.font").name, Font.PLAIN, size)
    }
}
