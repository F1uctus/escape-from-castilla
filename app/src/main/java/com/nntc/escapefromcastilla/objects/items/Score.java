package com.nntc.escapefromcastilla.objects.items;

import com.crown.items.InventoryItem;

public class Score extends InventoryItem {
    private int count;

    public Score(int count) {
        super("Score");
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
