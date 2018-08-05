package com.qr.test.process.test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qr.test.process.enums.ActionType;
import com.qr.test.process.input.Action;
import com.qr.test.process.input.TestRunnerInput;

public class TestRunner {

	private TestRunner() {
		// TODO Auto-generated constructor stub
	}

	public void startTest(WebDriver webDriver, TestRunnerInput input) {
		webDriver.get(input.getUrl());
		WebElement webElement = null;

		List<Action> actions = input.getActions();
		for (Action action : actions) {
			if (ActionType.WAIT == action.getActionType()) {
				switch (action.getFindByAttbuteType()) {
				case CLASS:
					(new WebDriverWait(webDriver, 10))
							.until(ExpectedConditions.elementToBeClickable(By.cssSelector(action.getCssClass())));
					break;
				case ID:
					(new WebDriverWait(webDriver, 10))
							.until(ExpectedConditions.elementToBeClickable(By.id(action.getId())));
					break;
				case NAME:
					(new WebDriverWait(webDriver, 10))
							.until(ExpectedConditions.elementToBeClickable(By.name(action.getName())));
					break;
				case XPATH:
					(new WebDriverWait(webDriver, 10))
							.until(ExpectedConditions.elementToBeClickable(By.xpath(action.getxPath())));
					break;
				case LINK_TEXT:
					(new WebDriverWait(webDriver, 10))
							.until(ExpectedConditions.elementToBeClickable(By.linkText(action.getLinkText())));
					break;
				default:
					break;

				}
				sleep(3000);
			} else {
				webElement = getWebElementByAction(webDriver, action);
				performAction(webDriver, webElement, action);
				sleep(1000);
			}
		}
	}

	private void sleep(long sleepTime) {
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private WebElement getWebElementByAction(WebDriver webDriver, Action action) {
		WebElement webElement = null;
		switch (action.getFindByAttbuteType()) {
		case ID:
			webElement = webDriver.findElement(By.id(action.getId()));
			break;
		case CLASS:
			webElement = webDriver.findElement(By.cssSelector(action.getCssClass()));
			break;
		case XPATH:
			webElement = webDriver.findElement(By.xpath(action.getxPath()));
			break;
		case NAME:
			webElement = webDriver.findElement(By.name(action.getName()));
			break;
		case LINK_TEXT:
			webElement = webDriver.findElement(By.linkText(action.getLinkText()));
			break;
		default:
			break;
		}
		return webElement;
	}

	private void performAction(WebDriver webDriver, WebElement webElement, Action action) {
		switch (action.getActionType()) {
		case CLICK:
			if (webElement.getTagName().equalsIgnoreCase("select")) {
				Select select = new Select(webElement);
				select.selectByVisibleText(action.getValue());
			} else if (webElement.getTagName().equalsIgnoreCase("a")) {
				JavascriptExecutor jse2 = (JavascriptExecutor) webDriver;
				jse2.executeScript("arguments[0].click()", webElement);
			} else {
				Actions actions = new Actions(webDriver);
				JavascriptExecutor jse2 = (JavascriptExecutor) webDriver;
				jse2.executeScript("arguments[0].scrollIntoView()", webElement);
				try {
					actions.moveToElement(webElement).click().perform();
				} catch (ElementNotVisibleException ex) {
					jse2.executeScript("arguments[0].click()" + webElement);
				}

			}
			break;
		case FILL_TEXT:
			webElement.clear();
			webElement.sendKeys(action.getValue());
			break;
		default:
			break;
		}
	}

	public static TestRunner newInstance() {
		return new TestRunner();
	}
}