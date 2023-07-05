create table produtos(
                         id bigint PRIMARY KEY not null auto_increment,
                         nome varchar(255) not null,
                         marca varchar(255) not null,
                         tamanho varchar(3) not null,
                         fornecedor varchar(255) null,
                         preco_compra decimal(7,2) not null,
                         preco_venda decimal(7,2) not null
);