package com.vvs.trabalhofinal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Classe que implementa o teste funcional, 
 * gravada e extraído da ferramenta Selenium
 */
@SpringBootTest(classes = TrabalhofinalApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class FuncionalTest {

    /**
     * Porta em que o Sistema Carros ficará disponível
     */
    @LocalServerPort
    private int porta;

  /**
   * Driver para realizar a navegação por código.
   */
  private static WebDriver driver;

  /**
   * Atributo necessário para execução de JS no navegador
   */
  static JavascriptExecutor js;

  /**
   * Inicializa o driver e o JS
   */
  @BeforeAll
  public static void setUp() {
    FuncionalTest.driver = new FirefoxDriver();
    js = (JavascriptExecutor) driver;
  }

  /**
   * Encerra o driver ao final dos testes
   */
  @AfterAll
  public static void tearDown() {
    driver.quit();
  }

  /** 
   * Testa:
   *        1 - Se a página inicial abre
   *        2 - Se um carro é inserido corretamente
   *        3 - Se o carro inserido pode ter a cor alterada
   *        4 - Se é possível deletar o carro inserido
  */
  @Test
  public void testeFuncional() {
    driver.get("http://127.0.0.1:"+porta+"/api/carros");
    assertThat(driver.findElement(By.cssSelector("h2")).getText(), is("Carros"));
    driver.findElement(By.linkText("Adicionar novo carro")).click();
    driver.findElement(By.id("modelo")).click();
    driver.findElement(By.id("modelo")).sendKeys("Cerato");
    driver.findElement(By.id("fabricante")).click();
    driver.findElement(By.id("fabricante")).sendKeys("KIA");
    driver.findElement(By.id("cor")).click();
    driver.findElement(By.id("cor")).sendKeys("Preto");
    driver.findElement(By.cssSelector("html")).click();
    driver.findElement(By.cssSelector("p:nth-child(4) > input:nth-child(1)")).click();
    assertThat(driver.findElement(By.cssSelector("td:nth-child(1)")).getText(), is("Cerato"));
    assertThat(driver.findElement(By.cssSelector("td:nth-child(2)")).getText(), is("KIA"));
    assertThat(driver.findElement(By.cssSelector("td:nth-child(3)")).getText(), is("Preto"));
    driver.findElement(By.linkText("Voltar ao início")).click();
    assertThat(driver.findElement(By.cssSelector("tr:nth-child(3) > td:nth-child(2)")).getText(), is("Cerato"));
    assertThat(driver.findElement(By.cssSelector("tr:nth-child(3) > td:nth-child(3)")).getText(), is("KIA"));
    assertThat(driver.findElement(By.cssSelector("tr:nth-child(3) > td:nth-child(4)")).getText(), is("Preto"));
    driver.findElement(By.cssSelector("tr:nth-child(3) a")).click();
    driver.findElement(By.id("cor")).click();
    driver.findElement(By.id("cor")).clear();
    driver.findElement(By.id("cor")).sendKeys("");
    driver.findElement(By.id("cor")).sendKeys("Branco");
    driver.findElement(By.cssSelector("p:nth-child(5) > input:nth-child(1)")).click();
    assertThat(driver.findElement(By.cssSelector("td:nth-child(3)")).getText(), is("Branco"));
    driver.findElement(By.linkText("Voltar ao início")).click();
    assertThat(driver.findElement(By.cssSelector("tr:nth-child(3) > td:nth-child(4)")).getText(), is("Branco"));
    driver.findElement(By.cssSelector("tr:nth-child(3) .btn")).click();
    assertThat(driver.findElement(By.cssSelector("h1")).getText(), is("Sucesso ao deletar o carro!"));
    driver.findElement(By.linkText("Voltar ao início")).click();
  }
}
