
package app;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner entrada = new Scanner(System.in);
    static ArrayList<Jogo> jogos = new ArrayList<>();

    public static void main(String[] args) {

        int opcao;

        do {
            System.out.println("\n========== SISTEMA DE GERENCIAMENTO ==========");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar todos");
            System.out.println("3 - Buscar por ID");
            System.out.println("4 - Atualizar");
            System.out.println("5 - Excluir");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = entrada.nextInt();
            entrada.nextLine();

            switch (opcao) {

                case 1:
                    cadastrar();
                    break;

                case 2:
                    listar();
                    break;

                case 3:
                    buscar();
                    break;

                case 4:
                    atualizar();
                    break;

                case 5:
                    excluir();
                    break;

                case 0:
                    System.out.println("Sistema encerrado!");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

        entrada.close();
    }

    public static void cadastrar() {

        System.out.println("\n--- CADASTRAR JOGO ---");

        System.out.print("ID: ");
        int id = entrada.nextInt();
        entrada.nextLine();

        if (buscarJogo(id) != null) {
            System.out.println("Esse ID já está cadastrado!");
            return;
        }

        System.out.print("Nome do jogo: ");
        String nome = entrada.nextLine();

        System.out.print("Gênero: ");
        String genero = entrada.nextLine();

        System.out.print("Plataforma: ");
        String plataforma = entrada.nextLine();

        Jogo jogo = new Jogo(id, nome, genero, plataforma);

        jogos.add(jogo);

        System.out.println("Jogo cadastrado com sucesso!");
    }

    public static void listar() {

        System.out.println("\n--- LISTA DE JOGOS ---");

        if (jogos.isEmpty()) {
            System.out.println("Nenhum jogo cadastrado!");
            return;
        }

        for (Jogo jogo : jogos) {
            jogo.exibirDados();
        }
    }

    public static void buscar() {

        System.out.print("Digite o ID: ");
        int id = entrada.nextInt();
        entrada.nextLine();

        Jogo jogo = buscarJogo(id);

        if (jogo != null) {
            jogo.exibirDados();
        } else {
            System.out.println("Jogo não encontrado!");
        }
    }

    public static void atualizar() {

        System.out.print("Digite o ID do jogo: ");
        int id = entrada.nextInt();
        entrada.nextLine();

        Jogo jogo = buscarJogo(id);

        if (jogo != null) {

            System.out.print("Novo nome: ");
            jogo.setNome(entrada.nextLine());

            System.out.print("Novo gênero: ");
            jogo.setGenero(entrada.nextLine());

            System.out.print("Nova plataforma: ");
            jogo.setPlataforma(entrada.nextLine());

            System.out.println("Jogo atualizado com sucesso!");

        } else {
            System.out.println("Jogo não encontrado!");
        }
    }

    public static void excluir() {

        System.out.print("Digite o ID do jogo: ");
        int id = entrada.nextInt();
        entrada.nextLine();

        Jogo jogo = buscarJogo(id);

        if (jogo != null) {
            jogos.remove(jogo);
            System.out.println("Jogo excluído com sucesso!");
        } else {
            System.out.println("Jogo não encontrado!");
        }
    }

    public static Jogo buscarJogo(int id) {

        for (Jogo jogo : jogos) {

            if (jogo.getId() == id) {
                return jogo;
            }
        }

        return null;
    }
}
