package com.example.gridrisky.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val name: Int,
    val availableCourses: Int,
    @DrawableRes val imageRes: Int
)

/*
package com.example.gridrisky.model Ini menunjukkan bahwa file ini berada di dalam package
com.example.gridrisky.model yang digunakan untuk mengelompokkan kode-kode terkait.

@DrawableRes dan @StringRes adalah annotations yang menunjukkan bahwa
parameter tertentu harus merujuk pada resource drawable (gambar) atau string (teks) di Android
*/