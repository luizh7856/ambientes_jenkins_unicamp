import org.AnuncioBean;
import org.ProdutoBean;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.net.URL;
import java.net.MalformedURLException;


public class AnuncioBeanTest {
    @Test
    void testConstrutorDefault(){
        AnuncioBean anuncio = new AnuncioBean();
        assertTrue(anuncio.getProduto() instanceof ProdutoBean);
        assertTrue(anuncio.getFotosUrl().isEmpty());
        assertEquals(0.0, anuncio.getDesconto(), "O valor do desconto está errado");
    }

    @Test
    void testConstrutorComParametros() throws Exception {
        ProdutoBean produto = new ProdutoBean("123", "Produto Teste",
                "Descricao teste", 100.0, "Novo");
        ArrayList<URL> fotosUrl = new ArrayList<>();
        fotosUrl.add(new URL("https://www.exemplo.com/foto1.jpg"));
        fotosUrl.add(new URL("https://www.exemplo.com/foto2.jpg"));
        Double desconto = 0.1;

        AnuncioBean anuncio = new AnuncioBean(produto, fotosUrl, desconto);

        assertEquals(produto, anuncio.getProduto(), "Produto deveria ser inicializado corretamente");
        assertEquals(fotosUrl, anuncio.getFotosUrl(), "Lista de fotos deveria ser inicializada corretamente");
        assertEquals(desconto, anuncio.getDesconto(), "Desconto deveria ser inicializado corretamente");
        assertThrows(IllegalArgumentException.class,
                () -> new AnuncioBean(produto, fotosUrl, -0.1),
                "Deveria lançar exceção para valor negativo");
    }


    @Test
    void testSettersAndGetters() {

        ArrayList<URL> fotosUrl = new ArrayList<>();
        try {
            fotosUrl.add(new URL("https://images.pexels.com/photos/607812/pexels-photo-607812.jpeg"));
            fotosUrl.add(new URL("https://www.consumerreports.org/content/dam/CRO-Images-2020/Electronics/02Feb"));
            fotosUrl.add(new URL("https://images.unsplash.com/photo-1511707171634-5f897ff02aa9"));
        } catch (MalformedURLException e) {
            fail("Erro na URL usada no teste");
        }

        AnuncioBean anuncio = new AnuncioBean();
        ProdutoBean produto = new ProdutoBean("123", "Produto", "Descricao", 10.0, "Novo");
        anuncio.setProduto(produto);
        anuncio.setDesconto(0.9);
        anuncio.setFotosUrl(fotosUrl);

        assertEquals(produto, anuncio.getProduto(), "O retorno do GetProduto está errado");
        assertEquals(0.9, anuncio.getDesconto(), "O valor do desconto está errado");
        assertFalse(anuncio.getFotosUrl().isEmpty());
        assertThrows(IllegalArgumentException.class,
                () -> anuncio.setDesconto(-0.1) ,
                "Deveria lançar exceção para valor negativo");
    }

    @Test
    void testGetValorComDesconto() {
        AnuncioBean anuncio = new AnuncioBean();
        ProdutoBean produto = new ProdutoBean("123", "Produto", "Descricao", 100.0, "Novo");
        anuncio.setProduto(produto);
        anuncio.setDesconto(0.2);
        assertEquals(80,anuncio.getValor(), "O valor com desconto está errado");
    }
}
