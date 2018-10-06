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

import android.annotation.TargetApi
import android.graphics.Path
import android.os.Build
import android.view.animation.Interpolator
import android.view.animation.PathInterpolator

/**
 * API 21+ implementation for path interpolator compatibility.
 */
internal object PathInterpolatorCompatApi21 {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    fun create(path: Path): Interpolator =
        PathInterpolator(path)

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    fun create(controlX: Float, controlY: Float): Interpolator =
        PathInterpolator(controlX, controlY)

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    fun create(controlX1: Float, controlY1: Float, controlX2: Float, controlY2: Float): Interpolator =
        PathInterpolator(controlX1, controlY1, controlX2, controlY2)

}// prevent instantiation
