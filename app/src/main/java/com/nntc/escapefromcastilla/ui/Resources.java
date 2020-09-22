package com.nntc.escapefromcastilla.ui;

import java.util.ListResourceBundle;

public class Resources {
    public static class Russian extends ListResourceBundle {
        protected Object[][] getContents() {
            return new Object[][]{
                {"message.ok", "Действие выполнено"},
                {"delta.outOfBounds", "Если это действие выполнится, свойство выйдет за границы диапазона"},
                {"move.lowEnergy", "Я слишком устал, чтобы дойти туда"},
                {"move.obstacle", "Не могу туда попасть"}
            };
        }
    }

    public static class English extends ListResourceBundle {
        protected Object[][] getContents() {
            return new Object[][]{
                {"message.ok", "Action successful"},
                {"delta.outOfBounds", "Property went out of range while executing action"},
                {"move.lowEnergy", "I'm too tired to get there"},
                {"move.obstacle", "I can't get there"}
            };
        }
    }
}
