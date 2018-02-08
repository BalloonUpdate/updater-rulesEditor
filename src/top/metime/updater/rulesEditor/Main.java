package top.metime.updater.rulesEditor;

import top.metime.updater.rulesEditor.view.MainPanel;

/**
 * 程序的入口类
 * @author innc
 */
public class Main
{
	public static final String RULE_FILE_NAME = "serverRules.json";
	
	/**
	 * 程序的入口方法
	 * @param args Class参数
	 */
	public static void main(String[] args)
	{
		//从一个主面板开始
		new MainPanel().init();
	}
}
