package it.liceoarzignano.vote.ui.contracts

import it.liceoarzignano.vote.data.io.DataSaver
import it.liceoarzignano.vote.data.model.Room
import it.liceoarzignano.vote.ui.components.*
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.*

class VoteFormContract(val room: Room, val size: Int) {
    private val MAX = 2
    private var i: Int = 1

    fun setup(frame: VoteFrame, title: JLabel, list: JList<String>, button: JButton,
              progressBar: JProgressBar, progressLabel: JLabel) {
        frame.setSize(800, 600)
        frame.setCustomFont(18)

        title.text = "Classe " + room.name
        title.font = frame.getCustomFont(48)

        val model = DefaultListModel<String>()
        for (teacher in room.teachers) {
            model.addElement(teacher.name)
        }
        list.cellRenderer = CheckBoxListCellRenderer()
        list.selectionModel = VoteSelectionModel(list, MAX)
        list.addListSelectionListener(ListSelectionDocument())
        list.model = model

        progressBar.maximum = size
        progressLabel.text = "1/$size"

        button.addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(mouseEvent: MouseEvent?) {
                super.mouseClicked(mouseEvent)
                if (list.selectedIndices.size < MAX) {
                    Toast.makeToast(frame, "Seleziona $MAX docenti").display()
                    return
                }

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
            val out = DataSaver().exportResult(room)
            JOptionPane.showMessageDialog(frame, "Votazione completata con successo\n\n" +
                    "Numero votanti: $size\nRisultati: $out", "Votazione", JOptionPane.INFORMATION_MESSAGE)
        }
    }
}