package com.sedsoftware.core.tools.impl.manager

import android.content.res.Resources
import android.content.res.Resources.NotFoundException
import com.sedsoftware.core.domain.tools.Logger
import com.sedsoftware.core.domain.tools.ResourceManager
import javax.inject.Inject

class AppResourceManager @Inject constructor(
    private val resources: Resources,
    private val logger: Logger
) : ResourceManager {

    private companion object {
        const val DEFAULT_STRING = ""
    }

    override fun getString(stringId: Int): String =
        try {
            resources.getString(stringId)
        } catch (exception: NotFoundException) {
            logger.e("Failed to found resource (${exception.message})")
            DEFAULT_STRING
        }
}
