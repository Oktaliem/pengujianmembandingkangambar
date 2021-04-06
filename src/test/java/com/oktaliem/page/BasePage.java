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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

import static com.google.common.io.Files.toByteArray;
import static com.oktaliem.utils.path.BASE_IMAGE_PATH;
import static com.oktaliem.utils.path.SCREENSHOT_PATH;

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

    BufferedImage expImage;
    BufferedImage actImage;
    ImageDiffer imgDiff;
    ImageDiff diff;
    BufferedImage markedImage;
    SoftAssert softAssert = new SoftAssert();

    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] saveScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Actual Element image", type = "image/png")
    protected static byte[] getShutterbugElementScreenshot(WebElement el, String image) throws IOException, InterruptedException {
        TimeUnit.MILLISECONDS.sleep(2000);
        Shutterbug.shootElement(driver, el).withName(image).save();
        BufferedImage getImage = ImageIO.read(new File(SCREENSHOT_PATH + image + ".png"));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ImageIO.write(getImage, "png", output);
        return output.toByteArray();
    }

    @Attachment(value = "Actual image", type = "image/png")
    protected static byte[] getAShotElementScreensAshot(WebElement el, String image) throws IOException, InterruptedException {
        TimeUnit.MILLISECONDS.sleep(2000);
        Screenshot screenshot = new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driver, el);
        try {
            ImageIO.write(screenshot.getImage(), "PNG", new File(SCREENSHOT_PATH + image + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedImage getImage = ImageIO.read(new File(SCREENSHOT_PATH + image + ".png"));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ImageIO.write(getImage, "png", output);
        return output.toByteArray();
    }

    @Attachment(value = "Actual image", type = "image/png")
    protected static byte[] getSeleniumElementScreenShot(WebElement el, String image) throws IOException, InterruptedException {
        TimeUnit.MILLISECONDS.sleep(2000);
        File f = el.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(f, new File(SCREENSHOT_PATH + image + ".png"));
        BufferedImage getImage = ImageIO.read(new File(SCREENSHOT_PATH + image + ".png"));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ImageIO.write(getImage, "png", output);
        return output.toByteArray();
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

    @Step
    public void performVisualTest(String actual_image, String expected_image, String diff_image) {
        try {
            checkIfImagesAreIdentical(expected_image, actual_image, diff_image);
        } catch (AssertionError | IOException error) {
            getBaseLineImage(expected_image);
            getScreenshotDiffer(diff_image);
            Assert.fail("Image are not identical, check allure report to see the difference");
            error.printStackTrace();
        }
    }

    @Attachment(value = "Base line image", type = "image/png")
    public byte[] getBaseLineImage(String name) {
        File expImage = new File(BASE_IMAGE_PATH + name + ".png");
        try {
            return toByteArray(expImage);
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    @Attachment(value = "Visual test (diff image)", type = "image/png")
    public byte[] getScreenshotDiffer(String name) {
        File expImage = new File(SCREENSHOT_PATH + name + ".png");
        try {
            return toByteArray(expImage);
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    @Step
    protected void checkIfImagesAreIdentical(String base, String actual, String result) throws IOException {
        expImage = ImageIO.read(new File(BASE_IMAGE_PATH + base + ".png"));
        actImage = ImageIO.read(new File(SCREENSHOT_PATH + actual + ".png"));
        imgDiff = new ImageDiffer();
        diff = imgDiff.makeDiff(actImage, expImage);
        if (diff.hasDiff()) {
            Log.info("Images are not identical, saving diff image");
            markedImage = diff.getMarkedImage();
            ImageIO.write(markedImage, "PNG", new File(SCREENSHOT_PATH + result + ".png"));
        }
        Assert.assertFalse(diff.hasDiff(), "Images are identical");
        Log.info("Images are identical");
    }

    @Step
    public void performVisualTestNotIdentical(String actual_image, String expected_image, String diff_image) throws IOException {
        assertImagesShouldNotIdentical(expected_image, actual_image, diff_image);
        getBaseLineImage(expected_image);
        getScreenshotDiffer(diff_image);
    }

    @Step
    protected void assertImagesShouldNotIdentical(String base, String actual, String result) throws IOException {
        expImage = ImageIO.read(new File(BASE_IMAGE_PATH + base + ".png"));
        actImage = ImageIO.read(new File(SCREENSHOT_PATH + actual + ".png"));
        imgDiff = new ImageDiffer();
        diff = imgDiff.makeDiff(actImage, expImage);
        if (diff.hasDiff()) {
            markedImage = diff.getMarkedImage();
            ImageIO.write(markedImage, "PNG", new File(SCREENSHOT_PATH + result + ".png"));
        }
        Assert.assertTrue(diff.hasDiff(), "Checking that images are not identical,");
        Log.info("Checking Images should not identical");
    }


}
