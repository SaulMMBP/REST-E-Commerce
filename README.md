# REST E-COMMERCE

## Requerimientos

- Mostrar lista de productos
- Agregar productos a carrito de compra
- Verificación de carrito de compra
- Autenticación (login/logout)
- Seguimiento de ordenes previas de usuarios autenticados

## Endpoints

| Métodos HTTP | endpoint                                      |
|--------------|-----------------------------------------------|
| GET          | /products                                     |
| GET          | /products/{id}                                |
| GET          | /products/search/byCategoryId?id={categoryId} |
| GET          | /products/search/byNameContaining?name={name} |
| GET          | /categories                                   |
| GET          | /categories/{id}                              |
| POST         | /checkout/purchase                            |
| GET          | /orders/byCustomerEmail?email={customerEmail} |

## Base de datos

```mermaid
erDiagram
  product {
    id BIGINT
    sku VARCHAR
    name VARCHAR
    description VARCHAR
    unit_price DECIMAL
    image_url VARCHAR
    active BIT
    units_in_stock INT
    date_created DATETIME
    last_updated DATETIME
    category_id BIGINT
  }

  category {
    id BIGINT
    name VARCHAR
  }

  product }|--|| category : in
  
  customer {
    id BIGINT
    first_name VARCHAR
    last_name VARCHAR
    email VARCHAR
  }
  
  orders {
    id BIGINT
    order_tracking_number VARCHAR
    total_price DECIMAL
    total_quantity INT
    status VARCHAR
    date_create DATETIME
    last_updated DATETIME
    billing_address_id BIGINT
    customer_id BIGINT
    shopping_address_id BIGINT
  }
  
  address {
    id BIGINT
    city VARCHAR
    country VARCHAR
    state VARCHAR
    street VARCHAR
    zip_code VARCHAR
  }
  
  order_item {
    image_url VARCHAR
    quantity INT
    unit_price DECIMAL
    order_id BIGINT
    product_id BIGINT
  }
  
  customer ||--|{ orders : create
  orders ||--|| address : shipping
  orders ||--|| address : billing
  orders ||--|{ order_item : contains
```

## Dependencias

- spring-boot-starter-data-jpa
- spring-boot-starter-data-rest
- spring-boot-starter-oauth2-resource-server
- mysql-connector-java
- lombok

## Environment variables

### Database

- CONNECTION_STRING
- USER_DB
- PASSWORD_DB

### Auth0
- ISSUER
- AUDIENCE