package top.metime.updater.rulesEditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;
import top.metime.updater.rulesEditor.callback.MainWindowEventCallback;

public class CommentMainWindow 
{
	private MainWindowEventCallback eventCallback;
	
	
	private JFrame window;
	private JMenuBar menuBar;
		private JButton loadFile;
		private JButton saveFile;
	private Container rootPanel;
		private JPanel leftPanel;
			private JPanel rulesListPanel;
				private JLabel rulesListDesc;
				private JList rulesList;
				private JPanel rulesListActionPanel;
					private JButton ruleAdd;
					private JButton ruleRename;
					private JButton ruleRemove;
			private JPanel pathEditPanel;
				private JLabel serverPathDesc;
				private JTextField serverPathEdit;
				private JLabel clientPathDesc;
				private JTextField clientPathEdit;
		private JPanel rightPanel;
			private JLabel ignoreFilesDesc;
			private JList ignoreFilesList;
			private JPanel ignoreFilesActionPanel;
				private JButton ignoreFilesAdd;
				private JButton ignoreFilesEdit;
				private JButton ignoreFilesRemove;
	
	public CommentMainWindow(MainWindowEventCallback eventCallback)
	{
		this.eventCallback = eventCallback;
	}
				
	private void init3()
	{
		loadFile.addActionListener((ActionEvent e)->
		{
			eventCallback.onClickLoadFileButton();
		});
		
		saveFile.addActionListener((ActionEvent e)->
		{
			eventCallback.onClickSaveFileButton();
		});
		
		rulesList.setModel(new DefaultListModel<String>());
		ignoreFilesList.setModel(new DefaultListModel<String>());

		ruleAdd.addActionListener((ActionEvent e)->
		{
			eventCallback.onClickRuleAdd();
		});
		
		ruleRename.addActionListener((ActionEvent e)->
		{
			eventCallback.onClickRuleRemove();
		});
		
		ruleRemove.addActionListener((ActionEvent e)->
		{
			eventCallback.onClickRuleRemove();
		});
		
		ignoreFilesAdd.addActionListener((ActionEvent e)->
		{
			eventCallback.onClickIgnoreFilesAdd();
		});
		
		ignoreFilesEdit.addActionListener((ActionEvent e)->
		{
			eventCallback.onClickIgnoreFilesEdit();
		});
		
		ignoreFilesRemove.addActionListener((ActionEvent e)->
		{
			eventCallback.onClickIgnoreFilesRemove();
		});
	}
	
	private void init2()
	{
		window.setVisible(true);
	}
	
