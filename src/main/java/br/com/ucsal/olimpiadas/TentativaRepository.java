package br.com.ucsal.olimpiadas;

import java.util.ArrayList;
import java.util.List;

public class TentativaRepository {
    private long proximoId = 1;
    private final List<Tentativa> tentativas = new ArrayList<>();

    public Tentativa salvar(Tentativa tentativa) {
        tentativa.setId(proximoId++);
        tentativas.add(tentativa);
        return tentativa;
    }

    public List<Tentativa> listarTodas() {
        return new ArrayList<>(tentativas);
    }
}