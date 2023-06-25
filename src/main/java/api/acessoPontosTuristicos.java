package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import modelo.BarERestaurante;
import modelo.CentroDeCompras;
import modelo.FeiraLivre;
import modelo.Hotel;
import modelo.MercadoPublico;
import modelo.Museu;
import modelo.Ponte;
import modelo.Teatro;
import modelo.touristSpot;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

/**
 *
 * @author euluc
 */
//TO DO - A comparação != null ta servindo de nada, trocar ela
//Usar o mesmo mecanismo da conversão de endereço para latitude e longitude para descobrir a latitude e longitude dos baresERestaurantes ou apagar eles da lista
//Testar em todos os "gets" se a latitude ou longitude ta retornando nulo
//Talvez criar uma classe abstrata de requisição get
@ManagedBean(name = "pontosTuristicos")
@ViewScoped
public class acessoPontosTuristicos implements Serializable {


    //Variáveis usadas para dar informações ao modal
    int currentID;
    touristSpot currentTouristSpot;

    //Variáveis da requisição para a API
    String currentAPIURL;
    URL url;
    HttpURLConnection conn;
    int responseCode;

   
    BufferedReader reader;
    StringBuilder response;

    //Variáveis de controle do filtro
    boolean isBarERestauranteFiltered;
    boolean isCentroDeComprasFiltered;
    boolean isFeiraLiveFiltered;
    boolean isHotelFiltered;
    boolean isMercadoPublicoFiltered;
    boolean isMuseuFiltered;
    boolean isPonteFiltered;
    boolean isTeatroFiltered;

    //Listas com as atrações
    List<Museu> museus;
    List<Teatro> teatros;
    List<MercadoPublico> mercadosPublicos;
    List<FeiraLivre> feirasLivres;
    List<Ponte> pontes;
    List<CentroDeCompras> centrosDeCompras;
    List<Hotel> hoteis;
    List<BarERestaurante> baresERestaurantes;
    List<touristSpot> allPontosTuristicos;
    
    //Variável de controle da ordenação
    boolean isOrdenacaoActive;

//    @ManagedProperty("#{acessoAPIGeocoding}")
//    acessoAPIGeocoding geocod;
    
    
    @PostConstruct
    public void init() {

        isBarERestauranteFiltered = true;
        isCentroDeComprasFiltered = true;
        isFeiraLiveFiltered = true;
        isHotelFiltered = true;
        isMercadoPublicoFiltered = true;
        isMuseuFiltered = true;
        isPonteFiltered = true;
        isTeatroFiltered = true;
        
        isOrdenacaoActive = false;

        this.currentTouristSpot = null;

        museus = new ArrayList<>();
        updateMuseus();

        teatros = new ArrayList<>();
        updateTeatros();

        mercadosPublicos = new ArrayList<>();
        updateMercadosPublicos();

        feirasLivres = new ArrayList<>();
//        updateFeirasLivres();

        pontes = new ArrayList<>();
        updatePontes();

        centrosDeCompras = new ArrayList<>();
        updateCentrosDeCompras();

        hoteis = new ArrayList<>();
        updateHoteis();

        baresERestaurantes = new ArrayList<>();
//        updateBaresERestaurantes();

        allPontosTuristicos = new ArrayList<>();
        updateAllPontosTuristicos();

        FacesContext facesContext = FacesContext.getCurrentInstance();
        UIViewRoot viewRoot = facesContext.getViewRoot();
        viewRoot.getViewMap().put("lista", allPontosTuristicos);

    }

    public acessoPontosTuristicos() {
        this.currentTouristSpot = null;
    }

