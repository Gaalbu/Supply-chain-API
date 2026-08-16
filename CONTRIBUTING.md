# Contributing

This is a personal portfolio project. Contributions aren't actively solicited, but the notes below describe how the project is meant to be worked on.

> **Note:** this file is a work in progress — it will be filled out with concrete run/test/lint instructions once the corresponding tooling lands (see the project's implementation plan).

## Quick start

See the [root README](./README.md) for how to run the project locally.

## Commit style

Commits follow [Conventional Commits](https://www.conventionalcommits.org/) (`feat:`, `fix:`, `docs:`, `test:`, `chore:`, ...).

## Before opening a PR

- Run the backend test suite (`./mvnw verify` in `api/`).
- Run the frontend test suite (`npm test` in `web/`).
- Run linting/formatting checks once configured.
