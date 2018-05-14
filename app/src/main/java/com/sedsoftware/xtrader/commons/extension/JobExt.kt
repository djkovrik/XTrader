package com.sedsoftware.xtrader.commons.extension

import kotlinx.coroutines.experimental.Job

fun Job.addToCancelableJobsPool(jobs: MutableList<Job>) = jobs.add(this)
