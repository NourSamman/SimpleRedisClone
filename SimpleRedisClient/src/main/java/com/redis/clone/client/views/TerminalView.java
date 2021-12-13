package com.redis.clone.client.views;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.flowingcode.vaadin.addons.xterm.ITerminalClipboard.UseSystemClipboard;
import com.flowingcode.vaadin.addons.xterm.ITerminalOptions.CursorStyle;
import com.redis.clone.client.command.CommandGateway;
import com.flowingcode.vaadin.addons.xterm.TerminalHistory;
import com.flowingcode.vaadin.addons.xterm.XTerm;
import com.vaadin.flow.component.dependency.StyleSheet;
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

	Logger logger = LoggerFactory.getLogger(TerminalView.class);

	private XTerm xterm;

	@Autowired
	private CommandGateway command;

	public TerminalView() {

		initTerminalView();
		initTerminalConfig();

		add(xterm);
	}

	private void initTerminalView() {
		setSizeFull();
		setPadding(false);
		getElement().getStyle().set("background", "black");
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
			if (ev.getLine() != null && !ev.getLine().trim().isEmpty()) {

				if (ev.getLine().trim().equalsIgnoreCase("CLS"))
					xterm.clear();
				else {
					try {
						String res = command.run(ev.getLine());
						xterm.writeln(res);
					} catch (Exception e) {
						logger.error(e.getMessage());
						xterm.writeln(e.getMessage());
					}
				}
			}

			xterm.writePrompt();
		});

		xterm.focus();
		xterm.fit();
	}
}
