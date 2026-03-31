package br.com.ucsal.olimpiadas;

import java.util.ArrayList;
import java.util.List;

public class ProvaRepository implements ProvaRepositoryInterface {
    private long proximoId = 1;
    private final List<Prova> provas = new ArrayList<>();

    @Override
    public Prova salvar(Prova prova) {
        prova.setId(proximoId++);
        provas.add(prova);
        return prova;
    }

    @Override
    public List<Prova> listarTodos() {
        return new ArrayList<>(provas);
    }

    @Override
    public Prova buscarPorId(long id) {
        return provas.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    @Override
    public boolean existePorId(long id) {
        return provas.stream().anyMatch(p -> p.getId() == id);
    }
}