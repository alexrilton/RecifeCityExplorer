package Teste_Elementos;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestePrincipalPage {
    private WebDriver driver;

    @Before
    public void setUp() {
        // Configuração do Selenium
        System.setProperty("webdriver.chrome.driver", "/home/alexbezerra/NetBeansProjects/RecifeCityExplorer/src/test/java/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/RecifeCityExplorer/principalPage.xhtml");
    }

    @Test
    public void testeElementosPesquisa() {
        // Verificar se os elementos estão presentes na página
        
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement barraPesquisar = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='pesquisar']//input[@type='submit']")));
        WebElement botaoSearch = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='pesquisar']//input[@type='submit']")));
        
        // Realizar asserções para verificar se os elementos foram encontrados
        Assert.assertNotNull(barraPesquisar);
        Assert.assertNotNull(botaoSearch);          

    }

    @Test
    public void testeElementosBotoes() {
        // Verificar se os elementos estão presentes na página
        
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement botoes = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nav-item")));
                
        // Realizar asserções para verificar se os elementos foram encontrados
        Assert.assertNotNull(botoes);     

    }
    
       
    @After
    public void tearDown() {
        // Encerrar o WebDriver após a conclusão dos testes
        driver.quit();
    }
}