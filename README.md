# project-management
API REST do Sistema de Gerenciamento de Projetos

### Base URL
```
http://localhost:8080/api/v1
```

### Testes com Postman
Aquivo na raiz do projet: project-management.postman_collection.json


### Criar banco de dados no PostgreSQL

Nome do banco de dados: gm_pms

```
$ sudo -i -u postgres
$ psql
postgres=# CREATE DATABASE gm_pms OWNER postgres;
``` 

OBS.: Atualizar password em application.properties