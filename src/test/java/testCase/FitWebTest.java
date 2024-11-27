package testCase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import baseTest.BaseClass;

public class FitWebTest extends BaseClass {
    private static final Logger logger = LogManager.getLogger(FitWebTest.class);
    public SoftAssert sa;

    @Test
    public void functionalityCheck() {
        logger.info("***** Start test: functionalityCheck *****");

        try {
            
            logger.info("Navigating to Revenue Calculator page.");
            revenuePage.navigateToRevenueCalculator();

          
            logger.info("Scrolling to slider and adjusting it.");
            revenuePage.scrollToSlider();
            revenuePage.adjustSlider(93);

            
            logger.info("Setting input field value.");
            revenuePage.setInputField("560");
            sa = new SoftAssert();
            logger.info("Verifying input field value.");
            sa.assertEquals(revenuePage.getInputFieldValue(), "560", "Input field value mismatch");

           
            logger.info("Selecting CPT codes.");
            String[] cptCodes = {"57", "19.19", "63", "15"};
            for (String code : cptCodes) {
                revenuePage.selectCPTCode(code);
            }

            
            logger.info("Validating total reimbursement.");
            String actualText = revenuePage.getTotalReimbursementText();
            Assert.assertTrue(actualText.contains("$110160"), "Total Reimbursement mismatch");
            
            logger.info("Test completed successfully.");
        } catch (Exception e) {
            logger.error("An error occurred during the test.", e);
        }
    }

    @AfterMethod
    public void tearDown() {
        logger.info("Closing the browser.");
        if (driver != null) {
            driver.quit();
            if (sa != null) {
                sa.assertAll();
            }
        }
    }
}
