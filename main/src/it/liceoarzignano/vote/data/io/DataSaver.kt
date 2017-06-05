package it.liceoarzignano.vote.data.io

import it.liceoarzignano.vote.data.model.Room
import java.io.File
import java.io.FileWriter

class DataSaver {
    private val STUDENTS_DIR = "RESULTS_STUDENTS" + File.separator
    private val PARENTS_DIR = "RESULTS_PARENTS" + File.separator
    private val NEWLINE = System.lineSeparator()
    private val SEPARATOR = ", "

    fun export(room: Room, size: Int, parent: Boolean) {
        val dir: String
        if (parent) {
            dir = PARENTS_DIR
        } else {
            dir = STUDENTS_DIR
        }
        for (teacher in room.teachers) {
            val file = File(dir + teacher.name.replace(" ", "") + ".csv")
            if (!File(dir).exists()) {
                File(dir).mkdirs()
            }

            val out = FileWriter(file, true)
            val result: Double = teacher.votes.toDouble() / size.toDouble()
            out.append(room.name + SEPARATOR + teacher.votes + SEPARATOR + size + SEPARATOR + result + NEWLINE)
            out.close()
        }
    }
}