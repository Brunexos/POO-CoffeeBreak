# ☕ Coffee Break POO

> Um sistema de gerenciamento de coffee break universitário, desenvolvido em **Java** para a disciplina de **Programação Orientada a Objetos (POO)**.

**Status:** 🚧 Em desenvolvimento  
**Interface:** Terminal (menu textual)  
**Contexto:** Projeto acadêmico — AV3, semestre 2026.2

## 📖 Sobre o projeto

O **Coffee Break POO** é um sistema pensado para organizar os registros de um coffee break na faculdade.

A proposta inicial é permitir o gerenciamento de comidas, bebidas e pedidos, com operações acessíveis por um menu no terminal.

Além das funcionalidades de cadastro e consulta, o projeto tem como foco a aplicação prática dos principais conceitos de orientação a objetos em Java.

> 🎮 **Ideia para o futuro:** explorar uma versão com interface gráfica, na qual o usuário monta seu prato selecionando alimentos. A primeira versão será textual, conforme os requisitos da disciplina.

## ✨ Funcionalidades previstas

- [ ] Cadastrar, alterar e remover registros.
- [ ] Listar os registros em formato tabular.
- [ ] Buscar um registro por um de seus atributos.
- [ ] Ordenar registros por um atributo.
- [ ] Gerenciar comidas e bebidas.
- [ ] Registrar pedidos e seus itens.
- [ ] Disponibilizar menus e submenus no terminal.
- [ ] Carregar automaticamente pelo menos 7 objetos de cada classe concreta para testes.
- [ ] Armazenar os objetos utilizando `ArrayList` ou outra estrutura de dados.

*As funcionalidades serão marcadas à medida que forem implementadas.*

## 🧩 Conceitos de POO

| Conceito | Possível aplicação |
|---|---|
| **Encapsulamento** | Proteção de atributos e acesso controlado por métodos. |
| **Herança** | `Comida` e `Bebida` herdando de `Produto`. |
| **Classe abstrata** | `Produto` como base para diferentes tipos de itens. |
| **Interface** | Contrato para comportamentos comuns. |
| **Polimorfismo** | Métodos sobrescritos nas diferentes subclasses. |
| **Composição** | Um `Pedido` formado por itens selecionados. |

*A estrutura de classes é uma proposta inicial e poderá evoluir durante o desenvolvimento.*

## 🖥️ Exemplo de menu

```text
========== COFFEE BREAK ==========
1 - Gerenciar comidas
2 - Gerenciar bebidas
3 - Gerenciar pedidos
4 - Buscar registros
5 - Listar e ordenar registros
0 - Sair
==================================
Escolha uma opção: _
```

Os menus de gerenciamento terão opções para incluir, alterar, excluir e listar registros.

## 🛠️ Tecnologias

- **Java** — Linguagem de programação.
- **Programação Orientada a Objetos** — Base da arquitetura.
- **ArrayList** — Armazenamento de objetos em memória.
- **Git e GitHub** — Versionamento e colaboração.

## ▶️ Como executar

O projeto está em desenvolvimento. Quando os arquivos Java estiverem disponíveis, a execução ocorrerá a partir da classe principal `Programa.java`.

Para uma estrutura simples, com os arquivos Java no mesmo diretório e um JDK instalado:

```bash
javac *.java
java Programa
```

*As instruções serão atualizadas conforme a estrutura final do projeto.*

## 📋 

- Desenvolvimento obrigatório em Java.
- Composição, herança, classe abstrata, interface e polimorfismo.
- Cadastro, alteração e exclusão de objetos.
- Listagem tabular, busca e ordenação.
- Interface textual com menus.
- Dados iniciais automáticos para testes.
- Armazenamento em uma estrutura de dados.
- Código comentado e nomes descritivos.
- Classe principal denominada `Programa.java`.

## 📅 Entrega e apresentação

| Etapa | Data |
|---|---|
| **Entrega no Canvas** | 26/11/2026 |
| **Apresentações** | 26/11, 01/12 e 03/12/2026 |

## 👥 Equipe

Projeto desenvolvido por uma equipe de **3 integrantes**.

- Integrante 1 — *Bruno César de Bortoli*
- Integrante 2 — *Felipe Cardim Gomes*
- Integrante 3 — *Paulo Rogerio*

**Disciplina:** Programação Orientada a Objetos  
**Professor:** Artur Henrique Kronbauer  
**Semestre:** 2026.2

---

☕ *Feito com Java, POO e uma boa dose de café.*
