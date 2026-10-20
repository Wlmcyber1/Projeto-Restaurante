# 🍝 Pastaria Totem — Autoatendimento

## 👥 Integrantes
* **Seu Nome Completo** - Matrícula (Seu Curso/Turma)

## 📌 Descrição do Projeto e Público-Alvo
O **Pastaria Totem** é um protótipo funcional de aplicativo Android desenvolvido em **Kotlin** e **Jetpack Compose** para a disciplina de Desenvolvimento para Dispositivos Móveis. 

O projeto simula um terminal de autoatendimento (estilo totem de fast-food) para uma cantina italiana focada em massas artesanais e pizzas. O objetivo principal do aplicativo é substituir o atendimento convencional no balcão, permitindo que o cliente selecione seus pratos, customize as quantidades e envie o pedido diretamente para a cozinha de forma autônoma e linear, dispensando a necessidade de criação de contas ou login.

## 🛠️ Tecnologias e Bibliotecas Utilizadas
* **Linguagem:** Kotlin
* **Interface UI:** Jetpack Compose (Material Design 3)
* **Arquitetura de Navegação:** Navigation Component com Jetpack Compose (Constantes de Rota)
* **Consumo de API:** Retrofit / OkHttp *(Em implementação)*
* **Renderização de Imagens:** Coil *(Em implementação)*
* **Persistência de Dados (Back-End):** Java + Spring Boot + PostgreSQL *(Em implementação)*

## 🚀 Funcionalidades Atuais e Fluxo do App
* [x] **Tela de Boas-Vindas:** Interface imersiva com identidade visual italiana, contendo botão interativo de ação para iniciar a experiência do usuário.
* [x] **Arquitetura de Navegação Base:** Centralização das rotas do aplicativo em um arquivo de constantes de texto na pasta de rotas.
* [ ] **Tela de Cardápio Geral:** Abas para filtragem por categorias (Massas, Pizzas e Bebidas) e grade de produtos em duas colunas *(Em desenvolvimento)*.
* [ ] **Tela de Detalhes do Prato:** Customização de quantidade e botão para despacho do pedido *(Em desenvolvimento)*.
* [ ] **Simulação de Estados da Cozinha:** Fluxo visual utilizando Coroutines do Kotlin para demonstrar o andamento do prato na cozinha *(Em desenvolvimento)*.

## 📁 Organização Estrutural do Projeto (Front-End)
```text
com.cantina.totem/
│
├── data/
│   └── model/          # Modelos de dados para o JSON (Data Classes)
│
├── navigation/
│   ├── Routes.kt       # Arquivo centralizador das constantes de rotas
│   └── TotemNavGraph.kt # Configuração do NavHost e fluxo de telas
│
├── network/            # Configuração do Retrofit e Interfaces de Serviço
│
└── ui/
    ├── screens/
    │   ├── WelcomeScreen.kt # Código da interface da tela de Boas-Vindas
    │   ├── MenuScreen.kt    # Interface do cardápio principal
    │   └── DetailScreen.kt  # Interface de detalhes e estados da cozinha
    └── theme/          # Definições de cores (Creme, Verde-Oliva) e tipografia
```

## 🔧 Instruções para Execução
1. Realize o clone deste repositório.
2. Abra o projeto no **Android Studio**.
3. Aguarde a sincronização do Gradle.
4. Execute o aplicativo em um dispositivo físico ou emulador Android.
*(Nota: O aplicativo inicia na tela de Welcome e já responde ao clique do botão de início).*

## ⚠️ Limitações Conhecidas
* No estágio atual, a navegação entre a tela inicial e o cardápio está simulada de forma local até a conclusão da grade de produtos.
* O consumo de dados remotos via HTTP está desativado nesta primeira entrega do repositório.

## 🤖 Registro de Uso de IA
Ferramentas de IA generativa foram utilizadas estritamente como suporte e mentoria de design visual para a construção dos layouts e definição da paleta de cores no Jetpack Compose. Toda a engenharia de arquitetura de software, estrutura de pastas e codificação manual da navegação por constantes de rotas foi projetada e compreendida pela desenvolvedora.
