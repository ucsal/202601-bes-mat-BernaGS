## 📂 Estrutura de pacotes
src/
├── main/
│ └── java/
│ └── br/com/ucsal/olimpiadas/
│ ├── App.java
│ ├── Participante.java
| ├── NotaCalculator.java
| ├── NotaAcertosCalculator.java
│ ├── Prova.java
│ ├── QuestaoInterface.java
│ ├── Questao.java
│ ├── QuestaoBasica.java
│ ├── QuestaoComFen.java
│ ├── Resposta.java
│ ├── Tentativa.java
│ ├── ParticipanteRepositoryInterface.java
│ ├── ParticipanteRepository.java
│ ├── QuestaoRepositoryInterface.java
│ ├── TentativaRepositoryInterface.java
│ ├── ProvaRepository.java
│ ├── ProvaRepositoryInterface.java
│ ├── QuestaoRepository.java
│ └── TentativaRepository.java
└── test/
└── java/
└── br/com/ucsal/olimpiadas/
└── ExemploTest.java

## 🔧 Principais mudanças realizadas

### 1. **S** – *Single Responsibility Principle* (SRP)

**Aplicação:**  
- Foram criadas classes específicas para gerenciar o armazenamento e recuperação de cada entidade:  
  `ParticipanteRepository`, `ProvaRepository`, `QuestaoRepository`, `TentativaRepository`.  
- Essas classes assumiram a responsabilidade de manter as listas em memória e controlar a geração automática de IDs.  
- A classe `App` perdeu a responsabilidade de persistência e passou a delegar essas operações aos repositórios.

**Mudanças realizadas:**
- Criadas as classes:
  - `ParticipanteRepository`: gerencia a lista e os IDs de `Participante`.
  - `ProvaRepository`: gerencia a lista e os IDs de `Prova`.
  - `QuestaoRepository`: gerencia a lista e os IDs de `Questao`.
  - `TentativaRepository`: gerencia a lista e os IDs de `Tentativa`.
- A classe `App` passou a utilizar esses repositórios, removendo as listas estáticas e os contadores de ID.
- Todos os métodos que antes manipulavam diretamente as listas (`participantes`, `provas`, `questoes`, `tentativas`) agora delegam as operações aos repositórios.
- O comportamento funcional permanece inalterado; apenas a organização do código foi melhorada.

**Resultado:**  
- A classe `App` agora só se preocupa com o fluxo da aplicação e interação.  
- Cada repositório tem uma única responsabilidade: gerenciar o ciclo de vida de uma entidade específica.  
- Facilita futuras alterações, como trocar a persistência em memória por um banco de dados, sem modificar a lógica de negócio.

---

### 2. **O** – *Open/Closed Principle* (OCP)

**Aplicação:**  
O cálculo da nota estava diretamente implementado dentro da classe `App` (método `calcularNota`). Para permitir que novas formas de pontuação possam ser adicionadas sem modificar o código existente, extraímos essa lógica para uma interface.

**Mudanças realizadas:**
- Criada a interface `NotaCalculator` com o método `calcular(Tentativa)`.
- Criada a implementação concreta `NotaAcertosCalculator`, que reflete a regra original (número de acertos).
- A classe `App` passou a utilizar uma instância de `NotaCalculator` (via campo estático) em vez de chamar diretamente o método `calcularNota`.
- O método `calcularNota` foi removido de `App`.

**Resultado:**
A classe `App` agora está fechada para modificações em relação à lógica de pontuação. Se no futuro quisermos adicionar uma nova forma de calcular nota (por exemplo, nota ponderada), basta criar uma nova classe implementando `NotaCalculator` e substituir a instância em `App`, sem alterar o código da aplicação principal.

---

### 3. **L** – *Liskov Substitution Principle* (LSP)

**Aplicação:**  
O princípio da substituição de Liskov afirma que objetos de uma superclasse devem poder ser substituídos por objetos de suas subclasses sem afetar a corretude do programa. Introduzimos uma interface `QuestaoInterface` que declara os métodos usados pela aplicação, e a classe `Questao` a implementa.

**Mudanças realizadas:**
- Criada a interface `QuestaoInterface` contendo os métodos `getId()`, `getProvaId()`, `getEnunciado()`, `getAlternativas()`, `isRespostaCorreta()` e `getFenInicial()`.
- A classe `Questao` foi modificada para implementar essa interface.
- Em `App`, o loop que percorre as questões agora utiliza o tipo `QuestaoInterface`, demonstrando que o código funciona com qualquer implementação dessa interface.
- Foi criada uma classe `QuestaoComDica` (estendendo `Questao`) como exemplo de um novo tipo de questão que pode ser adicionado sem modificar o código existente.

