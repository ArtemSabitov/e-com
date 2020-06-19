package utils;

import init.BaseTest;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import practice.automationtesting.in.utils.Logger;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomTestListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult tr) {
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
    }


    @Override
    public void onTestFailure(ITestResult tr) {
        WebDriver driver = ((BaseTest) tr.getInstance()).getDriver();

        Reporter.setCurrentTestResult(tr);
        File screenshot = getScreenShotFile(tr, driver);
        Reporter.setCurrentTestResult(null);

        if (screenshot != null) {
            Path content = Paths.get(screenshot.getPath());
            try {
                InputStream is = Files.newInputStream(content);
                Allure.addAttachment("Screenshot", is);
            } catch (IOException e) {
                e.printStackTrace();
                Logger.error("Can't read screenshot file: \n" + e.toString());
            }
        }
    }


    @Override
    public void onTestSkipped(ITestResult tr) {
    }


    @Nullable
    private File getScreenShotFile(ITestResult testResult, WebDriver driver) {
        File screenshotFile = null;
        try {
            DateFormat dateFormatForFile = new SimpleDateFormat("HH-mm-ss-SSS");
            DateFormat dateFormatForDir = new SimpleDateFormat("yyyy-MM-dd");

            String filename = dateFormatForFile.format(new Date()) + "_" + testResult.getMethod().getMethodName() + ".png";
            String screenshotsDir = System.getProperty("user.dir").replace("\\", "/") + "/target/surefire-reports/html/Screenshots/";
            String destDir = screenshotsDir + dateFormatForDir.format(new Date());
            String destDirForReport = "screenshots/" + dateFormatForDir.format(new Date());

            Screenshot screenshotData = new AShot().shootingStrategy(ShootingStrategies.viewportRetina(100, 0, 0, 1)).takeScreenshot(driver);

            final BufferedImage image = screenshotData.getImage();
            String tmpPath = System.getProperty("user.dir").replace("\\", "/");
            ImageIO.write(image, "PNG", new File(tmpPath + "/" + filename));
            File screenshot = new File(tmpPath + "/" + filename);
            screenshotFile = new File(destDir + "/" + filename);
            FileUtils.moveFile(screenshot, screenshotFile);
            Logger.info(destDirForReport + "/" + filename);
            return screenshotFile;

        } catch (Exception e) {
            Logger.error("Exception during getting screenshot file. " + e.toString());
        }
        return screenshotFile;
    }

    public void onStart(ITestContext context) {
    }

    public void onFinish(ITestContext context) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }
}