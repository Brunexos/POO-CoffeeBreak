# Coffee Break POO

Sistema textual em Java para a AV3 de Programacao Orientada a Objetos (2026.2).

## Funcionalidades

- Cardapio: cadastrar, alterar, excluir, buscar por codigo, listar em tabela e ordenar por nome ou preco.
- Pedidos: cadastrar com itens, alterar itens enquanto pendente, excluir, buscar, listar em tabela e ordenar por codigo ou total.
- Fluxo de status: `PENDENTE` -> `PROCESSANDO` -> `CONCLUIDO`; pedidos pendentes tambem podem ser cancelados.
- Formas de pagamento: Pix, dinheiro e cartao (credito ou debito). O atendente registra a confirmacao no menu; o programa nao acessa bancos nem cobra valores reais.
- Dados iniciais: sete comidas, sete bebidas e sete pedidos. Cada pedido inicial possui dois itens.

Os registros ficam em memoria durante a execucao. Ao reiniciar, os dados iniciais sao carregados novamente.

## Conceitos de POO

| Conceito | Onde aparece |
|---|---|
| Heranca e classe abstrata | `Comida` e `Bebida` herdam de `Produto`. |
| Polimorfismo | `toString()` e sobrescrito nas subclasses; Pix, dinheiro e cartao implementam `FormaPagamento`. |
| Interface | `FormaPagamento` define `pagar` e `getNome`. |
| Composicao | `Pedido` guarda uma lista propria de `ItemPedido`. |
| Injecao de dependencia | `Programa.main` entrega listas aos construtores de `Cardapio` e `GerenciadorPedidos`; cada `Pedido` recebe uma `FormaPagamento` pelo construtor. |
| Inversao de controle | O metodo `main` escolhe as dependencias concretas; `Pedido` usa a interface `FormaPagamento` sem criar Pix, dinheiro ou cartao por conta propria. |
| Estrutura de dados | `Cardapio` e `GerenciadorPedidos` usam listas de objetos. |

## Organizacao das classes

- `Progama/Programa.java`: unico arquivo da pasta; seu unico metodo e `main`, com dados iniciais, Scanner e menus textuais.
- `Cardapio.java`: guarda, consulta, ordena e exibe produtos.
- `GerenciadorPedidos.java`: guarda, consulta, ordena e exibe a tabela de pedidos.
- `Pedido.java`: guarda itens, calcula total, controla status e exibe os detalhes de um pedido.
- `FormaPagamento.java` e suas implementacoes: fornecem as opcoes Pix, dinheiro, cartao de credito e cartao de debito.

## Como executar no PowerShell

Instale um JDK e, na raiz do repositorio, execute:

```powershell
$arquivos = (Get-ChildItem .\CoffeBreak\src -Recurse -Filter *.java).FullName
New-Item -ItemType Directory -Force .\CoffeBreak\out | Out-Null
javac -encoding UTF-8 -d .\CoffeBreak\out $arquivos
java -cp .\CoffeBreak\out Progama.Programa
```

O nome da pasta `Progama` e mantido por compatibilidade com a estrutura inicial. A classe principal exigida pela AV3 e `Programa.java`.

## Entrega

O arquivo `ENTREGA_AV3.txt` reune o codigo de todas as classes para envio ao Canvas. Prazo informado no enunciado: **26/11/2026**. Todos os integrantes devem conhecer o funcionamento completo para a apresentacao.
