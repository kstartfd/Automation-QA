package webdriver.elements;

import org.openqa.selenium.By;

public class CheckBox extends BaseElement {
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
		if (!element.isSelected()) {
			click();
		}
	}

	public void checkAndWait() {
		waitForIsElementPresent();
		if (!element.isSelected()) {
			click();
			browser.waitForPageToLoad();
		}
	}

	public void unCheck() {
		waitForIsElementPresent();
		if (element.isSelected()) {
			click();
		}
	}

	public boolean isChecked() {
		waitForIsElementPresent();
		if (element.isSelected()) {
			return true;
		} else {
			return false;
		}

	}
}
