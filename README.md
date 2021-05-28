# teste-tecnico - TOTVS

# Tecnologias #
- Spring Boot 2.5.0 / Java 8
- Spring Data JPA
- Bean Validation
- Thymeleaf 3.0.12
- Postgresql 12
- Lombok 1.18.20

# Instruções de instalação #
- Instale o banco de dados Postgresql com versão igual ou maior que 12 
- Obs: A senha padrão é teste. 
Para alterar a senha modifique o atributo spring.datasource.password no arquivo <b>application.properties</b> do projeto;
- Crie um banco com o nome "pedidos";
- Baixe o projeto e extraia para o local desejado, copie o caminho do arquivo e depois navegue até ele usando o prompt de comando (CMD);
- Execute o comando: mvnw install -DskipTests;
- Navegue até a pasta chamada "target" criada na pasta principal do projeto;
- Execute o comando: java -jar teste-tecnico-0.0.1-SNAPSHOT.jar

# Sistema de Pedidos #
- Novo pedido - Cadastra um novo pedido;
- Listagem de pedidos - Lista todos os pedidos;
- Listagem de produtos - Lista todos os produtos;
- Novo produto - Cadastra um novo produto;
