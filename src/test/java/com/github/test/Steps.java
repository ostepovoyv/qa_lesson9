package com.github.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class Steps {

    private final SelenideElement searchInput = $(".header-search-input"),
                                  issueTab = $("#issues-tab");

    @Step("Открываем главную страницу")
    public Steps openMainPage(String url) {
        open(url);
        return this;
    }

    @Step("Ищем репозитрий {repo}")
    public Steps searchRepo(String repo) {
        searchInput.click();
        searchInput.sendKeys(repo);
        searchInput.submit();
        return this;
    }

    @Step("Открываем репозиторий {repo}")
    public Steps openRepo(String repo) {
        $(linkText(repo)).click();
        return this;
    }

    @Step("Открываем таб Issues")
    public Steps openIssueTab() {
        issueTab.click();
        return this;
    }

    @Step("Проверяем наличие Issue с названием {issue}")
    public Steps checkIssue(String issue) {
        $(withText(issue)).should(Condition.exist);
        return this;
    }

}
