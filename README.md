# Challenge Backend Java

Este proyecto es una prueba técnica que implica el desarrollo de una aplicación utilizando Java Spring Boot, centrada en la gestión de clientes, cuentas y movimientos. El objetivo es demostrar buenas prácticas en el desarrollo de software, incluyendo la separación de responsabilidades en microservicios, manejo de excepciones, pruebas unitarias y despliegue en contenedores Docker.

## Comenzando 🚀

Estas directrices te ayudarán a obtener una copia funcional del proyecto en tu máquina local para fines de desarrollo y pruebas.

1. Clona el proyecto desde su repositorio: [https://github.com/paigvimo/devsu-001](https://github.com/paigvimo/devsu-001).

2. El proyecto está conformado por dos microservicios:
   - **Gestión de personas y clientes**
   - **Manejo de cuentas y movimientos**
   
   Además, incluye servicios de configuración y Eureka para el registro y descubrimiento de servicios.

Consulta la sección **Despliegue** para obtener detalles sobre cómo desplegar el proyecto.

### Pre-requisitos 📋

Asegúrate de tener instalados los siguientes componentes:

- Maven 3.8.8 o superior
- Java 17 o superior
- Docker
- PostgreSQL
- Variables de entorno necesarias: se pueden configurar en la maquina anfitrión que desplegará los controladores, a excepción de la variable: GIT_USER_PASSWORD, mismo que por seguridad puede consultarla al correo pvandriod93@gmail.com.
 ```
setx DB_CUSTOMER_PASSWORD "xxx"
setx DB_ACCOUNT_TRANSACTION_PASSWORD "xxx"
setx GIT_CONFIG_URI "xxx"
setx GIT_USER_NAME "xxx"
setx GIT_USER_PASSWORD "xxx"
```

### Instalación 🔧

Sigue estos pasos para configurar el proyecto en tu equipo:

1. Navega a la ruta del archivo `pom.xml` de cada microservicio.

2. En primera instancia se debe compilar cada proyecto mediante:
```
mvn clean install
```

3. Crea las imágenes Docker de cada microservicio ejecutando el siguiente comando:
```
docker-compose create
```

4. Como comprobación de este paso deberías poder listar todas las imagenes creadas:
```
docker images
```
## Despliegue 📦

Para el despliegue utilizaremos de acuerdo al requerimiento Docker: 
```
docker-compose up -d
```
```
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Summary for source 1.0-SNAPSHOT:
[INFO]
[INFO] source ............................................. SUCCESS [  0.251 s]
[INFO] business ........................................... SUCCESS [  0.846 s]
[INFO] msa-customer ....................................... SUCCESS [  4.288 s]
[INFO] msa-account-transaction ............................ SUCCESS [  2.816 s]
[INFO] services ........................................... SUCCESS [  0.016 s]
[INFO] config-server ...................................... SUCCESS [  0.813 s]
[INFO] discovery-server ................................... SUCCESS [  0.781 s]
```

Despues de unos 20 segundos del despliegue podemos ver que servicios se han levantado 
```
docker ps
```
```
Name                          Command               State           Ports
---------------------------------------------------------------------------------------------
config-server-001             /__cacert_entrypoint.sh ja ...   Up      0.0.0.0:9090->8080/tcp
db-account-transaction-001    docker-entrypoint.sh postgres    Up      0.0.0.0:5433->5432/tcp
db-customer-001               docker-entrypoint.sh postgres    Up      0.0.0.0:5432->5432/tcp
discovery-server-001          /__cacert_entrypoint.sh ja ...   Up      0.0.0.0:9091->8080/tcp
msa-account-transaction-001   /__cacert_entrypoint.sh ja ...   Up      0.0.0.0:8081->8080/tcp
msa-customer-001              /__cacert_entrypoint.sh ja ...   Up      0.0.0.0:8080->8080/tcp
```

Dado que Docker no espera que una imagen se para levantar la dependiente, necesitamos iniciar nuevamente los microservicios que no se deben haber levantado.
```
docker-compose start
```
Podemos consultar los logs generados por cada microservicio
```
docker logs -f microservicio
```
Para detener los contenedores sea que hayamos modificado algo o cualquier otro motivo, debemos usar la sentencia
```
docker-compose down
```

## Ejecutando las pruebas ⚙️

Dentro de la URL de cada servicio podemos validar que los mismos se encuentras desplegados.

Podemos a demás ejecutar los ejemplos generados en postam presentes en este repositorio para validar las diferentes funcioanlidades solicitadas. Para ello debemos configurar las variables que hacen referencia a cada microservicio, de acuerdo a la ruta en la que los hayamos desplegado.

Cabe mencionar que aunque se solicitó CRUDS para todos los microservicios, se consideró que en transacción por motivos de auditoría, atomicidad, seguridad y congruencia de la información no se permite actualizar ni eliminar.

**Ejemplos:**
```
Cliente guardado
{
    "name": "PAUL VIDAL",
    "gender": "M",
    "birthdate": "1993-07-18",
    "identificationNumber": "1",
    "address": "CALLE 1 5-00 Y CALLE 2",
    "telephone": "555-1111",
    "status": "ACT",
    "password": "aPass.1234"
}
```

```
Cuenta guardada
{
    "accountId": 1,
    "customerId": 1,
    "accountType": "SAV",
    "initialAmount": 500.00,
    "accountStatus": "ACT" 
}
```

```
Transacción generada
{
    "id": 4,
    "account": {
        "accountId": 1,
        "customerId": 1,
        "accountType": "SAV",
        "initialAmount": 50.00000000000,
        "accountStatus": "ACT"
    },
    "transactionDate": "2024-08-24T23:37:52.451+00:00",
    "transactionType": "CRE",
    "amount": 10,
    "balance": 60.00000000000
}
```

```
Reporte
{
    "accounts": [
        {
            "accountId": 1,
            "balance": 65.00000000000,
            "transactions": [
                {
                    "id": 1,
                    "account": {
                        "accountId": 1,
                        "customerId": 1,
                        "accountType": "SAV",
                        "initialAmount": 50.00000000000,
                        "accountStatus": "ACT"
                    },
                    "transactionDate": "2024-08-24T23:36:04.072+00:00",
                    "transactionType": "CRE",
                    "amount": 50.00000000000,
                    "balance": 50.00000000000
                },
                {
                    "id": 4,
                    "account": {
                        "accountId": 1,
                        "customerId": 1,
                        "accountType": "SAV",
                        "initialAmount": 50.00000000000,
                        "accountStatus": "ACT"
                    },
                    "transactionDate": "2024-08-24T23:37:52.451+00:00",
                    "transactionType": "CRE",
                    "amount": 10.00000000000,
                    "balance": 60.00000000000
                },
                {
                    "id": 5,
                    "account": {
                        "accountId": 1,
                        "customerId": 1,
                        "accountType": "SAV",
                        "initialAmount": 50.00000000000,
                        "accountStatus": "ACT"
                    },
                    "transactionDate": "2024-08-24T23:38:09.336+00:00",
                    "transactionType": "CRE",
                    "amount": 20.00000000000,
                    "balance": 80.00000000000
                },
                {
                    "id": 6,
                    "account": {
                        "accountId": 1,
                        "customerId": 1,
                        "accountType": "SAV",
                        "initialAmount": 50.00000000000,
                        "accountStatus": "ACT"
                    },
                    "transactionDate": "2024-08-24T23:38:17.954+00:00",
                    "transactionType": "DEB",
                    "amount": -15.00000000000,
                    "balance": 65.00000000000
                }
            ]
        }
    ]
}
```

Se puede validar la información también, mediante el esquema de base de datos incluido en los archivos.

## Construido con 🛠️

_Menciona las herramientas que utilizaste para crear tu proyecto_

* [Spring](https://spring.io/) - Framework
* [Java](https://www.java.com/es/)) - Lenguaje de programación
* [Maven](https://maven.apache.org/) - Gestor de dependencias
* [PostgreSQL](https://www.postgresql.org/) - Base de datos
* [Docker](https://www.docker.com/) - Gestor de contenedores
* [Postman](https://www.postman.com/) - Herramienta de pruebas

## Versionado 📌

Se usó [Git](https://git-scm.com/) para el versionado. 


## Autores ✒️

* **Paúl Vidal** - *Desarrollo completo* - [paigvimo](https://github.com/paigvimo/)
