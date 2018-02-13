package org.kostykevich.tests;

import org.kostykevich.forms.*;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import webdriver.BaseTest;

import java.util.List;

import static org.testng.Assert.fail;

public class TestOnlinerFindTV extends BaseTest {
	private String nameProduct;
	private String maxPrice;
	private String minYear;
	private String minDiagonal;
	private String maxDiagonal;


	@BeforeTest
	@Parameters({"nameProduct", "maxPrice", "minYear", "minDiagonal", "maxDiagonal"})
	public void beforeTest(String nameProduct, String maxPrice, String minYear, String minDiagonal, String maxDiagonal) {
		this.nameProduct = nameProduct;
		this.maxPrice = maxPrice;
		this.minYear = minYear;
		this.minDiagonal = minDiagonal;
		this.maxDiagonal = maxDiagonal;

	}


	public void runTest() {

		logger.step(1);
		MainPage mainPage = new MainPage();
		mainPage.assertLogo();
		mainPage.openCatalog();

		logger.step(2);
		CatalogPage catalogPage = new CatalogPage();
		catalogPage.openTVSet();

		logger.step(3);
		TVPage tvPage = new TVPage();
		tvPage.findTVItem(maxPrice, minYear, minDiagonal, maxDiagonal);


		logger.step(4);
		tvPage.assertFoundResult(nameProduct, minYear, maxPrice, minDiagonal, maxDiagonal);


	}


}