	private void init1()
	{
		window.setSize(550, 450);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.setJMenuBar(menuBar);
		window.setAlwaysOnTop(true);
		menuBar.add(loadFile);
		menuBar.add(saveFile);
		rootPanel.setLayout(new GridBagLayout());
		
			leftPanel.setLayout(new GridBagLayout());
			GridBagConstraints leftPanelCon = new GridBagConstraints();
			leftPanelCon.gridx = 0;
			leftPanelCon.gridy = 0;
			leftPanelCon.weightx = 1;
			leftPanelCon.weighty = 1;
			leftPanelCon.insets = new Insets(10, 10, 10, 20);
			leftPanelCon.fill = GridBagConstraints.BOTH;
			rootPanel.add(leftPanel, leftPanelCon);
			leftPanel.setBackground(Color.CYAN);
			
				rulesListPanel.setLayout(new BorderLayout());
				GridBagConstraints rulesListPanelCon = new GridBagConstraints();
				rulesListPanelCon.gridx = 0;
				rulesListPanelCon.gridy = 0;
				rulesListPanelCon.weightx = 1;
				rulesListPanelCon.weighty = 0.6f;
				rulesListPanelCon.fill = GridBagConstraints.BOTH;
				leftPanel.add(rulesListPanel, rulesListPanelCon);
				rulesListPanel.hashCode();
				
					rulesListDesc.hashCode();
					rulesListPanel.add(rulesListDesc, BorderLayout.NORTH);
					rulesListDesc.hashCode();
					
					rulesList.hashCode();
					rulesListPanel.add(rulesList, BorderLayout.CENTER);
					rulesList.hashCode();
					
					rulesListActionPanel.setLayout(new GridLayout(0, 3));
					rulesListPanel.add(rulesListActionPanel, BorderLayout.SOUTH);
					rulesListActionPanel.hashCode();
					
						ruleAdd.hashCode();
						rulesListActionPanel.add(ruleAdd);
						ruleAdd.hashCode();
						
						ruleRename.hashCode();
						rulesListActionPanel.add(ruleRename);
						ruleRename.hashCode();
						
						ruleRemove.hashCode();
						rulesListActionPanel.add(ruleRemove);
						ruleRemove.hashCode();
						
						
				
				pathEditPanel.setLayout(new GridLayout(4, 0));
				GridBagConstraints pathEditPanelCon = new GridBagConstraints();
				pathEditPanelCon.gridx = 0;
				pathEditPanelCon.gridy = 1;
				pathEditPanelCon.weightx = 1;
				pathEditPanelCon.fill = GridBagConstraints.BOTH;
				leftPanel.add(pathEditPanel, pathEditPanelCon);
				pathEditPanel.add(serverPathDesc);
				pathEditPanel.add(serverPathEdit);
				pathEditPanel.add(clientPathDesc);
				pathEditPanel.add(clientPathEdit);
				
		
			rightPanel.setLayout(new BorderLayout());
			GridBagConstraints rightCon = new GridBagConstraints();
			rightCon.gridx = 1;
			rightCon.gridy = 0;
			rightCon.weightx = 4;
			rightCon.weighty = 1;
			rightCon.insets = new Insets(10, 10, 10, 10);
			rightCon.fill = GridBagConstraints.BOTH;
			rootPanel.add(rightPanel, rightCon);
			rightPanel.hashCode();
//			rightPanel.setBackground(Color.ORANGE);
			
				ignoreFilesDesc.hashCode();
				rightPanel.add(ignoreFilesDesc, BorderLayout.NORTH);
				ignoreFilesDesc.hashCode();
				
				ignoreFilesList.hashCode();
				rightPanel.add(ignoreFilesList, BorderLayout.CENTER);
				ignoreFilesList.hashCode();
				
				ignoreFilesActionPanel.hashCode();
				rightPanel.add(ignoreFilesActionPanel, BorderLayout.SOUTH);
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
						
	}
	
	private void init0()
	{
		window = new JFrame();
		menuBar = new JMenuBar();
		loadFile = new JButton("加载");
		saveFile = new JButton("保存");
		rootPanel = window.getContentPane();
		leftPanel = new JPanel();
		rulesListPanel = new JPanel();
		rulesListDesc = new JLabel("规则列表：");
		rulesList = new JList();
		rulesListActionPanel = new JPanel();
		ruleAdd = new JButton("添加");
		ruleRename = new JButton("重命名");
		ruleRemove = new JButton("删除");
		pathEditPanel = new JPanel();
		serverPathDesc = new JLabel("服务端路径：");
		serverPathEdit = new JTextField();
		clientPathDesc = new JLabel("客户端路径：");
		clientPathEdit = new JTextField();
		rightPanel = new JPanel();
		ignoreFilesDesc = new JLabel("忽略的文件列表：");
		ignoreFilesList = new JList();
		ignoreFilesActionPanel = new JPanel();
		ignoreFilesAdd = new JButton("添加");
		ignoreFilesEdit = new JButton("编辑");
		ignoreFilesRemove = new JButton("移除");
	}
	
	public void init()
	{
		init0();
		init1();
		init2();
	}
	
	
	
	// API
	public void updateRulesList()
	{
		rulesList.setModel(rulesList.getModel());
	}
	
	public void updateIgnoreList()
	{
		ignoreFilesList.setModel(ignoreFilesList.getModel());
	}
	
	public void addElementToRulesList(String element)
	{
		
	}
}
