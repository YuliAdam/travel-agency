CREATE TABLE admin(
  id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
   admin_name VARCHAR(50) NOT NULL,
   login VARCHAR(50) NOT NULL UNIQUE,
   tell VARCHAR(50) NOT NULL,
   password VARCHAR(255) NOT NULL
);
CREATE TABLE user (
  id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
   user_name VARCHAR(50) NOT NULL,
   login VARCHAR(50) NOT NULL UNIQUE,
   tell VARCHAR(50) NOT NULL,
   password VARCHAR(255) NOT NULL,
   role VARCHAR(255) NOT NULL
);
CREATE TABLE hotel (
  id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
   country VARCHAR(255) NOT NULL,
   name VARCHAR(50) NOT NULL,
   star INT NOT NULL,
   guests INT DEFAULT NULL,
   breakfast TINYINT(1) DEFAULT "0",
   lunch TINYINT(1) DEFAULT "0",
   dinner TINYINT(1) DEFAULT "0",
   tv TINYINT(1) DEFAULT "0",
   air_conditioner TINYINT(1) DEFAULT "0",
   balcony TINYINT(1) DEFAULT "0"
);
CREATE TABLE offer (
  id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
   type VARCHAR(255) NOT NULL,
   country VARCHAR(255) NOT NULL,
   num_of_the_days INT DEFAULT NULL,
   start date DEFAULT NULL,
   transport VARCHAR(255) DEFAULT NULL,
   id_hotel BIGINT DEFAULT NULL,
   price FLOAT DEFAULT NULL
);

ALTER TABLE offer ADD CONSTRAINT FK_OFFER_ON_ID_HOTEL FOREIGN KEY (id_hotel) REFERENCES hotel (id);

CREATE TABLE orders (
  id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
   user_id BIGINT NOT NULL,
   offer_id BIGINT NOT NULL,
   order_date datetime NOT NULL
);

ALTER TABLE orders ADD CONSTRAINT FK_ORDERS_ON_OFFER FOREIGN KEY (offer_id) REFERENCES offer (id);

ALTER TABLE orders ADD CONSTRAINT FK_ORDERS_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);


