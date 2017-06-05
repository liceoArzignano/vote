package it.liceoarzignano.vote.ui.contracts

import it.liceoarzignano.vote.data.io.DataLoader
import it.liceoarzignano.vote.data.io.DataSaver
import it.liceoarzignano.vote.data.model.Room
import it.liceoarzignano.vote.ui.SelectorForm
import it.liceoarzignano.vote.ui.components.*
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.*

class VoteFormContract(val room: Room, val size: Int, val parent: Boolean) {
    private var i: Int = 1

    fun setup(frame: VoteFrame, title: JLabel, question: JTextArea, list: JList<String>, button: JButton,
              progressBar: JProgressBar, progressLabel: JLabel) {
        // Frame
        frame.setSize(1000, 1000)
        frame.setCustomFont(24)

        // Title
        title.text = "Classe " + room.name
        title.font = frame.getCustomFont(56)

        // Question
        question.text = DataLoader().getQuestion()
        question.font = frame.getCustomFont(20)

        // List
        val model = DefaultListModel<String>()
        for (teacher in room.teachers) {
            model.addElement(teacher.name)
        }
        list.cellRenderer = CheckBoxListCellRenderer()
        list.selectionModel = VoteSelectionModel()
        list.addListSelectionListener(ListSelectionDocument())
        list.model = model

        // Progress
        progressBar.maximum = size
        progressLabel.text = "1/$size"

        // Button
        button.addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(mouseEvent: MouseEvent?) {
                super.mouseClicked(mouseEvent)

                i++
                onVote(frame, list, progressBar, progressLabel, button)
            }
        })
    }

    private fun onVote(frame: JFrame, list: JList<String>, progressBar: JProgressBar,
                       progressLabel: JLabel, button: JButton) {
        if (i <= size +1) {
            list.selectedIndices.forEach { room.teachers[it].votes++ }
            JOptionPane.showMessageDialog(frame, "Il tuo voto e\' stato registrato. Grazie per aver votato!", "Votazione",
                    JOptionPane.INFORMATION_MESSAGE)

            list.clearSelection()
            progressBar.value = i
            progressLabel.text = "$i/$size"
        }

        if (i == size + 1) {
            button.isEnabled = false
            list.isEnabled = false
            DataSaver().export(room, size, parent)
            val input: Int = JOptionPane.showOptionDialog(frame, "Votazione completata con successo",
                    "Votazione", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null)
            if (input == JOptionPane.OK_OPTION) {
                SelectorForm(parent)
                frame.dispose()
            }
        }
    }
}