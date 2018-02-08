package top.metime.updater.rulesEditor.callback;

import java.util.ArrayList;

/**
 * 规则编辑面板事件的回调接口
 * @author innc
 */
public interface RuleEditPanelEventCallback
{

	/**
	 * 当点击规则编辑面板的确定按钮时被调用
	 * 
	 * @param ignoreFiles 忽略的文件
	 * @param serverPath 服务端路径
	 * @param clientPath 客户端路径
	 * @param tag 标签
	 */
	public void onRuleEditPanelReturn(ArrayList<String> ignoreFiles, String serverPath, String clientPath, int tag);

	/**
	 * 当关闭规则编辑面板时被调用
	 */
	public void onRuleEditPanelCancel();
	
	
//	/**
//	 * 当点击规则编辑面板的添加忽略文件按钮时被调用
//	 */
//	public void onClickIgnoreFilesAdd();
//
//	/**
//	 * 当点击规则编辑面板的编辑忽略文件按钮时被调用
//	 */
//	public void onClickIgnoreFilesEdit();
//
//	/**
//	 * 当点击规则编辑面板的删除忽略文件按钮时被调用
//	 */
//	public void onClickIgnoreFilesRemove();
	
}
