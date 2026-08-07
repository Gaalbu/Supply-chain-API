# Supply Chain API

API backend em Spring Boot para gerenciamento de pacotes em um fluxo de supply chain. O projeto persiste os dados em PostgreSQL, publica eventos no RabbitMQ quando o status de um pacote muda e expõe endpoints REST para criação e atualização de status.

> **Importante:** este backend deve ser utilizado em conjunto com o front-end do projeto: [Supply-chain-web](https://github.com/Gaalbu/Supply-chain-web)

## Visão geral

Esta aplicação foi feita para registrar pacotes, acompanhar a evolução do status de entrega e disparar uma mensagem assíncrona quando houver mudança de status. O listener de RabbitMQ simula a notificação ao cliente após a atualização.

### Tecnologias utilizadas

- Java 17
- Spring Boot 3
- Spring Web
- Spring Data JPA
- Spring Validation
- Spring AMQP / RabbitMQ
- PostgreSQL
- Lombok

## Estrutura do projeto

```text
src/main/java/com/supplychain/supply_chain_api/
├── config/      # Configuração do RabbitMQ
├── controller/  # Endpoints REST
├── domain/
│   ├── entity/   # Entidade Pacote
│   ├── enums/    # Status de entrega
│   └── repository/ # Repositório JPA
├── dto/         # Objetos de entrada e evento
├── event/       # Listener de mensagens
└── service/     # Regra de negócio
```

## Funcionalidades

- Criar pacote com código de rastreio e destinatário
- Atualizar status do pacote
- Persistir os dados no banco PostgreSQL
- Publicar evento no RabbitMQ ao alterar o status
- Consumir o evento em um listener que simula notificação por e-mail

## Modelo de domínio

### `Pacote`

Entidade principal da aplicação. Contém:

- `id`
- `codigoRastreio`
- `destinatario`
- `status`
- `dataCriacao`
- `ultimaAtualizacao`

### Status disponíveis

O enum `EntregaStatus` possui os valores:

- `PREPARACAO`
- `EM_TRANSITO`
- `SAIU_PARA_ENTREGA`
- `ENTREGUE`
- `DEVOLVIDO`

## Integração com o front-end

O front-end que consome esta API está neste repositório:

- https://github.com/Gaalbu/Supply-chain-web

Os dois projetos foram pensados para rodar em conjunto:

- o **front-end** faz a interface com o usuário
- a **API** expõe os endpoints e cuida da persistência e mensageria

## Pré-requisitos

- Java 17
- Docker e Docker Compose
- Maven Wrapper incluído no projeto (`./mvnw`)

## Como rodar o projeto

### 1. Subir os serviços de infraestrutura

O projeto usa PostgreSQL e RabbitMQ definidos no arquivo `docker-compose.yml`.

```bash
docker compose up -d
```

Se o seu ambiente usar a sintaxe antiga, também funciona com:

```bash
docker-compose up -d
```

### 2. Rodar a API

Se no Linux aparecer `bash: ./mvnw: Permissão negada`, rode primeiro:

```bash
chmod +x mvnw
```

Se ainda assim preferir, você também pode executar com:

```bash
bash ./mvnw spring-boot:run
```

```bash
./mvnw spring-boot:run
```

### 3. Verificar se subiu corretamente

A aplicação deve iniciar na porta `8080`.

## Configuração de banco e mensageria

As configurações locais estão em `src/main/resources/application.properties`:

- PostgreSQL em `localhost:5432`
- banco `logistics_db`
- usuário `user`
- senha `password`
- RabbitMQ em `localhost:5672`

O `docker-compose.yml` já sobe:

- `postgres:15-alpine`
- `rabbitmq:3.12-management`

## Fluxo de mensageria

Quando o status de um pacote é alterado:

1. a API atualiza o registro no banco
2. publica um evento na exchange `supply-chain.exchange`
3. roteia a mensagem com a chave `pacote.atualizado`
4. o listener `OuvintePacote` consome a fila `pacote.mudanca.status`
5. a aplicação simula o envio de e-mail ao cliente

## Endpoints disponíveis

### Criar pacote

- **POST** `/api/pacotes`

Exemplo de payload:

```json
{
  "destinatario": "João Silva",
  "codigoRastreio": "PKG001"
}
```

Resposta: `201 Created`

### Atualizar status do pacote

- **PATCH** `/api/pacotes/{codigoRastreio}/status`

Exemplo de payload:

```json
{
  "novoStatus": "EM_TRANSITO"
}
```

Resposta: `204 No Content`

## Exemplo de uso com curl

### Criar pacote

```bash
curl -X POST http://localhost:8080/api/pacotes \
  -H "Content-Type: application/json" \
  -d '{
	"destinatario": "João Silva",
	"codigoRastreio": "PKG001"
  }'
```

### Atualizar status

```bash
curl -X PATCH http://localhost:8080/api/pacotes/PKG001/status \
  -H "Content-Type: application/json" \
  -d '{
	"novoStatus": "EM_TRANSITO"
  }'
```

## Testes

O projeto inclui a classe base de testes gerada pelo Spring Boot. Para executar os testes:

Se ocorrer o mesmo problema de permissão no Linux, use `chmod +x mvnw` uma vez ou execute:

```bash
bash ./mvnw test
```

```bash
./mvnw test
```

## Observações

- Se PostgreSQL ou RabbitMQ não estiverem ativos, a API não sobe corretamente.
- Se você quiser usar a interface visual do RabbitMQ, acesse `http://localhost:15672`.
- O front-end deve ser executado junto com esta API para a experiência completa.
