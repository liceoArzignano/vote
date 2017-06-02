package it.liceoarzignano.vote.data.model

class Vote {
    var id: Long = 0
    var teacher: Teacher

    constructor(votedId: Teacher) {
        this.teacher = votedId
    }

    constructor(id: Long, votedId: Teacher) {
        this.id = id
        this.teacher = votedId
    }
}
