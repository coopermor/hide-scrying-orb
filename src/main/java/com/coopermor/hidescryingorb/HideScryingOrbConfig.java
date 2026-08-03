package com.coopermor.hidescryingorb;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("hidescryingorb")
public interface HideScryingOrbConfig extends Config
{
	@ConfigItem(
		keyName = "hideOrb",
		name = "Hide Orb",
		description = "Hide orb in the middle of the screen"
	)
	default boolean hideOrb()
	{
		return true;
	}

	@ConfigItem(
		keyName = "hideTint",
		name = "Hide Tint",
		description = "Hide full screen purple tint"
	)
	default boolean hideTint()
	{
		return true;
	}
	@ConfigItem(
		keyName = "hideChatbox",
		name = "Hide Chatbox",
		description = "Hide chatbox (in modern layout)"
	)
	default boolean hideChatbox()
	{
		return false;
	}
}
