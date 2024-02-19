<p align="center">
  <img  src="https://cdn-icons-png.flaticon.com/512/1803/1803023.png">
</p>
<h1 align="center">
  Payments Api
</h1>
<div align="center">

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)

</div>

<br/>

# Descrição

API de pagamentos de débitos de pessoas físicas e jurídicas, possibilitando a criação, edição e busca de pagamentos (incluso filtro)

## Features

- Criar novo pagamento
- Listar todos os pagamentos
- Filtrar busca de pagamentos
- Editar status do pagamento
- Deletar um pagamento

## Documentação da API

### Pagamento

#### Criar novo pagamento

```http
POST /api/v1/payments
```

##### Request:

| Body            | Tipo     | Descrição                                    |
| :-------------- | :------- | :------------------------------------------- |
| `debitCode`     | `int`    | **Obrigatório**. Código do débito sendo pago |
| `payerCpfCnpj`  | `string` | **Obrigatório**. CPF/CNPJ do pagador         |
| `paymentMethod` | `string` | **Obrigatório**. Método de pagamento         |
| `cardNumber`    | `string` | Número do cartão                             |
| `paymentValue`  | `int`    | **Obrigatório**. Valor do pagamento          |

**`paymentValue` deve ser enviado em centavos**

#

#### Listar todos os pagamentos

```http
GET /api/v1/payments
```

##### Response:

- Response 200 (application/json)

  - Body

          {
            "data": [
              {
                "id": "a7e0b9c6-07a7-451d-b5a5-296dcbbc3b2c",
                "debitCode": 1,
                "payerCpfCnpj": "12312312312",
                "paymentMethod": "cartao_credito",
                "cardNumber": "12345678901232456",
                "paymentValue": 8000,
                "status": "Pendente de Processamento",
                "createdAt": "2024-02-19T15:14:16.443218",
                "updatedAt": "2024-02-19T15:14:16.443231"
              },
              {
                "id": "11784194-6ef0-4038-ad2b-18276b02c401",
                "debitCode": 2,
                "payerCpfCnpj": "12312312312",
                "paymentMethod": "cartao_credito",
                "cardNumber": "12345678901232456",
                "paymentValue": 1,
                "status": "Processado com Sucesso",
                "createdAt": "2024-02-19T15:31:05.189453",
                "updatedAt": "2024-02-19T15:31:05.189467"
              },
            ]
          }

#

#### Filtrar busca de pagamentos

```http
GET /api/v1/payments?queryParams
```

##### Todos os filtros de buscas

| queryParams    |   Tipo   |                   Descrição |
| :------------- | :------: | --------------------------: |
| `debitCode`    |  `int`   | Código do débito sendo pago |
| `payerCpfCnpj` | `string` |         CPF/CNPJ do pagador |
| `status`       | `string` |         Status do pagamento |

**`status` pode ser "Pendente de Processamento" | "Processado com Sucesso" | "Processado com Falha"**

##### Response:

- Response 200 (application/json)

  - Body

          {
            "data": [
              {
                "id": "a7e0b9c6-07a7-451d-b5a5-296dcbbc3b2c",
                "debitCode": 1,
                "payerCpfCnpj": "12312312312",
                "paymentMethod": "cartao_credito",
                "cardNumber": "12345678901232456",
                "paymentValue": 8000,
                "status": "Pendente de Processamento",
                "createdAt": "2024-02-19T15:14:16.443218",
                "updatedAt": "2024-02-19T15:14:16.443231"
              },
              {
                "id": "11784194-6ef0-4038-ad2b-18276b02c401",
                "debitCode": 2,
                "payerCpfCnpj": "12312312312",
                "paymentMethod": "cartao_credito",
                "cardNumber": "12345678901232456",
                "paymentValue": 1,
                "status": "Processado com Sucesso",
                "createdAt": "2024-02-19T15:31:05.189453",
                "updatedAt": "2024-02-19T15:31:05.189467"
              },
            ]
          }

#

#### Editar status do pagamento

```http
PUT /api/v1/payments/{id}
```

**`id` Identificador único do pagamento a ser editado**

##### Request:

| Body     | Tipo     | Descrição                                 |
| :------- | :------- | :---------------------------------------- |
| `status` | `string` | **Obrigatório**. Novo status do pagamento |

**`status` deve ser enviado "PENDENT" | "APPROVED" | "DENIED"**

##### Response:

- Response 200 (application/json)

  - Body

          {
            "id": "11784194-6ef0-4038-ad2b-18276b02c401",
            "debitCode": 2,
            "payerCpfCnpj": "12312312312",
            "paymentMethod": "cartao_credito",
            "cardNumber": "12345678901232456",
            "paymentValue": 1,
            "status": "Processado com Sucesso",
            "createdAt": "2024-02-19T15:31:05.189453",
            "updatedAt": "2024-02-19T15:31:05.189467"
          }

#

#### Deletar um pagamento

```http
DELETE /api/v1/payments/{id}
```

**`id` Identificador único do pagamento a ser deletado**

##### Response:

- Response 204
