package br.com.ucsal.olimpiadas;

import java.util.List;

public interface ProvaRepositoryInterface {
    Prova salvar(Prova prova);
    List<Prova> listarTodos();
    Prova buscarPorId(long id);
    boolean existePorId(long id);
}