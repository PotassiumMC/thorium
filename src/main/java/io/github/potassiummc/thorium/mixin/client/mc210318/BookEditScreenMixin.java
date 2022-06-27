package io.github.potassiummc.thorium.mixin.client.mc210318;

import net.minecraft.client.gui.screen.ingame.BookEditScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(BookEditScreen.class)
public abstract class BookEditScreenMixin {

    @ModifyConstant(method = "method_27593(Ljava/lang/String;)Z", constant = @Constant(intValue = 16), allow = 1)
    private static int getCorrectMaxLength(int constant) {
        // <= 16 === < 17
        return 17;
    }

}
