package com.example.minhaprimeiraaplicao.ui.theme.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.minhaprimeiraaplicao.model.Categoria
import com.example.minhaprimeiraaplicao.model.ItemCarrinho
import com.example.minhaprimeiraaplicao.model.Produto

// Mesma paleta italiana das telas Inicio e Painel
private val Creme = Color(0xFFFFFDF9)
private val CremeEscuro = Color(0xFFF6EEE2)
private val VerdeOliva = Color(0xFF0F4A2A)
private val Terracota = Color(0xFFC0583A)
private val TerracotaClara = Color(0xFFE3A07F)

@Composable
fun Carrinho(
    itens: List<ItemCarrinho>,
    onVoltar: () -> Unit,
    onEnviarParaCozinha: () -> Unit
) {
    // Soma os subtotais de todos os itens
    val total = itens.sumOf { it.subtotal }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Creme, Creme, CremeEscuro)))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FaixaItalianaCarrinho()
            Spacer(Modifier.height(20.dp))
            Text(
                text = "IL TUO ORDINE",
                fontFamily = FontFamily.Serif,
                fontSize = 14.sp,
                letterSpacing = 6.sp,
                color = Terracota
            )
            Spacer(Modifier.height(6.dp))
            Text(
                text = "Seu pedido",
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
                color = VerdeOliva
            )
            Spacer(Modifier.height(10.dp))
            Box(
                Modifier
                    .width(120.dp)
                    .height(1.dp)
                    .background(TerracotaClara)
            )
            Spacer(Modifier.height(20.dp))

            if (itens.isEmpty()) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Seu pedido está vazio",
                        fontFamily = FontFamily.Serif,
                        fontStyle = FontStyle.Italic,
                        fontSize = 18.sp,
                        color = VerdeOliva.copy(alpha = 0.75f)
                    )
                }
            } else {
                // weight(1f): a lista ocupa o espaço que sobrar e rola se for grande
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    LazyColumn(
                        contentPadding = PaddingValues(20.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        items(itens, key = { it.produto.id }) { item ->
                            LinhaItem(item)
                        }
                    }
                }
            }

            Spacer(Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Total",
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = VerdeOliva
                )
                Text(
                    text = formatarPreco(total),
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp,
                    color = Terracota
                )
            }

            Spacer(Modifier.height(20.dp))
            Button(
                onClick = onEnviarParaCozinha,
                enabled = itens.isNotEmpty(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = VerdeOliva,
                    contentColor = Creme
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
            ) {
                Text(
                    text = "Enviar para cozinha",
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp
                )
            }
            Spacer(Modifier.height(8.dp))
            TextButton(onClick = onVoltar) {
                Text(
                    text = "Voltar ao cardápio",
                    fontFamily = FontFamily.Serif,
                    fontSize = 16.sp,
                    color = Terracota
                )
            }
        }
    }
}

@Composable
private fun LinhaItem(item: ItemCarrinho) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${item.quantidade}x",
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Terracota,
            modifier = Modifier.width(44.dp)
        )
        Text(
            text = item.produto.nome,
            fontFamily = FontFamily.Serif,
            fontSize = 18.sp,
            color = VerdeOliva,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = formatarPreco(item.subtotal),
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            color = VerdeOliva
        )
    }
}

private fun formatarPreco(valor: Double) = "R$ %.2f".format(valor).replace('.', ',')

@Composable
private fun FaixaItalianaCarrinho() {
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

@Preview(showBackground = true, widthDp = 600, heightDp = 1000)
@Composable
private fun CarrinhoPreview() {
    val massas = Categoria(1, "Massas")
    Carrinho(
        itens = listOf(
            ItemCarrinho(Produto(1, "Spaghetti al Pomodoro", 42.9, null, null, massas), 2),
            ItemCarrinho(Produto(2, "Lasagna Bolognese", 54.0, null, null, massas), 1)
        ),
        onVoltar = {},
        onEnviarParaCozinha = {}
    )
}
