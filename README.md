## 💻 Sobre o projeto

Adopet é um site fictício de adoção de pets, com funcionalidades para cadastro de tutores, de abrigos e de pets, além de solicitação de adoções. Nesse repositório o projeto será uma API Rest em Java do Adopet.

Este repositório é uma **refatoração** da [versão anterior](https://github.com/brunorsnts/adopet-api/tree/main), aplicando boas práticas de desenvolvimento de software estudadas no curso.

---

## 🔧 Refatorações aplicadas

### Camada de Serviços
Toda a lógica de negócio que estava concentrada nos controllers foi extraída para classes `@Service` dedicadas (`AbrigoService`, `PetService`, `TutorService`, `AdocaoService`, `EmailService`), deixando os controllers responsáveis apenas pela camada HTTP.

### Padrão DTO
Os endpoints pararam de expor diretamente os entities JPA. Foram criados DTOs (Data Transfer Objects) com `record` do Java para cada operação de entrada e saída, separando o contrato da API do modelo de persistência e evitando mass-assignment.

### Padrão Strategy nas validações de adoção
As regras de validação de uma solicitação de adoção (pet disponível, pet sem adoção em andamento, tutor sem adoção em andamento, tutor dentro do limite de 5 adoções) foram extraídas para classes `@Component` que implementam a interface `ValidacaoSolicitacaoAdocao`, injetadas como `List<>` no serviço. Adicionar ou remover uma regra não exige alteração em nenhuma classe existente.

### Injeção de dependência por construtor
Substituído `@Autowired` em campo por injeção via construtor em todos os controllers e services, tornando as dependências explícitas e facilitando testes.

### Queries no repositório
Substituídas operações `findAll()` com filtragem em memória por queries declarativas no Spring Data JPA (`findByAdotado`, `existsByPetIdAndStatus`, `countByTutorIdAndStatus`), melhorando a performance.

### Tratamento de erros
Adicionado tratamento de `ValidationException` (regras de negócio) e `EntityNotFoundException` (recursos não encontrados) em todos os endpoints, retornando `400 Bad Request` com mensagem descritiva em vez de `500 Internal Server Error`.

---

## ⚙️ Funcionalidades

- [x] Cadastro/atualização de tutores;
- [x] Cadastro de abrigos;
- [x] Cadastro de pets do abrigo;
- [x] Listagem de pets disponíveis para adoção;
- [x] Solicitação de adoção;
- [x] Aprovação/reprovação de adoção;

---

## 🎨 Layout

O projeto desse repositório é apenas a API Backend, mas existe um figma com o layout que está disponível neste link: <a href="https://www.figma.com/file/TlfkDoIu8uyjZNla1T8TpH?embed_host=notion&kind=&node-id=518%3A11&t=esSUkfGQEWUeUASj-1&type=design&viewer=1">Layout no Figma</a>

---

## 🛠 Tecnologias

As seguintes tecnologias foram utilizadas no desenvolvimento da API Rest do projeto:

- **[Java 17](https://www.oracle.com/java)**
- **[Spring Boot 3](https://spring.io/projects/spring-boot)**
- **[Maven](https://maven.apache.org)**
- **[MySQL](https://www.mysql.com)**
- **[Hibernate](https://hibernate.org)**
- **[Flyway](https://flywaydb.org)**

---

## 📝 Licença

O projeto desse repositório foi desenvolvido por [Alura](https://www.alura.com.br) e utilizado nos cursos de boas práticas de programação com Java.

Instrutor: [Rodrigo Ferreira](https://cursos.alura.com.br/user/rodrigo-ferreira)

---
