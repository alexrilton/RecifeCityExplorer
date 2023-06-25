package Teste_API;

import api.acessoPontosTuristicos;
import static org.junit.Assert.*;
import org.junit.Test;


public class filtroAPITest {
// Cria uma instância do objeto pontosTuristicos
        acessoPontosTuristicos pontosTuristicos = new acessoPontosTuristicos();
    
    
    @Test
    public void testSelectBooleanCheckbox() {
   
        // Verifica o valor inicial da propriedade isFeiraLiveFiltered
        assertFalse(pontosTuristicos.isIsFeiraLiveFiltered());

        // Simula a seleção da checkbox
        pontosTuristicos.setIsFeiraLiveFiltered(true);

        // Verifica se o valor da propriedade isFeiraLiveFiltered foi atualizado corretamente
        assertTrue(pontosTuristicos.isIsFeiraLiveFiltered());
        
        // Verifice o valor inicial da propriedade isBarERestauranteFiltered
        assertFalse(pontosTuristicos.isIsBarERestauranteFiltered());

        // Simula a seleção da checkbox
        pontosTuristicos.setIsBarERestauranteFiltered(true);

        // Verifica se o valor da propriedade isBarERestauranteFiltered foi atualizado corretamente
        assertTrue(pontosTuristicos.isIsBarERestauranteFiltered());

        // Repete os passos anteriores para cada variável

        assertFalse(pontosTuristicos.isIsCentroDeComprasFiltered());
        pontosTuristicos.setIsCentroDeComprasFiltered(true);
        assertTrue(pontosTuristicos.isIsCentroDeComprasFiltered());

        assertFalse(pontosTuristicos.isIsHotelFiltered());
        pontosTuristicos.setIsHotelFiltered(true);
        assertTrue(pontosTuristicos.isIsHotelFiltered());

        assertFalse(pontosTuristicos.isIsMercadoPublicoFiltered());
        pontosTuristicos.setIsMercadoPublicoFiltered(true);
        assertTrue(pontosTuristicos.isIsMercadoPublicoFiltered());

        assertFalse(pontosTuristicos.isIsMuseuFiltered());
        pontosTuristicos.setIsMuseuFiltered(true);
        assertTrue(pontosTuristicos.isIsMuseuFiltered());

        assertFalse(pontosTuristicos.isIsPonteFiltered());
        pontosTuristicos.setIsPonteFiltered(true);
        assertTrue(pontosTuristicos.isIsPonteFiltered());

        assertFalse(pontosTuristicos.isIsTeatroFiltered());
        pontosTuristicos.setIsTeatroFiltered(true);
        assertTrue(pontosTuristicos.isIsTeatroFiltered());
    }
}