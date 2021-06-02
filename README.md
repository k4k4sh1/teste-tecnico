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
1. Faça o clone ou baixe o projeto e extraia para o local desejado
2. Abra a sua IDE e faça a importação de projetos Maven
3. Caso não possua o Lombok adicionado a sua IDE, siga as instruções neste link para instação [Lombok Setup](https://projectlombok.org/setup/eclipse)
4. Instale o banco de dados Postgresql com versão igual ou maior que 12 
- Obs: A senha padrão é teste.
5. Para alterar a senha modifique o atributo abaixo dentro do arquivo <b>application.properties</b> do projeto:
```
spring.datasource.password=minhanovasenha
```
6. Crie um banco de dados com o nome "pedidos";
7. Navegue até o caminho do projeto usando o prompt de comando (CMD):
```
cd C:\Users\lucas\Desktop\teste-tecnico
```
- Execute o comando para realizar as atualizações das bibliotecas e buildar o projeto: 
```
mvnw install -DskipTests
```
- Navegue até a pasta chamada "target" criada na pasta principal do projeto:
```
cd target
```
- Execute o comando para iniciar o sistema: 
```
java -jar teste-tecnico-0.0.1-SNAPSHOT.jar
```

# Sistema de Pedidos #
- Novo pedido - Cadastra um novo pedido;
- Listagem de pedidos - Lista todos os pedidos;
- Listagem de produtos - Lista todos os produtos;
- Novo produto - Cadastra um novo produto;
