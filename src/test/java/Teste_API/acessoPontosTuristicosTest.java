package Teste_API;

import api.acessoPontosTuristicos;
import java.net.HttpURLConnection;
import org.junit.Test;
import static org.junit.Assert.*;

public class acessoPontosTuristicosTest {

    HttpURLConnection conn;
    int responseCode;
    
    // Cria uma instância do objeto acessoPontosTuristicos
      acessoPontosTuristicos testeAPI = new acessoPontosTuristicos();
        

    @Test
    public void testUpdateHoteis() {
       
        // Tenta conexão com API 
        testeAPI.setCurrentAPIURL("http://dados.recife.pe.gov.br/api/3/action/datastore_search?resource_id=0d8fb090-2863-4d51-9b21-baae4bae5a11");
        
        testeAPI.connectURL();
        
        // Verifica se houve resposta
        assertTrue(testeAPI.getResponseCode()==200);
    }
    
    @Test
    public void testUpdateMuseus() {
       
        // Tenta conexão com API 
        testeAPI.setCurrentAPIURL("http://dados.recife.pe.gov.br/api/3/action/datastore_search?resource_id=97ab18da-f940-43b1-b0d4-a9e93e90bed5");
        testeAPI.connectURL();
        
        // Verifica se houve resposta
        assertTrue(testeAPI.getResponseCode()==200);
    }
    
    @Test
    public void testUpdateTeatros() {
       
        // Tenta conexão com API 
        testeAPI.setCurrentAPIURL("http://dados.recife.pe.gov.br/api/3/action/datastore_search?resource_id=16d45f07-1fab-4b8c-95d1-dbf555b6f913");
        testeAPI.connectURL();
        
        // Verifica se houve resposta 
        assertTrue(testeAPI.getResponseCode()==200);
    }
    
    @Test
    public void testUpdateMercadosPublicos() {
       
        // Tenta conexão com API 
        testeAPI.setCurrentAPIURL("http://dados.recife.pe.gov.br/api/3/action/datastore_search?resource_id=40d97dcb-4a14-4365-bced-8555998a498d");
        testeAPI.connectURL();
        
        // Verifica se houve resposta
        assertTrue(testeAPI.getResponseCode()==200);
    }
    
    
    @Test
    public void testUpdateFeirasLivres() {
       
        // Tenta conexão com API 
        testeAPI.setCurrentAPIURL("http://dados.recife.pe.gov.br/api/3/action/datastore_search?resource_id=dc6b3d07-3124-453d-b11e-72364cced7aa");
        testeAPI.connectURL();
        
        // Verifica se houve resposta
        assertTrue(testeAPI.getResponseCode()==200);
    }
    
    
    @Test
    public void testUpdatePontes() {
       
        // Tenta conexão com API 
        testeAPI.setCurrentAPIURL("http://dados.recife.pe.gov.br/api/3/action/datastore_search?resource_id=61fcd098-058b-4bb1-9918-f46cfbac3261");
        testeAPI.connectURL();
        
        // Verifica se houve resposta
        assertTrue(testeAPI.getResponseCode()==200);
    }
    
    
    @Test
    public void testUpdateCentrosDeCompras() {
       
        // Tenta conexão com API 
        testeAPI.setCurrentAPIURL("http://dados.recife.pe.gov.br/api/3/action/datastore_search?resource_id=81f406de-8468-4bb9-b038-0956d6684acd");
        testeAPI.connectURL();
        
        // Verifica se houve resposta
        assertTrue(testeAPI.getResponseCode()==200);
    }
    
    public void testUpdateBaresERestaurantes() {
       
        // Tenta conexão com API 
        testeAPI.setCurrentAPIURL("http://dados.recife.pe.gov.br/api/3/action/datastore_search?resource_id=d85bf4e3-637e-4e1b-9b03-970dca4403c7");
        testeAPI.connectURL();
        
        // Verifica se houve resposta
        assertTrue(testeAPI.getResponseCode()==200);
    }
    
}