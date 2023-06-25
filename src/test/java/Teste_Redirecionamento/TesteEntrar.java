package Teste_Redirecionamento;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.*;

public class TesteEntrar {
private WebDriver driver;

@Before
public void setUp() {
    // Configurar o driver do Chrome
    System.setProperty("webdriver.chrome.driver", "test/chromedriver/chromedriver.exe");

    // Inicializar o WebDriver
    driver = new ChromeDriver();
}

@After
public void tearDown() {
    // Fechar o navegador
    driver.quit();
}

@Test
public void testRedirecionamentoBotaoEntrar() {
    // Navegar para a página desejada
    driver.get("http://localhost:8080/WebApplication1/index.xhtml");

    // Aguardar até que o elemento com a classe "buttonEntrar" esteja visível
    WebDriverWait wait = new WebDriverWait(driver, 10);
    WebElement buttonEntrar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("buttonEntrar")));

    // Realizar a ação desejada no elemento
    buttonEntrar.click();        
    
     // Aguardar o carregamento da nova URL
        String expectedURL = "http://localhost:8080/WebApplication1/principalPage.xhtml";
        wait.until(ExpectedConditions.urlToBe(expectedURL));

        // Verificar se o redirecionamento ocorreu corretamente
        String actualURL = driver.getCurrentUrl();
        assertEquals(expectedURL, actualURL);
}
}