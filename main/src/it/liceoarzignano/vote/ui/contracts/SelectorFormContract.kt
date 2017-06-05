package it.liceoarzignano.vote.ui.contracts

import it.liceoarzignano.vote.data.io.DataLoader
import it.liceoarzignano.vote.ui.components.Toast
import it.liceoarzignano.vote.ui.VoteForm
import it.liceoarzignano.vote.ui.components.VoteFrame
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.util.regex.Pattern
import javax.swing.*

class SelectorFormContract {

    fun setup(frame: VoteFrame, comboBox: JComboBox<String>, numberPicker: JTextField,
              label: JLabel, button: JButton, parent: Boolean) {
        frame.setCustomFont(18)

        val rooms = DataLoader().loadRooms()
        rooms.forEach { comboBox.addItem(it.name) }

        if (parent) {
            numberPicker.text = "1"
            numberPicker.isVisible = false
            label.text = "Votazione genitori"
        }
        button.addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(mouseEvent: MouseEvent?) {
                super.mouseClicked(mouseEvent)
                if (Pattern.compile("[0-9]+").matcher(numberPicker.text).matches()) {
                    frame.isVisible = false
                    frame.dispose()
                    VoteForm(comboBox.selectedIndex, numberPicker.text.toInt(), parent)
                } else {
                    Toast.makeToast(frame, "Inserisci un numero di votanti valido").display()
                }
            }
        })
    }

}