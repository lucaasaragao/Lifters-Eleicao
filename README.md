# Lifters-Eleicao

## Descrição do Projeto

Este projeto é um teste/desafio de programação desenvolvido em Java utilizando o framework Spring e conectado a um banco de dados PostgreSQL. O objetivo é criar um sistema de votação que gerencie candidatos, eleitores e sessões de votação. Abaixo estão as principais funcionalidades e componentes do sistema:

### Funcionalidades

1. **Gestão de Candidatos**
   - **Cadastro de Candidatos**: Permite a criação e armazenamento de candidatos no banco de dados.
   - **Listagem de Candidatos**: Recupera todos os candidatos registrados.
   - **Atualização de Candidatos**: Permite a atualização das informações de um candidato existente.
   - **Remoção de Candidatos**: Permite a exclusão de candidatos, com restrições baseadas no número de votos recebidos.

2. **Gestão de Eleitores**
   - **Cadastro de Eleitores**: Permite a criação e armazenamento de eleitores no banco de dados.
   - **Listagem de Eleitores**: Recupera todos os eleitores registrados.
   - **Atualização de Eleitores**: Permite a atualização das informações de um eleitor existente.
   - **Remoção de Eleitores**: Permite a exclusão de eleitores.

3. **Sessão de Votação**
   - **Abertura e Fechamento de Sessões**: Controla a abertura e fechamento das sessões de votação. A votação só é permitida quando a sessão está aberta.
   - **Verificação de Sessão Aberta**: Antes de permitir que um eleitor vote, o sistema verifica se a sessão de votação está aberta.

4. **Processo de Votação**
   - **Votação**: Permite que um eleitor vote em um candidato. Verifica se o eleitor possui votos disponíveis e se a sessão de votação está aberta. Após votar, os votos do eleitor são zerados.

### Tecnologias Utilizadas

- **Java**: Linguagem de programação principal utilizada para desenvolver a aplicação.
- **Spring Boot**: Framework para simplificar o processo de configuração e execução de aplicações Spring.
- **Spring Data JPA**: Utilizado para acessar e manipular dados no banco de dados PostgreSQL.
- **PostgreSQL**: Sistema de gerenciamento de banco de dados relacional utilizado para armazenar os dados da aplicação.

### Estrutura do Código

- **Modelos (`Model`)**: Classes que representam as entidades do sistema (Candidato, Eleitor, Sessão).
- **Repositórios (`Repository`)**: Interfaces que permitem a comunicação com o banco de dados.
- **Serviços (`Service`)**: Classes que contêm a lógica de negócios da aplicação.
- **Controladores (`Controller`)**: Classes que definem os endpoints da API e gerenciam as requisições HTTP.

### Exemplo de Uso

1. **Criar um Candidato**: Envie uma requisição POST para `/candidates` com as informações do candidato.
2. **Criar um Eleitor**: Envie uma requisição POST para `/constituents` com as informações do eleitor.
3. **Iniciar uma Sessão de Votação**: Envie uma requisição POST para `/sessions/open` para abrir uma sessão.
4. **Votar**: Envie uma requisição POST para `/votes` com o número do documento do eleitor e o número do candidato.
