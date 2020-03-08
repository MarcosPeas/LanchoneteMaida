SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


--
-- Banco de dados: `lanchonete_maida`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `estabelecimento`
--

CREATE TABLE `estabelecimento` (
  `id` int(11) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `gestor_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `estabelecimento`
--

INSERT INTO `estabelecimento` (`id`, `nome`, `gestor_id`) VALUES
(1, 'Lanchonete Maida', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `item_pedido`
--

CREATE TABLE `item_pedido` (
  `id` int(11) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `valor_total` decimal(6,2) DEFAULT NULL,
  `valor_unitario` decimal(6,2) DEFAULT NULL,
  `pedido_id` int(11) DEFAULT NULL,
  `produto_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


--
-- Estrutura da tabela `mensagem_pedido`
--

CREATE TABLE `mensagem_pedido` (
  `id` int(11) NOT NULL,
  `horario` datetime DEFAULT NULL,
  `texto` varchar(255) DEFAULT NULL,
  `titulo` varchar(30) DEFAULT NULL,
  `pedido_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `pedido`
--

CREATE TABLE `pedido` (
  `id` int(11) NOT NULL,
  `forma_pagamento` varchar(30) DEFAULT NULL,
  `horario_aceito` datetime DEFAULT NULL,
  `horario_entregue` datetime DEFAULT NULL,
  `horario_pedido` datetime DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `valor` decimal(6,2) DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


--
-- Estrutura da tabela `pedido_itens_pedido`
--

CREATE TABLE `pedido_itens_pedido` (
  `pedido_id` int(11) NOT NULL,
  `itens_pedido_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



-- --------------------------------------------------------

--
-- Estrutura da tabela `pedido_mensagens`
--

CREATE TABLE `pedido_mensagens` (
  `pedido_id` int(11) NOT NULL,
  `mensagens_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `produto`
--

CREATE TABLE `produto` (
  `id` int(11) NOT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `disponivel` bit(1) NOT NULL,
  `tipo` varchar(16) DEFAULT NULL,
  `titulo` varchar(30) DEFAULT NULL,
  `valor` decimal(6,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `data_nascimento` date DEFAULT NULL,
  `email` varchar(22) NOT NULL,
  `nome` varchar(80) DEFAULT NULL,
  `perfil` varchar(16) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `telefone` varchar(18) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dados da tabela `usuario`
--

INSERT INTO `usuario` (`id`, `data_nascimento`, `email`, `nome`, `perfil`, `senha`, `telefone`) VALUES
(1, '1990-07-02', 'peas.shadow@gmail.com', 'Marcos Peas', 'ROLE_GESTOR', '$2a$10$9QORdqliV9X9mq9hQoMaqubzI0SrkzueEYSKsRN3Dm3Yx4auro4e2', '(99) 982028399'),
(2, '1990-07-04', 'maria@gmail.com', 'Maria', 'ROLE_CLIENTE', '$2a$10$HNDp88aG3GKfg5ykOdq91erA4b1mqd.urxTYI6Gq1P5SElJ92z6ru', NULL);



--
-- Índices para tabela `estabelecimento`
--
ALTER TABLE `estabelecimento`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK3307gjd3j6u7c95deapapnrv4` (`gestor_id`);


--
-- Índices para tabela `item_pedido`
--
ALTER TABLE `item_pedido`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK60ym08cfoysa17wrn1swyiuda` (`pedido_id`),
  ADD KEY `FKtk55mn6d6bvl5h0no5uagi3sf` (`produto_id`);

--
-- Índices para tabela `mensagem_pedido`
--
ALTER TABLE `mensagem_pedido`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKtknk77m72q93q7xn8j63d54gp` (`pedido_id`);

--
-- Índices para tabela `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK6uxomgomm93vg965o8brugt00` (`usuario_id`);

--
-- Índices para tabela `pedido_itens_pedido`
--
ALTER TABLE `pedido_itens_pedido`
  ADD UNIQUE KEY `UK_wynewt6raix8jyuxtpj4t6ig` (`itens_pedido_id`),
  ADD KEY `FKme4lsxo03xc4w8n2jax5yjs1f` (`pedido_id`);

--
-- Índices para tabela `pedido_mensagens`
--
ALTER TABLE `pedido_mensagens`
  ADD UNIQUE KEY `UK_jomqdenmqo0lhu3nyjfyky2oa` (`mensagens_id`),
  ADD KEY `FK5254tqklhbxdpxa6jhqvq85cb` (`pedido_id`);

--
-- Índices para tabela `produto`
--
ALTER TABLE `produto`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);


--
-- AUTO_INCREMENT de tabela `estabelecimento`
--
ALTER TABLE `estabelecimento`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `item_pedido`
--
ALTER TABLE `item_pedido`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `mensagem_pedido`
--
ALTER TABLE `mensagem_pedido`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `pedido`
--
ALTER TABLE `pedido`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `produto`
--
ALTER TABLE `produto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;


--
-- Limitadores para a tabela `estabelecimento`
--
ALTER TABLE `estabelecimento`
  ADD CONSTRAINT `FK3307gjd3j6u7c95deapapnrv4` FOREIGN KEY (`gestor_id`) REFERENCES `usuario` (`id`);

--
-- Limitadores para a tabela `item_pedido`
--
ALTER TABLE `item_pedido`
  ADD CONSTRAINT `FK60ym08cfoysa17wrn1swyiuda` FOREIGN KEY (`pedido_id`) REFERENCES `pedido` (`id`),
  ADD CONSTRAINT `FKtk55mn6d6bvl5h0no5uagi3sf` FOREIGN KEY (`produto_id`) REFERENCES `produto` (`id`);

--
-- Limitadores para a tabela `mensagem_pedido`
--
ALTER TABLE `mensagem_pedido`
  ADD CONSTRAINT `FKtknk77m72q93q7xn8j63d54gp` FOREIGN KEY (`pedido_id`) REFERENCES `pedido` (`id`);

--
-- Limitadores para a tabela `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `FK6uxomgomm93vg965o8brugt00` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`);

--
-- Limitadores para a tabela `pedido_itens_pedido`
--
ALTER TABLE `pedido_itens_pedido`
  ADD CONSTRAINT `FKa95bnq47rq5p8nopst85a7td0` FOREIGN KEY (`itens_pedido_id`) REFERENCES `item_pedido` (`id`),
  ADD CONSTRAINT `FKme4lsxo03xc4w8n2jax5yjs1f` FOREIGN KEY (`pedido_id`) REFERENCES `pedido` (`id`);

--
-- Limitadores para a tabela `pedido_mensagens`
--
ALTER TABLE `pedido_mensagens`
  ADD CONSTRAINT `FK1b2msbjhryjv7ymgi4v55burc` FOREIGN KEY (`mensagens_id`) REFERENCES `mensagem_pedido` (`id`),
  ADD CONSTRAINT `FK5254tqklhbxdpxa6jhqvq85cb` FOREIGN KEY (`pedido_id`) REFERENCES `pedido` (`id`);
COMMIT;
