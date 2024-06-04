CREATE TABLE money (
  id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
   id_user BIGINT NOT NULL unique,
   balance FLOAT DEFAULT "0" NOT NULL,
   last_transfer FLOAT DEFAULT "0" NOT NULL,
   last_change_date datetime NOT NULL,
   version BIGINT DEFAULT "0" NOT NULL
);

ALTER TABLE money ADD CONSTRAINT FK_MONEY_ON_USER FOREIGN KEY (id_user) REFERENCES user (id);