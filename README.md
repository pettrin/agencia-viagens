# API de Agência de Viagens

API RESTful desenvolvida em Java com Spring Boot para gerenciamento de destinos de viagem. O projeto atende ao cenário de uma agência que deseja disponibilizar endpoints para cadastro, consulta, atualização, avaliação, reserva e exclusão de destinos, mantendo a lógica de negócio em uma camada de serviço.

## Objetivo

Disponibilizar uma API para que clientes, parceiros e aplicações terceiras possam consultar e operar informações de destinos turísticos, pacotes de viagem, disponibilidade de hotéis e atividades associadas.

## Tecnologias utilizadas

- Java 21
- Spring Boot
- Spring Web MVC
- Maven

## Escopo implementado

- Cadastro de destinos de viagem.
- Listagem de todos os destinos cadastrados.
- Pesquisa de destinos por nome ou localização.
- Visualização detalhada de um destino específico.
- Atualização completa de um destino.
- Avaliação de destino com nota de 1 a 10 e recálculo da média.
- Reserva de pacote de viagem para um destino.
- Exclusão de destino.
- Tratamento básico para destino não encontrado e nota inválida.

> Observação: conforme o enunciado da atividade, o projeto não depende de banco de dados, autenticação ou recursos avançados. Os dados são mantidos em memória durante a execução da aplicação.

## Estrutura principal

```text
src/main/java/com/agencia/viagens
├── controller
│   └── DestinoController.java
├── dto
│   ├── AvaliacaoRequest.java
│   └── ReservaRequest.java
├── exception
│   ├── DestinoNaoEncontradoException.java
│   └── GlobalExceptionHandler.java
├── model
│   └── Destino.java
├── service
│   └── DestinoService.java
└── ViagensApplication.java
```

## Como executar

Na raiz do projeto, execute:

```bash
./mvnw spring-boot:run
```

No Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

Por padrão, a aplicação ficará disponível em:

```text
http://localhost:8080
```

## Endpoints

| Método | Endpoint | Descrição |
| --- | --- | --- |
| `POST` | `/destinos` | Cadastra um novo destino de viagem. |
| `GET` | `/destinos` | Lista todos os destinos cadastrados. |
| `GET` | `/destinos/search?termo={valor}` | Pesquisa destinos por nome ou localização. |
| `GET` | `/destinos/{id}` | Retorna os detalhes de um destino específico. |
| `PUT` | `/destinos/{id}` | Atualiza completamente os dados de um destino. |
| `PATCH` | `/destinos/{id}/avaliar` | Registra uma avaliação de 1 a 10 e recalcula a média. |
| `POST` | `/destinos/{id}/reservas` | Realiza uma reserva de pacote para o destino. |
| `DELETE` | `/destinos/{id}` | Exclui um destino. |

## Modelo de destino

```json
{
  "id": 1,
  "nome": "Rio de Janeiro",
  "localizacao": "Rio de Janeiro, Brasil",
  "descricao": "Destino com praias, pontos turísticos e vida cultural intensa.",
  "precoPacote": 2499.9,
  "disponibilidadeHotel": true,
  "atividades": [
    "Cristo Redentor",
    "Pão de Açúcar",
    "Praia de Copacabana"
  ],
  "mediaAvaliacao": 8.5,
  "quantidadeAvaliacoes": 2
}
```

## Exemplos de uso

### Cadastrar destino

```http
POST /destinos
Content-Type: application/json
```

```json
{
  "nome": "Rio de Janeiro",
  "localizacao": "Rio de Janeiro, Brasil",
  "descricao": "Destino com praias, pontos turísticos e vida cultural intensa.",
  "precoPacote": 2499.9,
  "disponibilidadeHotel": true,
  "atividades": [
    "Cristo Redentor",
    "Pão de Açúcar",
    "Praia de Copacabana"
  ]
}
```

Resposta esperada: `201 Created`.

### Listar destinos

```http
GET /destinos
```

Resposta esperada: `200 OK`.

### Pesquisar destinos

```http
GET /destinos/search?termo=rio
```

Resposta esperada: `200 OK`.

### Consultar destino por ID

```http
GET /destinos/1
```

Resposta esperada: `200 OK`.

Caso o destino não exista, a API retorna `404 Not Found`.

### Atualizar destino

```http
PUT /destinos/1
Content-Type: application/json
```

```json
{
  "nome": "Rio de Janeiro",
  "localizacao": "Rio de Janeiro, Brasil",
  "descricao": "Pacote atualizado com novas atividades turísticas.",
  "precoPacote": 2799.9,
  "disponibilidadeHotel": true,
  "atividades": [
    "Cristo Redentor",
    "Museu do Amanhã",
    "Praia de Ipanema"
  ]
}
```

Resposta esperada: `200 OK`.

### Avaliar destino

```http
PATCH /destinos/1/avaliar
Content-Type: application/json
```

```json
{
  "nota": 9
}
```

Resposta esperada: `200 OK`.

A nota deve estar entre 1 e 10. Caso contrário, a API retorna `400 Bad Request`.

### Reservar pacote

```http
POST /destinos/1/reservas
Content-Type: application/json
```

```json
{
  "nomeCliente": "Maria Silva",
  "quantidadePessoas": 2
}
```

Resposta esperada: `200 OK`.

### Excluir destino

```http
DELETE /destinos/1
```

Resposta esperada: `204 No Content`.

## Tratamento de erros

| Situação | Status HTTP | Resposta |
| --- | --- | --- |
| Destino não encontrado | `404 Not Found` | Mensagem informando que o destino não foi encontrado. |
| Nota de avaliação inválida | `400 Bad Request` | Mensagem informando que a nota deve estar entre 1 e 10. |

## Testes

Para executar os testes automatizados:

```bash
./mvnw test
```

No Windows PowerShell:

```powershell
.\mvnw.cmd test
```

## Observações para avaliação

O projeto segue a separação básica esperada para uma API Spring Boot:

- `DestinoController` recebe e responde às requisições HTTP.
- `DestinoService` concentra a lógica de negócio.
- `model/Destino` representa os dados principais do domínio.
- `dto` contém objetos específicos para entrada de avaliação e reserva.
- `exception` centraliza o tratamento de erros conhecidos.

Essa organização facilita a leitura, a manutenção e a validação dos critérios solicitados no desafio.
