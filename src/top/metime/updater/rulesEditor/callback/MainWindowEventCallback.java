package top.metime.updater.rulesEditor.callback;

public interface MainWindowEventCallback 
{
	public void onClickLoadFileButton();
	public void onClickSaveFileButton();
	
	public void onClickRuleAdd();
	public void onClickRuleRename();
	public void onClickRuleRemove();
	
	public void onClickIgnoreFilesAdd();
	public void onClickIgnoreFilesEdit();
	public void onClickIgnoreFilesRemove();
}
