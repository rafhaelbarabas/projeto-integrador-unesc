# Projeto Integrador II - UNESC
Este é o reposótorio do projeto integrador II, do curso de análise e desenvolvimento de sistemas, da Unesc.

O projeto conciste em uma pequena aplicação, divida em front-end e back-end, que tem finalidade de pesquisa cientifica, onde o intuito é realizar a comparação entre duas tecnologias diferentes de bancos de dados, relacional e não relacional e apresentar métricas de performance entre elas para que o usuário possa visualizar qual a tecnologia foi mais performática.

Para esse projeto, estamos simulando um contexto de um catalogo de produtos de um e-commerce, com aproximadamente 150 mil registros.

Equipe: Rafhael Andrade e Guilherme Oliveira.

# Back-end
Uma API que expoe duas rotas, uma para cada tecnolgia de banco de dados, e essa aplicação também é a responsável por gerar as métricas de desempenho.

- <b>Relacional (MySQL)</b> - http://{SERVICE_URL}/relational-db/products

- <b>Não relacional (MongoDB)</b> - http://{SERVICE_URL}/non-relational-db/products

- <b>Métricas</b> - São geradas a partir do Spring Actuator a as rotas da API foram instrumentados para o Prometheus com a biblioteca io.micrometer

<a href="https://github.com/rafhaelbarabas/projeto-integrador-unesc/tree/main/back-end/ads-e-commerce#readme">Mais informações aqui</a>

# Front-end
Uma interface simples onde temos a possibilidade de simular uma determinada quantidade de usuários simultâneos executando requisições para API, o usuário pode escolher qual banco deseja testar e executar uma série de requisições para obter as métricas de desempenho.

<a href="https://github.com/rafhaelbarabas/projeto-integrador-unesc/tree/main/front-end#readme">Mais informações aqui</a>

