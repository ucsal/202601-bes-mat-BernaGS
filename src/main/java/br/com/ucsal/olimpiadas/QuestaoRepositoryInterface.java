package br.com.ucsal.olimpiadas;

import java.util.List;

public interface QuestaoRepositoryInterface {
    Questao salvar(Questao questao);
    List<QuestaoBasica> listarTodas();
    List<QuestaoBasica> buscarPorProvaId(long provaId);
}