package org.battlegame.demo.gui.imp;

import javafx.util.Duration;
import org.battlegame.demo.gui.build.OptionDTO;
import org.battlegame.demo.gui.build.OptionListener;
import org.battlegame.demo.gui.build.OptionMenuContainer;
import org.battlegame.demo.gui.transitions.ETransitions;
import org.battlegame.demo.gui.transitions.IMenuTransitional;
import org.battlegame.demo.observerMenu.EMenu;
import org.battlegame.demo.observerMenu.MenuController;

import java.util.List;

public class MainMenu extends OptionMenuContainer  implements IMenuTransitional {

    public MainMenu(MenuController menuController) {
        super(
                menuController,
                List.of(
                        new OptionListener(
                                new OptionDTO("Options")
                        ),
                        new OptionListener(
                                new OptionDTO("Back")
                        ),
                        new OptionListener(
                                new OptionDTO("Exit")
                        )
                )
        );
    }

    @Override
    public boolean onEnterPressed() {
        switch (getOptionIndex()) {
            case 0 -> toOptionsMenu();
            case 1 -> toNone();
            case 2 -> toExit();
        }
        return true;
    }

    private void toExit() {
        getMenuController().changeCurrentMenuBehavior(EMenu.EXIT);
    }
    public void toOptionsMenu() {
        getMenuController().changeCurrentMenuBehavior(EMenu.OPTIONS);
    }
    public void toNone() {
        System.out.println("Already in Main Menu!");
    }

    @Override
    public void startTransition() {
        ETransitions.OPEN.startTransition(Duration.millis(200), this);
    }
}
