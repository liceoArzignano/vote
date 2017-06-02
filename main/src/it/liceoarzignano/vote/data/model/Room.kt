package it.liceoarzignano.vote.data.model

class Room(var name: String) {
    var votes = ArrayList<Vote>()
    var teachers = ArrayList<Teacher>()
}
