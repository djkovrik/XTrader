package com.sedsoftware.data.extension

import io.realm.RealmModel
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

fun <T : RealmModel> T.saveToDbAsync() =
  launch {
    saveToDb()
  }

fun <T : RealmModel> List<T>.saveAllToDbAsync() =
  launch {
    saveAllToDb()
  }

inline fun <reified T : RealmModel> queryOneFromDbAsync(crossinline query: Query<T>): Deferred<T?> =
  async(CommonPool) {
    queryOneFromDb(query)
  }

inline fun <reified T : RealmModel> queryListFromDbAsync(crossinline query: Query<T>): Deferred<List<T>> =
  async(CommonPool) {
    queryListFromDb(query)
  }

inline fun <reified T : RealmModel> queryAllFromDbAsync(): Deferred<List<T>> =
  async(CommonPool) {
    queryAllFromDb<T>()
  }

inline fun <reified T : RealmModel> deleteFromDbAsync(crossinline query: Query<T>) =
  launch {
    deleteFromDb(query)
  }

inline fun <reified T : RealmModel> deleteAllFromDbAsync() =
  launch {
    deleteAllFromDb<T>()
  }
