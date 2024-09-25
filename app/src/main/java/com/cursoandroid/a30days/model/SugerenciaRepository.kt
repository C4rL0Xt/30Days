package com.cursoandroid.a30days.model

import com.cursoandroid.a30days.R

object SugerenciaRepository {
    val sugerencias = listOf(
        Sugerencia(
            day = R.string.dia1,
            nameRes = R.string.sugerencia1,
            description = R.string.descripcion1,
            imagenRes = R.drawable.biking
        ),
        Sugerencia(
            day = R.string.dia2,
            nameRes = R.string.sugerencia2,
            description = R.string.descripcion2,
            imagenRes = R.drawable.drinking
        ),
        Sugerencia(
            day = R.string.dia3,
            nameRes = R.string.sugerencia3,
            description = R.string.descripcion3,
            imagenRes = R.drawable.reading
        )
    )
}