public class Estudante implements Mostravel{
    private int id;
    private String nome;

    public Estudante(int id, String nome){
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String mostrarInfo() {
        return id + " - " + nome;
    }
}