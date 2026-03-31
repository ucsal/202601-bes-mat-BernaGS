package br.com.ucsal.olimpiadas;

import java.util.List;

public interface TentativaRepositoryInterface {
    Tentativa salvar(Tentativa tentativa);
    List<Tentativa> listarTodas();
}