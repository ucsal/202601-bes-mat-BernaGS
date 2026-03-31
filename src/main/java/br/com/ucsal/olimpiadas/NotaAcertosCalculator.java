package br.com.ucsal.olimpiadas;

public class NotaAcertosCalculator implements NotaCalculator {
    @Override
    public int calcular(Tentativa tentativa) {
        int acertos = 0;
        for (var r : tentativa.getRespostas()) {
            if (r.isCorreta()) acertos++;
        }
        return acertos;
    }
}