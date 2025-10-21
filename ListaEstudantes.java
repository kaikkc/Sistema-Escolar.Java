import java.util.*;
public class ListaEstudantes {
    private List<Estudante> estudantes = new ArrayList<>();

    // Adicionar estudante
    public void adicionarEstudante(Estudante e) {
        if(e==null){
            throw new IllegalArgumentException("Estudante Não pode ser nulo.");
        }
        if(estudantes.stream().anyMatch(existente -> existente.getId() == e.getId())){
            throw new IllegalArgumentException("Estudante com Id" + e.getId() + "já existe.");
        }
        estudantes.add(e);
    }

    // Remover estudante pelo id
    public void removerEstudantePorId(int id) {
        if(id < 0){
            throw new IllegalArgumentException("Id não pode ser negativo.");
        }
        boolean removido = estudantes.removeIf(e -> e.getId() == id);
        if(!removido){
            throw new IllegalArgumentException("Estudando com Id" + id + "não encontrado.");
        }
    }
    // Obter estudante por índice com validação
    public Estudante obterEstudantePorIndice(int indice){
        if(indice < 0 || indice >= estudantes.size()){
            throw new IndexOutOfBoundsException("índice inválido:" + indice + "Deve estar entre 0 e " + (estudantes.size() - 1));
        }
        return estudantes.get(indice);
    }
    // Buscar por nome (ou parte do nome)
    public List<Estudante> buscarPorNome(String nome) {
        List<Estudante> encontrados = new ArrayList<>();
        for (Estudante e : estudantes) {
            if (e.getNome().toLowerCase().contains(nome.toLowerCase())) {
                encontrados.add(e);
            }
        }
        return encontrados;
    }

    // Ordenar alfabeticamente pelo nome
    public void ordenarPorNome() {
        estudantes.sort(Comparator.comparing(Estudante::getNome, String.CASE_INSENSITIVE_ORDER));
    }

    // Mostrar todos
    public List<Estudante> getEstudantes() {
        return new ArrayList<>(estudantes);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Lista de Estudantes:\n");
        for (Estudante e : estudantes) {
            sb.append(e.toString()).append("\n");
        }
        return sb.toString();
    }
}
