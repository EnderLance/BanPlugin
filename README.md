BanPlugin
=========

The new version of the Ban plugin for Amestria


This is a plugin which basically rewrites the /ban command on minecraft bukkit servers.

It makes no use of the default pardon command. Perhaps I should disable it?

Here is an overview of the plugin.

/ban is the only command. It functions only with two arguments: <player> and <reason>. The reason cannot contain spaces,
but it can contain underscores(_), dashes(-), and dots(.). So an example would be 
  
  /ban EnderLance griefing_the_noobs_house.
  
When this is done, the player will receive a message: 
  
  You have banned EnderLance.

If this is done from a console, it will say: 

  Player EnderLance's ban status is now true.

In the log file, banLog.txt which is found in /path/to/server/plugins/folder/BanPlugin/banLog.txt

It looks a bit like this:

  EnderLance	has changed the ban status of	EnderLance	to	true	for	griefing_the_noobs_house.

And when the console bans EnderLance it says:

  CONSOLE		  has changed the ban status of	EnderLance	to	true	for	griefing_the_noobs_house.

To unban a player, repeat the command. You can specify a different reason:

  /ban EnderLance he_has_been_forgiven.
  
and the log will say:

  CONSOLE		has changed the ban status of	EnderLance	to	false	for	he_has_been_forgiven.



Thanks for looking at this plugin! Have a great day!

- EnderLance