    public void connectURL() {
        try {
            url = new URL(currentAPIURL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            responseCode = conn.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getJSON() {
        JSONObject jsonObject = null;
        try {
            if (responseCode == HttpURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();

                jsonObject = new JSONObject(response.toString()).getJSONObject("result");
            } else {
                System.out.println("API Request Failed. Response Code: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException ex) {
            Logger.getLogger(acessoPontosTuristicos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return jsonObject;
    }

    public JSONArray JSONToList(JSONObject currentJSON, String key) throws JSONException {
        JSONArray currentJSONArray = null;
        currentJSONArray = currentJSON.getJSONArray(key);
        return currentJSONArray;
    }

    //Usado no getBaresERestaurantes
//    public Double[] getLatELongBaseadoNoEndereco(String endereco) {
//        Double[] currentLatLong = new Double[2];
//        System.out.println("\n\n\n\n\n\n\n " + endereco + "\n\n\n\n\n\n\n\n\n");
//        currentLatLong = geocod.getLatLong(endereco);
//        return currentLatLong;
//    }
    public void updateMuseus() {
        currentAPIURL = "http://dados.recife.pe.gov.br/api/3/action/datastore_search?resource_id=97ab18da-f940-43b1-b0d4-a9e93e90bed5";
        connectURL();
        JSONObject currentResponse = getJSON();
        JSONArray arrayMuseus = null;
        if (currentResponse != null) {
            try {
                arrayMuseus = JSONToList(currentResponse, "records");
            } catch (JSONException ex) {
                Logger.getLogger(acessoPontosTuristicos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (arrayMuseus != null) {
            for (int i = 0; i < arrayMuseus.length(); i++) {
                try {
                    // Obter o objeto JSON correspondente ao registro atual
                    JSONObject currentMuseu = arrayMuseus.getJSONObject(i);

                    if (currentMuseu.getString("latitude").equals("null") || currentMuseu.getString("longitude").equals("null")) {
                        continue;
                    }
                    // Extrair os valores do registro e criar um objeto Museu
                    int _id = currentMuseu.getInt("_id");
                    String nome = currentMuseu.getString("nome");
                    String descricao = currentMuseu.getString("descricao");
                    String bairro = currentMuseu.getString("bairro");
                    String logradouro = currentMuseu.getString("logradouro");
                    Double latitude = Double.parseDouble(currentMuseu.getString("latitude"));
                    Double longitude = Double.parseDouble(currentMuseu.getString("longitude"));
                    String telefone = currentMuseu.getString("telefone");
                    String site = currentMuseu.getString("site");

                    Museu museu = new Museu(_id, nome, descricao, bairro, logradouro, latitude, longitude, telefone, site);

                    // Adicionar o objeto à lista
                    museus.add(museu);
                } catch (JSONException ex) {
                    Logger.getLogger(acessoPontosTuristicos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void updateTeatros() {
        currentAPIURL = "http://dados.recife.pe.gov.br/api/3/action/datastore_search?resource_id=16d45f07-1fab-4b8c-95d1-dbf555b6f913";
        connectURL();
        JSONObject currentResponse = getJSON();
        JSONArray arrayTeatro = null;

        if (currentResponse != null) {
            try {
                arrayTeatro = JSONToList(currentResponse, "records");
            } catch (JSONException ex) {
                Logger.getLogger(acessoPontosTuristicos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (arrayTeatro != null) {
            for (int i = 0; i < arrayTeatro.length(); i++) {
                try {
                    // Obter o objeto JSON correspondente ao registro atual
                    JSONObject currentTeatro = arrayTeatro.getJSONObject(i);
                    if (currentTeatro.getString("latitude").equals("null") || currentTeatro.getString("longitude").equals("null")) {
                        continue;
                    }
                    // Extrair os valores do registro e criar um objeto Teatro
                    int _id = currentTeatro.getInt("_id");
                    String nome = currentTeatro.getString("nome");
                    String descricao = currentTeatro.getString("descricao");
                    String bairro = currentTeatro.getString("bairro");
                    String logradouro = currentTeatro.getString("logradouro");
                    String telefone = currentTeatro.getString("Telefone");
                    Double latitude = Double.parseDouble(currentTeatro.getString("latitude"));
                    Double longitude = Double.parseDouble(currentTeatro.getString("longitude"));
                    Teatro teatro = new Teatro(_id, nome, descricao, bairro, logradouro, telefone, latitude, longitude);

                    // Adicionar o objeto à lista de Teatro
                    teatros.add(teatro);
                } catch (JSONException ex) {
                    Logger.getLogger(acessoPontosTuristicos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void updateMercadosPublicos() {
        currentAPIURL = "http://dados.recife.pe.gov.br/api/3/action/datastore_search?resource_id=40d97dcb-4a14-4365-bced-8555998a498d";
        connectURL();
        JSONObject currentResponse = getJSON();
        JSONArray arrayMercadoPublico = null;

        if (currentResponse != null) {
            try {
                arrayMercadoPublico = JSONToList(currentResponse, "records");
            } catch (JSONException ex) {
                Logger.getLogger(acessoPontosTuristicos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (arrayMercadoPublico != null) {
            for (int i = 0; i < arrayMercadoPublico.length(); i++) {
                try {
                    // Obter o objeto JSON correspondente ao registro atual
                    JSONObject currentMercadoPublico = arrayMercadoPublico.getJSONObject(i);

                    if (currentMercadoPublico.getString("latitude").equals("null") || currentMercadoPublico.getString("longitude").equals("null")) {
                        continue;
                    }
                    // Extrair os valores do registro e criar um objeto MercadoPublico
                    int _id = currentMercadoPublico.getInt("_id");
                    String nome = currentMercadoPublico.getString("nome");
                    String descricao = currentMercadoPublico.getString("descricao");
                    String bairro = currentMercadoPublico.getString("bairro");
                    Double latitude = Double.parseDouble(currentMercadoPublico.getString("latitude"));
                    Double longitude = Double.parseDouble(currentMercadoPublico.getString("longitude"));
                    MercadoPublico mercadoPublico = new MercadoPublico(_id, nome, descricao, bairro, latitude, longitude);

                    // Adicionar o objeto à lista de MercadoPublico
                    mercadosPublicos.add(mercadoPublico);
                } catch (JSONException ex) {
                    Logger.getLogger(acessoPontosTuristicos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void updateFeirasLivres() {
        currentAPIURL = "http://dados.recife.pe.gov.br/api/3/action/datastore_search?resource_id=dc6b3d07-3124-453d-b11e-72364cced7aa";
        connectURL();
        JSONObject currentResponse = getJSON();
        JSONArray arrayFeiraLivre = null;

        if (currentResponse != null) {
            try {
                arrayFeiraLivre = JSONToList(currentResponse, "records");
            } catch (JSONException ex) {
                Logger.getLogger(acessoPontosTuristicos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (arrayFeiraLivre != null) {
            for (int i = 0; i < arrayFeiraLivre.length(); i++) {
                try {
                    // Obter o objeto JSON correspondente ao registro atual
                    JSONObject currentFeiraLivre = arrayFeiraLivre.getJSONObject(i);

                    if (currentFeiraLivre.getString("Latitude").equals("null") || currentFeiraLivre.getString("Longitude").equals("null")) {
                        continue;
                    }

                    // Extrair os valores do registro e criar um objeto FeiraLivre
                    int _id = currentFeiraLivre.getInt("_id");
                    String nome = currentFeiraLivre.getString("Nome");
                    String localizacao = currentFeiraLivre.getString("Localização");
                    System.out.println(localizacao);
                    String dias = currentFeiraLivre.getString("Dias");
                    String horario = currentFeiraLivre.getString("Horário");
                    String observacao = currentFeiraLivre.getString("Observação");
                    Double latitude = Double.parseDouble(currentFeiraLivre.getString("Latitude"));
                    Double longitude = Double.parseDouble(currentFeiraLivre.getString("Longitude"));

                    FeiraLivre feiraLivre = new FeiraLivre(_id, nome, localizacao, dias, horario, observacao, latitude, longitude);

                    // Adicionar o objeto à lista de FeiraLivre
                    feirasLivres.add(feiraLivre);
                } catch (JSONException ex) {
                    Logger.getLogger(acessoPontosTuristicos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void updatePontes() {
        currentAPIURL = "http://dados.recife.pe.gov.br/api/3/action/datastore_search?resource_id=61fcd098-058b-4bb1-9918-f46cfbac3261";
        connectURL();
        JSONObject currentResponse = getJSON();
        JSONArray arrayPontes = null;

        if (currentResponse != null) {
            try {
                arrayPontes = JSONToList(currentResponse, "records");
            } catch (JSONException ex) {
                Logger.getLogger(acessoPontosTuristicos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (arrayPontes != null) {
            for (int i = 0; i < arrayPontes.length(); i++) {
                try {
                    // Obter o objeto JSON correspondente ao registro atual
                    JSONObject currentPonte = arrayPontes.getJSONObject(i);

                    if (currentPonte.getString("latitude").equals("null") || currentPonte.getString("longitude").equals("null")) {
                        continue;
                    }
                    // Extrair os valores do registro e criar um objeto Ponte
                    int _id = currentPonte.getInt("_id");
                    String nome = currentPonte.getString("Nome");
                    String descricao = currentPonte.getString("descricao");
                    String bairro = currentPonte.getString("bairro");
                    Double latitude = Double.parseDouble(currentPonte.getString("latitude"));
                    Double longitude = Double.parseDouble(currentPonte.getString("longitude"));

                    Ponte ponte = new Ponte(_id, nome, descricao, bairro, latitude, longitude);

                    // Adicionar o objeto à lista
                    pontes.add(ponte);
                } catch (JSONException ex) {
                    Logger.getLogger(acessoPontosTuristicos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void updateCentrosDeCompras() {
        currentAPIURL = "http://dados.recife.pe.gov.br/api/3/action/datastore_search?resource_id=81f406de-8468-4bb9-b038-0956d6684acd";
        connectURL();
        JSONObject currentResponse = getJSON();
        JSONArray arrayCentroDeCompras = null;

        if (currentResponse != null) {
            try {
                arrayCentroDeCompras = JSONToList(currentResponse, "records");
            } catch (JSONException ex) {
                Logger.getLogger(acessoPontosTuristicos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (arrayCentroDeCompras != null) {
            for (int i = 0; i < arrayCentroDeCompras.length(); i++) {
                try {
                    // Obter o objeto JSON correspondente ao registro atual
                    JSONObject currentCentroDeCompras = arrayCentroDeCompras.getJSONObject(i);

                    if (currentCentroDeCompras.getString("latitude").equals("null") || currentCentroDeCompras.getString("longitude").equals("null")) {
                        continue;
                    }
                    // Extrair os valores do registro e criar um objeto CentroDeCompras
                    int _id = currentCentroDeCompras.getInt("_id");
                    String nome = currentCentroDeCompras.getString("nome");
                    String descricao = currentCentroDeCompras.getString("descricao");
                    String bairro = currentCentroDeCompras.getString("bairro");
                    String logradouro = currentCentroDeCompras.getString("logradouro");
                    Double latitude = Double.parseDouble(currentCentroDeCompras.getString("latitude"));
                    Double longitude = Double.parseDouble(currentCentroDeCompras.getString("longitude"));
                    String telefone = currentCentroDeCompras.getString("telefone");
                    String site = currentCentroDeCompras.getString("site");
                    String funcionamento = currentCentroDeCompras.getString("funcionamento");
                    String funcionamentoDomingo = currentCentroDeCompras.getString("funcionamentoDomingo");

                    CentroDeCompras centroDeCompras = new CentroDeCompras(_id, nome, descricao, bairro, logradouro, latitude, longitude, telefone, site, funcionamento, funcionamentoDomingo);

                    // Adicionar o objeto à lista
                    centrosDeCompras.add(centroDeCompras);
                } catch (JSONException ex) {
                    Logger.getLogger(acessoPontosTuristicos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void updateHoteis() {
        currentAPIURL = "http://dados.recife.pe.gov.br/api/3/action/datastore_search?resource_id=0d8fb090-2863-4d51-9b21-baae4bae5a11";
        connectURL();
        JSONObject currentResponse = getJSON();
        JSONArray arrayHoteis = null;

        if (currentResponse != null) {
            try {
                arrayHoteis = JSONToList(currentResponse, "records");
            } catch (JSONException ex) {
                Logger.getLogger(acessoPontosTuristicos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (arrayHoteis != null) {
            for (int i = 0; i < arrayHoteis.length(); i++) {
                try {
                    // Obter o objeto JSON correspondente ao registro atual
                    JSONObject currentHotel = arrayHoteis.getJSONObject(i);
                    if (currentHotel.getString("latitude").equals("null") || currentHotel.getString("longitude").equals("null")) {
                        continue;
                    }
                    // Extrair os valores do registro e criar um objeto Hoteis
                    int _id = currentHotel.getInt("_id");
                    String nome = currentHotel.getString("nome");
                    String endereco = currentHotel.getString("endereco");
                    String telefone = currentHotel.getString("telefone");
                    String site = currentHotel.getString("site");
                    Double latitude = Double.parseDouble(currentHotel.getString("latitude"));
                    Double longitude = Double.parseDouble(currentHotel.getString("longitude"));

                    Hotel hotel = new Hotel(_id, nome, endereco, telefone, site, latitude, longitude);

                    // Adicionar o objeto à lista
                    hoteis.add(hotel);
                } catch (JSONException ex) {
                    Logger.getLogger(acessoPontosTuristicos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void updateBaresERestaurantes() {
        currentAPIURL = "http://dados.recife.pe.gov.br/api/3/action/datastore_search?resource_id=d85bf4e3-637e-4e1b-9b03-970dca4403c7";

        connectURL();
        JSONObject currentResponse = getJSON();
        JSONArray arrayBaresERestaurantes = null;

        if (currentResponse != null) {
            try {
                arrayBaresERestaurantes = JSONToList(currentResponse, "records");
            } catch (JSONException ex) {
                Logger.getLogger(acessoPontosTuristicos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (arrayBaresERestaurantes != null) {
            for (int i = 0; i < arrayBaresERestaurantes.length(); i++) {
                try {
                    // Obter o objeto JSON correspondente ao registro atual
                    JSONObject currentBarERestaurante = arrayBaresERestaurantes.getJSONObject(i);

//                if(currentBarERestaurante.getString("latitude").equals("null") || currentBarERestaurante.getString("longitude").equals("null")) {
//                    continue;
//                }
// Extrair os valores do registro e criar um objeto BaresERestaurantes
                    int _id = currentBarERestaurante.getInt("_id");
                    String nome = new String(currentBarERestaurante.getString("nome").getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
                    String endereco = currentBarERestaurante.getString("endereco");
                    String telefone = currentBarERestaurante.getString("telefone");
                    String especialidade = currentBarERestaurante.getString("especialidade");
                    String site = currentBarERestaurante.getString("site");
                    String email = currentBarERestaurante.getString("email");
                    
                    BarERestaurante barERestaurante = new BarERestaurante(_id, nome, endereco, telefone, especialidade, site, email);
                    
                    
//                    Double[] currentLatLong = getLatELongBaseadoNoEndereco(endereco);
//                    BarERestaurante barERestaurante = new BarERestaurante(_id, nome, endereco, telefone, especialidade, site, email, currentLatLong[0], currentLatLong[1]);

// Adicionar o objeto à lista
                    baresERestaurantes.add(barERestaurante);
                } catch (JSONException ex) {
                    Logger.getLogger(acessoPontosTuristicos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void updateAllPontosTuristicos() {
//        allPontosTuristicos.addAll(baresERestaurantes);
        allPontosTuristicos.addAll(centrosDeCompras);
        allPontosTuristicos.addAll(hoteis);
        allPontosTuristicos.addAll(mercadosPublicos);
        allPontosTuristicos.addAll(museus);
        allPontosTuristicos.addAll(pontes);
        allPontosTuristicos.addAll(teatros);
//        allPontosTuristicos.addAll(feirasLivres);
    }

    public void updateSelectedTouristSpot(int id, String tipoTouristSpot) {
        System.out.println("Chamou");
        System.out.println(id);
        System.out.println(tipoTouristSpot);
        switch (tipoTouristSpot) {
            case "Bar E Restaurante":
                currentTouristSpot = baresERestaurantes.get(id - 1);

                break;
            case "Centro De Compras":
                currentTouristSpot = centrosDeCompras.get(id - 1);

                break;
            case "Feira Livre":
                currentTouristSpot = feirasLivres.get(id - 1);

                break;
            case "Hotel":
                currentTouristSpot = hoteis.get(id - 1);

                break;
            case "Mercado Publico":
                currentTouristSpot = mercadosPublicos.get(id - 1);

                break;
            case "Museu":
                currentTouristSpot = museus.get(id - 1);

                break;
            case "Ponte":
                currentTouristSpot = pontes.get(id - 1);

                break;
            case "Teatro":
                currentTouristSpot = teatros.get(id - 1);

                break;
        }

    }

    public boolean isInstanceOf(Object objeto, Class<?> classe) {
        return classe.isInstance(objeto);
    }

    public Museu convertToMuseu(Object objeto) {
        if (objeto instanceof Museu) {
            return (Museu) objeto;
        }
        return null;
    }

    public BarERestaurante convertToBarERestaurante(Object objeto) {
        if (objeto instanceof BarERestaurante) {
            return (BarERestaurante) objeto;
        }
        return null;
    }

    public CentroDeCompras convertToCentroDeCompras(Object objeto) {
        if (objeto instanceof CentroDeCompras) {
            return (CentroDeCompras) objeto;
        }
        return null;
    }

    public FeiraLivre convertToFeiraLivre(Object objeto) {
        if (objeto instanceof FeiraLivre) {
            return (FeiraLivre) objeto;
        }
        return null;
    }

    public Hotel convertToHotel(Object objeto) {
        if (objeto instanceof Hotel) {
            return (Hotel) objeto;
        }
        return null;
    }

    public MercadoPublico convertToMercadoPublico(Object objeto) {
        if (objeto instanceof MercadoPublico) {
            return (MercadoPublico) objeto;
        }
        return null;
    }

    public Ponte convertToPonte(Object objeto) {
        if (objeto instanceof Ponte) {
            return (Ponte) objeto;
        }
        return null;
    }

    public Teatro convertToTeatro(Object objeto) {
        if (objeto instanceof Teatro) {
            return (Teatro) objeto;
        }
        return null;
    }

    //Gets and Setters
    public boolean isIsBarERestauranteFiltered() {
        return isBarERestauranteFiltered;
    }

    public void setIsBarERestauranteFiltered(boolean isBarERestauranteFiltered) {
        this.isBarERestauranteFiltered = isBarERestauranteFiltered;
    }

    public boolean isIsCentroDeComprasFiltered() {
        return isCentroDeComprasFiltered;
    }

    public void setIsCentroDeComprasFiltered(boolean isCentroDeComprasFiltered) {
        this.isCentroDeComprasFiltered = isCentroDeComprasFiltered;
    }

    public boolean isIsFeiraLiveFiltered() {
        return isFeiraLiveFiltered;
    }

    public void setIsFeiraLiveFiltered(boolean isFeiraLiveFiltered) {
        this.isFeiraLiveFiltered = isFeiraLiveFiltered;
    }

    public boolean isIsHotelFiltered() {
        return isHotelFiltered;
    }

    public void setIsHotelFiltered(boolean isHotelFiltered) {
        this.isHotelFiltered = isHotelFiltered;
    }

    public boolean isIsMercadoPublicoFiltered() {
        return isMercadoPublicoFiltered;
    }

    public void setIsMercadoPublicoFiltered(boolean isMercadoPublicoFiltered) {
        this.isMercadoPublicoFiltered = isMercadoPublicoFiltered;
    }

    public boolean isIsMuseuFiltered() {
        return isMuseuFiltered;
    }

    public void setIsMuseuFiltered(boolean isMuseuFiltered) {
        this.isMuseuFiltered = isMuseuFiltered;
    }

    public boolean isIsPonteFiltered() {
        return isPonteFiltered;
    }

    public void setIsPonteFiltered(boolean isPonteFiltered) {
        this.isPonteFiltered = isPonteFiltered;
    }

    public boolean isIsTeatroFiltered() {
        return isTeatroFiltered;
    }

    public void setIsTeatroFiltered(boolean isTeatroFiltered) {
        this.isTeatroFiltered = isTeatroFiltered;
    }

    public boolean isIsOrdenacaoActive() {
        return isOrdenacaoActive;
    }

    public void setIsOrdenacaoActive(boolean isOrdenacaoActive) {
        this.isOrdenacaoActive = isOrdenacaoActive;
    }

    public List<Museu> getMuseus() {
        return museus;
    }

    public void setMuseus(List<Museu> museus) {
        this.museus = museus;
    }

    public List<Teatro> getTeatros() {
        return teatros;
    }

    public void setTeatros(List<Teatro> teatros) {
        this.teatros = teatros;
    }

    public List<MercadoPublico> getMercadosPublicos() {
        return mercadosPublicos;
    }

    public void setMercadosPublicos(List<MercadoPublico> mercadosPublicos) {
        this.mercadosPublicos = mercadosPublicos;
    }

    public List<FeiraLivre> getFeirasLivres() {
        return feirasLivres;
    }

    public void setFeirasLivres(List<FeiraLivre> feirasLivres) {
        this.feirasLivres = feirasLivres;
    }

    public List<Ponte> getPontes() {
        return pontes;
    }

    public void setPontes(List<Ponte> pontes) {
        this.pontes = pontes;
    }

    public List<CentroDeCompras> getCentrosDeCompras() {
        return centrosDeCompras;
    }

    public void setCentrosDeCompras(List<CentroDeCompras> centrosDeCompras) {
        this.centrosDeCompras = centrosDeCompras;
    }

    public List<Hotel> getHoteis() {
        return hoteis;
    }

    public void setHoteis(List<Hotel> hoteis) {
        this.hoteis = hoteis;
    }

    public List<BarERestaurante> getBaresERestaurantes() {
        return baresERestaurantes;
    }

    public void setBaresERestaurantes(List<BarERestaurante> baresERestaurantes) {
        this.baresERestaurantes = baresERestaurantes;
    }

    public List<touristSpot> getAllPontosTuristicos() {
        return allPontosTuristicos;
    }

    public void setAllPontosTuristicos(List<touristSpot> allPontosTuristicos) {
        this.allPontosTuristicos = allPontosTuristicos;
    }

    public int getCurrentID() {
        return currentID;
    }

    public void setCurrentID(int currentID) {
        this.currentID = currentID;
    }

    public touristSpot getCurrentTouristSpot() {
        return currentTouristSpot;
    }

    public void setCurrentTouristSpot(touristSpot currentTouristSpot) {
        this.currentTouristSpot = currentTouristSpot;
    }
    

//    public acessoAPIGeocoding getGeocod() {
//        return geocod;
//    }
//
//    public void setGeocod(acessoAPIGeocoding geocod) {
//        this.geocod = geocod;
//    }

    public void setCurrentAPIURL(String httpdadosrecifepegovbrapi3actiondatastore) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
     public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

}
