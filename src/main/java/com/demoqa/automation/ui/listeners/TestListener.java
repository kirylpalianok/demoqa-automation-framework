package com.demoqa.automation.ui.listeners;

import com.demoqa.automation.ui.utils.ScreenshotUtil;
import io.qameta.allure.Allure;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;

public class TestListener implements ITestListener {

	@Override
	public void onTestFailure(ITestResult result) {

		byte[] screenshot = ScreenshotUtil.takeScreenshot();

		if (screenshot != null) {
			Allure.addAttachment(
					result.getName(),
					new ByteArrayInputStream(screenshot)
			);
		}
	}
}

