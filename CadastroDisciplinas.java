import java.util.*;
public class CadastroDisciplinas {
    private Set<Disciplina> disciplinas = new LinkedHashSet<>();

    // Adicionar disciplina
    public boolean adicionarDisciplina(Disciplina d) {
        disciplinas.add(d);
    }

    // Remover disciplina pelo código
    public void removerDisciplina(String codigo) {
        disciplinas.removeIf(d -> d.getCodigo().equalsIgnoreCase(codigo));
    }

    // Verificar se existe
    public boolean existeDisciplina(String codigo) {
        for (Disciplina d : disciplinas) {
            if (d.getCodigo().equalsIgnoreCase(codigo)) {
                return true;
            }
        }
        return false;
    }

    // Mostrar todas
    public Set<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Disciplinas Cadastradas:\n");
        for (Disciplina d : disciplinas) {
            sb.append(d.toString()).append("\n");
        }
        return sb.toString();
    }
}
