package org.battlegame.demo.observerMenu;

import org.battlegame.demo.gui.build.OptionMenuContainer;
import org.battlegame.demo.gui.utils.MenuRepo;

public class MenuController {
    private static final MenuController instance = new MenuController();
    private OptionMenuContainer currentMenuBehavior;
    public MenuController() {

    }

    public void changeCurrentMenuBehavior(EMenu menu) {
        currentMenuBehavior = MenuRepo.getInstance().findMenu(menu);
    }

    public OptionMenuContainer getCurrentMenuBehavior() {
        return currentMenuBehavior;
    }

    public static MenuController getInstance() {
        return instance;
    }
}
