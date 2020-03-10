ALTER TABLE `item_pedido` DROP FOREIGN KEY `FK60ym08cfoysa17wrn1swyiuda`; 
ALTER TABLE `item_pedido` ADD CONSTRAINT `FK60ym08cfoysa17wrn1swyiuda` FOREIGN KEY (`pedido_id`) REFERENCES `pedido`(`id`) ON DELETE CASCADE ON UPDATE RESTRICT;

ALTER TABLE `pedido_mensagens` DROP FOREIGN KEY `FK5254tqklhbxdpxa6jhqvq85cb`; 
ALTER TABLE `pedido_mensagens` ADD CONSTRAINT `FK5254tqklhbxdpxa6jhqvq85cb` FOREIGN KEY (`pedido_id`) REFERENCES `pedido`(`id`) ON DELETE CASCADE ON UPDATE RESTRICT;

ALTER TABLE `pedido_mensagens` DROP FOREIGN KEY `FK1b2msbjhryjv7ymgi4v55burc`; 
ALTER TABLE `pedido_mensagens` ADD CONSTRAINT `FK1b2msbjhryjv7ymgi4v55burc` FOREIGN KEY (`mensagens_id`) REFERENCES `mensagem_pedido`(`id`) ON DELETE CASCADE ON UPDATE RESTRICT;


ALTER TABLE `pedido` DROP FOREIGN KEY `FK6uxomgomm93vg965o8brugt00`; 
ALTER TABLE `pedido` ADD CONSTRAINT `FK6uxomgomm93vg965o8brugt00` FOREIGN KEY (`usuario_id`) REFERENCES `usuario`(`id`) ON DELETE SET NULL ON UPDATE RESTRICT;