# teste-tecnico - TOTVS

# Tecnologias Utilizadas #
- Maven
- Spring Boot 2.5.0 / Java 8
- Spring Data JPA
- Bean Validation
- Thymeleaf 3.0.12
- Postgresql 12
- Lombok 1.18.20

# Instruções de instalação #
- Faça o clone ou baixe o projeto e extraia para o local desejado
- Abra a sua IDE e faça a importação de projetos Maven
- Caso não possua o Lombok adicionado a sua IDE, siga as instruções neste link para instação [Lombok Setup](https://projectlombok.org/setup/eclipse)
- Instale o banco de dados Postgresql com versão igual ou maior que 12 
- Obs: A senha padrão é teste.
Para alterar a senha modifique o atributo <b>spring.datasource.password</b> no arquivo <b>application.properties</b> do projeto;
- Crie um banco com o nome "pedidos";
- Navegue até o caminho do projeto usando o prompt de comando (CMD):
```
cd C:\Users\lucas\Desktop\teste-tecnico
```
- Execute o comando: 
```
mvnw install -DskipTests
```
- Navegue até a pasta chamada "target" criada na pasta principal do projeto:
```
cd target
```
- Execute o comando: 
```
java -jar teste-tecnico-0.0.1-SNAPSHOT.jar
```

# Sistema de Pedidos #
- Novo pedido - Cadastra um novo pedido;
- Listagem de pedidos - Lista todos os pedidos;
- Listagem de produtos - Lista todos os produtos;
- Novo produto - Cadastra um novo produto;
