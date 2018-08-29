package com.sedsoftware.core.factory

import com.sedsoftware.core.marker.ViewModelOwner

interface ViewModelFactory {

    fun <T : ViewModelOwner> create(modelClass: Class<T>): T
}
