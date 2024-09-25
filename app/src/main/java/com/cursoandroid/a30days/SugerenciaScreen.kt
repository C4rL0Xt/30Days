package com.cursoandroid.a30days

import android.content.res.Configuration
import android.widget.Space
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring.DampingRatioLowBouncy
import androidx.compose.animation.core.Spring.StiffnessVeryLow
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cursoandroid.a30days.model.Sugerencia
import com.cursoandroid.a30days.model.SugerenciaRepository
import com.cursoandroid.a30days.ui.theme._30DaysTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SugerenciaList(
    sugerencias: List<Sugerencia>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
){
    val visibleState = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }
    AnimatedVisibility(
        visibleState = visibleState,
        enter = fadeIn(
            animationSpec = spring(dampingRatio = DampingRatioLowBouncy)
        ),
        exit = fadeOut(),
        modifier = Modifier
    ) {
        LazyColumn (contentPadding = contentPadding) {
            itemsIndexed(sugerencias) {
                index, sugerencia -> SugerenciaListItem(sugerencia = sugerencia,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .animateEnterExit(
                            enter = slideInVertically(
                                animationSpec = spring(
                                    stiffness = StiffnessVeryLow,
                                    dampingRatio = DampingRatioLowBouncy
                                ),
                                initialOffsetY = { it * (index + 1) }
                            )
                        ))
            }
        }
    }
}

@Composable
fun SugerenciaListItem(
    sugerencia: Sugerencia,
    modifier: Modifier = Modifier
){
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier,
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .sizeIn(minHeight = 72.dp)
        ){
            Column(modifier = Modifier.weight(1f)) {
                Text(text = stringResource(sugerencia.day),
                    style = MaterialTheme.typography.displaySmall,
                    fontSize = 18.sp)
                Text(text = stringResource(sugerencia.nameRes),
                    style = MaterialTheme.typography.displaySmall)
                Spacer(modifier = Modifier.height(10.dp))
                Image(painter = painterResource(sugerencia.imagenRes), contentDescription = null,
                    alignment = Alignment.Center, contentScale = ContentScale.FillWidth)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = stringResource(sugerencia.description),
                    style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}

@Preview("Light Theme")
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SugerenciaPreview(){
    val sugerencia = Sugerencia(
        R.string.dia1,
        R.string.sugerencia1,
        R.string.descripcion1,
        R.drawable.biking
    )
    
    _30DaysTheme {
        SugerenciaListItem(sugerencia = sugerencia)
    }

}

@Preview("Sugerencia List")
@Composable
fun _30DaysPreview() {
    _30DaysTheme(darkTheme = false) {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            SugerenciaList(sugerencias = SugerenciaRepository.sugerencias)
        }
    }
}