package top.metime.updater.rulesEditor;

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

public class RuleEditPanel implements InputTextPanelEventCallback
{
	private RuleEditPanelEventCallback eventCallback;
	private int selectedRuleIndex;
	
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
			
			eventCallback.onRuleEditPanelReturn(set, serverPathEdit.getText(), clientPathEdit.getText(), selectedRuleIndex);
			
			window.dispose();
		});
		
		ignoreFilesAdd.addActionListener((ActionEvent e)->
		{
			new InputTextPanel().init(RuleEditPanel.this, "", -1, window);
		});
		
		ignoreFilesEdit.addActionListener((ActionEvent e)->
		{
			new InputTextPanel().init(RuleEditPanel.this, (String) ignoreFilesList.getSelectedValue(), ignoreFilesList.getSelectedIndex(), window);
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
	
	
	public void init(RuleEditPanelEventCallback eventCallback, JFrame superWindow, ArrayList<String> ignoreFiles, String serverPath, String clientPath, int selectedRuleIndex)
	{
		this.eventCallback = eventCallback;
		this.selectedRuleIndex = selectedRuleIndex;
		
		superWindow.setVisible(false);
		
		init0();
		init1();
		init2();
		
		
		DefaultListModel<String> listModel = new DefaultListModel();
		for(String per : ignoreFiles)
		{
			listModel.addElement(per);
		}
		
		ignoreFilesList.setModel(listModel);
		serverPathEdit.setText(serverPath);
		clientPathEdit.setText(clientPath);
	}


	@Override
	public void onInputTextPanelReturn(String textFieldContent, int selectedRuleIndex)
	{
		DefaultListModel<String> listModel = (DefaultListModel<String>) ignoreFilesList.getModel();
		if(selectedRuleIndex!=-1)
		{
			listModel.setElementAt(textFieldContent, selectedRuleIndex);
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
