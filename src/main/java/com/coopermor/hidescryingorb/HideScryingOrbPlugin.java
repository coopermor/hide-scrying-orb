package com.coopermor.hidescryingorb;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.VarbitChanged;
import net.runelite.api.events.WidgetLoaded;
import net.runelite.api.gameval.InterfaceID;
import net.runelite.api.gameval.VarbitID;
import net.runelite.api.widgets.Widget;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
	name = "Hide Scrying Orb"
)
public class HideScryingOrbPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private HideScryingOrbConfig config;

	@Override
	protected void startUp() throws Exception
	{
		log.debug("Hide Scrying Orb started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		resetWidgets();
		log.debug("Hide Scrying Orb stopped!");
	}

	@SuppressWarnings("unused")
	@Subscribe
	private void onConfigChanged(ConfigChanged event)
	{
		if (event.getGroup().equals("hidescryingorb"))
		{
			setWidgets(false);
		}
	}

	private void setWidgets(boolean overrideChat)
	{
		Widget wiggler = client.getWidget(InterfaceID.PohScryingPool.WIGGLER);
		if (wiggler != null)
		{
			wiggler.setHidden(config.hideOrb());
		}
		Widget overlay = client.getWidget(InterfaceID.PohScryingPool.CONTAINER_RECT0);
		if (overlay != null)
		{
			overlay.setHidden(config.hideTint());
		}
		Widget container = client.getWidget(InterfaceID.PohScryingPool.CONTAINER);
		Widget chatModern = client.getWidget(InterfaceID.ToplevelPreEoc.CHAT_CONTAINER);
		Widget chatClassic = client.getWidget(InterfaceID.ToplevelOsrsStretch.CHAT_CONTAINER);
		Widget chat = chatModern != null ? chatModern : chatClassic;
		if (chat == null)
		{
			return;
		}
		if (overrideChat)
		{
			chat.setHidden(false);
			return;
		}
		if (container != null)
		{
			chat.setHidden(config.hideChatbox());
		}
	}

	private void resetWidgets()
	{
		Widget wiggler = client.getWidget(InterfaceID.PohScryingPool.WIGGLER);
		if (wiggler != null)
		{
			wiggler.setHidden(false);
		}
		Widget container = client.getWidget(InterfaceID.PohScryingPool.CONTAINER_RECT0);
		if (container != null)
		{
			container.setHidden(false);
		}
		Widget chatModern = client.getWidget(InterfaceID.ToplevelPreEoc.CHAT_CONTAINER);
		Widget chatClassic = client.getWidget(InterfaceID.ToplevelOsrsStretch.CHAT_CONTAINER);
		Widget chat = chatModern != null ? chatModern : chatClassic;
		if (chat != null)
		{
			chat.setHidden(false);
		}
	}

	@SuppressWarnings("unused")
	@Subscribe
	public void onWidgetLoaded(final WidgetLoaded event)
	{
		if (event.getGroupId() != InterfaceID.POH_SCRYING_POOL)
		{
			return;
		}
		setWidgets(false);
	}

	@SuppressWarnings("unused")
	@Subscribe
	private void onVarbitChanged(final VarbitChanged event)
	{
		if (event.getVarbitId() != VarbitID.BUSY)
		{
			return;
		}
		setWidgets(event.getValue() == 0);
	}

	@SuppressWarnings("unused")
	@Provides
	HideScryingOrbConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(HideScryingOrbConfig.class);
	}
}
