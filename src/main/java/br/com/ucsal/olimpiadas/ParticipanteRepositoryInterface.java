package br.com.ucsal.olimpiadas;

import java.util.List;

public interface ParticipanteRepositoryInterface {
    Participante salvar(Participante participante);
    List<Participante> listarTodos();
    Participante buscarPorId(long id);
    boolean existePorId(long id);
}