# Projeto Integrador II - UNESC - Back-end
Desenvolvido por Rafhael Andrade e Guilherme Silveira.

# Tecnologias
Desenvolvido em Java, utilizando ferramentas do ecossistema Spring framework.
- Java 11
- Spring Boot 2.6.5
- Spring Actuator
- Prometheus
- Instrumentação com micrometer
- Persistência bilingue para os bancos de dados:
  - MySQL
  - MongoDB

# Conceito
Trata-se de uma aplicação que expoe uma API com rotas para realizar consultas em diferentes tecnologias de bancos de dados e gerar métricas para medição do desempenho em cada tecnologia. 

As bases de dados são geradas automaticamente pelo hibernate e são populadas na primeira inicialização através de uma técnica de leitura de arquivos CSV que foram gerados com dados aleatórios nessa ferramenta: https://www.onlinedatagenerator.com/ 

# Requisitos:
- Maven 3.6.3
- Java 11 / JDK recomendada: openjdk 11.0.6 2020-01-14 
- Docker e Docker-Compose
- MongoDB v4 - (Em breve estará incluso no docker)

# Como executar:

Utilizando um terminal / cmd com permissões de administrador, execute os comandos abaixo:

Iniciar banco de dados MySQL e Prometheus:

<pre>
  <code>docker-compose up</code>
</pre>

Instalar dependencias:

<pre>
  <code>mvn clean install</code>
</pre>

Iniciar:

<pre>
  <code>mvn spring-boot:start</code>
</pre>

Parar:

<pre>
  <code>mvn spring-boot:stop</code>
</pre>

Em breve toda aplicação será disponibilizada em uma única imagem docker.

# Preview
<a href="https://ibb.co/0yCTCJ4"><img src="https://i.ibb.co/nM797kd/image.png" alt="image" border="0"></a>