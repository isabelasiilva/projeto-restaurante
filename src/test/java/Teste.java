import org.example.cardapio.ComCheddar;
import org.example.cardapio.ItemCardapio;
import org.example.cardapio.Lanche;
import org.example.cardapio.LancheFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Teste {
    @Test
    void doisMaisDoisSaoQuatro() {
        assertEquals(4, 2 + 2);
    }

    @Test
    void xBaconComCheddarCusta26() {
        ItemCardapio lanche = new ComCheddar(LancheFactory.xBacon());
        assertEquals(26.0, lanche.getPreco(), 0.001);
    }

    @Test
    void lancheNaoDeveAceitarPrecoNegativo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Lanche("X-Burger", -10.0);
        });
    }
}
