import org.ProdutoBean;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProdutoBeanTest {

    @Test
    void testConstrutorDefault() {
        ProdutoBean produto = new ProdutoBean();
        assertEquals("", produto.getCodigo());
        assertEquals("", produto.getNome());
        assertEquals("", produto.getDescricao());
        assertEquals(0.0, produto.getValor());
        assertEquals("", produto.getEstado());
    }

    @Test
    void testConstrutorComParametros() {
        ProdutoBean produto = new ProdutoBean("123", "Produto",
                "Descricao", 10.0, "Novo");
        assertEquals("123", produto.getCodigo());
        assertEquals("Produto", produto.getNome());
        assertEquals("Descricao", produto.getDescricao());
        assertEquals(10.0, produto.getValor());
        assertEquals("Novo", produto.getEstado());

        assertThrows(IllegalArgumentException.class,
                    () -> new ProdutoBean("123", "Produto",
                        "Descricao", -10.0, "Novo"),
                    "Deveria lançar exceção para valor negativo");
        }


    @Test
    void testSettersAndGetters() {
        ProdutoBean produto = new ProdutoBean();
        produto.setCodigo("456");
        produto.setNome("Teste");
        produto.setDescricao("Desc");
        produto.setValor(20.0);
        produto.setEstado("Usado");

        assertEquals("456", produto.getCodigo());
        assertEquals("Teste", produto.getNome());
        assertEquals("Desc", produto.getDescricao());
        assertEquals(20.0, produto.getValor());
        assertEquals("Usado", produto.getEstado());
        assertThrows(IllegalArgumentException.class,
                    () -> produto.setValor(-10.0),
                    "Deveria lançar exceção para valor negativo");



    }

}