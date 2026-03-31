package br.com.ucsal.olimpiadas;

public interface QuestaoInterface {
    long getId();
    long getProvaId();
    String getEnunciado();
    String[] getAlternativas();
    boolean isRespostaCorreta(char marcada);
    String getFenInicial();
}