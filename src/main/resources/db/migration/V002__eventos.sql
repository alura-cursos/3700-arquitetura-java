CREATE TABLE eventos(
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL UNIQUE,
    descricao VARCHAR(255) NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    data DATETIME NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    uf CHAR(2) NOT NULL,
    logradouro VARCHAR(100) NOT NULL,
    bairro VARCHAR(100) NOT NULL,
    cep VARCHAR(9) NOT NULL,
    numero VARCHAR(20),
    complemento VARCHAR(100),

    PRIMARY KEY(id)
);

CREATE TABLE compras(
    id BIGINT NOT NULL AUTO_INCREMENT,
    data DATETIME NOT NULL,
    usuario_id BIGINT NOT NULL,
    forma_de_pagamento VARCHAR(50) NOT NULL,

    PRIMARY KEY(id),
    CONSTRAINT fk_compras_usuario FOREIGN KEY(usuario_id) REFERENCES usuarios(id)
);

CREATE TABLE ingressos(
    id BIGINT NOT NULL AUTO_INCREMENT,
    evento_id BIGINT NOT NULL,
    descricao VARCHAR(100) NOT NULL,
    preco DECIMAL(7,2) NOT NULL,
    codigo VARCHAR(255) NOT NULL UNIQUE,
    compra_id BIGINT,
    tipo VARCHAR(100),
    versao INT NOT NULL,

    PRIMARY KEY(id),
    CONSTRAINT fk_ingressos_evento FOREIGN KEY(evento_id) REFERENCES eventos(id),
    CONSTRAINT fk_ingressos_compra FOREIGN KEY(compra_id) REFERENCES compras(id)
);
