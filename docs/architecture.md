# Arquitetura

## Fluxo atual

1. O frontend consulta e cria recursos pela API REST.
2. Spring Security valida o JWT nos endpoints de escrita.
3. O serviço persiste o pacote pelo Spring Data JPA.
4. Uma mudança de status publica `EventoPacoteDTO` na exchange `supply-chain.exchange`.
5. A fila durável `pacote.mudanca.status` recebe a routing key `pacote.atualizado`.
6. O listener consome a mensagem e simula a etapa de notificação.

## Limites de consistência

A gravação no PostgreSQL e a publicação no RabbitMQ não formam uma única transação. Uma falha entre essas operações pode deixar um status salvo sem evento correspondente. Um padrão outbox é uma evolução indicada antes de tratar o fluxo como entrega confiável.

O consumidor atual não persiste identificadores de evento, não é idempotente e não configura retry ou dead-letter queue. Essas mudanças afetam o desenho operacional e devem ser introduzidas com testes de integração.

## Banco de dados

O schema é criado/atualizado pelo Hibernate. Flyway não foi adicionado nesta rodada porque não há baseline validada contra bancos já existentes. Uma migration inicial deve ser verificada em banco limpo e acompanhada de estratégia explícita para instalações existentes.
