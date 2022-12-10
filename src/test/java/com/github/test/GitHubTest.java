package com.github.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class GitHubTest {

    private final SelenideElement searchInput = $(".header-search-input"),
                                  issueTab = $("#issues-tab");
    private static final String URL= "https://github.com";
    private static final String REPO = "ostepovoyv/qa_lesson8";
    private static final String ISSUE_NAME = "Test issue";

    @Test
    public void testGitHubSelenide() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        open(URL);
        searchInput.click();
        searchInput.sendKeys(REPO);
        searchInput.submit();
        $(linkText(REPO)).click();
        issueTab.click();
        $(withText(ISSUE_NAME)).should(Condition.exist);
    }


    @Test
    public void testGitHubLambda() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем сайт GitHub", ()->{
            open(URL);
        });
        step("Ищем репозитрий " + REPO, ()->{
            searchInput.click();
            searchInput.sendKeys(REPO);
            searchInput.submit();
        });
        step("Открываем репозиторий " + REPO, ()->{
            $(linkText(REPO)).click();
        });
        step("Переходим на вкладку Issues", ()->{
            issueTab.click();
        });
        step("Проверяем наличие issue с наименованием " + ISSUE_NAME, ()->{
            $(withText(ISSUE_NAME)).should(Condition.exist);
        });
    }

    @Test
    public void testGitHubSelenideSteps() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Steps steps = new Steps();
        steps.openMainPage(URL)
                .searchRepo(REPO)
                .openRepo(REPO)
                .openIssueTab()
                .checkIssue(ISSUE_NAME);
    }

}
