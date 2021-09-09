-- Table Cozinha
insert into cozinha (id, nome) values (1, "Tailandesa");
insert into cozinha (id, nome) values (2, "Indiana");

-- Tabela Restaurante
insert into restaurante (nome, taxa_frete, cozinha_id) values ("Thai Gourmet", 10, 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values ("Thai Delivery", 9.50, 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values ("Tuk Tuk Comida Indiana", 15, 2);

-- Tabela Forma de Pagamento
insert into forma_de_pagamento (descricao) values ("Débito");
insert into forma_de_pagamento (descricao) values ("Crédito");

-- Tabela Estado
insert into estado (id, nome) values (1, "Santa Catarina");
insert into estado (id, nome) values (2, "Sao Paulo");
insert into estado (id, nome) values (3, "Paraná");

-- Tabela Cidade
insert into cidade (id, nome, estado_id) values (1, "Pomerode", 1);
insert into cidade (id, nome, estado_id) values (2, "Blumenau", 1);
insert into cidade (id, nome, estado_id) values (3, "Campinas", 2);
insert into cidade (id, nome, estado_id) values (4, "Sao Paulo Capital", 2);
insert into cidade (id, nome, estado_id) values (5, "Curitiba", 3);
insert into cidade (id, nome, estado_id) values (6, "Pato Branco", 3);

-- Tabela Permissao
insert into permissao (nome, descricao) values ("Deletar", "O usuário poderá deletar pedidos do sistema");
insert into permissao (nome, descricao) values ("Adicionar", "O usuário poderá adicionar pedidos do sistema");
insert into permissao (nome, descricao) values ("Atualizar", "O usuário poderá atualizar pedidos do sistema");
