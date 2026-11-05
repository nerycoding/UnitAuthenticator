# Unit Authenticator

Aplicação de terminal em Java que implementa uma tabela hash própria para armazenar nomes e senhas. Colisões são tratadas por encadeamento com listas ligadas.

## Requisitos

- Java 21
- Maven 3.9 ou superior

## Executar testes

```bash
mvn test
```

## Executar a aplicação

```bash
mvn package
java -cp target/classes ink.lucasnsnt.unitauthenticator.Main
```

## Funcionalidades

1. Inserção de usuário
2. Remoção de usuário
3. Busca por nome
4. Login com nome e senha
5. Encerramento do programa
