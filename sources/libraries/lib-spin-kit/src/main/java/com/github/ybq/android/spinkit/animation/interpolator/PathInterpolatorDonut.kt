/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.ybq.android.spinkit.animation.interpolator

import android.graphics.Path
import android.graphics.PathMeasure
import android.view.animation.Interpolator

/**
 * A path interpolator implementation compatible with API 4+.
 */
@Suppress("MagicNumber")
internal class PathInterpolatorDonut(path: Path) : Interpolator {

    companion object {

        /**
         * Governs the accuracy of the approximation of the [Path].
         */
        private const val PRECISION = 0.002f

        private fun createQuad(controlX: Float, controlY: Float): Path {
            val path = Path()
            path.moveTo(0.0f, 0.0f)
            path.quadTo(controlX, controlY, 1.0f, 1.0f)
            return path
        }

        private fun createCubic(controlX1: Float, controlY1: Float, controlX2: Float, controlY2: Float): Path {
            val path = Path()
            path.moveTo(0.0f, 0.0f)
            path.cubicTo(controlX1, controlY1, controlX2, controlY2, 1.0f, 1.0f)
            return path
        }
    }

    constructor(controlX: Float, controlY: Float) : this(createQuad(controlX, controlY))

    constructor(controlX1: Float, controlY1: Float, controlX2: Float, controlY2: Float)
            : this(createCubic(controlX1, controlY1, controlX2, controlY2))

    private val mX: FloatArray
    private val mY: FloatArray

    init {
        val pathMeasure = PathMeasure(path, false /* forceClosed */)

        val pathLength = pathMeasure.length
        val numPoints = (pathLength / PRECISION).toInt() + 1

        mX = FloatArray(numPoints)
        mY = FloatArray(numPoints)

        val position = FloatArray(2)
        for (i in 0 until numPoints) {
            val distance = i * pathLength / (numPoints - 1)
            pathMeasure.getPosTan(distance, position, null /* tangent */)

            mX[i] = position[0]
            mY[i] = position[1]
        }
    }

    override fun getInterpolation(t: Float): Float {
        if (t <= 0.0f) {
            return 0.0f
        } else if (t >= 1.0f) {
            return 1.0f
        }

        // Do a binary search for the correct x to interpolate between.
        var startIndex = 0
        var endIndex = mX.size - 1
        while (endIndex - startIndex > 1) {
            val midIndex = (startIndex + endIndex) / 2
            if (t < mX[midIndex]) {
                endIndex = midIndex
            } else {
                startIndex = midIndex
            }
        }

        val xRange = mX[endIndex] - mX[startIndex]
        if (xRange == 0f) {
            return mY[startIndex]
        }

        val tInRange = t - mX[startIndex]
        val fraction = tInRange / xRange

        val startY = mY[startIndex]
        val endY = mY[endIndex]

        return startY + fraction * (endY - startY)
    }
}
