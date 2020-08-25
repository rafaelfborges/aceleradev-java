# ORM Java

Este desafio contém um projeto Maven pré-configurado com Springboot, Spring-JPA e banco de dados H2.
Abaixo você encontra um Modelo Entidade Relacionamento de um banco de dados utilizado pela Code Nation.

<details>
  <summary>Clique aqui para visualizar o modelo de entidade relacionamento</summary>
  
  ![Entity Model](https://github.com/rafaelfborges/aceleradev-java/blob/master/codenation-sample_desafio_java9.png)

</details>

Você utilizará o package `entity` para criar as entidades JPA para o modelo proposto.

Informações adicionais:
- O projeto deve `buildar` e `iniciar` sem erros
- Verifique os *logs* para ter certeza de que suas tabelas foram criadas corretamente
- Utilize o comando `mvnw spring-boot:run` para iniciar a aplicação

O que será  avaliado:

- Colunas não nulas
- Tamanho das colunas
- Relacionamento entre tabelas (bidirecionalmente)
- Nome de tabelas e colunas

## Conteúdos úteis para o desafio
- https://www.devmedia.com.br/primeiros-passos-com-o-spring-boot/33654
- https://blog.algaworks.com/spring-boot/
- https://www.devmedia.com.br/integracao-do-jpa-com-spring-framework/34221
- https://academiadev.gitbook.io/joinville/jpa/criando-entidades-jpa-com-h2
- https://www.baeldung.com/spring-boot-h2-database
- https://receitasdecodigo.com.br/spring-boot/como-utilizar-o-banco-de-dados-h2-com-spring-boot
- https://hellokoding.com/jpa-one-to-many-relationship-mapping-example-with-spring-boot-maven-and-mysql/
- https://dzone.com/articles/all-jpa-annotations-mapping-annotations
- https://www.baeldung.com/database-auditing-jpa
- https://rashidi.github.io/spring-boot-data-audit/
