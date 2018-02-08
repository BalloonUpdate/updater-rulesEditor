package top.metime.updater.rulesEditor.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListModel;
import top.metime.updater.rulesEditor.callback.InputTextPanelEventCallback;
import top.metime.updater.rulesEditor.callback.RuleEditPanelEventCallback;

/**
 * 规则编辑面板
 * @author innc
 */
public class RuleEditPanel implements InputTextPanelEventCallback
{
	private RuleEditPanelEventCallback eventCallback;
	private int tag;
	
	private JFrame window;
	private JMenuBar menuBar;
		private JButton enter;
	private Container rootPanel;
		private JPanel ignoreFileEditPanel;
			private JLabel ignoreFilesDesc;
			private JList ignoreFilesList;
			private JPanel ignoreFilesActionPanel;
				private JButton ignoreFilesAdd;
				private JButton ignoreFilesEdit;
				private JButton ignoreFilesRemove;
		private JPanel pathEditPanel;
			private JLabel serverPathDesc;
			private JTextField serverPathEdit;
			private JLabel clientPathDesc;
			private JTextField clientPathEdit;
		
	
	private RuleEditPanel()
	{
		
	}
	
	/**
	 * 实例化窗口里的控件
	 */
	private void init0()
	{
		window = new JFrame();
		menuBar = new JMenuBar();
		enter = new JButton("确定");
		rootPanel = window.getContentPane();
		pathEditPanel = new JPanel();
		serverPathDesc = new JLabel("服务端路径：");
		serverPathEdit = new JTextField();
		clientPathDesc = new JLabel("客户端路径：");
		clientPathEdit = new JTextField();
		ignoreFileEditPanel = new JPanel();
		ignoreFilesDesc = new JLabel("忽略的文件列表：");
		ignoreFilesList = new JList();
		ignoreFilesActionPanel = new JPanel();
		ignoreFilesAdd = new JButton("添加");
		ignoreFilesEdit = new JButton("编辑");
		ignoreFilesRemove = new JButton("移除");
	}
	
