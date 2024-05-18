import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.microsoft.cognitiveservices.speech.ResultReason;
import com.microsoft.cognitiveservices.speech.SpeechConfig;
import com.microsoft.cognitiveservices.speech.SpeechRecognizer;
import com.microsoft.cognitiveservices.speech.audio.AudioConfig;
import com.microsoft.cognitiveservices.speech.audio.AudioProcessingConstants;
import com.microsoft.cognitiveservices.speech.audio.AudioProcessingOptions;
import com.trailsignup.Common.RandomGenerator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class TrialSignup {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		SpeechConfig config = SpeechConfig.fromSubscription("c5f183bc0c084b85a9d61e7bb5be626c", "francecentral");
		
		System.setProperty("webdriver.gecko.driver", "/Users/mohseenshaik/Documents/project/Driver/geckodriver");
        
		WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        
        driver.get("https://signin.storyful.com/trial/signup-form-01489905846464720243");
        
        driver.findElement(By.id("hs-eu-confirmation-button")).click();
        
        driver.findElement(By.id("first_name")).click();
        driver.findElement(By.id("first_name")).sendKeys("Test");
        
        driver.findElement(By.id("last_name")).click();
        driver.findElement(By.id("last_name")).sendKeys("4");
        
        driver.findElement(By.id("user_email")).click();
        driver.findElement(By.id("user_email")).sendKeys(RandomGenerator.randomEmail());
        
        driver.findElement(By.id("user_password")).click();
        driver.findElement(By.id("user_password")).sendKeys("Pass@123");
        
        driver.findElement(By.id("terms")).click();
        
        WebElement iframe = driver.findElement(By.xpath("//*[@title='reCAPTCHA']"));

        driver.switchTo().frame(iframe);
        driver.findElement(By.xpath("//*[@id='recaptcha-anchor']/div[@class='recaptcha-checkbox-border']")).click();
        
        driver.switchTo().defaultContent();

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='recaptcha challenge expires in two minutes']")));
        WebElement audioOptionButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("recaptcha-audio-button")));
        audioOptionButton.click();


        AudioProcessingOptions audioProcessingOptions = AudioProcessingOptions.create(AudioProcessingConstants.AUDIO_INPUT_PROCESSING_ENABLE_DEFAULT);
        AudioConfig audioInput = AudioConfig.fromDefaultMicrophoneInput(audioProcessingOptions);
        List<String> recognizedSpeechParts = new ArrayList<>();
        SpeechRecognizer recognizer = new SpeechRecognizer(config, audioInput);
        {
            recognizer.recognized.addEventListener((s, e) -> {
                if (e.getResult().getReason() == ResultReason.RecognizedSpeech) {
                    recognizedSpeechParts.add(e.getResult().getText());
                    System.out.println("RECOGNIZED: Text=" + e.getResult().getText());
                }
                else if (e.getResult().getReason() == ResultReason.NoMatch) {
                    System.out.println("NOMATCH: Speech could not be recognized.");
                }
            });

            // Starts continuous recognition. Uses stopContinuousRecognitionAsync() to stop recognition.
            recognizer.startContinuousRecognitionAsync().get();
            WebElement playButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='PLAY']")));
            playButton.click();
            Thread.sleep(10000);
            recognizer.stopContinuousRecognitionAsync().get();
        }

        config.close();
        audioInput.close();
        audioProcessingOptions.close();
        recognizer.close();

        WebElement audioResponseInput = driver.findElement(By.id("audio-response"));
        String captchaText =  String. join("", recognizedSpeechParts);
        audioResponseInput.sendKeys(captchaText);

        WebElement verifyButton = driver.findElement(By.id("recaptcha-verify-button"));
        verifyButton.click();

        driver.switchTo().defaultContent();
        
        driver.findElement(By.id("send")).click();

	}

}
