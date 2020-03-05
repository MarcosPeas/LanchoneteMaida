CREATE TABLE estabelecimento (
  id int(11) NOT NULL,
  nome varchar(255) NOT NULL,
  gestor_id int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE item_pedido (
  id int(11) NOT NULL,
  quantidade int(11) NOT NULL,
  valor_total decimal(19,2) DEFAULT NULL,
  valor_unitario decimal(19,2) DEFAULT NULL,
  pedido_id int(11) DEFAULT NULL,
  produto_id int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE mensagem_pedido (
  id int(11) NOT NULL,
  horario datetime DEFAULT NULL,
  texto varchar(255) DEFAULT NULL,
  titulo varchar(255) DEFAULT NULL,
  pedido_id int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE pedido (
  id int(11) NOT NULL,
  forma_pagamento varchar(255) DEFAULT NULL,
  horario_aceito datetime DEFAULT NULL,
  horario_entregue datetime DEFAULT NULL,
  horario_pedido datetime DEFAULT NULL,
  status int(11) DEFAULT NULL,
  valor decimal(19,2) DEFAULT NULL,
  usuario_id int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE pedido_itens_pedido (
  pedido_id int(11) NOT NULL,
  itens_pedido_id int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE pedido_mensagens (
  pedido_id int(11) NOT NULL,
  mensagens_id int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE produto (
  id int(11) NOT NULL,
  descricao varchar(255) DEFAULT NULL,
  disponivel bit(1) NOT NULL,
  tipo varchar(255) DEFAULT NULL,
  titulo varchar(255) DEFAULT NULL,
  valor decimal(19,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE usuario (
  id int(11) NOT NULL,
  data_nascimento date DEFAULT NULL,
  email varchar(255) DEFAULT NULL,
  nome varchar(255) DEFAULT NULL,
  perfil varchar(255) DEFAULT NULL,
  senha varchar(255) DEFAULT NULL,
  telefone varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


ALTER TABLE estabelecimento
  ADD PRIMARY KEY (id),
  ADD KEY FK3307gjd3j6u7c95deapapnrv4 (gestor_id);

ALTER TABLE item_pedido
  ADD PRIMARY KEY (id),
  ADD KEY FK60ym08cfoysa17wrn1swyiuda (pedido_id),
  ADD KEY FKtk55mn6d6bvl5h0no5uagi3sf (produto_id);

ALTER TABLE mensagem_pedido
  ADD PRIMARY KEY (id),
  ADD KEY FKtknk77m72q93q7xn8j63d54gp (pedido_id);

ALTER TABLE pedido
  ADD PRIMARY KEY (id),
  ADD KEY FK6uxomgomm93vg965o8brugt00 (usuario_id);

ALTER TABLE pedido_itens_pedido
  ADD UNIQUE KEY UK_wynewt6raix8jyuxtpj4t6ig (itens_pedido_id),
  ADD KEY FKme4lsxo03xc4w8n2jax5yjs1f (pedido_id);

ALTER TABLE pedido_mensagens
  ADD UNIQUE KEY UK_jomqdenmqo0lhu3nyjfyky2oa (mensagens_id),
  ADD KEY FK5254tqklhbxdpxa6jhqvq85cb (pedido_id);

ALTER TABLE produto
  ADD PRIMARY KEY (id);

ALTER TABLE usuario
  ADD PRIMARY KEY (id);


ALTER TABLE estabelecimento
  MODIFY id int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE item_pedido
  MODIFY id int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE mensagem_pedido
  MODIFY id int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE pedido
  MODIFY id int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE produto
  MODIFY id int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE usuario
  MODIFY id int(11) NOT NULL AUTO_INCREMENT;


ALTER TABLE estabelecimento
  ADD CONSTRAINT FK3307gjd3j6u7c95deapapnrv4 FOREIGN KEY (gestor_id) REFERENCES usuario (id);

ALTER TABLE item_pedido
  ADD CONSTRAINT FK60ym08cfoysa17wrn1swyiuda FOREIGN KEY (pedido_id) REFERENCES pedido (id),
  ADD CONSTRAINT FKtk55mn6d6bvl5h0no5uagi3sf FOREIGN KEY (produto_id) REFERENCES produto (id);

ALTER TABLE mensagem_pedido
  ADD CONSTRAINT FKtknk77m72q93q7xn8j63d54gp FOREIGN KEY (pedido_id) REFERENCES pedido (id);

ALTER TABLE pedido
  ADD CONSTRAINT FK6uxomgomm93vg965o8brugt00 FOREIGN KEY (usuario_id) REFERENCES usuario (id);

ALTER TABLE pedido_itens_pedido
  ADD CONSTRAINT FKa95bnq47rq5p8nopst85a7td0 FOREIGN KEY (itens_pedido_id) REFERENCES item_pedido (id),
  ADD CONSTRAINT FKme4lsxo03xc4w8n2jax5yjs1f FOREIGN KEY (pedido_id) REFERENCES pedido (id);

ALTER TABLE pedido_mensagens
  ADD CONSTRAINT FK1b2msbjhryjv7ymgi4v55burc FOREIGN KEY (mensagens_id) REFERENCES mensagem_pedido (id),
  ADD CONSTRAINT FK5254tqklhbxdpxa6jhqvq85cb FOREIGN KEY (pedido_id) REFERENCES pedido (id);
COMMIT;