package object_repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * CasesPage POM — Elements for the Cases (Support) module.
 * @author Preeti
 */
public class CasesPage {

    public CasesPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "img[title='Create Case...']")
    private WebElement createCaseBtn;

    @FindBy(name = "title")
    private WebElement caseTitleField;

    @FindBy(name = "status")
    private WebElement statusDropdown;

    @FindBy(name = "priority")
    private WebElement priorityDropdown;

    @FindBy(css = "input[title='Save [Alt+S]']")
    private WebElement saveBtn;

    @FindBy(className = "dvHeaderText")
    private WebElement headerText;

    public WebElement getCreateCaseBtn()    { return createCaseBtn;    }
    public WebElement getCaseTitleField()   { return caseTitleField;   }
    public WebElement getStatusDropdown()   { return statusDropdown;   }
    public WebElement getPriorityDropdown() { return priorityDropdown; }
    public WebElement getSaveBtn()          { return saveBtn;          }
    public String getCaseHeader()           { return headerText.getText(); }
}
