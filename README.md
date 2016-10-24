##Jon's Exclusives
###A modpack utility mod made for Minecraft.

[Features](#features)  
[How to use](#howToUse)  
[Server side, client side](#serverSideClientSide)  
[Custom cape design](#customCapeDesign)

<a name="features"/>
###Features:
#####v1.0.1 for Minecraft 1.7.10/1.8.9/1.9.4/1.10.2 introduces:
* Custom capes for donors, or any other specific type of users, using the [DeveloperCapes](https://github.com/jadar/DeveloperCapes) libraries, based on a remote cape json file (that can be hosted in GitHub, too)
* Custom player login events for special times, with optional fireworks, and optional automatic messages for surpassing x amounts of downloads (for Technic modpacks only) - all client sided.

<a name="howToUse"/>
###How to use:
#####Check out [`example`](https://github.com/xJon/Jons-Exclusives/tree/1.9.4/1.10.2/example) for an example of the local configs (.cfg that you put in modpacks), the remote configs (.json), and the remote cape file (.json), (both stored online). The remote configs have a few modes, as followed:
* 0: Basic message (uses message as a string).
* 1: Message (mainly for coloring) + "Thank you for x downloads!". 
shows up until y downloads are reached (x and y are specified in the remote config json file). Will work only for Technic modpacks with the modpack slug setup in the local config and if Technic's API works.
* 2: Message (mainly for coloring) + player name + ", have fun playing!".
* 3 plus or -1 minus: Unimplemented, would return an error in the log (won't crash).

For coloring and formatting use MOTD codes in the message string, specified [here](http://minecraft.gamepedia.com/Formatting_codes#Use_in_server.properties_and_pack.mcmeta).

<a name="serverSideClientSide"/>
###Server side, client side
* For the cape feature only, the mod does not have to be installed server-side. You can see other's capes, even if playing in servers without the mod, as long as you all have the same cape json file (that can be found in the `example` package).
* For the custom login events feature, the mod does have to be installed server-side unfortunately due to Minecraft limitations. That being said, it can be fully controllable through the server's configuration file.

<a name="customCapeDesign"/>
###Custom cape design
> The cape image files are 22x17, and should be of the PNG format. Additionally, there is support for high definition capes. Those must be of the size that is divisible by 16. The height must be half of the width. So 1024x512, 2048x1024, and so on. Host them on a server, Dropbox, or GitHub.
