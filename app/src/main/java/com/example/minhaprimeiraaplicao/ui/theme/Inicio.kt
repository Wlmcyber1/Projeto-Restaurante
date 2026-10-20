package com.example.minhaprimeiraaplicao.ui.theme

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.cos
import kotlin.math.sin

// Paleta italiana do totem
private val Creme = Color(0xFFFFFDF9)
private val CremeEscuro = Color(0xFFF6EEE2)
private val VerdeOliva = Color(0xFF0F4A2A)
private val VerdeManjericao = Color(0xFF3F7D3A)
private val Terracota = Color(0xFFC0583A)
private val TerracotaClara = Color(0xFFE3A07F)
private val Molho = Color(0xFFB23A2A)
private val Massa = Color(0xFFF1C65B)
private val MassaEscura = Color(0xFFD9A23A)
private val Almondega = Color(0xFF7A3418)

//aqui fica o visual
@Composable
fun Inicio(onIrParaPainel: () -> Unit) {
    val pulso = rememberInfiniteTransition(label = "pulso")
    val escalaBotao by pulso.animateFloat(
        initialValue = 1f,
        targetValue = 1.03f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "escalaBotao"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(listOf(Creme, Creme, CremeEscuro))
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp, vertical = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FaixaItaliana()

            Spacer(Modifier.height(40.dp))

            Text(
                text = "BENVENUTI",
                fontFamily = FontFamily.Serif,
                fontSize = 16.sp,
                letterSpacing = 6.sp,
                color = Terracota
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Pastaria",
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                fontSize = 52.sp,
                color = VerdeOliva,
                textAlign = TextAlign.Center

            )
            Spacer(Modifier.height(12.dp))
            Divisor()
            Spacer(Modifier.height(12.dp))
            Text(
                text = "Autêntica cucina italiana, feita na hora",
                fontFamily = FontFamily.Serif,
                fontStyle = FontStyle.Italic,
                fontSize = 18.sp,
                color = VerdeOliva.copy(alpha = 0.75f),
                textAlign = TextAlign.Center
            )

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                IlustracaoMassa(
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .aspectRatio(1f)
                )
            }

            Button(
                onClick = onIrParaPainel,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(92.dp)
                    .scale(escalaBotao),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = VerdeOliva,
                    contentColor = Creme
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
            ) {
                Button(onClick = onIrParaPainel) {
                    Text(
                        text = "Toque para Iniciar",
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 28.sp,
                        letterSpacing = 1.sp

                    )
                }



            }

            Spacer(Modifier.height(16.dp))
            Text(
                text = "Faça seu pedido em poucos toques",
                fontFamily = FontFamily.Serif,
                fontSize = 14.sp,
                color = Terracota
            )
        }
    }
}

@Composable
private fun FaixaItaliana() {
    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        listOf(VerdeOliva, CremeEscuro, Terracota).forEach { cor ->
            Box(
                modifier = Modifier
                    .width(36.dp)
                    .height(6.dp)
                    .background(cor, RoundedCornerShape(3.dp))
            )
        }
    }
}

@Composable
private fun Divisor() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            Modifier
                .width(60.dp)
                .height(1.dp)
                .background(TerracotaClara)
        )
        Canvas(
            Modifier
                .padding(horizontal = 10.dp)
                .size(10.dp)
        ) {
            rotate(45f) {
                drawRect(Terracota, topLeft = Offset(size.width * 0.15f, size.height * 0.15f), size = size * 0.7f)
            }
        }
        Box(
            Modifier
                .width(60.dp)
                .height(1.dp)
                .background(TerracotaClara)
        )
    }
}

