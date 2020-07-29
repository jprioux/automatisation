/**
 *     This file is part of the Squashtest platform.
 *     Copyright (C) 2018 - 2019 Henix
 *
 *     See the NOTICE file distributed with this work for additional
 *     information regarding copyright ownership.
 *
 *     This is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     this software is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public License
 *     along with this software.  If not, see <http://www.gnu.org/licenses />.
 */
package squash;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.fr.Alors;
import cucumber.api.java.fr.Etantdonnéque;
import cucumber.api.java.fr.Etque;
import cucumber.api.java.fr.Quand;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import squash.page.ActionWordWorkspacePage;
import squash.page.HomeWorkspacePage;
import squash.page.LoginPage;
import squash.page.TestCaseInfoPage;
import squash.utils.SlowWebDriver;

import java.util.concurrent.TimeUnit;

public class AnnotationSteps {
	private static final String SQUASH_URL = "http://192.168.0.138:8080/squash";
	private WebDriver driver;
	private WebDriverWait wait;
	private WebElement popup;

	@Before
	public void setup(){
		driver = new SlowWebDriver(new ChromeDriver(), 500);
		wait = new WebDriverWait(driver, 20);
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		driver.get(SQUASH_URL);
	}

	@Etantdonnéque("je suis sur la page d'authentification Squash")
	public void je_suis_sur_la_page_d_authentification_Squash() {
		LoginPage squashLoginPage = new LoginPage(driver);
		squashLoginPage.checkElementWithId("username");
		squashLoginPage.checkElementWithId("password");
	}

	@Etantdonnéque("je me connecte en tant que {string} avec mot de passe {string}")
	public void je_me_connecte_en_tant_que_avec_mot_de_passe(String username, String password) {
		LoginPage squashLoginPage = new LoginPage(driver);
		squashLoginPage.fillUserNameAndPasswordInput(wait, username, password);
		squashLoginPage.loginToHomePage(wait);
	}

	@Etantdonnéque("je suis sur la page d'accueil Squash")
	public void je_suis_sur_la_page_d_accueil_Squash() {
		HomeWorkspacePage homePage = new HomeWorkspacePage(driver);
		Assert.assertTrue(homePage.checkHomePage());
	}

	@Etantdonnéque("je navigate au CdT BBD dont l'id est {long}")
	public void je_navigate_au_CdT_BDD_dont_l_id_est(long id) {
		String testCaseUrl = SQUASH_URL+"/test-cases/"+id+"/info";
		driver.get(testCaseUrl);
		TestCaseInfoPage testCaseInfoPage = new TestCaseInfoPage(driver);
		testCaseInfoPage.checkElementWithId("test-case-name-div");
		WebElement testCaseNameElement = testCaseInfoPage.getDriver().findElement(By.id("test-case-name"));
		Assert.assertEquals("Test case name href must be the same to test case url.", testCaseUrl, testCaseNameElement.getAttribute("href"));
	}

	/////////////////// 10488 //////////////////
	@Etantdonnéque("il existe une action {string} d'id {int} dans la librairie {int}")
	public void il_existe_une_action_d_id_dans_la_librairie(String existingAction, int actionWordId, int libraryId) {
		String testCaseUrl = SQUASH_URL+"/action-word-workspace";
		driver.get(testCaseUrl);
		ActionWordWorkspacePage actionWordWorkspacePage = new ActionWordWorkspacePage(driver);
		actionWordWorkspacePage.findExistingAction(wait, existingAction, actionWordId, libraryId);
	}

	@Quand("je souhaite modifier le pas de test avec l'action {string}")
	public void je_souhaite_modifier_le_pas_de_test_avec_l_action(String actionToModify) {
		TestCaseInfoPage testCaseInfoPage = new TestCaseInfoPage(driver);
		testCaseInfoPage.initActionInTestStep(wait, actionToModify);
	}

