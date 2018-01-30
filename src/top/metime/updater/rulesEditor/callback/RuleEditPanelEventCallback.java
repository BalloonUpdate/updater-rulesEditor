package top.metime.updater.rulesEditor.callback;

import java.util.ArrayList;

public interface RuleEditPanelEventCallback
{
	public void onRuleEditPanelReturn(ArrayList<String> ignoreFiles, String serverPath, String clientPath, int tag);
	public void onRuleEditPanelCancel();
}
