 Viteger Automate CRM

A production-ready **Selenium + TestNG + Java** automation framework for the [Vtiger CRM](https://www.vtiger.com/) 
platform, built with Maven, Page Object Model, Data-Driven Testing, and Extent Reports.

---

  Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 17 |
| Automation | Selenium WebDriver 4.43.0 |
| Test Runner | TestNG 7.12.0 |
| Build Tool | Maven |
| Reporting | ExtentReports 5.1.2 (Spark HTML) |
| Data-Driven | Apache POI 5.5.1 (Excel), JSON Simple, `.properties` |
| IDE | Eclipse |

---

 Project Structure

```
vtiger-automation-framework/
│
├── src/main/java/
│   ├── base_utility/
│   │   └── BaseClass.java              # Browser setup, login/logout lifecycle (@Before/@After)
│   ├── generic_utility/
│   │   ├── WebDriverUtility.java       # Reusable WebDriver helpers (waits, actions, screenshots)
│   │   ├── FileUtility.java            # Reads data from Excel (.xlsx) and JSON
│   │   └── JavaUtility.java            # Random number, timestamp generators
│   ├── listeners_utility/
│   │   └── List_Imp.java               # TestNG Listener — ExtentReports + auto screenshot on failure
│   └── object_repository/              # Page Object Model (POM) classes
│       ├── LoginPage.java
│       ├── HomePage.java
│       ├── LeadsPage.java
│       ├── ContactsPage.java
│       ├── OpportunityPage.java
│       ├── ProductsPage.java
│       ├── DocumentsPage.java
│       
│
├── src/test/java/
│   ├── crm/                            # Main CRM test suite
│   │   ├── login/       LoginTest.java
│   │   ├── lead/        LeadTest.java
│   │   ├── contact/     ContactTest.java
│   │   ├── opportunity/ OpportunityTest.java
│   │   ├── org/         CreateOrganizationTest.java
│   │   ├── products/    CreateProductTest.java
│   │   └── documents/   UploadDocumentTest.java
│   ├── ddt_extra/                      # DDT demos (Excel, JSON, Properties)
│   ├── pom_extra/                      # POM practice (SauceDemo)
│   ├── testng_extra/                   # TestNG concepts (Annotations, DataProvider, Reports)
│   └── types_of_execution/             # Batch / Parallel / Group XML demos
│
├── src/test/resources/
│   ├── commondata.json                 # Browser, URL, credentials config
│   ├── commondata.properties           # Alternate properties-based config
│   └── testScriptData.xlsx             # Test data for all CRM modules
│
├── advance_reports/                    # Auto-generated HTML Extent Reports
├── screenshots/                        # Auto-captured screenshots on test failure
│
├── batch.xml                           # Run selective test methods
├── parallel.xml                        # Parallel execution (3 threads)
├── group.xml                           # Group-based execution (smoke/regression)
├── cbt.xml                             # Cross-browser testing suite
└── pom.xml                             # Maven dependencies
```

---

 Test Coverage

| Module | Test Class | What's Automated |
|---|---|---|
| Login | `LoginTest` | Valid login + Home page navigation verification |
| Leads | `LeadTest` | Create Lead with Excel DDT + assertion |
| Contacts | `ContactTest` | Create Contact |
| Organizations | `CreateOrganizationTest` | Create Org with unique random name |
| Opportunities | `OpportunityTest` | Create Opportunity with popup window handling |
| Products | `CreateProductTest` | Create Product with dropdown selection |
| Documents | `UploadDocumentTest` | Upload Document / file attachment |

---

 Prerequisites

- Java 17+
- Maven 3.9+
- Chrome / Edge / Firefox browser installed
- Vtiger CRM running locally at `http://localhost:8888`

---

 Configuration

Update `src/test/resources/commondata.json` before running:

```json
{
  "bro": "chrome",
  "url": "http://localhost:8888",
  "un": "admin",
  "pwd": "*****"
}
```

Supported browser values: `chrome`, `edge`, `firefox`, `safari`

---

 How to Run

**Run full suite via Maven:**
```bash
mvn test
```

**Run specific TestNG XML suites in Eclipse:**
- Right-click any `.xml` file → **Run As → TestNG Suite**

| XML File | Purpose |
|---|---|
| `batch.xml` | Run selective test methods |
| `parallel.xml` | Run 3 modules in parallel (3 threads) |
| `group.xml` | Run `smoke` or `regression` group |
| `cbt.xml` | Cross-browser testing |

---

 Reports & Screenshots

- **HTML Extent Report** → auto-generated in `advance_reports/` after every suite run
- **Failure Screenshots** → auto-captured in `screenshots/` via TestNG Listener on any test failure

---

 Framework Design

- **Page Object Model (POM)** — UI locators separated from test logic
- **BaseClass** — Centralized `@BeforeClass` (browser launch) and `@BeforeMethod/@AfterMethod` (login/logout) so each test method always starts with a fresh session
- **Data-Driven Testing** — Test data read from Excel (`testScriptData.xlsx`), JSON (`commondata.json`), and `.properties` files via `FileUtility`
- **TestNG Listener** (`List_Imp`) — Hooks into `onTestFailure` to auto-capture screenshots and log to Extent Report
- **Random Data** — `JavaUtility.generateRandomNumber()` appended to names to prevent duplicate-record conflicts

---

 Author
**Preeti Chaurasiya**  
GitHub: [@preetichaurasiya22](https://github.com/preetichaurasiya22)
