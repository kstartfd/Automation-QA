package webdriver.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;



public class CheckBox extends BaseElement {

	/**
	 *
	 * @param loc
     */
	public CheckBox(By loc) {
		super(loc);
	}

	public CheckBox(By loc, String nameOf) {
		super(loc, nameOf);
	}

	public CheckBox(String stringLocator, String nameOfElement) {
		super(stringLocator, nameOfElement);
	}

	@Override
	protected String getElementType() {
		return getLoc("loc.checkbox");
	}

	public boolean isEnabled() {
		return this.getElement().isEnabled();
	}

	public void check() {
		waitForIsElementPresent();
		info(String.format(getLoc("loc.checkbox.checking") + " '%1$s' checkbox", name));
		if (browser.getDriver() instanceof JavascriptExecutor) {
			((JavascriptExecutor)browser.getDriver()).executeScript("arguments[0].style.border='3px solid red'", element);
		}

		if (!element.isSelected()) {
			click();
		}
	}

	public void unCheck() {
		waitForIsElementPresent();
		info(String.format(getLoc("loc.checkbox.checking") + " '%1$s' checkbox", name));
		if (browser.getDriver() instanceof JavascriptExecutor) {
			((JavascriptExecutor)browser.getDriver()).executeScript("arguments[0].style.border='3px solid red'", element);
		}
		if (element.isSelected()) {
			click();
		}
	}

}
