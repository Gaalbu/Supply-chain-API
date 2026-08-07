# SUPPLY CHAIN WEB

Frontend do projeto Supply Chain, desenvolvido em Angular, com foco em rastreio de pacotes e suporte a internacionalização (PT-BR/EN-US).

## TECNOLOGIAS

- Angular 21
- TypeScript
- Tailwind CSS
- ngx-translate
- Vitest (testes unitários via Angular CLI)

## FUNCIONALIDADES ATUAIS

- Tela de rastreio em `/rastreio`
- Busca de pacote com simulação de retorno assíncrono
- Exibição de código, status, destinatário e última atualização
- Troca de idioma com persistência em `localStorage`

## ESTRUTURA DO PROJETO

```text
src/
  app/
    app.config.ts
    app.routes.ts
    pages/
      dashboard/
      login/
      rastreio/
  assets/
    i18n/
      pt-br.json
      en-us.json
```

## COMO EXECUTAR

```bash
npm install
npm start
```

Aplicação disponível em: `http://localhost:4200`

## SCRIPTS

```bash
npm start      # sobe o servidor de desenvolvimento
npm run build  # gera build de produção
npm run watch  # build contínuo em modo desenvolvimento
npm test       # executa testes unitários
```

## OBSERVAÇÕES

- No estado atual, o rastreio usa dados simulados no frontend.
- A integração com a API (`Supply-chain-API`) pode ser conectada na página `rastreio`.
