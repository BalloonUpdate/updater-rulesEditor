package top.metime.updater.rulesEditor.callback;

/**
 * 主面板事件的回调接口
 * @author innc
 */
public interface MainWindowEventCallback 
{

	/**
	 * 当点击主面板的加载文件按钮时被调用
	 */
	public void onClickLoadFileButton();

	/**
	 * 当点击主面板的保存文件按钮时被调用
	 */
	public void onClickSaveFileButton();
	
	/**
	 * 当点击主面板的添加规则按钮时被调用
	 */
	public void onClickRuleAdd();

	/**
	 * 当点击主面板的重命名规则按钮时被调用
	 */
	public void onClickRuleRename();

	/**
	 * 当点击主面板的删除规则按钮时被调用
	 */
	public void onClickRuleRemove();
	
}
