package it.liceoarzignano.vote.data.io

import it.liceoarzignano.vote.data.model.Room
import java.io.File
import java.io.FileWriter
import java.io.IOException

class DataSaver {
    private val EXPORT_DIR = "RESULTS" + File.separator
    private val NEWLINE = System.lineSeparator()
    private val SEPARATOR = ", "
    private val HEADER = "Nome" + SEPARATOR + "Punteggio" + NEWLINE

    fun exportResult(room: Room): String {
        val file = File(EXPORT_DIR + room.name + "_results.csv")
        if (!File(EXPORT_DIR).exists()) {
            File(EXPORT_DIR).mkdirs()
        }

        val writer = FileWriter(file)
        writer.write(HEADER)
        for (teacher in room.teachers) {
            writer.append(teacher.name + SEPARATOR + teacher.votes + NEWLINE)
        }

        writer.close()

        return file.name
    }
}