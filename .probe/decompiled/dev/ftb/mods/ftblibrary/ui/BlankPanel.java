package dev.ftb.mods.ftblibrary.ui;

public class BlankPanel extends Panel {

    private final String id;

    public BlankPanel(Panel panel) {
        this(panel, "");
    }

    public BlankPanel(Panel panel, String _id) {
        super(panel);
        this.id = _id;
    }

    @Override
    public void clearWidgets() {
    }

    @Override
    public void addWidgets() {
    }

    @Override
    public void alignWidgets() {
    }

    @Override
    public String toString() {
        return this.id.isEmpty() ? super.toString() : this.id;
    }
}