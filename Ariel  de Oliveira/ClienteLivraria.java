import java.util.ArrayList;
import java.util.List;

public class ClienteLivraria {
    private String nome;
    private String cpf;
    private List<ProdutoLivro> livrosComprados = new ArrayList<ProdutoLivro>();

    public ClienteLivraria() {
    }

    public ClienteLivraria(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<ProdutoLivro> getLivrosComprados() {
        return livrosComprados;
    }

    public void setLivrosComprados(List<ProdutoLivro> livrosComprados) {
        this.livrosComprados = livrosComprados;
    }
}