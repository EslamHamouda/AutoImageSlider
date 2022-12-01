package com.eslamhamouda.autoimageslider.models

import com.eslamhamouda.autoimageslider.constants.ScaleTypes


class SlideModel(
    var id: Long? = 0,
    var name: String? = "",
    var adsURL: String? = "",
    var imageURL: String? = "",
    var scaleType: ScaleTypes? = null
)