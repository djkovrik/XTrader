package com.sedsoftware.data.extension

import io.realm.Realm
import io.realm.RealmModel
import io.realm.RealmQuery

typealias Query<T> = RealmQuery<T>.() -> Unit

fun <T : RealmModel> T.saveToDb() {
  Realm.getDefaultInstance().use { realm ->
    realm.executeTransaction { transaction -> transaction.copyToRealmOrUpdate(this@saveToDb) }
  }
}

fun <T : RealmModel> List<T>.saveAllToDb() {
  Realm.getDefaultInstance().use { realm ->
    realm.executeTransaction { transaction -> transaction.copyToRealmOrUpdate(this@saveAllToDb) }
  }
}

fun <T : RealmModel> T.queryOneFromDb(query: Query<T>): T? {
  Realm.getDefaultInstance().use { realm ->
    return realm.where(this.javaClass).withQuery(query).findFirst()?.let { run { realm.copyFromRealm(it) } }
  }
}

inline fun <reified T : RealmModel> queryOneFromDb(query: Query<T>): T? {
  Realm.getDefaultInstance().use { realm ->
    return realm.where(T::class.java).runQuery(query).findFirst()?.let { run { realm.copyFromRealm(it) } }
  }
}

fun <T : RealmModel> T.queryListFromDb(query: Query<T>): List<T> {
  Realm.getDefaultInstance().use { realm ->
    return realm.where(this.javaClass).withQuery(query).findAll().run { realm.copyFromRealm(this) }
  }
}

inline fun <reified T : RealmModel> queryListFromDb(query: Query<T>): List<T> {
  Realm.getDefaultInstance().use { realm ->
    return realm.where(T::class.java).runQuery(query).findAll().run { realm.copyFromRealm(this) }
  }
}

fun <T : RealmModel> T.queryAllFromDb(): List<T> {
  Realm.getDefaultInstance().use { realm ->
    return realm.where(this.javaClass).findAll().run { realm.copyFromRealm(this) }
  }
}

inline fun <reified T : RealmModel> queryAllFromDb(): List<T> {
  Realm.getDefaultInstance().use { realm ->
    return realm.where(T::class.java).findAll().run { realm.copyFromRealm(this) }
  }
}

fun <T : RealmModel> T.deleteFromDb(query: Query<T>) {
  Realm.getDefaultInstance().use { realm ->
    realm.executeTransaction { transaction ->
      transaction.where(this.javaClass).withQuery(query).findAll().deleteAllFromRealm()
    }
  }
}

inline fun <reified T : RealmModel> deleteFromDb(crossinline query: Query<T>) {
  Realm.getDefaultInstance().use { realm ->
    realm.executeTransaction { transaction ->
      transaction.where(T::class.java).runQuery(query).findAll().deleteAllFromRealm()
    }
  }
}

fun <T : RealmModel> T.deleteAllFromDb() {
  Realm.getDefaultInstance().use { realm ->
    realm.executeTransaction { transaction ->
      transaction.where(this.javaClass).findAll().deleteAllFromRealm()
    }
  }
}

inline fun <reified T : RealmModel> deleteAllFromDb() {
  Realm.getDefaultInstance().use { realm ->
    realm.executeTransaction { transaction -> transaction.where(T::class.java).findAll().deleteAllFromRealm() }
  }
}

inline fun <T> T.withQuery(block: (T) -> Unit): T {
  block(this); return this
}

inline fun <reified T : RealmModel> RealmQuery<T>.runQuery(block: Query<T>): RealmQuery<T> {
  block(this); return this
}
