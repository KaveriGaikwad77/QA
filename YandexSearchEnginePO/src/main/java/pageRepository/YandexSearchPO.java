package pageRepository;


import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;
import utility.TestUtils;

public class YandexSearchPO extends TestBase {
	
	WebDriverWait wait = new WebDriverWait(d, 1);
	
	// Page Repository
	
	@FindBy(xpath = "//input[@id='text']")
	WebElement searchInputBox;
	
	@FindBy(xpath = "//span[contains(text(),'Search')]/parent::button")
	WebElement searchButton;
	
	@FindBy(xpath = "//a//div[@class='organic__url-text']")
	List<WebElement> searchArticlesResult;

	@FindBy(xpath = "//div[@class='popup__content']//li")
	List<WebElement> searchSuggestionList;
	
	@FindBy(xpath = "//span[@class='input__box']//i/parent::div")
	WebElement virtualKeyboardIcon;
	
	@FindBy(xpath = "//div[@class='keyboard-popup']//tr//td//span[@class='keyboard__key-m']")
	List<WebElement> virtualKeyboardCharacterList;
	
	// Initializing the Page Objects:
	public YandexSearchPO() {
		PageFactory.initElements(d, this);
	}
	
	/* Method Mechanism - Provide Search Criteria in SearchBox , Hit Submit 
	 * Get the list of search result 
	 * verify search result list contains search criteria */
	public Boolean verify_Search_Relavant_Articles_Populated_When_Topic_Provided_And_Hit_Submit(String searchCriteria) {
		Boolean flag=true;
		
		//Provide Search Criteria in SearchBox , Hit Submit
		searchInputBox.sendKeys(searchCriteria);
		searchButton.click();
		TestUtils.takeScreenShot();
		
		//Verify list result contains text relevant to search
		System.out.println("Search Result Count = "+searchArticlesResult.size());
		for(int i=0;i<searchArticlesResult.size()-1;i++)
		{
			System.out.println(i+") Artical Search Result = "+searchArticlesResult.get(i).getText().toUpperCase());
			
			
			if(!searchArticlesResult.get(i).getText().toUpperCase().contains(searchCriteria.toUpperCase()))
			{
				//setting flag to false when result dosen`t contains search criteria
				System.out.println("Failed = "+searchArticlesResult.get(i).getText().toUpperCase());
				flag=false;
				break;
			}
		}
		//returning test result
		return flag;
	}
	
	/* Method Mechanism - Provide Search Criteria in SearchBox 
	 * Get the list of auto-suggestion
	 * verify auto-suggestion list contains search criteria */
	public Boolean Verify_When_User_Search_For_Particular_Topic_Autosuggestion_Starting_From_Same_Letters_Starts_Appearing(String searchCriteria) {
		Boolean flag=true;
		//Provide Search Criteria in SearchBox
		searchInputBox.sendKeys(searchCriteria);
		
		//Verify auto-suggestion for search contains text relevant to search
		System.out.println("Search Auto Suggestion Count = "+searchSuggestionList.size());
		for(int i=0;i<searchSuggestionList.size();i++)
		{
			System.out.println(i+") Auto Suggestion = "+searchSuggestionList.get(i).getText().toUpperCase());
			
			
			if(!searchSuggestionList.get(i).getText().toUpperCase().contains(searchCriteria.toUpperCase()))
			{
				//setting flag to false when auto-suggestion result dosen`t contains search criteria
				System.out.println("Failed = "+searchSuggestionList.get(i).getText().toUpperCase());
				flag=false;
				break;
			}
		}
		return flag;
	}

	
	/* Method Mechanism - Click on Virtual Keyboard Icon
	 *Once Virtual Keyboard appears , press letter to provide search criteria
	 * verify auto-suggestion list contains search criteria appear */
	public Boolean Verify_User_Is_Able_To_Provide_SearchCriteria_with_Onscreen_Keyboard_And_Relvant_SearchResult_Appear(String searchCriteria) {
		Boolean flag=true;
		
		WebElement keyboard=wait.until(ExpectedConditions.elementToBeClickable(virtualKeyboardIcon));
		//Click on Virtual Keyboard Icon and Press letter
		keyboard.click();
	
		
		
         System.out.println("providedCharacter = "+searchCriteria);
			System.out.println("Keyboard Letter = "+virtualKeyboardCharacterList.size());
			for(int i=0;i<virtualKeyboardCharacterList.size();i++)
			{
				System.out.println(i+" = "+virtualKeyboardCharacterList.get(i).getText());
				
				if(virtualKeyboardCharacterList.get(i).getText().toUpperCase().contains(searchCriteria.toUpperCase()))
				{
					System.out.println(searchCriteria.toUpperCase());
					virtualKeyboardCharacterList.get(i).click();
					
					//Verify auto-suggestion for search contains text relevant to search
					System.out.println("Search Auto Suggestion Count = "+searchSuggestionList.size());
					for(int k=0;k<searchSuggestionList.size();k++)
					{
						System.out.println(k+") Auto Suggestion = "+searchSuggestionList.get(k).getText().toUpperCase());
								
						if(!searchSuggestionList.get(k).getText().toUpperCase().contains(searchCriteria.toUpperCase()))
						{
					       //setting flag to false when auto-suggestion result dosen`t contains search criteria
							System.out.println("Failed = "+searchSuggestionList.get(k).getText().toUpperCase());
							flag=false;
							break;
						}
					}
					
				}				
			}
		
				
		return flag;
	}

}
