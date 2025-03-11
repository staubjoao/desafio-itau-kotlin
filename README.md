# API de Transações e Estatísticas

Esta é uma API REST desenvolvida em Kotlin com Spring Boot para receber transações e calcular estatísticas sobre essas transações nos últimos 60 segundos. O projeto foi desenvolvido como parte de um desafio técnico.

---

## Funcionalidades Principais

### 1. Receber Transações (`POST /transacao`)
- **Descrição**: Aceita transações com `valor` e `dataHora`.
- **Validações**:
  - A transação não pode estar no futuro.
  - O valor não pode ser negativo.
- **Respostas**:
  - `201 Created`: Transação válida e registrada.
  - `422 Unprocessable Entity`: Transação inválida (futura ou valor negativo).
  - `400 Bad Request`: Requisição malformada (JSON inválido).

### 2. Limpar Transações (`DELETE /transacao`)
- **Descrição**: Remove todas as transações armazenadas em memória.
- **Resposta**:
  - `200 OK`: Transações removidas com sucesso.

### 3. Calcular Estatísticas (`GET /estatistica`)
- **Descrição**: Retorna estatísticas das transações dos últimos 60 segundos.
- **Estatísticas**:
  - `count`: Quantidade de transações.
  - `sum`: Soma dos valores.
  - `avg`: Média dos valores.
  - `min`: Menor valor.
  - `max`: Maior valor.
- **Resposta**:
  - `200 OK`: Retorna um JSON com as estatísticas.
  - Se não houver transações nos últimos 60 segundos, todos os valores serão `0`.

---

## Requisitos Técnicos

- **Linguagem**: Kotlin
- **Framework**: Spring Boot
- **Armazenamento**: Em memória (sem banco de dados ou cache externo)
- **Formato de Dados**: JSON

---

## Como Executar o Projeto

### Pré-requisitos
- Java 17 ou superior
- Kotlin 1.8 ou superior
- Maven ou Gradle (dependendo da configuração do projeto)

### Passos para Execução

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git
   cd seu-repositorio
   ```
   
2. Compile o projeto:

   ```bash
   ./mvnw clean install
   ```

3. Execute a aplicação:

   ```bash
   ./mvnw spring-boot:run
   ```

## Endpoints
### POST /transacao
Recebe uma transação no formato JSON

```json
{
    "valor": 123.45,
    "dataHora": "2023-10-01T12:34:56.789-03:00"
}
```

### DELETE /transacao
Limpa todas as transações armazenadas.

### GET /estatistica
Retorna estatísticas no formato JSON:

```json
{
    "count": 10,
    "sum": 1234.56,
    "avg": 123.456,
    "min": 12.34,
    "max": 123.56
}
```

Testes
O projeto inclui testes automatizados para garantir o funcionamento correto dos endpoints. Para executar os testes, use o comando:

```bash
./mvnw test
```
