package br.com.ucsal.olimpiadas;

import java.util.ArrayList;
import java.util.List;

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

    public List<Questao> buscarPorProvaId(long provaId) {
        return questoes.stream().filter(q -> q.getProvaId() == provaId).toList();
    }
}