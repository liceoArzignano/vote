package it.liceoarzignano.vote.data.io

import it.liceoarzignano.vote.data.model.Room
import it.liceoarzignano.vote.data.model.Teacher
import java.io.*

/*
 * Program structure:
 * lib
 * | vote.jar
 * | [...]
 * bin
 * | vote.bat
 * | vote
 * | DATA
 *   | Rooms.txt
 *   | Teachers.txt
 *   | Domanda.txt
 *   | teachers
 *     | RossiMario.txt
 *     | VerdiLuigi.txt
 *     | BluAndrea.txt
 *     | [...]
 *  | RESULTS
 *    | 1A1_results.csv
 *    | [...]
 */
class DataLoader {
    private val BASE_PATH = "DATA" + File.separator
    private val ROOMS = BASE_PATH + "Rooms.txt"
    private val TEACHERS = BASE_PATH + "Teachers.txt"
    private val TEACHERS_FOLDER = BASE_PATH + "teachers" + File.separator
    private val QUESTION = BASE_PATH + "Domanda.txt"

    fun loadRooms(): List<Room> {
        if (!File(ROOMS).canRead()) {
            throw IOException("Unable to access " + ROOMS)
        }

        val rooms = ArrayList<Room>()
        val reader = BufferedReader(FileReader(ROOMS))

        // Load rooms
        var line: String? = reader.readLine()
        while (line != null) {
            rooms.add(Room(line))
            line = reader.readLine()
        }

        return rooms
    }

    fun loadRoomsWithTeachers(): List<Room> {
        val rooms = loadRooms()
        val teachers = ArrayList<Teacher>()
        val nameReader = BufferedReader(FileReader(TEACHERS))

        // Create teacher list
        var name = nameReader.readLine()
        while (name != null) {
            teachers.add(Teacher(name))
            name = nameReader.readLine()
        }

        for ((i, teacher) in teachers.withIndex()) {
            val items = ArrayList<String>()
            val file = TEACHERS_FOLDER + teacher.name.replace(" ", "") + ".txt"
            val reader = BufferedReader(FileReader(file))
            var line = reader.readLine()

            // Read assigned rooms
            while (line != null) {
                items.add(line)
                line = reader.readLine()
            }


            for ((c, room) in rooms.withIndex()) {
                if (items.contains(room.name)) {
                    rooms[c].teachers.add(teachers[i])
                }
            }
        }

        return rooms
    }

    fun getQuestion(): String {
        val reader = BufferedReader(FileReader(QUESTION))
        var line = reader.readLine()
        val readQuestion = StringBuilder()

        readQuestion.append("<html>")
        while (line != null) {
            readQuestion.append(line)
            line = reader.readLine()
            if (line != null) {
                readQuestion.append("<br>")
            }
        }

        return readQuestion.toString()
    }
}
