# 📚 Portfólio de Atividades - Banco de Dados Relacional com Java (JDBC)

Este repositório contém a resolução de **10 exercícios práticos** focados em modelagem de dados, linguagem SQL (MySQL) e integração de sistemas usando **Java Puro com JDBC**. As atividades cobrem desde relacionamentos simples de 1:N até tabelas associativas mais complexas de N:M (Muitos-para-Muitos).

O projeto foi estruturado seguindo o padrão **MVC (Model-View-Controller)** simplificado, onde o terminal interativo (`Scanner`) atua como a interface de usuário.

---

## 👥 Organização e Autores

O trabalho foi distribuído igualmente entre a dupla, onde cada integrante ficou responsável pelo desenvolvimento de 5 atividades completas (Modelagem, SQL, Models, Controllers e Telas):

### 🛠️ Desenvolvido por Vinicius Almeida
* **Exercício 1
* **Exercício 2
* **Exercício 3
* **Exercício 4
* **Exercício 5

### 🛠️ Desenvolvido por Rafael Rezende
* **Exercício 6
* **Exercício 7
* **Exercício 8
* **Exercício 9
* **Exercício 10

---

## 📂 Estrutura Padrão dos Exercícios

Cada uma das 10 atividades segue rigorosamente a mesma divisão de pastas e responsabilidades no código:

* **`models/` (Modelos):** Classes Java puras (POJOs) que espelham as colunas do banco de dados MySQL e tratam os dados com encapsulamento.
* **`controller/` (Controles):** Camada de persistência que usa o JDBC para abrir conexões, preparar declarações (`PreparedStatement`) e executar comandos como `INSERT`, `UPDATE` e `SELECT` com junções complexas (`JOINs`).
* **`view/` (Visões/Telas):** Classes de entrada (como as classes `Main`) que geram menus interativos no console do terminal para testes dinâmicos de cada funcionalidade.
* **`dao/` (Data Access Object):** Centraliza a classe `Conexao`, responsável por obter e disponibilizar a conexão com o servidor do MySQL local.

---

## 🚀 Instruções para Configurar e Rodar o Projeto

Siga os passos abaixo para garantir que o ambiente local consiga executar qualquer uma das 10 atividades:

### 1. Configurar o Banco de Dados (MySQL)
Antes de rodar qualquer código Java, você precisa criar os respectivos schemas/bancos de dados no seu MySQL local. 
1. Abra o seu gerenciador de banco (MySQL Workbench, DBeaver, etc.).
2. Certifique-se de executar os scripts SQL fornecidos em cada exercício para gerar as tabelas e chaves estrangeiras (`FOREIGN KEY`) corretas.
3. *Dica:* Lembre-se de verificar o nome do banco de dados configurado na classe `dao.Conexao` de cada atividade.

### 2. Configurar os Dados de Acesso Locais
Abra a classe responsável pela conexão (`dao.Conexao`) e certifique-se de ajustar a URL do banco, o usuário (geralmente `root`) e a senha para corresponderem aos dados do seu servidor MySQL local.

### 3. Importar o Driver do MySQL (Evitar erro de Driver)
Para que o Java consiga se comunicar com o MySQL, é obrigatório ter o driver **MySQL Connector/J** inserido no projeto:

* **Se o projeto usa Maven (`pom.xml`):** Adicione a dependência dentro do bloco `<dependencies>`:
    ```xml
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <version>8.3.0</version>
    </dependency>
    ```
* **Se o projeto for Java Puro (Sem gerenciador):** Baixe o arquivo `.jar` do site oficial do MySQL e adicione-o como uma biblioteca externa nas propriedades do projeto (*Project Structure -> Dependencies* no IntelliJ ou *Build Path* no Eclipse).

### 4. Executar os Menus
Cada exercício possui a sua respectiva classe `Main.java` dentro do seu pacote de visualização. Para testar qualquer uma das 10 atividades, basta abrir o arquivo desejado e executá-lo. O menu interativo guiará os testes diretamente pelo terminal!
