package ru.netology.testmode.page;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    public VerificationPage() {
        $("[data-test-id=code]").shouldBe(visible);
    }

    public DashboardPage validVerify(String verificationCode) {
        $("[data-test-id=code] input").setValue(verificationCode);
        $("[data-test-id=action-verify]").click();
        return new DashboardPage();
    }

    public void invalidVerify(String verificationCode) {
        $("[data-test-id=code] input").setValue(verificationCode);
        $("[data-test-id=action-verify]").click();
        $("[data-test-id=error-notification]").shouldBe(visible);
    }
}