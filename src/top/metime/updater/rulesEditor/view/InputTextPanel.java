package top.metime.updater.rulesEditor.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import top.metime.updater.rulesEditor.callback.InputTextPanelEventCallback;

/**
 * 文字编辑面板
 * @author innc
 */
public class InputTextPanel
{
	private InputTextPanelEventCallback eventCallback;
	private int tag;
	
	private JFrame window;
	private Container rootPanel;
		private JTextField inputField;
		private JButton enter;
	
	/**
	 * 实例化窗口里的控件
	 */
	private void init0(String inputFieldDefualtText)
	{
		window = new JFrame();
		rootPanel = window.getContentPane();
		inputField = new JTextField(inputFieldDefualtText);
		enter = new JButton("确定");
	}
	
	/**
	 * 对窗口里的空间进行布局
	 */
	//针对源代码的说明：调用hashCode();只是为了源代码的美观，在实际中没有任何用处
	private void init1()
	{
		window.setSize(300, 70);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		window.setAlwaysOnTop(true);
		rootPanel.setLayout(new BorderLayout());
		
			inputField.hashCode();
			rootPanel.add(inputField, BorderLayout.CENTER);
			inputField.hashCode();
			
			enter.hashCode();
			rootPanel.add(enter, BorderLayout.EAST);
			enter.hashCode();
	}
	
	/**
	 * 设置窗口可见，为控件们添加各种事件监听器
	 */
	private void init2()
	{
		window.setVisible(true);
		window.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				eventCallback.onInputTextPanelExit();
			}
			
		});
		
		inputField.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					eventCallback.onInputTextPanelReturn(inputField.getText().trim(), tag);
					window.dispose();
				}
			}
			
		});
		
		enter.addActionListener((ActionEvent e)->
		{
			eventCallback.onInputTextPanelReturn(inputField.getText().trim(), tag);
			window.dispose();
		});
		
		
		
	}
	
	/**
	 * 创建一个文字编辑面板
	 * 
	 * @param eventCallback 文字编辑面板的事件回调
	 * @param inputFieldDefualtText 文字编辑面板的默认文字
	 * @param tag 标签
	 * @param superWindow 父窗口
	 */
	public static void create(InputTextPanelEventCallback eventCallback, String inputFieldDefualtText, int tag, JFrame superWindow)
	{
		InputTextPanel panel = new InputTextPanel();
		
		panel.eventCallback = eventCallback;
		panel.tag = tag;
		superWindow.setVisible(false);
		
		panel.init0(inputFieldDefualtText);
		panel.init1();
		panel.init2();
	}
}
