## 📂 Estrutura de pacotes
src/
├── main/
│ └── java/
│ └── br/com/ucsal/olimpiadas/
│ ├── App.java
│ ├── Participante.java
│ ├── Prova.java
│ ├── Questao.java
│ ├── Resposta.java
│ ├── Tentativa.java
│ ├── ParticipanteRepository.java
│ ├── ProvaRepository.java
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

**Resultado:**  
- A classe `App` agora só se preocupa com o fluxo da aplicação e interação.  
- Cada repositório tem uma única responsabilidade: gerenciar o ciclo de vida de uma entidade específica.  
- Facilita futuras alterações, como trocar a persistência em memória por um banco de dados, sem modificar a lógica de negócio.

---

### 2. **O** – *Open/Closed Principle* (OCP)



---

### 3. **L** – *Liskov Substitution Principle* (LSP)



---

### 4. **I** – *Interface Segregation Principle* (ISP)



---

### 5. **D** – *Dependency Inversion Principle* (DIP)



---

## 📌 Histórico de Commits

| Commit | Descrição | Arquivos Alterados |
|--------|-----------|---------------------|
| 1 | refactor: extrair repositórios para gerenciamento de dados (SRP) | `App.java`, `ParticipanteRepository.java`, `ProvaRepository.java`, `QuestaoRepository.java`, `TentativaRepository.java` |

---

**Nome:** Carlos Bernardo Goês Dos Santos  
**Email:** Carlosbernardogoes.santos@ucsal.edu.br  
**Matrícula:** 200034825  
**Atividade de refatoração SOLID – POOA – 12/03/2026**