package com.eslamhamouda.autoimageslider.interfaces

import com.eslamhamouda.autoimageslider.constants.ActionTypes


interface TouchListener {
    /**
     * Click listener touched item function.
     *
     * @param  touched  slider boolean
     */
    fun onTouched(touched: ActionTypes)
}