package TestFunctions;


import geolocalizacao.pontosProximos;
import org.junit.Test;

/**
 *
 * @author johnn
 */
public class TestDistance {
    
    public double distanciaReal;    
    public double distance;
    public double diference;  
    
    //Geolocalização do IFPE
    public double ifpeLat = -8.058195815938717;
    public double ifpeLon = -34.94989206073905;
    //Geolocalização do Hospital das Clínicas
    public double hcpLat = -8.047202259805784;
    public double hcpLon = -34.946358011698734;
    //Distância entre IFPE e Hospital das Clínicas Real        
    public double ifpeHcpDistance = 1280;
    //Geolocalização do Rio Mar
    public double rioMarLat = -8.086566034550481;
    public double rioMarLon = -34.89460754016371;
    //Distância entre IFPE e o Rio Mar Real        
    public double ifpeRioMarDistance = 6850;
    //Geolocalização de Porto de Galinhas
    public double portLat = -8.50551706969523;
    public double portLon = -35.00322755489455;
    //Distância entre IFPE e Porto de Galinhas Real        
    public double ifpePortDistance = 50000;
    
    
    
    public void setDiference(double diferenceNew)
    {
        this.diference = diferenceNew;
    }
    
    public double getDiference()
    {
        return diference;
    }
   
    public void setDistance (double distanceNew)
    {
        this.distance = distanceNew;
    }
    
    public double getDistance()
    {
        return distance;
    }
    
    public void setDistanciaReal (double distanciaRealNew)
    {
        this.distanciaReal = distanciaRealNew;
    }
    
    public double getDistanciaReal()
    {
        return distanciaReal;
    }

    //Calcula o módulo da distância entre os pontos
    public double distanceModule(double dist1, double dist2)
    {
        if(dist1>dist2)
            return (dist1-dist2);
        else
            return (dist1-dist2)*-1;        
    }
    
    
    //Instanciar objeto Pontos Próximos utilizando o IFPE como referência
    pontosProximos testDistance = new pontosProximos(ifpeLat,ifpeLon);
        
    //Testa o Método de calcular a distância, com tolerância de 100m
    @Test
    public void calcularDistanciaTest(){
    
    setDistance(testDistance.calcularDistancia(hcpLat,hcpLon));
    
    setDiference(distanceModule(distance,ifpeHcpDistance));
    
    //System.out.println(diference);
    
    assert(diference<100);        
}
    
    @Test
    public void calcularDistanciaTest2(){
    
    setDistance(testDistance.calcularDistancia(rioMarLat,rioMarLon));
    
    setDiference(distanceModule(distance,ifpeRioMarDistance));
    
    //System.out.println(diference);
    
    assert(diference<100);
        
}
    
    @Test
    public void calcularDistanciaTest3(){
    
    setDistance(testDistance.calcularDistancia(portLat,portLon));
    
    setDiference(distanceModule(distance,ifpePortDistance));
    
    //System.out.println(diference);
    
    assert(diference<100);
        
}
    
    
    
}
