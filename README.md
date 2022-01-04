# Digital Innovation: Desenvolvendo um sistema de gerenciamento de alunos em API REST com Spring Boot

 Neste projeto desenvolvemos um pequeno sistema para o gerenciamneto de alunos de uma instituição de ensino através de uma API REST, criada com o Spring Boot.
 
 Este sistema foi baseado em um outro [projeto do Rodrigo Peleias](https://github.com/rpeleias/personapi_digital_innovation_one) durante uma live coding da [Digital Innovation One](https://digitalinnovation.one/).
 
 ## Lombok
  O Lombok é um `Framework` criado sob a licença `MIT`, podendo ser usado livremente em qualquer projeto Java. Seu principal objetivo é diminuir a verbosidade das classes, repetição    de código "clichê", por meio de anotações em java. Métodos como: `equals`, `hashCode`, `toString`, `Construtores`, `getters` e `setters` não precisam ser implementados, pois
por meio de anotações adicionadas ao nosso código, ensinamos o compilador (maven ou gradle) durante o processo de compilação a criar este código Java automaticamente.

Site do [Projeto Lombok](https://projectlombok.org/features/all)

## DAO - Data Access Object
 DAO (Data Access Object ou Objeto de acesso a dados) é um padrão para aplicações que utilizam persistência de dados, onde tem a separação das regras de negócio das regras de acesso a banco de dados, implementada com linguagens de programação orientadas a objetos (como por exemplo Java) e arquitetura MVC. Basicamente o DAO atua como um intermediário entre a aplicação e o banco de dados.

 ## Verbos HTTP
É um um conjunto de métodos de requisição responsáveis por indicar a ação a ser executada para um dado recurso. Embora esses métodos possam ser descritos como substantivos, eles também são comumente referenciados como HTTP Verbs (Verbos HTTP).
### Principais verbos HTTP
|Verbo|Ação|
|---|---|
|POST| é utilizado para submeter uma entidade a um recurso específico, frequentemente causando uma mudança no estado do recurso ou efeitos colaterais no servidor|
|GET| solicita a representação de um recurso específico. Requisições utilizando o método GET devem retornar apenas dados|
|PUT| substitui todas as atuais representações do recurso de destino pela carga de dados da requisição|
|DELETE| O método DELETE remove um recurso específico|

## Testes Unitários
[Clique aqui](https://github.com/alissonfernandes/studentapi_digital_innovation_one/tree/master/src/test/java/one/digitalinnovation/studentapi) e veja os testes unitários realizados neste projeto.