	/**
	 * 对窗口里的空间进行布局
	 */
	//针对源代码的说明：调用hashCode();只是为了源代码的美观，在实际中没有任何用处
	private void init1()
	{
		window.setSize(350, 340);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.setJMenuBar(menuBar);
//		window.setAlwaysOnTop(true);
		menuBar.add(enter);
		rootPanel.setLayout(new GridBagLayout());
		
			ignoreFileEditPanel.setLayout(new BorderLayout());
			GridBagConstraints rightCon = new GridBagConstraints();
			rightCon.gridx = 0;
			rightCon.gridy = 0;
			rightCon.weightx = 1;
			rightCon.weighty = 1;
			rightCon.insets = new Insets(10, 10, 10, 10);
			rightCon.fill = GridBagConstraints.BOTH;
			rootPanel.add(ignoreFileEditPanel, rightCon);
			ignoreFileEditPanel.hashCode();
//			rightPanel.setBackground(Color.ORANGE);
			
				ignoreFilesDesc.hashCode();
				ignoreFileEditPanel.add(ignoreFilesDesc, BorderLayout.NORTH);
				ignoreFilesDesc.hashCode();
				
				ignoreFilesList.hashCode();
				ignoreFileEditPanel.add(ignoreFilesList, BorderLayout.CENTER);
				ignoreFilesList.hashCode();
				
				ignoreFilesActionPanel.hashCode();
				ignoreFileEditPanel.add(ignoreFilesActionPanel, BorderLayout.SOUTH);
				ignoreFilesActionPanel.setLayout(new GridLayout(0, 3));
				ignoreFilesActionPanel.hashCode();
				
					ignoreFilesAdd.hashCode();
					ignoreFilesActionPanel.add(ignoreFilesAdd);
					ignoreFilesAdd.hashCode();
					
					ignoreFilesEdit.hashCode();
					ignoreFilesActionPanel.add(ignoreFilesEdit);
					ignoreFilesEdit.hashCode();
		
					ignoreFilesRemove.hashCode();
					ignoreFilesActionPanel.add(ignoreFilesRemove);
					ignoreFilesRemove.hashCode();
					
			pathEditPanel.setLayout(new GridLayout(4, 0));
			GridBagConstraints pathEditPanelCon = new GridBagConstraints();
			pathEditPanelCon.gridx = 0;
			pathEditPanelCon.gridy = 1;
			pathEditPanelCon.weightx = 1;
			pathEditPanelCon.insets = new Insets(0, 10, 10, 10);
			pathEditPanelCon.fill = GridBagConstraints.BOTH;
			rootPanel.add(pathEditPanel, pathEditPanelCon);
			pathEditPanel.add(serverPathDesc);
			pathEditPanel.add(serverPathEdit);
			pathEditPanel.add(clientPathDesc);
			pathEditPanel.add(clientPathEdit);
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
				eventCallback.onRuleEditPanelCancel();
			}
		});
		
		enter.addActionListener((ActionEvent e)->
		{
			ArrayList<String> set = new ArrayList<>();
			ListModel model = ignoreFilesList.getModel();
			for(int c=0;c<model.getSize();c++)
			{
				set.add((String) model.getElementAt(c));
			}
			
			eventCallback.onRuleEditPanelReturn(set, serverPathEdit.getText(), clientPathEdit.getText(), tag);
			
			window.dispose();
		});
		
		ignoreFilesAdd.addActionListener((ActionEvent e)->
		{
			InputTextPanel.create(RuleEditPanel.this, "", -1, window);
		});
		
		ignoreFilesEdit.addActionListener((ActionEvent e)->
		{
			InputTextPanel.create(RuleEditPanel.this, (String) ignoreFilesList.getSelectedValue(), ignoreFilesList.getSelectedIndex(), window);
		});
		
		ignoreFilesRemove.addActionListener((ActionEvent e)->
		{
			if(ignoreFilesList.getSelectedIndex()!=-1)
			{
				DefaultListModel<String> listModel = (DefaultListModel<String>) ignoreFilesList.getModel();
				listModel.remove(ignoreFilesList.getSelectedIndex());
			}
		});

	}
	
	/**
	 * 创建一个规则编辑面板
	 * 
	 * @param eventCallback 规则编辑面板的事件回掉
	 * @param superWindow 父窗口
	 * @param ignoreFiles 忽略的文件们
	 * @param serverPath 服务端路径
	 * @param clientPath 客户端路径
	 * @param tag 标签
	 */
	public static void create(RuleEditPanelEventCallback eventCallback, JFrame superWindow, ArrayList<String> ignoreFiles, String serverPath, String clientPath, int tag)
	{
		RuleEditPanel panel = new RuleEditPanel();
		
		panel.eventCallback = eventCallback;
		panel.tag = tag;
		
		superWindow.setVisible(false);
		
		panel.init0();
		panel.init1();
		panel.init2();
		
		
		DefaultListModel<String> listModel = new DefaultListModel();
		/*
		原型：
		for(String per : ignoreFiles)
		{
			listModel.addElement(per);
		}
		*/
		ignoreFiles.forEach((per) ->
		{
			listModel.addElement(per);
		});
		
		panel.ignoreFilesList.setModel(listModel);
		panel.serverPathEdit.setText(serverPath);
		panel.clientPathEdit.setText(clientPath);
	}


	@Override
	public void onInputTextPanelReturn(String textFieldContent, int tag)
	{
		DefaultListModel<String> listModel = (DefaultListModel<String>) ignoreFilesList.getModel();
		if(tag!=-1)
		{
			listModel.setElementAt(textFieldContent, tag);
		}else{
			listModel.addElement(textFieldContent);
		}
		window.setVisible(true);
	}

	@Override
	public void onInputTextPanelExit()
	{
		window.setVisible(true);
	}
	
	
	
}
