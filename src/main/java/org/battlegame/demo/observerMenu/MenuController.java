package org.battlegame.demo.observerMenu;

import org.battlegame.demo.gui.arq.OptionMenuContainer;
import org.battlegame.demo.gui.utils.MenuRepo;

public class CentralMenu {
    private static final CentralMenu instance = new CentralMenu();
    private OptionMenuContainer currentMenuBehavior;
    public CentralMenu() {
    }

    public void changeCurrentMenuBehavior(EMenu menu) {
        currentMenuBehavior = MenuRepo.getInstance().findMenu(menu);
    }

    public OptionMenuContainer getCurrentMenuBehavior() {
        return currentMenuBehavior;
    }

    public static CentralMenu getInstance() {
        return instance;
    }
}
