package top.metime.updater.rulesEditor;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.json.JSONObject;
import top.metime.updater.rulesEditor.callback.InputTextPanelEventCallback;
import top.metime.updater.rulesEditor.callback.RuleEditPanelEventCallback;
import top.metime.updater.rulesEditor.memory.MRule;

public class MainPanel implements InputTextPanelEventCallback, RuleEditPanelEventCallback
{
	private ArrayList<MRule> d = new ArrayList<>();
	
	private JFrame window;
	private JMenuBar menuBar;
		private JButton loadFile;
		private JButton saveFile;
	private Container rootPanel;
		private JLabel rulesListDesc;
		private JList rulesList;
		private JPanel rulesListActionPanel;
			private JButton ruleAdd;
			private JButton ruleRename;
			private JButton ruleRemove;
	
	private void init0()
	{
		window = new JFrame();
		menuBar = new JMenuBar();
		loadFile = new JButton("加载");
		saveFile = new JButton("保存");
		rootPanel = window.getContentPane();
		rulesListDesc = new JLabel("规则列表：");
		rulesList = new JList();
		rulesListActionPanel = new JPanel();
		ruleAdd = new JButton("添加");
		ruleRename = new JButton("重命名");
		ruleRemove = new JButton("删除");
	}
	
	private void init1()
	{
		window.setSize(320, 270);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.setJMenuBar(menuBar);
//		window.setAlwaysOnTop(true);
		menuBar.add(loadFile);
		menuBar.add(saveFile);
		rootPanel.setLayout(new BorderLayout());
		
			rulesListDesc.hashCode();
			rootPanel.add(rulesListDesc, BorderLayout.NORTH);
			rulesListDesc.hashCode();
					
			rulesList.hashCode();
			rootPanel.add(rulesList, BorderLayout.CENTER);
			rulesList.hashCode();
					
			rulesListActionPanel.setLayout(new GridLayout(0, 3));
			rootPanel.add(rulesListActionPanel, BorderLayout.SOUTH);
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
						
	}
	
	private void init2()
	{
		window.setVisible(true);
		
		rulesList.setModel(new DefaultListModel<String>());
		
		rulesList.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				int selectIndex = rulesList.getSelectedIndex();
				if(e.getClickCount()==2&&selectIndex!=-1)
				{
					MRule mr = d.get(selectIndex);
					new RuleEditPanel().init(MainPanel.this, window, mr.ignoreFiles, mr.serverPath, mr.clientPath, selectIndex);
				}
			}
		});
		
		loadFile.addActionListener((ActionEvent e)->
		{
			JFileChooser chooser = new JFileChooser(new File("").getAbsoluteFile());
			chooser.setFileSelectionMode(JFileChooser. FILES_ONLY);
			window.setVisible(false);
			chooser.showOpenDialog(null);
			window.setVisible(true);
			File file = chooser.getSelectedFile();
			
			if(file!=null)
			{
				try
				{
					DefaultListModel<String> model = (DefaultListModel<String>) rulesList.getModel();
					model.clear();
					
					d.clear();
					
					try (BufferedReader reader = new BufferedReader(new FileReader(file)))
					{
						String temp = null;
						while((temp = reader.readLine())!=null)
						{
							temp = temp.trim();
							
							try
							{
								MRule mr = new MRule(new JSONObject(temp));
								d.add(mr);
								model.addElement(mr.ruleName);
								window.setTitle(file.getName());
							}
							catch(org.json.JSONException ex)
							{
								window.setVisible(false);
								JOptionPane.showMessageDialog(null, "无法读取，文件格式错误", "读取失败", JOptionPane.ERROR_MESSAGE);
								window.setVisible(true);
								return;
							}
							
						}
					}

				}
				catch(IOException ex){ex.printStackTrace();}
				window.setVisible(false);
				JOptionPane.showMessageDialog(null, "已加载"+d.size()+"条规则", "加载成功", JOptionPane.INFORMATION_MESSAGE);
				window.setVisible(true);
			}
		});
		
		saveFile.addActionListener((ActionEvent e)->
		{
			JFileChooser chooser = new JFileChooser(new File("").getAbsoluteFile());
			chooser.setFileSelectionMode(JFileChooser. FILES_ONLY);
			window.setVisible(false);
			chooser.showSaveDialog(null);
			window.setVisible(true);
			File file = chooser.getSelectedFile();
			
			if(file!=null)
			{
				window.setTitle(file.getName());
				try
				{
					BufferedWriter writer = new BufferedWriter(new FileWriter(file));

					for(MRule mrule : d)
					{
						writer.write(mrule.toJSONObject().toString());
						writer.newLine();
					}

					writer.close();
				}
				catch(IOException ex){ex.printStackTrace();}

				window.setVisible(false);
				JOptionPane.showMessageDialog(null, "已保存"+d.size()+"条规则", "保存成功", JOptionPane.INFORMATION_MESSAGE);
				window.setVisible(true);
			}
			
		});
		
		ruleAdd.addActionListener((ActionEvent e)->
		{
			new InputTextPanel().init(MainPanel.this, "", -1, window);
		});
		
		ruleRename.addActionListener((ActionEvent e)->
		{
			if(rulesList.getSelectedIndex()!=-1)
			{
				new InputTextPanel().init(MainPanel.this, (String) rulesList.getSelectedValue(), rulesList.getSelectedIndex(), window);
			}
		});
		
		ruleRemove.addActionListener((ActionEvent e)->
		{
			if(rulesList.getSelectedIndex()!=-1)
			{
				DefaultListModel<String> listModel = (DefaultListModel<String>) rulesList.getModel();
				listModel.remove(rulesList.getSelectedIndex());
				
				
				/*
				原型：
				for(MRule me : d)
				{
					if(me.clientPath.equals(rulesList.getSelectedValue()))
					{
						d.remove(me);
					}
				}
				*/
				d.stream().filter((me)->(me.clientPath.equals(rulesList.getSelectedValue()))).forEachOrdered((me)->
				{
					d.remove(me);
				});
				
			}
		});
		
		
	}
	
	public void init()
	{
		init0();
		init1();
		init2();
	}
	
	@Override
	public void onInputTextPanelReturn(String textFieldContent, int tag)
	{
		DefaultListModel<String> listModel = (DefaultListModel<String>) rulesList.getModel();
		if(tag!=-1)
		{
			listModel.setElementAt(textFieldContent, tag);
			MRule mr = d.get(tag);
			mr.ruleName = textFieldContent;
		}else{
			listModel.addElement(textFieldContent);
			MRule mr = new MRule(textFieldContent, "", "", new ArrayList<String>());
			d.add(mr);
		}
		window.setVisible(true);
	}

	@Override
	public void onInputTextPanelExit()
	{
		window.setVisible(true);
	}

	@Override
	public void onRuleEditPanelReturn(ArrayList<String> ignoreFiles, String serverPath, String clientPath, int tag)
	{
		MRule mr = d.get(tag);
		mr.ignoreFiles.clear();
		mr.ignoreFiles.addAll(ignoreFiles);
		mr.clientPath = clientPath;
		mr.serverPath = serverPath;
		window.setVisible(true);
	}

	@Override
	public void onRuleEditPanelCancel()
	{
		window.setVisible(true);
	}
	
}
