# Steam Discord Bot

Este projeto é um bot do Discord desenvolvido em Java usando **Spring Boot** e a **API da 
Steam** para buscar e exibir informações sobre perfis e atividades dos usuários do Steam. Ele 
processa dados JSON com **Jackson** e utiliza a **Java Discord API (JDA)** para interagir com os 
servidores do Discord.

## Funcionalidades

- **Buscar Perfil do Usuário**: Mostra detalhes do perfil de um usuário na Steam, 
  incluindo nome, quantidade de jogos que possuídos pelo usuário e 
  link direto para o perfil.

- **Exibir Jogos Recentes**: Lista os jogos que o usuário jogou nas últimas duas semanas, 
  mostrando o tempo total jogado e as horas nas últimas duas semanas.

- **Comandos de Ajuda**: O comando `!help` exibe a lista de comandos disponíveis, explicando como cada um pode ser usado.

## Estrutura do Projeto

- `CommandManager`: Centraliza a lógica dos comandos do bot, armazena os comandos disponíveis e os distribui para classes de comando específicas.
- `SteamService`: Serviço responsável por interagir com a API da Steam, manipulando requisições e processando respostas JSON.
- `DiscordEventListener`: Classe que escuta eventos do Discord (como mensagens recebidas) e aciona o `CommandManager` para processar os comandos.

Essa descrição agora considera o listener, que é fundamental para a interação entre o bot e o Discord.

## Tecnologias Usadas
- **Java 17**
- **Spring Boot**
- **JDA**: para interação com o Discord.
- **Jackson**: para processamento de dados JSON da Steam.
- **API da Steam**: para buscar dados de usuários e jogos.
