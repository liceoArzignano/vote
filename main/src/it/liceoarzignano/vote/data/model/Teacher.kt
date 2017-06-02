package it.liceoarzignano.vote.data.model

class Teacher {
    var name: String
    var votes: Int = 0

    constructor(name: String) {
        this.name = name
    }

    constructor(name: String, votes: Int) {
        this.name = name
        this.votes = votes
    }
}
