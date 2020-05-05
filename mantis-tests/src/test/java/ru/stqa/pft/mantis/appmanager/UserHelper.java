package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

public class UserHelper extends HelperBase {
    public UserHelper(ApplicationManager app) {
        super(app);
    }

    public UserData getUserFromBD() {
        Users users = app.db().users();
        return users.stream().filter((u) -> u.getAccessLevel() != 90).iterator().next();
    }

    public void resetPassword() {
        click(By.cssSelector("input[value=\"Сбросить пароль\"]"));
    }

    public void login(String username, String password) {
        type(By.name("username"), username);
        click(By.cssSelector("input[value=\"Войти\"]"));
        type(By.name("password"), password);
        click(By.cssSelector("input[value=\"Войти\"]"));
    }
}
