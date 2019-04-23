package com.sedsoftware.core.presentation.custom.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat

class MoveUpwardBehavior : CoordinatorLayout.Behavior<View> {

    constructor() : super()
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    override fun layoutDependsOn(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        // TODO Set view here
        return super.layoutDependsOn(parent, child, dependency)
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        val translationY = Math.min(0f, dependency.translationY - dependency.height)
        ViewCompat.animate(child).cancel()
        child.translationY = translationY
        child.setPadding(0, -Math.round(translationY), 0, 0)

        return true
    }

    override fun onDependentViewRemoved(parent: CoordinatorLayout, child: View, dependency: View) {
        child.setPadding(0, 0, 0, 0)
        ViewCompat.animate(child).translationY(0f).start()
    }
}
