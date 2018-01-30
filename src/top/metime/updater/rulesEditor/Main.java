package top.metime.updater.rulesEditor;

public class Main
{
	public static void main(String[] args)
	{
//		new MainWindow(null).init();
//		new RuleEditPanel().init(null, new HashSet<String>(), "", "");
		new MainPanel().init();
//		new InputTextPanel().init(new InputTextPanelEventCallback()
//		{
//			@Override
//			public void onEnter(String textFieldContent)
//			{
//				System.out.println("onEnter() "+textFieldContent);
//			}
//
//			@Override
//			public void onExit()
//			{
//				System.out.println("Exit");
//			}
//		}, "AG");
	}
}
