import org.AnuncianteBean;
import org.AnuncioBean;
import org.ProdutoBean;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.net.URL;


public class AnuncianteBeanTeste {
    @Test
    void testConstrutorDefault() {
        AnuncianteBean anunciante = new AnuncianteBean();
        assertEquals("", anunciante.getNome());
        assertEquals("", anunciante.getCPF());
        assertTrue(anunciante.getAnuncios().isEmpty());
    }

    @Test
    void testConstrutorComParametros() throws Exception {
        ProdutoBean produto = new ProdutoBean("001", "Celular",
                    "Smartphone top", 2000.0, "Novo");

        ArrayList<URL> fotos = new ArrayList<>();
        fotos.add(new URL("https://www.exemplo.com/celular1.jpg"));

        AnuncioBean anuncio = new AnuncioBean(produto, fotos, 0.15);

        ArrayList<AnuncioBean> anuncios = new ArrayList<>();
        anuncios.add(anuncio);

        AnuncianteBean anunciante = new AnuncianteBean("João da Silva", "12345678900", anuncios);

        assertEquals("João da Silva", anunciante.getNome(), "Nome deveria ser inicializado corretamente");
        assertEquals("12345678900", anunciante.getCPF(), "CPF deveria ser inicializado corretamente");
        assertEquals(anuncios, anunciante.getAnuncios(), "Lista de anúncios deveria ser inicializada corretamente");
        assertEquals(1, anunciante.getAnuncios().size(), "Lista de anúncios deveria conter 1 elemento");
        }

    @Test
    void testSettersAndGetters() {
        ArrayList<AnuncioBean> anuncios = new ArrayList<>();
        AnuncioBean anuncio1 = new AnuncioBean();
        AnuncioBean anuncio2 = new AnuncioBean();
        anuncios.add(anuncio1);
        anuncios.add(anuncio2);

        AnuncianteBean anunciante = new AnuncianteBean();
        anunciante.setNome("Joao");
        anunciante.setCPF("12345678900");
        anunciante.setAnuncios(anuncios);

        assertEquals("Joao", anunciante.getNome(), "O retorno do GetNome está errado");
        assertEquals("12345678900", anunciante.getCPF(), "O retorno do GetCPF está errado");
        assertEquals(anuncios, anunciante.getAnuncios(), "O retorno do GetAnuncios está errado");
    }


    private AnuncioBean criarAnuncio(double valor) throws Exception {
        ProdutoBean produto = new ProdutoBean("001", "Produto", "Descricao", valor, "Novo");
        ArrayList<URL> fotos = new ArrayList<>();
        fotos.add(new URL("https://www.exemplo.com/foto.jpg"));
        return new AnuncioBean(produto, fotos, 0.0);
    }

    @Test
    void testAddAnuncio() throws Exception {
        AnuncianteBean anunciante = new AnuncianteBean("João", "12345678900", new ArrayList<>());
        AnuncioBean anuncio = criarAnuncio(100.0);

        anunciante.addAnuncio(anuncio);

        assertEquals(1, anunciante.getAnuncios().size(), "Deveria conter 1 anúncio após adição");
        assertEquals(anuncio, anunciante.getAnuncios().get(0), "O anúncio adicionado deveria ser o mesmo");
    }

    @Test
    void testRemoveAnuncio() throws Exception {
        AnuncianteBean anunciante = new AnuncianteBean("João", "12345678900", new ArrayList<>());
        AnuncioBean anuncio1 = criarAnuncio(100.0);
        AnuncioBean anuncio2 = criarAnuncio(200.0);
        anunciante.addAnuncio(anuncio1);
        anunciante.addAnuncio(anuncio2);

        anunciante.removeAnuncio(0);

        assertEquals(1, anunciante.getAnuncios().size(), "Deveria conter 1 anúncio após remoção");
        assertEquals(anuncio2, anunciante.getAnuncios().get(0), "O anúncio restante deveria ser o segundo");

        assertThrows(IndexOutOfBoundsException.class,
                () ->  anunciante.removeAnuncio(5),
                "Deveria lançar exceção para índice inválido");

    }

    @Test
    void testValorMedioAnuncios() throws Exception {
        AnuncianteBean anunciante = new AnuncianteBean("João", "12345678900", new ArrayList<>());
        AnuncianteBean anuncianteVazio = new AnuncianteBean("Maria", "98765432100", new ArrayList<>());

        // adiciona anúncios
        anunciante.addAnuncio(criarAnuncio(100.0));
        anunciante.addAnuncio(criarAnuncio(200.0));
        anunciante.addAnuncio(criarAnuncio(300.0));

        assertEquals(200.0, anunciante.valorMedioAnuncios(), "A média dos anúncios deveria ser 200.0");
        assertEquals(0, anuncianteVazio.valorMedioAnuncios(), "A média dos anúncios deveria ser 0");
    }

}
