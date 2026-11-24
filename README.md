# 🍝 Pastaria — Autoatendimento via app


## 📌 Descrição do Projeto e Público-Alvo
O **Pastaria Totem** é um protótipo funcional de aplicativo Android desenvolvido em **Kotlin** e **Jetpack Compose** para a disciplina de Desenvolvimento para Dispositivos Móveis.

O projeto simula um terminal de autoatendimento (estilo totem de fast-food) para uma cantina italiana focada em massas artesanais. O objetivo principal do aplicativo é substituir o atendimento convencional no balcão, permitindo que o cliente selecione seus pratos, escolha as quantidades e envie o pedido diretamente para a cozinha de forma autônoma e linear, sem necessidade de criação de contas ou login.

## 🛠️ Tecnologias e Bibliotecas Utilizadas
* **Linguagem:** Kotlin
* **Interface UI:** Jetpack Compose (Material Design 3)
* **Navegação:** Navigation Compose, com as rotas centralizadas em `Routes.kt`
* **Estado da tela:** ViewModel compartilhado entre Cardápio e Pedido (guarda o carrinho)
* **Consumo de API:** Retrofit + OkHttp (Gson para converter o JSON)
* **Renderização de Imagens:** Coil
* **Back-End:** Java + Spring Boot + PostgreSQL (Docker) — repositório separado: [projeto-restaurante-back](https://github.com/Wlmcyber1/projeto-restaurante-back)

## 🚀 Funcionalidades e Fluxo do App
**Início → Cardápio → Seu pedido → Cozinha**

* [x] **Tela de Boas-Vindas:** identidade visual italiana e botão "Toque para Iniciar".
* [x] **Cardápio:** lista de pratos carregada da API, com foto, categoria, descrição e preço.
* [x] **Escolha de quantidade:** botões **+** e **−** em cada prato; o botão "Ver pedido (N)" mostra quantos itens foram escolhidos.
* [x] **Seu pedido (confirmação):** resumo com quantidade, subtotal de cada prato e total.
* [x] **Enviar para cozinha:** finaliza o pedido, limpa o carrinho e volta o totem para a tela inicial, pronto para o próximo cliente.
* [x] **Voltar ao cardápio:** retorna mantendo os itens já escolhidos.

## 🔧 Instruções para Execução
O app depende da API para carregar o cardápio. Sem ela, a tela fica em "Carregando produtos...".

**1. Subir o back-end** (repositório [projeto-restaurante-back](https://github.com/Wlmcyber1/projeto-restaurante-back)):
```bash
docker compose up -d      # sobe o PostgreSQL (porta 5433)
mvnw spring-boot:run      # sobe a API na porta 8082 e cria o cardápio inicial
```

**2. Rodar o app:**
1. Clone este repositório e abra no **Android Studio**.
2. Aguarde a sincronização do Gradle.
3. Abra o emulador e ligue-o à API:
   ```bash
   adb reverse tcp:8082 tcp:8082
   ```
   > Repita esse comando **sempre que abrir o emulador**: ele é desfeito quando o emulador fecha.
4. Execute o aplicativo.

## ⚠️ Limitações Conhecidas
* O envio para a cozinha é apenas visual: o pedido ainda não é gravado no back-end.
* O app acessa a API por `localhost` + `adb reverse`. Em Android 16+ com `targetSdk` recente, acessar o PC pela rede local (`10.0.2.2` ou IP da rede) exige a permissão `ACCESS_LOCAL_NETWORK`, ainda não implementada.

## 🤖 Registro de Uso de IA
Ferramentas de IA generativa foram utilizadas como suporte e mentoria no design visual (layouts e paleta de cores no Jetpack Compose) e também na implementação de partes do código: rota e tela de confirmação do pedido, carrinho no ViewModel e botões de quantidade. Todo o código gerado foi revisado, testado e compreendido pela desenvolvedora.