	@Etque("je supprime dans le pas de test le paramètre pour saisir {string}")
	public void je_supprime_dans_le_pas_de_test_le_parametre_pour_saisir(String existingAction) {
		TestCaseInfoPage testCaseInfoPage = new TestCaseInfoPage(driver);
		testCaseInfoPage.modifyActionInKeywordTestStep(wait, existingAction);
	}

	@Etque("je valide ma modification du pas de test")
	public void je_valide_ma_modification_du_pas_de_test() {
		TestCaseInfoPage testCaseInfoPage = new TestCaseInfoPage(driver);
		testCaseInfoPage.confirmKeywordTestStepModification(wait);
	}

	@Alors("le pas de test est bien modifié avec l'action {string}")
	public void le_pas_de_test_est_bien_modifie_avec_l_action(String updatedAction) {
		TestCaseInfoPage testCaseInfoPage = new TestCaseInfoPage(driver);
		testCaseInfoPage.checkActionInKeywordTestStep(wait, updatedAction);
		System.out.println("Le pas de test a bien été modifié");
	}

	////////////////// 239 //////////////////
	@Etantdonnéque("je saisis le texte pour une nouvelle action {string}")
	public void je_saisis_le_texte_pour_une_nouvelle_action(String input) {
		TestCaseInfoPage testCaseInfoPage = new TestCaseInfoPage(driver);
		testCaseInfoPage.fillActionWordInput(wait, input);
	}

	@Etantdonnéque("je saisis le param pour une nouvelle action {string}")
	public void je_saisis_le_param_pour_une_nouvelle_action(String input) {
		TestCaseInfoPage testCaseInfoPage = new TestCaseInfoPage(driver);
		testCaseInfoPage.fillActionWordInput(wait, input);
	}

	@Quand("j'ajoute un nouveau pas de test avec l'action saisie")
	public void je_ajoute_un_nouveau_pas_de_test_avec_l_action_saisie() {
		TestCaseInfoPage testCaseInfoPage = new TestCaseInfoPage(driver);
		testCaseInfoPage.addNewKeywordTestStep(wait);
		this.popup = testCaseInfoPage.getDriver().findElement(By.id("generic-error-dialog"));
		wait.until(ExpectedConditions.elementToBeClickable(popup));
		Assert.assertNotNull(popup);
	}

	@Alors("je suis dans le cas suivant : chevron ouvrant présent mais pas de chevron fermant")
	public void je_suis_dans_le_cas_suivant_chevron_ouvrant_present_mais_pas_de_chevron_fermant() {
		System.out.println("je suis dans le cas suivant : chevron ouvrant présent mais pas de chevron fermant");
	}

	@Alors("je suis dans le cas suivant : chevron fermant présent mais pas de chevron ouvrant")
	public void je_suis_dans_le_cas_suivant_chevron_fermant_present_mais_pas_de_chevron_ouvrant() {
		System.out.println("je suis dans le cas suivant : chevron fermant présent mais pas de chevron ouvrant");
	}

	@Alors("je suis dans le cas suivant : pas de paramètre renseigné entre les chevrons")
	public void je_suis_dans_le_cas_suivant_pas_de_parametre_renseigne_entre_les_chevrons() {
		System.out.println("je suis dans le cas suivant : pas de paramètre renseigné entre les chevrons");
	}

	@Alors("je suis dans le cas suivant : caractère interdit")
	public void je_suis_dans_le_cas_suivant_caractere_interdit() {
		System.out.println("je suis dans le cas suivant : caractère interdit");
	}

	@Alors("le message suivant est affiché dans une pop up :")
	public void le_message_suivant_est_affiche_dans_une_pop_up(String docString) {
		WebElement popupErrorMsg = driver.findElement(By.xpath("//div[@class='generic-error-main display-table-cell']"));
		Assert.assertNotNull(popupErrorMsg);
		String actual = popupErrorMsg.getText();
		Assert.assertEquals(docString, actual);
	}

	@After
	public void close(){
		if (driver != null) {
			driver.quit();
		}
	}
}
