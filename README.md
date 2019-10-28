# tempo-api

[![Build Status](https://travis-ci.org/tulioviglione/tempo-api.svg?branch=master)](https://travis-ci.org/tulioviglione/tempo-api) [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=com.calculo%3Atempo&metric=alert_status)](https://sonarcloud.io/dashboard?id=com.calculo%3Atempo) [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.calculo%3Atempo&metric=coverage)](https://sonarcloud.io/dashboard?id=com.calculo%3Atempo)

### Tecnologias

- Java 8
- Spring boot 2.2.0
- JUnit 5
- Swagger (Habilitado somente no ambiente de desenvolvimento)
- H2 (banco em memoria)
- Versionamento de banco com Flyway

### Informações aplicação
- Aplicação publicada no Heroku.
- Execução de testes unitários no TravisCI
- Verificação da qualidade de código com SonarCloud

### Link Heroku
https://tempos-api.herokuapp.com/

### Usuário
Para auxiliar nos teste a aplicação ja adiciona um usuário no banco.
Somente o método DELETE exige um usuário autenticado no sistema para execução.

> login: auxiliar
> senha: 12345678

#### Autenticação

> Requisição Post
> https://tempos-api.herokuapp.com/api/auth/

```
{
  "login": "auxiliar",
  "senha": "12345678"
}
```

#### Cadastro de usuário
Aplicação possui funcionalidade caso deseje ser adicionado novos usuários

> Requisição post
> https://tempos-api.herokuapp.com/api/usuarios

```
{
  "login": "string",
  "perfil": "ADMIN",
  "senha": "string"
}
```

#### Funcionalidades

### Carga de dados na aplicação
Requisição post realiza durante 60 segundo a carga de dados atualizada no sistema para teste.

> Requisição Post
> https://tempos-api.herokuapp.com/realizaCarga

### Adição de registro
endpoint a ser executado quando um video for inserido.

> Resquisição Post
> https://tempos-api.herokuapp.com/adicionaRegistro

```
{
  "duracao": 0,
  "timestamp": 0
}
```

### Get Estatisticas

endpoint principal, retorna as estatisticas com base nos ultimos 60 segundos
 
> Requisição get
> https://tempos-api.herokuapp.com/

Exemplo retorno

```
{
    "data": {
        "sum": 1518356.38,
        "avg": 200.49602271226726,
        "max": 201.0,
        "min": 200.0,
        "count": 7573
    },
    "errors": []
}
```

### Delete
Endpoint responsável por apagar todos os registros de tempo inseridos.

> Para apagar os registros é necessário informar um token, o mesmo pode ser obtido pelo usuário auxiliar previamente cadastrado

> Requisição delete
> https://tempos-api.herokuapp.com/


