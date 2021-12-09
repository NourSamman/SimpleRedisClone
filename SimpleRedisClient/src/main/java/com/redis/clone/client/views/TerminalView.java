package com.redis.clone.client.views;

import com.flowingcode.vaadin.addons.xterm.ITerminalClipboard.UseSystemClipboard;
import com.flowingcode.vaadin.addons.xterm.ITerminalOptions.CursorStyle;
import com.flowingcode.vaadin.addons.xterm.TerminalHistory;
import com.flowingcode.vaadin.addons.xterm.TerminalTheme;
import com.flowingcode.vaadin.addons.xterm.XTerm;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.shared.ui.LoadMode;

@SuppressWarnings("serial")
@PageTitle("Terminal")
@Route(value = "terminal")
@RouteAlias("")
@StyleSheet(loadMode = LoadMode.EAGER, value = "context://global-style.css")
public class TerminalView extends VerticalLayout {

	private XTerm xterm;

	public TerminalView() {
		
		setSizeFull();
		setPadding(false);
		getElement().getStyle().set("background", "black");

		initTerminalConfig();

		add(xterm);
	}

	private void initTerminalConfig() {
		
		xterm = new XTerm();
		xterm.setPrompt("user@redis# ");

		xterm.writeln("################################# REDIS CLONE ################################");
		xterm.writeln("##									    ##");
		xterm.writeln("## Commands: SET, GET, INCR, DECR, RPUSH, RPOP, LPUSH, LPOP, LINDEX, EXPIRE ##");
		xterm.writeln("##									    ##");
		xterm.writeln("############################################################################## \n");
		xterm.writePrompt();

		xterm.setCursorBlink(true);
		xterm.setCursorStyle(CursorStyle.UNDERLINE);
		xterm.setSizeFull();

		xterm.setCopySelection(true);
		xterm.setUseSystemClipboard(UseSystemClipboard.READWRITE);
		xterm.setPasteWithMiddleClick(true);
		xterm.setPasteWithRightClick(true);

		TerminalHistory.extend(xterm);

		xterm.addLineListener(ev -> {
			String[] line = ev.getLine().toLowerCase().split("\\s+");
			Notification.show(line[0]);
			switch (line[0]) {
			case "color":
				if (line.length > 1) {
					if (line[1].equals("on")) {
						xterm.setTheme(
								new TerminalTheme().withBackground("rgb(255,255,255)").withForeground("rgb(0,0,0)"));
						break;
					} else if (line[1].equals("off")) {
						xterm.setTheme(new TerminalTheme());
						break;
					}
				}
				xterm.writeln("color on:  use TI-99/4A palette");
				xterm.writeln("color off: use default palette");
				break;
			default:
				if (!ev.getLine().trim().isEmpty()) {
					xterm.writeln("Unknown command: " + line[0]);
				}
			}

			xterm.writePrompt();
		});

		xterm.focus();
		xterm.fit();
	}
}
