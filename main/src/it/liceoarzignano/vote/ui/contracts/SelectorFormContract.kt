package it.liceoarzignano.vote.ui.contracts

import it.liceoarzignano.vote.data.io.DataLoader
import it.liceoarzignano.vote.ui.components.Toast
import it.liceoarzignano.vote.ui.VoteForm
import it.liceoarzignano.vote.ui.components.VoteFrame
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.util.regex.Pattern
import javax.swing.JButton
import javax.swing.JComboBox
import javax.swing.JTextField

class SelectorFormContract {

    fun setup(frame: VoteFrame, comboBox: JComboBox<String>, numberPicker: JTextField, button: JButton) {
        frame.setCustomFont(18)

        val rooms = DataLoader().loadRooms()
        rooms.forEach {
            comboBox.addItem(it.name)
        }

        button.addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(mouseEvent: MouseEvent?) {
                super.mouseClicked(mouseEvent)
                if (Pattern.compile("[0-9]+").matcher(numberPicker.text).matches()) {
                    frame.isVisible = false
                    VoteForm(comboBox.selectedIndex, numberPicker.text.toInt())
                } else {
                    Toast.makeToast(frame, "Inserici un numero valido di votanti").display()
                }
            }
        })
    }

}