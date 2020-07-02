package br.com.caelum.leilao.teste;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AvaliadorTest {
    @Test
    public void deveEntenderLancesEmOrdemCrescente() {
        Usuario joao = new Usuario("João");
        Usuario jose = new Usuario("José");
        Usuario maria = new Usuario("Maria");

        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao, 200.0));
        leilao.propoe(new Lance(jose, 300.0));
        leilao.propoe(new Lance(maria, 400.0));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);


        assertEquals(400, leiloeiro.getMaiorLance(),0.00001);
        assertEquals(200, leiloeiro.getMenorLance(),0.00001);
        assertEquals(300, leiloeiro.getMediaLance(),0.00001);

    }

    @Test
    public void deveEntenderLeilaoComApenasUmLance() {
        Usuario joao = new Usuario("João");

        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao, 3000));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);


        assertEquals(3000, leiloeiro.getMaiorLance(),0.00001);
        assertEquals(3000, leiloeiro.getMenorLance(),0.00001);
        assertEquals(3000, leiloeiro.getMediaLance(),0.00001);

    }

    @Test
    public void deveEntenderLancesEmOrdemDecrescente() {
        Usuario joao = new Usuario("João");
        Usuario jose = new Usuario("José");
        Usuario maria = new Usuario("Maria");

        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao, 3000));
        leilao.propoe(new Lance(jose, 2000));
        leilao.propoe(new Lance(maria, 1000));
        leilao.propoe(new Lance(jose, 800));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);


        assertEquals(3000, leiloeiro.getMaiorLance(),0.00001);
        assertEquals(800, leiloeiro.getMenorLance(),0.00001);
        assertEquals(1700, leiloeiro.getMediaLance(),0.00001);

    }

    @Test
    public void testaMediaDeZeroLances() {
        Usuario joao = new Usuario("João");
        Usuario jose = new Usuario("José");
        Usuario maria = new Usuario("Maria");

        Leilao leilao = new Leilao("Iphone");

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        assertEquals(0, leiloeiro.getMediaLance(),0.00001);

    }

    @Test
    public void deveEncontrarOsTresMaioresLances() {
        Usuario joao = new Usuario("João");
        Usuario jose = new Usuario("José");
        Usuario maria = new Usuario("Maria");

        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao, 500));
        leilao.propoe(new Lance(jose, 400));
        leilao.propoe(new Lance(maria, 300));
        leilao.propoe(new Lance(jose, 450));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();
        assertEquals(3, maiores.size());
        assertEquals(500.0, maiores.get(0).getValor(),0.00001);
        assertEquals(450.0, maiores.get(1).getValor(),0.00001);
        assertEquals(400.0, maiores.get(2).getValor(),0.00001);

    }

    @Test
    public void deveEntenderLancesEmOrdemAleatoria() {
        Usuario joao = new Usuario("João");
        Usuario jose = new Usuario("José");
        Usuario maria = new Usuario("Maria");

        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao, 200));
        leilao.propoe(new Lance(jose, 300));
        leilao.propoe(new Lance(maria, 100));
        leilao.propoe(new Lance(joao, 500));
        leilao.propoe(new Lance(jose, 450));
        leilao.propoe(new Lance(maria, 250));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);


        assertEquals(500, leiloeiro.getMaiorLance(),0.00001);
        assertEquals(100, leiloeiro.getMenorLance(),0.00001);
        assertEquals(300, leiloeiro.getMediaLance(),0.00001);

    }

}
