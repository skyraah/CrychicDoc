package fr.frinn.custommachinery.common.guielement;

import fr.frinn.custommachinery.api.codec.NamedCodec;
import fr.frinn.custommachinery.api.guielement.GuiElementType;
import fr.frinn.custommachinery.common.init.Registration;
import fr.frinn.custommachinery.impl.guielement.AbstractGuiElement;
import fr.frinn.custommachinery.impl.guielement.AbstractTexturedGuiElement;
import net.minecraft.resources.ResourceLocation;

public class PlayerInventoryGuiElement extends AbstractTexturedGuiElement {

    public static final ResourceLocation BASE_TEXTURE = new ResourceLocation("custommachinery", "textures/gui/base_inventory.png");

    public static final NamedCodec<PlayerInventoryGuiElement> CODEC = NamedCodec.record(playerInventoryGuiElement -> playerInventoryGuiElement.group(makePropertiesCodec(BASE_TEXTURE).forGetter(AbstractGuiElement::getProperties)).apply(playerInventoryGuiElement, PlayerInventoryGuiElement::new), "Player inventory gui element");

    public PlayerInventoryGuiElement(AbstractGuiElement.Properties properties) {
        super(properties);
    }

    @Override
    public GuiElementType<PlayerInventoryGuiElement> getType() {
        return (GuiElementType<PlayerInventoryGuiElement>) Registration.PLAYER_INVENTORY_GUI_ELEMENT.get();
    }
}