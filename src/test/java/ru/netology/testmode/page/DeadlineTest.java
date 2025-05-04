package ru.netology.testmode.page;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.testmode.data.SQLHelper;

import static ru.netology.testmode.data.DataHelper.generateRandomLogin;
import static ru.netology.testmode.data.DataHelper.generateRandomPassword;

@Slf4j
public class DeadlineTest {

    @BeforeEach
    void setUp() {
        SQLHelper.updateUsers(generateRandomLogin(), generateRandomPassword());
        SQLHelper.updateUsers(generateRandomLogin(), generateRandomPassword());
    }

    @Test
    void deadlineTest() {
        var count = SQLHelper.countUsers();
        log.info(String.valueOf(count));
        var first = SQLHelper.getFirstUser();
        log.info(String.valueOf(first));
        System.out.println(first);
        var all = SQLHelper.getUsers();
        log.info(String.valueOf(all));
    }
}
