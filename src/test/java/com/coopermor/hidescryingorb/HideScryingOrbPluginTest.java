package com.coopermor.hidescryingorb;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class HideScryingOrbPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(HideScryingOrbPlugin.class);
		RuneLite.main(args);
	}
}