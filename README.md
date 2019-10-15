# project-management
API REST do Sistema de Gerenciamento de Projetos

### Recursos utilizados
* Spring Boot
* Spring Batch
* Gradle
* CQRS
* Cache
* PostgreSQL
* Mockito
* JUnit

### Base URL
```
http://localhost:8080/api/v1
```

### Executar
```
$ gradle bootRun
```

### Testes
```
$ gradle test
```

### Testes dos endpoints com Postman
Aquivo na raiz do projeto: project-management.postman_collection.json


### Criar banco de dados no PostgreSQL

Nome do banco de dados: gm_pms

```
$ sudo -i -u postgres
$ psql
postgres=# CREATE DATABASE gm_pms OWNER postgres;
``` 

OBS.: Atualizar password em application.properties