// Prato de espaguete com molho, almôndegas e manjericão desenhado em Canvas
@Composable
private fun IlustracaoMassa(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val c = center
        val w = size.minDimension

        // Sombra suave
        drawCircle(
            brush = Brush.radialGradient(
                listOf(Color.Black.copy(alpha = 0.12f), Color.Transparent),
                center = c + Offset(0f, w * 0.03f),
                radius = w * 0.52f
            ),
            radius = w * 0.52f,
            center = c + Offset(0f, w * 0.03f)
        )

        // Prato em terracota
        drawCircle(Terracota, radius = w * 0.46f, center = c)
        drawCircle(TerracotaClara, radius = w * 0.43f, center = c, style = Stroke(width = w * 0.006f))
        drawCircle(Creme, radius = w * 0.39f, center = c)
        drawCircle(CremeEscuro, radius = w * 0.32f, center = c)

        // Ninho de espaguete
        val fios = 28
        for (i in 0 until fios) {
            val ang = i * (360f / fios) * (Math.PI / 180f).toFloat()
            val desloc = w * 0.06f
            val raio = w * (0.13f + (i % 5) * 0.022f)
            val centroFio = c + Offset(cos(ang) * desloc, sin(ang) * desloc)
            drawArc(
                color = if (i % 3 == 0) MassaEscura else Massa,
                startAngle = i * 47f,
                sweepAngle = 220f + (i % 4) * 25f,
                useCenter = false,
                topLeft = centroFio - Offset(raio, raio),
                size = Size(raio * 2, raio * 2),
                style = Stroke(width = w * 0.016f, cap = StrokeCap.Round)
            )
        }

        // Molho de tomate
        drawCircle(Molho, radius = w * 0.12f, center = c)
        drawCircle(Molho.copy(alpha = 0.85f), radius = w * 0.05f, center = c + Offset(w * 0.11f, w * 0.03f))
        drawCircle(Molho.copy(alpha = 0.85f), radius = w * 0.04f, center = c + Offset(-w * 0.09f, w * 0.08f))
        drawCircle(Color.White.copy(alpha = 0.25f), radius = w * 0.025f, center = c + Offset(-w * 0.04f, -w * 0.05f))

        // Almôndegas
        listOf(
            Offset(-w * 0.05f, -w * 0.02f),
            Offset(w * 0.05f, -w * 0.04f),
            Offset(w * 0.01f, w * 0.06f)
        ).forEach { pos -> almondega(c + pos, w * 0.045f) }

        // Folhas de manjericão
        folha(c + Offset(w * 0.07f, w * 0.10f), w * 0.09f, -30f)
        folha(c + Offset(w * 0.11f, w * 0.07f), w * 0.07f, 40f)

        // Parmesão ralado
        listOf(
            Offset(-0.10f, -0.10f), Offset(0.12f, -0.12f), Offset(-0.15f, 0.04f),
            Offset(0.16f, 0.02f), Offset(-0.03f, 0.15f), Offset(0.03f, -0.16f),
            Offset(-0.12f, 0.12f), Offset(0.09f, 0.14f)
        ).forEach { p ->
            drawCircle(Color.White, radius = w * 0.008f, center = c + Offset(p.x * w, p.y * w))
        }
    }
}

private fun DrawScope.almondega(centro: Offset, raio: Float) {
    drawCircle(Almondega, radius = raio, center = centro)
    drawCircle(
        Color.White.copy(alpha = 0.3f),
        radius = raio * 0.3f,
        center = centro + Offset(-raio * 0.35f, -raio * 0.35f)
    )
}

private fun DrawScope.folha(base: Offset, comprimento: Float, angulo: Float) {
    rotate(angulo, pivot = base) {
        val ponta = base + Offset(0f, -comprimento)
        val largura = comprimento * 0.45f
        val path = Path().apply {
            moveTo(base.x, base.y)
            quadraticTo(base.x + largura, base.y - comprimento * 0.5f, ponta.x, ponta.y)
            quadraticTo(base.x - largura, base.y - comprimento * 0.5f, base.x, base.y)
            close()
        }
        drawPath(path, VerdeManjericao)
        drawLine(
            VerdeOliva,
            start = base,
            end = ponta,
            strokeWidth = comprimento * 0.04f
        )
    }
}

@Preview(showBackground = true, widthDp = 600, heightDp = 1000)
@Composable
private fun InicioPreview() {
    Inicio(onIrParaPainel = {})
}
