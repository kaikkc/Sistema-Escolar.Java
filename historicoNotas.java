import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
public class historicoNotas{
    private final Map<Integer, List<Matricula>> historico = new HashMap<>();
    private final ListaEstudantes listaEstudantes;

    public historicoNotas(ListaEstudantes listaEstudantes){
        this.listaEstudantes = listaEstudantes;
    }
    //Adiciona ou atualiza a matrícula(disciplina e nota) de um aluno
    public void adicionarMatricula(int idEstudante, String codigoDisciplina, double nota){
        List<Matricula> matriculas = historico.computeIfAbsent(idEstudante, k -> new ArrayList<>());
        //Caso já exista a matrícula para essa disciplina, atualiza a nota
        for(Matricula m: matriculas){
            if(m.getCodigoDisciplina().equals(codigoDisciplina)){
                m.setNota(nota);
                return;
            }
        }
        //Caso não exista, add uma nova matrícula
        matriculas.add(new Matricula(codigoDisciplina, nota));
    }
    public List<Matricula> obterMatriculas(int idEstudante){
        return historico.getOrDefault(idEstudante, new ArrayList<>());
    }
    public Optional<Double> obterNota(int idEstudante, String codigoDisciplina){
        return obterMatriculas(idEstudante).stream().filter(m -> m.getCodigoDisciplina().equals(codigoDisciplina)).map(Matricula::getNota).findFirst();
    }
    public void removerMatricula(int idEstudante, String codigoDisciplina){
        List<Matricula> matriculas = historico.get(idEstudante);
        if(matriculas !=null){
            matriculas.removeIf(m -> m.getCodigoDisciplina().equals(codigoDisciplina));
        }
    }
    public double mediaDoEstudante(int idEstudante){
        List<Matricula> matriculas = obterMatriculas(idEstudante);
        if(matriculas.isEmpty()) return 0.0;
        double soma = 0;
        for(Matricula m: matriculas){
            soma += m.getNota();
        }
        return soma / matriculas.size();
    }
    public double mediaDaDisciplina(String CodigoDisciplina){
        double soma = 0;
        int count = 0;
        for(List<Matricula> matriculas : historico.values()){
            for(Matricula m : matriculas){
                if(m.getCodigoDisciplina().equals(CodigoDisciplina)){
                    soma += m.getNota();
                    count++;
                }
            }
        }
        return count == 0 ? 0.0 : soma/count;
    }
    public List<Estudante> topNEstudantesPorMedia(int n){
        List<Estudante> allEstudantes = new ArrayList<>(listaEstudantes.getEstudantes());
        allEstudantes.sort(Comparator.<Estudante>comparingDouble(e -> mediaDoEstudante(e.getId())).reversed());
        return n >= allEstudantes.size() ? allEstudantes : allEstudantes.subList(0, n);
    }
}
