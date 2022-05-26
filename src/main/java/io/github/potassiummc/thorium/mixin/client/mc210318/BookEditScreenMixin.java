package io.github.potassiummc.thorium.mixin.client.mc210318;

import net.minecraft.client.gui.screen.ingame.BookEditScreen;
import net.minecraft.client.util.SelectionManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BookEditScreen.class)
public abstract class BookEditScreenMixin {

    @Final
    @Mutable
    @Shadow
    private SelectionManager bookTitleSelectionManager;

    @Shadow
    private String title;

    @Shadow
    protected abstract String getClipboard();

    @Shadow
    protected abstract void setClipboard(String clipboard);

    @Inject(method = "<init>", at = @At(value = "TAIL"))
    private void modifyBookTitleSelectionManager(PlayerEntity player, ItemStack itemStack, Hand hand, CallbackInfo ci) {
        this.bookTitleSelectionManager = new SelectionManager(() -> this.title, (title) -> this.title = title, this::getClipboard, this::setClipboard, (string) -> string.length() <= 16);
    }

}
