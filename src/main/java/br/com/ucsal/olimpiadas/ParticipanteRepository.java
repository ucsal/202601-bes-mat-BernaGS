package br.com.ucsal.olimpiadas;

import java.util.ArrayList;
import java.util.List;

public class ParticipanteRepository {
    private long proximoId = 1;
    private final List<Participante> participantes = new ArrayList<>();

    public Participante salvar(Participante participante) {
        participante.setId(proximoId++);
        participantes.add(participante);
        return participante;
    }

    public List<Participante> listarTodos() {
        return new ArrayList<>(participantes);
    }

    public Participante buscarPorId(long id) {
        return participantes.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public boolean existePorId(long id) {
        return participantes.stream().anyMatch(p -> p.getId() == id);
    }
}