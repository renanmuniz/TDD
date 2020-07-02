package br.com.caelum.leilao.servico;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Avaliador {

    private double maiorDeTodos = Double.NEGATIVE_INFINITY;
    private double menorDeTodos = Double.POSITIVE_INFINITY;
    private double valorMedio = 0;
    private double total = 0;
    private List<Lance> maiores;

    public void avalia(Leilao leilao) {

        for (Lance lance : leilao.getLances()) {

            if (lance.getValor() > maiorDeTodos) {
                maiorDeTodos = lance.getValor();
            }
            if (lance.getValor() < menorDeTodos) {
                menorDeTodos = lance.getValor();
            }
            total += lance.getValor();
        }
        if (total == 0) {
            valorMedio = 0;
            return;
        } else {
            valorMedio = total / leilao.getLances().size();
        }

        maiores = new ArrayList<Lance>(leilao.getLances());
        Collections.sort(maiores, new Comparator<Lance>() {
            @Override
            public int compare(Lance o1, Lance o2) {
                if(o1.getValor() < o2.getValor()) return 1;
                if(o1.getValor() > o2.getValor()) return -1;
                return 0;
            }
        });
        maiores = maiores.subList(0,maiores.size() > 3 ? 3 : maiores.size());
    }

    public double getMaiorLance() {
        return maiorDeTodos;
    }

    public double getMenorLance() {
        return menorDeTodos;
    }

    public double getMediaLance() {
        return valorMedio;
    }

    public List<Lance> getTresMaiores() {
        return maiores;
    }
}
