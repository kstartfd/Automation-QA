package webdriver.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;

public class ComboBox extends BaseElement {

	public ComboBox(By loc) {
		super(loc);
	}

	public ComboBox(By loc, String nameOf) {
		super(loc, nameOf);
	}

	public ComboBox(String stringLocator, String nameOfElement) {
		super(stringLocator, nameOfElement);
	}

	@Override
	protected String getElementType() {
		return getLoc("loc.combobox");
	}


	public void selectByVisibleText(String text) {
		waitForIsElementPresent();
		info(String.format(getLoc("loc.selecting.text") + " '%1$s' combobox", name) );
		browser.getDriver().getMouse().mouseMove(element.getCoordinates());
		if (browser.getDriver() instanceof JavascriptExecutor) {
			((JavascriptExecutor)browser.getDriver()).executeScript("arguments[0].style.border='3px solid red'", element);
		}
		new Select(this.getElement()).selectByVisibleText(text);
	}

	public void selectByValue(String value) {
		waitForIsElementPresent();
		info(String.format(getLoc("loc.selecting.value") + " '%1$s' combobox", name) );
		browser.getDriver().getMouse().mouseMove(element.getCoordinates());
		if (browser.getDriver() instanceof JavascriptExecutor) {
			((JavascriptExecutor)browser.getDriver()).executeScript("arguments[0].style.border='3px solid red'", element);
		}
		new Select(this.getElement()).selectByValue(value);

	}
}
