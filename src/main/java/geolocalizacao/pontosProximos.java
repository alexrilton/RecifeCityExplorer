/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geolocalizacao;

import api.acessoPontosTuristicos;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import modelo.Museu;
import org.primefaces.json.JSONException;

/**
 *
 * @author euluc
 */
public class pontosProximos {

    public static final double EARTH_RADIUS = 6371.0; // raio médio da Terra em km

    private Double currentLocalizacaoLat;
    private Double currentLocalizacaoLon;

    public pontosProximos() {
    }

    public pontosProximos(Double currentLocalizacaoLat, Double currentLocalizacaoLon) {
        this.currentLocalizacaoLat = currentLocalizacaoLat;
        this.currentLocalizacaoLon = currentLocalizacaoLon;
    }

    public Double getCurrentLocalizacaoLat() {
        return currentLocalizacaoLat;
    }

    public void setCurrentLocalizacaoLat(Double currentLocalizacaoLat) {
        this.currentLocalizacaoLat = currentLocalizacaoLat;
    }

    public Double getCurrentLocalizacaoLon() {
        return currentLocalizacaoLon;
    }

    public void setCurrentLocalizacaoLon(Double currentLocalizacaoLon) {
        this.currentLocalizacaoLon = currentLocalizacaoLon;
    }

    //Calculo de Vincenty
    public double calcularDistancia(double lat1, double lon1) {
        double phi1 = Math.toRadians(lat1);
        double phi2 = Math.toRadians(currentLocalizacaoLat);
        double lambda1 = Math.toRadians(lon1);
        double lambda2 = Math.toRadians(currentLocalizacaoLon);

        double deltaLambda = lambda2 - lambda1;

        double numerator = Math.sqrt(Math.pow(Math.cos(phi2) * Math.sin(deltaLambda), 2)
                + Math.pow(Math.cos(phi1) * Math.sin(phi2) - Math.sin(phi1) * Math.cos(phi2) * Math.cos(deltaLambda), 2));
        double denominator = Math.sin(phi1) * Math.sin(phi2) + Math.cos(phi1) * Math.cos(phi2) * Math.cos(deltaLambda);

        double centralAngle = Math.atan2(numerator, denominator);

        double distance = EARTH_RADIUS * centralAngle;

        return distance;
    }

    //Depois colocar para ser uma lista de TouristSpot
    public List<Museu> ordenarLista(List<Museu> currentLista) {
//        Collections.sort(currentLista, new Comparator<touristSpot>() {
//            @Override
//            public int compare(touristSpot e1, touristSpot e2) {
//                // Calcula a distância entre o elemento e a latitude/longitude fixa
//                double distanciaE1 = calcularDistanciaVincenty(e1.getLatitude(), e1.getLongitude(), latitudeFixa, longitudeFixa);
//                double distanciaE2 = calcularDistanciaVincenty(e2.getLatitude(), e2.getLongitude(), latitudeFixa, longitudeFixa);
//
//                // Compara as distâncias
//                return Double.compare(distanciaE1, distanciaE2);
//            }
//        });

        Collections.sort(currentLista, new Comparator<Museu>() {
            @Override
            public int compare(Museu e1, Museu e2) {
                double distanciaE1 = calcularDistancia(e1.getLatitude(), e1.getLongitude());
                double distanciaE2 = calcularDistancia(e2.getLatitude(), e2.getLongitude());

                return Double.compare(distanciaE1, distanciaE2);
            }
        });

        return currentLista;

    }

    public static void main(String[] args) throws JSONException {
        pontosProximos pp = new pontosProximos(-8.052569, -34.974795);
        for (Museu teste : pp.ordenarLista(new acessoPontosTuristicos().getMuseus())) {
            System.out.println(teste.getNome());
        }
    }
}
