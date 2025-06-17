package antiqueatlasautomarker.client;

import antiqueatlasautomarker.mixin.antiqueatlas.display.GuiToggleButtonAccessor;
import antiqueatlasautomarker.util.IUntoggleable;
import hunternif.mc.atlas.client.gui.GuiMarkerInList;
import hunternif.mc.atlas.client.gui.core.GuiComponent;
import hunternif.mc.atlas.client.gui.core.GuiScrollingContainer;
import hunternif.mc.atlas.client.gui.core.IButtonListener;
import hunternif.mc.atlas.registry.MarkerRegistry;
import hunternif.mc.atlas.registry.MarkerType;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Mouse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GuiHideMarkerTypeSelect extends GuiComponent {
    private static final int TYPE_SPACING = 1;

    private final GuiScrollingContainer scroller;

    private IMarkerTypeSelectListener listener;

    private final List<String> disabledMarkers;
    private final List<GuiMarkerInList> buttons = new ArrayList<>();

    public GuiHideMarkerTypeSelect(List<String> disabledMarkers) {
        this.scroller = new GuiScrollingContainer();
        this.scroller.setWheelScrollsHorizontally();
        this.addChild(this.scroller);

        this.disabledMarkers = disabledMarkers;
    }

    public void setListener(IMarkerTypeSelectListener listener) {
        this.listener = listener;
    }

    @Override
    public void initGui() {
        this.scroller.removeAllContent();
        int typeCount = (int) MarkerRegistry.getValues().stream().filter(t -> !t.isTechnical()).count();

        int allTypesWidth = typeCount *
                (GuiMarkerInList.FRAME_SIZE + TYPE_SPACING) - TYPE_SPACING;
        int scrollerWidth = Math.min(allTypesWidth, 300);
        this.scroller.setViewportSize(scrollerWidth, GuiMarkerInList.FRAME_SIZE);
        this.scroller.setGuiCoords((this.width - scrollerWidth)/2 - 5, Math.max(0, this.height/2 - 150));

        int contentX = 0;
        for (MarkerType markerType : MarkerRegistry.getValues()) {
            if (markerType.isTechnical()) continue;
            GuiMarkerInList markerButton = new GuiMarkerInList(markerType);
            ((IUntoggleable) markerButton).aaam$setIsUntoggleable();

            //Set the buttons to already be selected if those marker types are not disabled
            markerButton.setSelected(!this.disabledMarkers.contains(markerType.getRegistryName().toString()));

            markerButton.addListener((IButtonListener<GuiMarkerInList>) button -> {
                        listener.onSelectMarkerType(button.getMarkerType(), ((GuiToggleButtonAccessor) button).getIsSelected());

                        //Selects/unselects all except the one clicked
                        //If shift enabling a button, will disable all others
                        //If shift disabling a button, will enable all others
                        if(GuiScreen.isShiftKeyDown())
                            buttons.forEach(b -> b.setSelected(b.equals(button) == ((GuiToggleButtonAccessor) button).getIsSelected()));
                    }
            );

            buttons.add(markerButton);

            this.scroller.addContent(markerButton).setRelativeX(contentX);
            contentX += GuiMarkerInList.FRAME_SIZE + TYPE_SPACING;
        }
    }

    public interface IMarkerTypeSelectListener {
        void onSelectMarkerType(MarkerType markerType, boolean gotEnabled);
    }

    @Override
    public void handleMouseInput() throws IOException {
        //No scrolling
        if (Mouse.getEventDWheel() == 0) super.handleMouseInput();
    }
}
