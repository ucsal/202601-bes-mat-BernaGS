package br.com.ucsal.olimpiadas;

import java.util.ArrayList;
import java.util.List;

public class ProvaRepository {
    private long proximoId = 1;
    private final List<Prova> provas = new ArrayList<>();

    public Prova salvar(Prova prova) {
        prova.setId(proximoId++);
        provas.add(prova);
        return prova;
    }

    public List<Prova> listarTodos() {
        return new ArrayList<>(provas);
    }

    public Prova buscarPorId(long id) {
        return provas.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public boolean existePorId(long id) {
        return provas.stream().anyMatch(p -> p.getId() == id);
    }
}