**Resultado:**  
Agora podemos adicionar novos tipos de questão (como questões com dica, questões com imagem, etc.) simplesmente criando novas classes que implementem `QuestaoInterface`, sem alterar a lógica de aplicação de prova.

---

### 4. **I** – *Interface Segregation Principle* (ISP)

**Aplicação:**  
A interface original `QuestaoInterface` continha métodos que nem todos os clientes precisavam. Por exemplo, o `QuestaoRepository` só precisa de `getProvaId()`, enquanto o `App` precisa também de `getFenInicial()`. Para atender ao ISP, segregamos a interface em duas:

- `QuestaoBasica`: contém os métodos essenciais (`getId`, `getProvaId`, `getEnunciado`, `getAlternativas`, `isRespostaCorreta`).
- `QuestaoComFen`: estende `QuestaoBasica` e adiciona `getFenInicial()`.

**Mudanças realizadas:**
- Criadas `QuestaoBasica` e `QuestaoComFen`.
- `Questao` agora implementa `QuestaoComFen`.
- `QuestaoRepository.buscarPorProvaId` retorna `List<QuestaoBasica>`, eliminando a dependência desnecessária ao método `getFenInicial()`.
- Em `App`, fazemos a conversão para `QuestaoComFen` antes do loop, preservando o comportamento.

**Resultado:**  
Clientes (como o repositório) não são obrigados a depender de métodos que não usam. Se no futuro criarmos uma questão sem FEN, poderemos implementar apenas `QuestaoBasica` e o repositório continuará funcionando. O `App`, por sua vez, só usará `QuestaoComFen` quando necessário.

---

### 5. **D** – *Dependency Inversion Principle* (DIP)

**Aplicação:**  
Originalmente, a classe `App` dependia diretamente das implementações concretas dos repositórios e do cálculo de nota (via campos estáticos). Isso violava o DIP, pois módulos de alto nível (App) dependiam de módulos de baixo nível (repositórios concretos).

**Mudanças realizadas:**
- Criadas interfaces para cada repositório: `ParticipanteRepositoryInterface`, `ProvaRepositoryInterface`, `QuestaoRepositoryInterface`, `TentativaRepositoryInterface`.
- As classes concretas de repositório passaram a implementar essas interfaces.
- A classe `App` foi transformada em uma classe com estado, recebendo todas as suas dependências (repositórios e `NotaCalculator`) via construtor.
- O método `main` agora cria as instâncias concretas e as injeta em `App`, iniciando a aplicação com `app.start()`.

**Resultado:**  
A classe `App` agora depende apenas de abstrações (interfaces), não de implementações concretas. Isso facilita testes (podemos injetar mocks) e torna o sistema mais flexível para trocar implementações futuramente (por exemplo, repositórios que usam banco de dados) sem modificar o código de `App`.

---

## 📌 Histórico de Commits

| Commit | Descrição | Arquivos Alterados |
|--------|-----------|---------------------|
| 1 | refactor: extrair repositórios para gerenciamento de dados (SRP) | `App.java`, `ParticipanteRepository.java`, `ProvaRepository.java`, `QuestaoRepository.java`, `TentativaRepository.java` |
| 2 | **OCP:** Extração do cálculo de nota para interface | `App.java`, `NotaCalculator.java`, `NotaAcertosCalculator.java` |
| 3 | **LSP:** Introdução da interface QuestaoInterface e subtipos substituíveis | `Questao.java`, `QuestaoInterface.java`, `QuestaoComDica.java`, `App.java` |
| 4 | **ISP:** Segregação das interfaces QuestaoBasica e QuestaoComFen | `QuestaoBasica.java`, `QuestaoComFen.java`, `Questao.java`, `QuestaoRepository.java`, `App.java` |
| 5 | **DIP:** Injeção de dependências e inversão de controle | `App.java`, `ParticipanteRepositoryInterface.java`, `ProvaRepositoryInterface.java`, `QuestaoRepositoryInterface.java`, `TentativaRepositoryInterface.java`, `ParticipanteRepository.java`, `ProvaRepository.java`, `QuestaoRepository.java`, `TentativaRepository.java`, `NotaCalculator.java` |

---

**Nome:** Carlos Bernardo Goês Dos Santos  
**Email:** Carlosbernardogoes.santos@ucsal.edu.br  
**Matrícula:** 200034825  
**Atividade de refatoração SOLID – POOA – 12/03/2026**