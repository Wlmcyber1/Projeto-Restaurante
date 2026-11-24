package com.example.minhaprimeiraaplicao.ui.theme.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.text.style.TextAlign
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.minhaprimeiraaplicao.model.Produto
import com.example.minhaprimeiraaplicao.network.RetrofitInstancia
import com.example.minhaprimeiraaplicao.viewModel.PainelProdutoViewModel

// Mesma paleta italiana da tela Inicio
private val Creme = Color(0xFFFFFDF9)
private val CremeEscuro = Color(0xFFF6EEE2)
private val VerdeOliva = Color(0xFF0F4A2A)
private val Terracota = Color(0xFFC0583A)
private val TerracotaClara = Color(0xFFE3A07F)

@Composable
fun Painel(
    viewModel: PainelProdutoViewModel = viewModel(),
    onVerPedido: () -> Unit
) {
    // Busca os produtos assim que a tela abre
    LaunchedEffect(Unit) {
        viewModel.buscarProduto()
    }

    // Lê a lista guardada no ViewModel
    val produtos = viewModel.listaProdutos
    val quantidadeNoCarrinho = viewModel.carrinho.sumOf { it.quantidade }

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
            FaixaItalianaPainel()
            Spacer(Modifier.height(20.dp))
            Text(
                text = "IL NOSTRO MENU",
                fontFamily = FontFamily.Serif,
                fontSize = 14.sp,
                letterSpacing = 6.sp,
                color = Terracota
            )
            Spacer(Modifier.height(6.dp))
            Text(
                text = "Cardápio",
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

            if (produtos.isEmpty()) {
                Column(
                    modifier = Modifier.weight(1f).fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(color = Terracota)
                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = "Carregando produtos...",
                        fontFamily = FontFamily.Serif,
                        fontStyle = FontStyle.Italic,
                        fontSize = 18.sp,
                        color = VerdeOliva.copy(alpha = 0.75f)
                    )
                }
            } else {
                // items() percorre a lista de produtos e desenha um card para cada um
                LazyColumn(
                    modifier = Modifier.weight(1f).fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(bottom = 16.dp)
                ) {
                    items(produtos, key = { it.id }) { produto ->
                        CardProduto(
                            produto = produto,
                            quantidade = viewModel.quantidadeDe(produto),
                            onAdicionar = { viewModel.adicionarAoCarrinho(produto) },
                            onRemover = { viewModel.removerDoCarrinho(produto) }
                        )
                    }
                }
            }

            if (quantidadeNoCarrinho > 0) {
                Spacer(Modifier.height(16.dp))
                Button(
                    onClick = onVerPedido,
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
                        text = "Ver pedido ($quantidadeNoCarrinho)",
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun CardProduto(
    produto: Produto,
    quantidade: Int,
    onAdicionar: () -> Unit,
    onRemover: () -> Unit
) {
    val url = RetrofitInstancia.base_URL
   val imagens = produto.imagemUrl
    // Sem onClick: o card não é mais clicável, só os botões + e -
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = url + imagens ,
                contentDescription = produto.nome,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(96.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(CremeEscuro)
            )
            Spacer(Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                produto.categoria?.let {
                    Text(
                        text = it.nome.uppercase(),
                        fontFamily = FontFamily.Serif,
                        fontSize = 11.sp,
                        letterSpacing = 2.sp,
                        color = Terracota
                    )
                    Spacer(Modifier.height(2.dp))
                }
                Text(
                    text = produto.nome,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = VerdeOliva,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                produto.descricao?.let {
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = it,
                        fontFamily = FontFamily.Serif,
                        fontStyle = FontStyle.Italic,
                        fontSize = 14.sp,
                        color = VerdeOliva.copy(alpha = 0.7f),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Spacer(Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "R$ %.2f".format(produto.preco).replace('.', ','),
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        color = Terracota,
                        modifier = Modifier.weight(1f)
                    )
                    BotaoQuantidade(
                        simbolo = "−",
                        habilitado = quantidade > 0,
                        onClick = onRemover
                    )
                    Text(
                        text = "$quantidade",
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = VerdeOliva,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.width(36.dp)
                    )
                    BotaoQuantidade(
                        simbolo = "+",
                        habilitado = true,
                        onClick = onAdicionar
                    )
                }
            }
        }
    }
}

// Botão redondo verde; fica apagado quando não pode ser usado (ex: "−" com 0 itens)
@Composable
private fun BotaoQuantidade(simbolo: String, habilitado: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(if (habilitado) VerdeOliva else VerdeOliva.copy(alpha = 0.2f))
            .clickable(enabled = habilitado, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = simbolo,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Creme
        )
    }
}

@Composable
private fun FaixaItalianaPainel() {
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
