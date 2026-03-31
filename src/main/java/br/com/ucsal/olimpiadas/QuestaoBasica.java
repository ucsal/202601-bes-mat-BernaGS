package br.com.ucsal.olimpiadas;

public interface QuestaoBasica {
    long getId();
    long getProvaId();
    String getEnunciado();
    String[] getAlternativas();
    boolean isRespostaCorreta(char marcada);
}