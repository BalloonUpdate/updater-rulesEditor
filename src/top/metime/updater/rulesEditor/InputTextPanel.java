package top.metime.updater.rulesEditor;

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

public class InputTextPanel
{
	private InputTextPanelEventCallback eventCallback;
	private int selectedRuleIndex;
	
	private JFrame window;
	private Container rootPanel;
		private JTextField inputField;
		private JButton enter;
		
	private void init0(String inputFieldDefualtText)
	{
		window = new JFrame();
		rootPanel = window.getContentPane();
		inputField = new JTextField(inputFieldDefualtText);
		enter = new JButton("确定");
	}
	
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
					eventCallback.onInputTextPanelReturn(inputField.getText(), selectedRuleIndex);
					window.dispose();
				}
			}
			
		});
		
		enter.addActionListener((ActionEvent e)->
		{
			eventCallback.onInputTextPanelReturn(inputField.getText(), selectedRuleIndex);
			window.dispose();
		});
		
		
		
	}
	
	
	public void init(InputTextPanelEventCallback eventCallback, String inputFieldDefualtText, int selectedRuleIndex, JFrame superWindow)
	{
		this.eventCallback = eventCallback;
		this.selectedRuleIndex = selectedRuleIndex;
		superWindow.setVisible(false);
		
		init0(inputFieldDefualtText);
		init1();
		init2();
	}
}
