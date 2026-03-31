package br.com.ucsal.olimpiadas;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QuestaoRepository {
    private long proximoId = 1;
    private final List<Questao> questoes = new ArrayList<>();

    public Questao salvar(Questao questao) {
        questao.setId(proximoId++);
        questoes.add(questao);
        return questao;
    }

    public List<Questao> listarTodas() {
        return new ArrayList<>(questoes);
    }

    // Agora retorna List<QuestaoBasica> em vez de List<Questao>
    public List<QuestaoBasica> buscarPorProvaId(long provaId) {
        return questoes.stream()
                .filter(q -> q.getProvaId() == provaId)
                .collect(Collectors.toList());
    }
}