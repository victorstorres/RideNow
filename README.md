# App de Solicitação de Viagens

Este é um aplicativo para solicitação de viagens com motoristas, desenvolvido para plataformas Android e iOS. O app permite que o usuário solicite viagens, escolha um motorista e veja o histórico das viagens realizadas.

## Funcionalidades

### Solicitação de Viagem
- **Formulário de Solicitação**: O usuário pode informar o ID, endereço de origem e destino da viagem.
- **Estimativa de Valor**: O usuário clica no botão para estimar o valor da viagem, com base nas informações fornecidas.
- **Requisição à API**: Após submeter o formulário, o app faz uma requisição à API com os parâmetros necessários.
- **Exibição de Opções de Viagem**: Após a estimativa, a tela de opções de viagem é exibida, mostrando as opções de motoristas disponíveis.

### Opções de Viagem
- **Exibição de Mapa**: O app mostra um mapa estático com a rota da viagem e os pontos de origem e destino.
- **Lista de Motoristas**: A tela exibe uma lista de motoristas com as seguintes informações:
  - Nome
  - Descrição
  - Veículo
  - Avaliação
  - Valor da viagem
- **Botão "Escolher"**: O usuário pode escolher um motorista e confirmar a viagem. Ao clicar, o app faz uma requisição para confirmar a viagem.
- **Redirecionamento para Histórico de Viagens**: Após confirmar a viagem, o app direciona automaticamente para a tela de histórico de viagens.

### Histórico de Viagens
- **Filtro por Motorista**: O usuário pode filtrar o histórico de viagens por motorista
- **Exibição das Viagens Realizadas**: O histórico de viagens mostra a seguinte lista de informações:
  - Data e hora da viagem
  - Nome do motorista
  - Origem e destino
  - Distância
  - Tempo
  - Valor


## Tecnologias Utilizadas

- **Kotlin**: Utilizado para o desenvolvimento da aplicação Android.
- **Jetpack Compose**: Usado para criar a interface de usuário (UI).
- **Retrofit**: Para fazer as requisições de api
- **Navigation**: Para separar as telas
- **Hilt**: Para fazer as injeções
- **Google Maps API**: Para fazer um mapa estatico

## Alterando a API
- Para alterar a api do mapa e preciso entrar no manifest e mudar a opção GOOGLE_MAPS_API_KEY e coloque sua API
