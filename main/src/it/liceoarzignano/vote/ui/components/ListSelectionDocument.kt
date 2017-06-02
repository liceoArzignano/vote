package it.liceoarzignano.vote.ui.components

import javax.swing.*
import javax.swing.event.ListSelectionEvent
import javax.swing.event.ListSelectionListener
import javax.swing.text.BadLocationException
import javax.swing.text.PlainDocument
import java.text.MessageFormat

class ListSelectionDocument @JvmOverloads constructor(private val elementFormat: MessageFormat = MessageFormat("{0}"),
                          private val endLine: String = "\n") : PlainDocument(), ListSelectionListener {

    override fun valueChanged(e: ListSelectionEvent) {
        val list = e.source as JList<*>
        val model = list.model

        val listSelectionModel = list.selectionModel

        val minSelectionIndex = listSelectionModel.minSelectionIndex
        val maxSelectionIndex = listSelectionModel.maxSelectionIndex

        val textBuilder = StringBuilder()

        (minSelectionIndex..maxSelectionIndex).asSequence()
                .filter { listSelectionModel.isSelectedIndex(it) }
                .map { model.getElementAt(it) }
                .forEach { formatElement(it, textBuilder) }

        setText(textBuilder.toString())
    }

    private fun formatElement(element: Any, textBuilder: StringBuilder) {
        val formatted = elementFormat.format(arrayOf(element))
        textBuilder.append(formatted)
        textBuilder.append(endLine)
    }

    private fun setText(text: String) {
        try {
            remove(0, length)
            insertString(0, text, null)
        } catch (ignored: BadLocationException) {
        }

    }

    companion object {
        private val serialVersionUID = 2710696851334704262L
    }
}
