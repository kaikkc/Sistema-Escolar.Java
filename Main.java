import java.util.*;
import java.util.stream.Collectors;
public class Main{
    public static void main(String[] args) {
        ListaEstudantes listaEstudantes = new ListaEstudantes();
        CadastroDisciplinas cadastroDisciplinas = new CadastroDisciplinas();
        historicoNotas historicoNotas = new historicoNotas(listaEstudantes);

        //Carregar datasets (estudantes)
        listaEstudantes.adicionarEstudante(new Estudante(1, "ícaro"));
        listaEstudantes.adicionarEstudante(new Estudante(2, "Rhuan"));
        listaEstudantes.adicionarEstudante(new Estudante(3, "Kaik"));
        listaEstudantes.adicionarEstudante(new Estudante(4, "Goku"));
        listaEstudantes.adicionarEstudante(new Estudante(5, "Naruto"));

        //Exibir lista original(ordem de cadastro)
        System.out.println("---Lista de Estudantes(ordem de cadastro)---");
        for(Estudante e : listaEstudantes.getEstudantes()){
            System.out.println(e.mostrarInfo());
        }
        //Ordenar e exibir ordenada
        listaEstudantes.ordenarPorNome();
        System.out.println("\n--- Lista de Estudantes (ordenada) ---");
        System.out.println(listaEstudantes.getEstudantes().stream().map(Estudante::getNome).collect(Collectors.joining(", ")));
        //Disciplinas
        List<String> duplicadas = new ArrayList<>();
        if (!cadastroDisciplinas.adicionarDisciplina(new Disciplina("MAT101", "Matemática"))) {
        duplicadas.add("MAT101");}
        if(!cadastroDisciplinas.adicionarDisciplina(new Disciplina("PRG201", "Programação"))){
        duplicadas.add("PRG201");}
        if(!cadastroDisciplinas.adicionarDisciplina(new Disciplina("BD301", "Banco de Dados"))){
        duplicadas.add("BD301");}
        if(!cadastroDisciplinas.adicionarDisciplina(new Disciplina("EDF110", "Educação Física"))){
        duplicadas.add("EDF110");}

        System.out.println("\n--- Disciplina (ordem de inserção) ---");
        System.out.println(cadastroDisciplinas.getDisciplinas().stream().map(Disciplina::getCodigo).collect(Collectors.joining(",")));
        System.out.println("\n---Duplicatas Detectadas na importação---");
        if(duplicadas.isEmpty()) System.out.println("(nenhuma)");
        else duplicadas.forEach(System.out::println);
        //Histórico de notas(map)
        historicoNotas.adicionarMatricula(1, "MAT101", 8.5);
        historicoNotas.adicionarMatricula(1, "PRG201", 9.0);
        historicoNotas.adicionarMatricula(2, "PRG201", 7.0);
        historicoNotas.adicionarMatricula(2, "MAT101", 5.0);
        historicoNotas.adicionarMatricula(3, "BD301", 6.5);
        historicoNotas.adicionarMatricula(3, "MAT101", 7.5);
        historicoNotas.adicionarMatricula(4, "PRG201", 8.0);
        historicoNotas.adicionarMatricula(5, "EDF110", 10.0);
       
        System.out.println("\n==Matricula e Médias por Estudante==");
        for (Estudante e : listaEstudantes.getEstudantes()) {
            List<Matricula> mats = historicoNotas.obterMatriculas(e.getId());
            String matriculasTexto = mats.isEmpty()
                    ? "(sem matrículas)"
                    : mats.stream().map(Matricula::toString).collect(Collectors.joining(", "));
            System.out.printf("%s: %s  Média: %.2f%n",
                    e.getNome(), matriculasTexto, historicoNotas.mediaDoEstudante(e.getId()));
        }

        System.out.println("\n== Médias por Disciplina ==");
        for (Disciplina d : cadastroDisciplinas.getDisciplinas()) {
            double media = historicoNotas.mediaDaDisciplina(d.getCodigo());
            System.out.printf("%s: %.2f%n", d.getCodigo(), media);
        }

        System.out.println("\n== Top 3 alunos por média ==");
        List<Estudante> top3 = historicoNotas.topNEstudantesPorMedia(3);
        for (int i = 0; i < top3.size(); i++) {
            Estudante e = top3.get(i);
            System.out.printf("%d) %s - %.2f%n",
                    i + 1, e.getNome(), historicoNotas.mediaDoEstudante(e.getId()));
        }

        System.out.println("\n== Alunos com média >= 8.0 ==");
        List<String> aprovados = listaEstudantes.getEstudantes().stream()
                .filter(e -> historicoNotas.mediaDoEstudante(e.getId()) >= 8.0)
                .map(Estudante::getNome)
                .collect(Collectors.toList());
        System.out.println(aprovados.isEmpty() ? "(nenhum)" : String.join(", ", aprovados));

        System.out.println("\n== Disciplinas com média < 6.0 ==");
        List<String> disciplinasBaixas = cadastroDisciplinas.getDisciplinas().stream()
                .filter(d -> historicoNotas.mediaDaDisciplina(d.getCodigo()) < 6.0)
                .map(Disciplina::getCodigo)
                .collect(Collectors.toList());
        System.out.println(disciplinasBaixas.isEmpty() ? "(nenhuma)" : String.join(", ", disciplinasBaixas));

        // ============================
        // Extra — Mostrando o histórico completo
        // ============================
        System.out.println("\n" + historicoNotas);
    }
}
