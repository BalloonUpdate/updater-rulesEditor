package top.metime.updater.rulesEditor.callback;

/**
 * 文字编辑面板事件的回调
 * 
 * @author innc
 */
public interface InputTextPanelEventCallback
{

	/**
	 * 当点击文字编辑面板的确定按钮或者输入回车时被调用
	 * @param textFieldContent 文字内容
	 * @param tag 标签
	 */
	public void onInputTextPanelReturn(String textFieldContent, int tag);

	/**
	 * 但关闭文字编辑面板时被调用
	 */
	public void onInputTextPanelExit();
}
