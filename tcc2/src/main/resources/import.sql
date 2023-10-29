INSERT INTO tb_grupo(nome, url_Image, descricao, palavra_Chave, moment) VALUES ('Geral', 'imaginha', 'Grupo Geral da Academia', 'Imfit',TIMESTAMP WITH TIME ZONE '2022-07-25T13:00:00Z');


INSERT INTO tb_exercicio(nome, img_Url) VALUES ('Supino', 'Imagem supino');
INSERT INTO tb_exercicio(nome, img_Url) VALUES ('Agachamento', 'Imagem Agachamento');
INSERT INTO tb_exercicio(nome, img_Url) VALUES ('Levantamento Terra', 'Imagem lev.terra');
INSERT INTO tb_exercicio(nome, img_Url) VALUES ('Supino Inclinado', 'Imagem supino inc');
INSERT INTO tb_exercicio(nome, img_Url) VALUES ('Puxada pronada', 'Imagem puxada pronada');
INSERT INTO tb_exercicio(nome, img_Url) VALUES ('Triceps corda', 'Imagem triceps corda');
INSERT INTO tb_exercicio(nome, img_Url) VALUES ('Extensora', 'Imagem extensora');
INSERT INTO tb_exercicio(nome, img_Url) VALUES ('Desenvolvimento Ombros', 'Imagem des.ombro');
INSERT INTO tb_exercicio(nome, img_Url) VALUES ('Voador', 'Imagem voador');
INSERT INTO tb_exercicio(nome, img_Url) VALUES ('Flexora', 'Imagem flexora');
INSERT INTO tb_exercicio(nome, img_Url) VALUES ('Remada', 'Imagem remada');

INSERT INTO tb_treino(nome, grupo_Muscular, moment) VALUES ('Treino A', 'Peito e Triceps', TIMESTAMP WITH TIME ZONE '2022-07-25T13:00:00Z');
INSERT INTO tb_usuario(nome, cpf, email, senha, celular, data_Nasc) VALUES ('Marco Vinicius', '1111111', 'marquinhos@gmail.com', '$2a$12$jNQnYqokI3HDkmK8L2eboeq4WXawpUWeIi7wLT/VpOfB2Sw0aKFce', '4002-8922', '1973-05-06');
INSERT INTO tb_usuario(nome, cpf, email, senha, celular, data_Nasc) VALUES ('João Lucas', '2222222', 'joaodantas@gmail.com', '$2a$12$jNQnYqokI3HDkmK8L2eboeq4WXawpUWeIi7wLT/VpOfB2Sw0aKFce', '0003-8922', '1997-08-25');

INSERT INTO tb_role (authority) VALUES ('ROLE_ALUNO');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_usuario_role (usuario_id, role_id) VALUES (1, 1);
INSERT INTO tb_usuario_role (usuario_id, role_id) VALUES (1, 2);
INSERT INTO tb_usuario_role (usuario_id, role_id) VALUES (2, 1);