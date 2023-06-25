package Teste_Elementos;

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

public class TesteIndex {
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
    public void testCarregamentoPagina() {
        // Navegar para a página Index
        driver.get("http://localhost:8080/WebApplication1/index.xhtml");

        // Verificar se o elemento com a classe "buttonEntrar" está presente
        WebElement buttonEntrar = driver.findElement(By.className("buttonEntrar"));
        assertNotNull(buttonEntrar);

        // Verificar se o elemento com a classe "img" está presente
        WebElement img = driver.findElement(By.className("img"));
        assertNotNull(img);

        // Verificar se o elemento com a classe "img2" está presente
        WebElement img2 = driver.findElement(By.className("img2"));
        assertNotNull(img2);

        // Verificar se o elemento com a classe "img3" está presente
        WebElement img3 = driver.findElement(By.className("img3"));
        assertNotNull(img3);
        

        // Aguardar até que o elemento esteja visível usando WebDriverWait
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement nameSite = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nameSite")));
        assertNotNull(nameSite);
    }
}