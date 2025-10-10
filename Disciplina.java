public class Disciplina implements Mostravel{
    private String codigo;
    private String nome;

    public Disciplina(String codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String mostrarInfo() {
        return codigo + " - " + nome;
    }

    public boolean temMesmoCodigo(Disciplina outra) {
        return this.codigo.equals(outra.codigo);
    }
}