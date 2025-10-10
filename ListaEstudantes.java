import java.util.*;
public class ListaEstudantes {
    private List<Estudante> estudantes = new ArrayList<>();

    // Adicionar estudante
    public void adicionarEstudante(Estudante e) {
        estudantes.add(e);
    }

    // Remover estudante pelo id
    public void removerEstudantePorId(int id) {
        estudantes.removeIf(e -> e.getId() == id);
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
        return estudantes;
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
