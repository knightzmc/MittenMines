package me.bristermitten.mittenmines.persistence

interface Persistences<U, T, P : Persistence<U, T>> {
    val json: P
    val sqlite: P
    val mariadb: P
}
