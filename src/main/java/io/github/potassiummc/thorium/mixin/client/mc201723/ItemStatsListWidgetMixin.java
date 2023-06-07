package io.github.potassiummc.thorium.mixin.client.mc201723;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.client.gui.screen.StatsScreen;
import net.minecraft.client.gui.widget.AlwaysSelectedEntryListWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(net.minecraft.client.gui.screen.StatsScreen.ItemStatsListWidget.class)
public class ItemStatsListWidgetMixin extends AlwaysSelectedEntryListWidget<StatsScreen.ItemStatsListWidget.Entry> {

    @Shadow
    protected int selectedHeaderColumn;

    public ItemStatsListWidgetMixin(MinecraftClient minecraftClient, int i, int j, int k, int l, int m) {
        super(minecraftClient, i, j, k, l, m);
    }

    // Based on code analysis by ISRosillo14: https://bugs.mojang.com/browse/MC-201723
    @Redirect(method = "renderHeader", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Mouse;wasLeftButtonClicked()Z"))
    private boolean renderHeaderWasLeftClicked(Mouse instance) {
        return true;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (button == 0) {
            selectedHeaderColumn = -1;
        }

        return super.mouseReleased(mouseX, mouseY, button);
    }

}
