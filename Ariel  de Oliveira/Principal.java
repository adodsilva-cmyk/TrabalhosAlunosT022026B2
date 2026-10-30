import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    static Scanner scan = new Scanner(System.in);
    static List<ProdutoLivro> estoqueLivros = new ArrayList<ProdutoLivro>();
    static List<ClienteLivraria> listaClientes = new ArrayList<ClienteLivraria>();

    public static void main(String[] args) {
        popularSistema();
        System.out.println("Sistema de Vendas da Livraria iniciado!");
        mostrarMenu();
    }

    private static void popularSistema() {
        listaClientes.add(new ClienteLivraria("Ana Souza", "111.222.333-44"));
        listaClientes.add(new ClienteLivraria("Carlos Silva", "555.666.777-88"));

        estoqueLivros.add(new ProdutoLivro("Dom Casmurro", "Machado de Assis", 39.90, 5));
        estoqueLivros.add(new ProdutoLivro("1984", "George Orwell", 45.00, 3));
        estoqueLivros.add(new ProdutoLivro("O Hobbit", "J.R.R. Tolkien", 59.90, 4));
        estoqueLivros.add(new ProdutoLivro("Código Limpo", "Robert C. Martin", 89.90, 2));
    }

    public static void mostrarMenu() {
        int opcao = 0;
        do {
            System.out.println("\n-------------------- LIVRARIA LEITURA FAG --------------------");
            System.out.println("1 - Listar catálogo de livros");
            System.out.println("2 - Cadastrar novo livro no estoque");
            System.out.println("3 - Remover livro do estoque");
            System.out.println("4 - Realizar venda de livro");
            System.out.println("5 - Cadastrar cliente");
            System.out.println("6 - Listar clientes e compras");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scan.nextInt();
            scan.nextLine();
            validarEscolha(opcao);
        } while (opcao != 0);
    }

    public static void validarEscolha(int opcao) {
        switch (opcao) {
            case 0:
                System.out.println("Sistema encerrado. Volte sempre!");
                break;
            case 1:
                listarCatalogo();
                break;
            case 2:
                cadastrarLivroEstoque();
                break;
            case 3:
                removerLivroEstoque();
                break;
            case 4:
                realizarVenda();
                break;
            case 5:
                cadastrarCliente();
                break;
            case 6:
                listarClientes();
                break;
            default:
                System.out.println("Opção inválida! Tente novamente.");
        }
    }

    private static void listarCatalogo() {
        System.out.println("\n-------- Catálogo de Livros Disponíveis --------");
        for (int i = 0; i < estoqueLivros.size(); i++) {
            ProdutoLivro liv = estoqueLivros.get(i);
            System.out.printf("%d) %s - %s | R$ %.2f | Estoque: %d un.\n",
                    i + 1, liv.getTitulo(), liv.getAutor(), liv.getPreco(), liv.getQuantidadeEstoque());
        }
    }

    private static void cadastrarLivroEstoque() {
        ProdutoLivro livro = new ProdutoLivro();
        System.out.println("Informe o título do livro: ");
        livro.setTitulo(scan.nextLine());
        System.out.println("Informe o autor do livro: ");
        livro.setAutor(scan.nextLine());
        System.out.println("Informe o preço do livro (ex: 49.90): ");
        livro.setPreco(scan.nextDouble());
        System.out.println("Informe a quantidade em estoque: ");
        livro.setQuantidadeEstoque(scan.nextInt());
        scan.nextLine();
        estoqueLivros.add(livro);
        System.out.println("Livro cadastrado no estoque com sucesso!");
    }

    private static void removerLivroEstoque() {
        if (estoqueLivros.isEmpty()) {
            System.out.println("Não há livros no estoque.");
            return;
        }
        listarCatalogo();
        System.out.println("Informe o número do livro que deseja remover do catálogo:");
        int idx = scan.nextInt() - 1;
        scan.nextLine();
        if (idx >= 0 && idx < estoqueLivros.size()) {
            estoqueLivros.remove(idx);
            System.out.println("Livro removido do catálogo com sucesso!");
        } else {
            System.out.println("Índice inválido!");
        }
    }

    private static void realizarVenda() {
        if (listaClientes.isEmpty()) {
            System.out.println("Cadastre um cliente antes de realizar uma venda!");
            return;
        }
        if (estoqueLivros.isEmpty()) {
            System.out.println("Não há livros disponíveis para venda.");
            return;
        }
        listarClientes();
        System.out.println("Selecione o número do cliente:");
        int cIdx = scan.nextInt() - 1;
        scan.nextLine();
        if (cIdx < 0 || cIdx >= listaClientes.size()) {
            System.out.println("Cliente inválido!");
            return;
        }
        listarCatalogo();
        System.out.println("Selecione o número do livro a ser comprado:");
        int lIdx = scan.nextInt() - 1;
        scan.nextLine();
        if (lIdx < 0 || lIdx >= estoqueLivros.size()) {
            System.out.println("Livro inválido!");
            return;
        }
        ProdutoLivro livroEscolhido = estoqueLivros.get(lIdx);
        if (livroEscolhido.getQuantidadeEstoque() <= 0) {
            System.out.println("Livro esgotado no estoque!");
            return;
        }
        livroEscolhido.setQuantidadeEstoque(livroEscolhido.getQuantidadeEstoque() - 1);
        listaClientes.get(cIdx).getLivrosComprados().add(livroEscolhido);
        System.out.println("Venda realizada com sucesso para " + listaClientes.get(cIdx).getNome() + "!");
        System.out.printf("Valor pago: R$ %.2f\n", livroEscolhido.getPreco());
    }

    private static void cadastrarCliente() {
        ClienteLivraria cli = new ClienteLivraria();
        System.out.println("Informe o nome do cliente:");
        cli.setNome(scan.nextLine());
        System.out.println("Informe o CPF do cliente:");
        cli.setCpf(scan.nextLine());
        listaClientes.add(cli);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private static void listarClientes() {
        System.out.println("\n-------- Lista de Clientes e Histórico de Compras --------");
        for (int i = 0; i < listaClientes.size(); i++) {
            ClienteLivraria cli = listaClientes.get(i);
            System.out.printf("%d) %s (CPF: %s)\n", i + 1, cli.getNome(), cli.getCpf());
            if (!cli.getLivrosComprados().isEmpty()) {
                System.out.println("   Livros comprados:");
                for (ProdutoLivro l : cli.getLivrosComprados()) {
                    System.out.printf("   - %s (R$ %.2f)\n", l.getTitulo(), l.getPreco());
                }
            } else {
                System.out.println("   Nenhuma compra realizada ainda.");
            }
        }
    }
}