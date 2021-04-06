package com.oktaliem.page;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;
import com.oktaliem.page.webactions.BaseActions;
import com.oktaliem.page.webactions.Log;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

/**
 * Created by oktaliem
 */
public class BasePage extends BaseActions {
    protected static WebDriver driver;

    public BasePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        BasePage.driver = driver;
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] saveScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Step
    protected String fullScreenShootShutterbug(String fileName) throws IOException {
        Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE, 5000, true)
                .withName(fileName).save();
        Log.info("Take Screenshot and Save");
        byte[] imageContent = FileUtils.readFileToByteArray(new File("./screenshots/" + fileName + ".png"));
        return Base64.getEncoder().encodeToString(imageContent);
    }

    @Step
    protected String fullScreenShotAshot(String fileName) throws IOException {
        Screenshot result = new AShot().coordsProvider(new WebDriverCoordsProvider())
                .shootingStrategy(ShootingStrategies
                        .viewportPasting(5000))
                .takeScreenshot(driver);
        String path = "./screenshots/" + fileName + ".png";
        ImageIO.write(result.getImage(), "PNG", new File(path));
        byte[] imageContent = FileUtils.readFileToByteArray(new File(path));
        Log.info("Take Screenshot and Save");
        return Base64.getEncoder().encodeToString(imageContent);
    }


}
