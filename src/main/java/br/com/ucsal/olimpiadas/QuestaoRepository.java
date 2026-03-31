package br.com.ucsal.olimpiadas;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QuestaoRepository implements QuestaoRepositoryInterface {
    private long proximoId = 1;
    private final List<Questao> questoes = new ArrayList<>();

    @Override
    public Questao salvar(Questao questao) {
        questao.setId(proximoId++);
        questoes.add(questao);
        return questao;
    }

    @Override
    public List<QuestaoBasica> listarTodas() {
        return new ArrayList<>(questoes);
    }

    @Override
    public List<QuestaoBasica> buscarPorProvaId(long provaId) {
        return questoes.stream()
                .filter(q -> q.getProvaId() == provaId)
                .collect(Collectors.toList());
    }
}