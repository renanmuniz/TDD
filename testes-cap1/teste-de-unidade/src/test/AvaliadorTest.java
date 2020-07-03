package test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;
import builder.CriadorDeLeilao;
import org.junit.*;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.*;

public class AvaliadorTest {

    private Avaliador leiloeiro;
    private Usuario joao;
    private Usuario jose;
    private Usuario maria;

    @BeforeClass
    public static void InicioDosTestes() {
        System.out.println("Inicio dos testes da classe AvaliadorTest");
    }

    @Before
    public void criaAvaliador() {
        this.leiloeiro = new Avaliador();
        this.joao = new Usuario("João");
        this.jose = new Usuario("José");
        this.maria = new Usuario("Maria");
    }


    @Test
    public void deveEntenderLancesEmOrdemCrescente() {

        Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
                .lance(joao, 200.0)
                .lance(jose, 300.0)
                .lance(maria, 400.0)
                .constroi();

        leiloeiro.avalia(leilao);


        assertEquals(400, leiloeiro.getMaiorLance(),0.00001);

        //assertEquals(200, leiloeiro.getMenorLance(),0.00001);
        assertThat(leiloeiro.getMenorLance(),equalTo(200.0));

        assertEquals(300, leiloeiro.getMediaLance(),0.00001);

    }

    @Test
    public void deveEntenderLeilaoComApenasUmLance() {

        Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
                .lance(joao, 3000)
                .lance(jose, 300.0)
                .lance(maria, 400.0)
                .constroi();

        leiloeiro.avalia(leilao);


        assertEquals(3000, leiloeiro.getMaiorLance(),0.00001);
        assertEquals(300, leiloeiro.getMenorLance(),0.00001);
        assertEquals(1233.33333, leiloeiro.getMediaLance(),0.00001);

    }

    @Test
    public void deveEntenderLancesEmOrdemDecrescente() {


        Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
                .lance(joao, 3000)
                .lance(jose, 2000)
                .lance(maria, 1000)
                .lance(jose,800)
                .constroi();


        leiloeiro.avalia(leilao);


        assertEquals(3000, leiloeiro.getMaiorLance(),0.00001);
        assertEquals(800, leiloeiro.getMenorLance(),0.00001);
        assertEquals(1700, leiloeiro.getMediaLance(),0.00001);

    }

    @Test
    public void testaMediaDeZeroLances() {

        Leilao leilao = new CriadorDeLeilao().para("Iphone")
                .constroi();


        leiloeiro.avalia(leilao);

        assertEquals(0, leiloeiro.getMediaLance(),0.00001);

    }

    @Test
    public void deveEncontrarOsTresMaioresLances() {


        Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
                .lance(joao, 500)
                .lance(jose, 400)
                .lance(maria, 300)
                .lance(jose,450)
                .constroi();


        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();
        assertEquals(3, maiores.size());
        assertThat(maiores, hasItems(
                new Lance(joao, 500),
                new Lance(jose, 450),
                new Lance(jose, 400)
        ));

        //assertEquals(500.0, maiores.get(0).getValor(),0.00001);
        //assertEquals(450.0, maiores.get(1).getValor(),0.00001);
        //assertEquals(400.0, maiores.get(2).getValor(),0.00001);

    }

    @Test
    public void deveEntenderLancesEmOrdemAleatoria() {

        Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
                .lance(joao, 200)
                .lance(jose, 300)
                .lance(maria, 100)
                .lance(joao, 500)
                .lance(jose, 450)
                .lance(maria, 250)
                .constroi();


        leiloeiro.avalia(leilao);


        assertEquals(500, leiloeiro.getMaiorLance(),0.00001);
        assertEquals(100, leiloeiro.getMenorLance(),0.00001);
        assertEquals(300, leiloeiro.getMediaLance(),0.00001);

    }

    @Test(expected = RuntimeException.class)
    public void naoDeveAvaliarLeiloesSemNenhumLanceDados() {

        Leilao leilao = new CriadorDeLeilao().para("Playstation3").constroi();

        leiloeiro.avalia(leilao);
    }



    @AfterClass
    public static void FimDosTestes() {
        System.out.println("Fim dos testes da classe AvaliadorTest");
    }

}
