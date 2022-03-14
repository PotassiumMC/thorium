<img alt="thorium Icon" src="src/main/resources/assets/thorium/icon.png" width="128">

# thorium

[![Discord Shield](https://discordapp.com/api/guilds/938463953644847205/widget.png?style=shield)](https://discord.gg/bXG8H6PVuS)
[![Build Status](https://img.shields.io/github/workflow/status/PotassiumMC/thorium/build/master)](https://github.com/PotassiumMC/thorium/actions)
[![License](https://img.shields.io/github/license/PotassiumMC/thorium)](https://github.com/PotassiumMC/thorium/blob/master/LICENSE)
[![Latest Release](https://img.shields.io/github/v/release/PotassiumMC/thorium)](https://github.com/PotassiumMC/thorium/releases)
[![CurseForge Download Count](https://cf.way2muchnoise.eu/full_thorium_downloads.svg)](https://www.curseforge.com/minecraft/mc-mods/thorium)

thorium is a fabric mod that fixes 50+ small bugs and annoyances in Minecraft, without affecting gameplay mechanics.

Because thorium does not affect gameplay mechanics, it is not a hard requirement to have installed on the client or the server when joining a server.
However, we still recommend installing it on both, since not all issues can be fixed on the server (or client) side.

If you have encountered an issue or would like to request a fix for a Minecraft bug, please create an issue on our [issue tracker](https://github.com/PotassiumMC/thorium/issues/new/choose).

For support, check out our [Discord](https://discord.gg/bXG8H6PVuS) or [IRC Channels](https://webchat.esper.net/?channels=potassium)!

### Fixed issues

<!-- Sorry for the horrible table formatting -->
| Mojira Issue | Issue name |
|---|---|
| [MC-4490](https://bugs.mojang.com/browse/MC-4490) | Fishing line not attached to fishing rod in third person while crouching |
| [MC-6431](https://bugs.mojang.com/browse/MC-6431) | Status effects are lost when returning to the overworld from the exit end portal |
| [MC-12062](https://bugs.mojang.com/browse/MC-12062) | Hotbar selection resets to the far left when exiting the end |
| [MC-35361](https://bugs.mojang.com/browse/MC-35361) | Inventory opening is detected while in Nether Portal |
| [MC-46503](https://bugs.mojang.com/browse/MC-46503) | You can retain a mob's shader in spectator mode by running /kill |
| [MC-46737](https://bugs.mojang.com/browse/MC-46737) | Spectating entities whilst in third person still applies the mob's shader |
| [MC-46766](https://bugs.mojang.com/browse/MC-46766) | Mining a block in Survival, then changing to Spectator creates a breaking animation and sound |
| [MC-55347](https://bugs.mojang.com/browse/MC-55347) | Title with long duration shows in other world |
| [MC-69216](https://bugs.mojang.com/browse/MC-69216) | Switching to spectator mode while fishing keeps rod cast |
| [MC-75721](https://bugs.mojang.com/browse/MC-75721) | Arrow buttons within the book GUI are rendered above tooltips |
| [MC-81773](https://bugs.mojang.com/browse/MC-81773) | Bows and tridents drawn in survival/creative/adventure mode can be released in spectator mode |
| [MC-84661](https://bugs.mojang.com/browse/MC-84661) | Glowing is considered a positive effect in potion item tooltips |
| [MC-84873](https://bugs.mojang.com/browse/MC-84873) | DeathTime values 20+ cause corrupted mobs |
| [MC-86252](https://bugs.moajng.com/browse/MC-86252) | Shields stay blocking and allow left-clicking under a specific condition |
| [MC-115092](https://bugs.mojang.com/browse/MC-115092) | Squid/glow squid named "Dinnerbone" or "Grumm" is not upside-down |
| [MC-119417](https://bugs.mojang.com/browse/MC-119417) | A spectator can occupy a bed if they enter it and then are switched to spectator mode |
| [MC-123605](https://bugs.mojang.com/browse/MC-123605) | Debug world still sets clear weather time instead of deactivating gamerule doWeatherCycle |
| [MC-123450](https://bugs.mojang.com/browse/MC-123450) | Item frames play sound when item frame item is read from NBT |
| [MC-127970](https://bugs.mojang.com/browse/MC-127970) | Using riptide on a trident with an item in your off-hand causes visual glitch with the item in your offhand |
| [MC-129909](https://bugs.mojang.com/browse/MC-129909) | Players in spectator mode continue to consume foods and liquids shortly after switching game modes |
| [MC-139041](https://bugs.mojang.com/browse/MC-139041) | The sounds of fishing bobbers aren't controlled by the "Players" sound slider |
| [MC-143474](https://bugs.mojang.com/browse/MC-143474) | Respawning causes your hotbar to reset to the first space |
| [MC-147766](https://bugs.mojang.com/browse/MC-147766) | Shift key stays pressed until press any other key |
| [MC-148993](https://bugs.mojang.com/browse/MC-148993) | While spectating a player, chunks cannot be loaded |
| [MC-151412](https://bugs.mojang.com/browse/MC-151412) | "Edit Server Info" window does not focus "Server Name" text field automatically |
| [MC-159283](https://bugs.mojang.com/browse/MC-159283) | The End terrain does not generate in multiple rings centered around the world center |
| [MC-168016](https://bugs.mojang.com/browse/MC-168016) | The sounds of pages in books rustling aren't controlled by any sound sliders |
| [MC-170462](https://bugs.mojang.com/browse/MC-170462) | Bad Omen is considered a positive effect in potion item tooltips |
| [MC-175622](https://bugs.mojang.com/browse/MC-175622) | Wolf's tail will spin 360 degrees if max health is increased |
| [MC-181412](https://bugs.mojang.com/browse/MC-181412) | Removing a jukebox with a command while its playing a music disc, then the music will continue playing |
| [MC-187539](https://bugs.mojang.com/browse/MC-187539) | "tick" function tag runs before "load" instead of the other way around |
| [MC-193343](https://bugs.mojang.com/browse/MC-193343) | Soul speed effect remains after switching to spectator mode |
| [MC-195732](https://bugs.mojang.com/browse/MC-195732) | Going through Nether/End Portal while eating food or item causes two food items to be consumed in total |
| [MC-201723](https://bugs.mojang.com/browse/MC-201723) | Statistics sprites don't look pressed when clicked |
| [MC-202637](https://bugs.mojang.com/browse/MC-202637) | Last sound clip of eating will still play when Players volume is set to 0% |
| [MC-206705](https://bugs.mojang.com/browse/MC-206705) | Spyglasses stay in use in spectator mode |
| [MC-210318](https://bugs.mojang.com/browse/MC-210318) | Maximum length of book title changed from 16 to 15 characters |
| [MC-212926](https://bugs.mojang.com/browse/MC-212926) | Eating while entering a nether portal can auto skip the eating animation |
| [MC-215530](https://bugs.mojang.com/browse/MC-215530) | The freezing effect isn't immediately removed when switching into spectator mode |
| [MC-215531](https://bugs.mojang.com/browse/MC-215531) | The carved pumpkin overlay isn't removed when switching into spectator mode |
| [MC-225364](https://bugs.mojang.com/browse/MC-225364) | Chorus flowers can be shot in adventure |
| [MC-227337](https://bugs.mojang.com/browse/MC-227337) | When a shulker bullet hits an entity, the explodes sound is not played and particles are not produced |
| [MC-230603](https://bugs.mojang.com/browse/MC-230603) | Wolf ears and legs aren't mirrored |
| [MC-237843](https://bugs.mojang.com/browse/MC-237843) | Players can be idle kicked whilst viewing the end credits |
| [MC-244694](https://bugs.mojang.com/browse/MC-244694) | The sounds of goats stomping and ramming aren't controlled by the "Friendly Creatures" sound slider |
| [MC-244948](https://bugs.mojang.com/browse/MC-244948) | The minecraft:item.bundle.remove_one sound plays even when no items are unpacked from bundles |
| [MC-245394](https://bugs.mojang.com/browse/MC-245394) | The sounds of raid horns blaring aren't controlled by the correct sound slider |
| [MC-246257](https://bugs.mojang.com/browse/MC-246257) | The horns of some entities aren't mirrored | 
| [MC-246258](https://bugs.mojang.com/browse/MC-246258) | Hoglins' and zoglins' tusks aren't mirrored |
| [MC-248191](https://bugs.mojang.com/browse/MC-248191) | Vindicators' arms are misaligned when celebrating |
| [MC-249054](https://bugs.mojang.com/browse/MC-249054) | Closing inventory by clicking outside of GUI doesn't close inventory properly |
