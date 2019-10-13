package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestBase;
import pageRepository.YandexSearchPO;
import utility.TestUtils;

public class YandexSearchTest extends TestBase{
	YandexSearchPO yandexSearchPO;

	YandexSearchTest() {
		super();		
	}

	@BeforeMethod
	public void enviornmentSetUp()
	{
		initialize();
		//TestUtils.takeScreenShot();
		yandexSearchPO = new YandexSearchPO();
	}
	
	//Using Data Provider for search Result
	@Test(priority=1 , enabled=true , description="Search For Web Resources - Verify_When_User_Search_For_Particular_Topic_Relavant_Articles_Populated_As_Search_Result")
	public void search_For_Web_Resources__Verify_When_User_Search_For_Particular_Topic_And_Hit_Submit_Search_Relavant_Articles_Populated_As_Search_Result() throws IOException
	{
		Boolean result=yandexSearchPO.verify_Search_Relavant_Articles_Populated_When_Topic_Provided_And_Hit_Submit("Mobile");
		System.out.println("result = "+result);
		if(result==true)
		{
			
			Reporter.log("<br> <a href='"+ TestUtils.getScreenShotPath() + "'> <img src='"+ TestUtils.getScreenShotPath() + "' height='100' width='100'/> </a>");
			Assert.assertTrue(result, "Test PASS - When User Search For Particular Topic Relavant Articles Populated Successfully");
			Reporter.log("Test PASS - When User Search For Particular Topic Relavant Articles Populated Successfully");
		}
		else
		{
			Assert.assertTrue(result, "Test FAIL - When User Search For Particular Topic Relavant Articles not Populated");
			Reporter.log("Test FAIL - When User Search For Particular Topic Relavant Articles not Populated ");
		
		}
	}

	@Test(priority=2 , enabled=true , dataProvider="searchProvider",description="Help With Input - Verify_When_User_Search_For_Particular_Topic_Autosuggestion_Starting_From_Same_Letters_Starts_Appearing")
	public void help_With_Input__Verify_When_User_Search_For_Particular_Topic_Autosuggestion_Starting_From_Same_Letters_Starts_Appearing(String searchCriteria) throws IOException
	{
		System.out.println("Data Provider Input = "+searchCriteria);
		Reporter.log("Search Criteria Provided with Data Provider = "+searchCriteria);
		
		Boolean result=yandexSearchPO.Verify_When_User_Search_For_Particular_Topic_Autosuggestion_Starting_From_Same_Letters_Starts_Appearing(searchCriteria);
		System.out.println("result = "+result);
		if(result==true)
		{
			
			Reporter.log("<br> <a href='"+ TestUtils.getScreenShotPath() + "'> <img src='"+ TestUtils.getScreenShotPath() + "' height='100' width='100'/> </a>");
			Assert.assertTrue(result, "Test PASS - When User Search For Particular Topic Autosuggestion Starting From Same Letters Appeared Successfully.");
			Reporter.log("Test PASS - When User Search For Particular Topic Autosuggestion Starting From Same Letters Appeared Successfully.");
		}
		else
		{
			Assert.assertTrue(result, "Test FAIL - When User Search For Particular Topic Autosuggestion Relvant To Search Not Populated Successfully.");
			Reporter.log("Test FAIL - When User Search For Particular Topic Autosuggestion Relvant To Search Not Populated Successfully.");
		
		}
	}
	
	@Test(priority=3 , enabled=true ,description="Help With Input - Verify_User_Is_Able_To_Provide_SearchCriteria_with_Onscreen_Keyboard_And_Relvant_SearchResult_Appear")
	public void help_With_Input__Verify_User_Is_Able_To_Provide_SearchCriteria_with_Onscreen_Keyboard_And_Relvant_SearchResult_Appear() throws IOException
	{
				
		Boolean result=yandexSearchPO.Verify_User_Is_Able_To_Provide_SearchCriteria_with_Onscreen_Keyboard_And_Relvant_SearchResult_Appear("m");
		System.out.println("result = "+result);
		if(result==true)
		{
			
			Reporter.log("<br> <a href='"+ TestUtils.getScreenShotPath() + "'> <img src='"+ TestUtils.getScreenShotPath() + "' height='100' width='100'/> </a>");
			Assert.assertTrue(result, "Test PASS - When User Provide SearchCriteria With Onscreen Keyboard Autosuggestion Starting From Same Letters Appeared Successfully.");
			Reporter.log("Test PASS - When User When User Provide SearchCriteria With Onscreen Keyboard Autosuggestion Starting From Same Letters Appeared Successfully.");
		}
		else
		{
			Assert.assertTrue(result, "Test FAIL - When User Provide SearchCriteria With Onscreen Keyboard Autosuggestion Relvant To Search Not Populated Successfully.");
			Reporter.log("Test FAIL - When User Provide SearchCriteria With Onscreen Keyboardc Autosuggestion Relvant To Search Not Populated Successfully.");
		
		}
	}
	
	@DataProvider(name="searchProvider")
	public static Object[][] searchProvider()
	{
		return new Object[][]
				{
					{"Mobile"}
				};
	}
	@AfterMethod
	public void tearDownSetUp()
	{
		tearDown();
	}
}
