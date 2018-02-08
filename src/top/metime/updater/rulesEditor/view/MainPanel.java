package top.metime.updater.rulesEditor.view;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.json.JSONException;
import org.json.JSONObject;
import top.metime.updater.rulesEditor.Main;
import top.metime.updater.rulesEditor.callback.InputTextPanelEventCallback;
import top.metime.updater.rulesEditor.callback.RuleEditPanelEventCallback;
import top.metime.updater.rulesEditor.dataModel.MyDataModel;
import top.metime.updater.rulesEditor.mem.Rule;

/**
 * 小工具的主面板界面
 *
 * @author innc
 */
public class MainPanel implements InputTextPanelEventCallback, RuleEditPanelEventCallback
{

	private final File ruleFile=new File(Main.RULE_FILE_NAME);

	private JFrame window;//窗体
	private JMenuBar menuBar;//菜单栏
	private JButton loadFile;//菜单栏里的加载按钮
	private JButton saveFile;//菜单栏里的保存按钮
	private Container rootPanel;//面板的根容器
	private JLabel rulesListDesc;//规则列表的作用说明标签
	private JList rulesList;//规则列表
	private JPanel rulesListActionPanel;//规则的操作栏
	private JButton ruleAdd;//新建规则按钮
	private JButton ruleRename;//重命名规则按钮
	private JButton ruleRemove;//移除规则按钮

	private MyDataModel model;

	/**
	 * 实例化窗口里的控件
	 */
	private void init0()
	{
		window=new JFrame("初始化");
		menuBar=new JMenuBar();
		loadFile=new JButton("丢弃当前所有并重新加载");
		saveFile=new JButton("写出到文件");
		rootPanel=window.getContentPane();
		rulesListDesc=new JLabel("规则列表：");
		rulesList=new JList();
		rulesListActionPanel=new JPanel();
		ruleAdd=new JButton("添加");
		ruleRename=new JButton("重命名");
		ruleRemove=new JButton("删除");
	}

	/**
	 * 对窗口里的空间进行布局
	 */
	//针对源代码的说明：调用hashCode();只是为了源代码的美观，在实际中没有任何用处
	private void init1()
	{
		window.setSize(420, 370);
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

	/**
	 * 设置窗口可见，为控件们添加各种事件监听器
	 */
	private void init2()
	{
		window.setVisible(true);

		//为规则列表控件设置数据模型（并初始化）
		rulesList.setModel(model=new MyDataModel());

		//规则列表 的监听器
		rulesList.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				int selectIndex=rulesList.getSelectedIndex();
				if(e.getClickCount()==2&&selectIndex!=-1)//双击且选中的索引不为-1
				{
					//获取被点击的那一条规则
					Rule 规则=model.getElementAt2(selectIndex);

					//打开规则的详细编辑面板
					RuleEditPanel.create(MainPanel.this, window, 规则.ignoreFiles, 规则.serverPath, 规则.clientPath, selectIndex);
				}
			}
		});

		//加载文件 按钮的监听器
		loadFile.addActionListener((ActionEvent e) ->
		{
			loadRulesFile();
		});

		//保存文件 按钮的监听器
		saveFile.addActionListener((ActionEvent e) ->
		{
			if(model.getSize()==0)
			{
				window.setVisible(false);
				JOptionPane.showMessageDialog(null, "现在一条规则都没有", "", JOptionPane.ERROR_MESSAGE);
				window.setVisible(true);
				
				return;
			}
			
			try(BufferedWriter writer=new BufferedWriter(new FileWriter(ruleFile)))
			{
				//把内容写出到文件，每行一个JSONObject.toString()
				for(int c=0; c<model.getSize(); c++)
				{
					writer.write(model.getElementAt2(c).toJSONObject().toString());
					writer.newLine();
				}

				window.setVisible(false);
				JOptionPane.showMessageDialog(null, "已保存"+model.getSize()+"条规则", "保存成功", JOptionPane.INFORMATION_MESSAGE);
				window.setVisible(true);

				SimpleDateFormat formater=new SimpleDateFormat("HH：mm：ss");
				window.setTitle("保存于："+formater.format(new Date(System.currentTimeMillis())));

			}catch(IOException ex)
			{
				Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);

				window.setVisible(false);
				JOptionPane.showMessageDialog(null, "保存失败：无法写入文件\n原因："+ex.getLocalizedMessage(), "保存失败", JOptionPane.ERROR_MESSAGE);
				window.setVisible(true);
			}

		});

		//添加规则 按钮的监听器
		ruleAdd.addActionListener((ActionEvent e) ->
		{
			InputTextPanel.create(MainPanel.this, "", -1, window);
		});

		//重命名规则 按钮的监听器
		ruleRename.addActionListener((ActionEvent e) ->
		{
			if(rulesList.getSelectedIndex()!=-1)
			{
				InputTextPanel.create(MainPanel.this, model.getElementAt(rulesList.getSelectedIndex()), rulesList.getSelectedIndex(), window);
			}
		});

		//删除规则 按钮的监听器
		ruleRemove.addActionListener((ActionEvent e) ->
		{
			if(rulesList.getSelectedIndex()!=-1)
			{
				model.removeElementAt(rulesList.getSelectedIndex());
				refreshList();
			}
		});

	}

	/**
	 * 初始化窗体和窗体里的控件们
	 */
	public void init()
	{
		init0();
		init1();
		init2();
		
		loadRulesFile();
	}

	/**
	 * 加载保存着规则们的文件
	 */
	private void loadRulesFile()
	{
		if(ruleFile.exists())
		{
			model.clear();
			refreshList();

			try(BufferedReader reader=new BufferedReader(new FileReader(ruleFile)))
			{
				String temp;
				while((temp=reader.readLine())!=null)
				{
					Rule 规则=new Rule(new JSONObject(temp.trim()));
					model.addElement(规则);
				}

			}catch(IOException ex)
			{
				Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);

				window.setVisible(false);
				JOptionPane.showMessageDialog(null, "读取失败：无法读取文件（IOException）\n原因："+ex.getLocalizedMessage()+"\n详细原因："+ex.getMessage(), "读取失败", JOptionPane.ERROR_MESSAGE);
				window.setVisible(true);
			}catch(JSONException ex)
			{
				window.setVisible(false);
				JOptionPane.showMessageDialog(null, "文件格式错误，无法解析文件", "解析失败", JOptionPane.ERROR_MESSAGE);
				window.setVisible(true);
			}

			window.setVisible(false);
			JOptionPane.showMessageDialog(null, "已加载"+model.getSize()+"条规则", "加载成功", JOptionPane.INFORMATION_MESSAGE);
			window.setVisible(true);
			
			SimpleDateFormat formater=new SimpleDateFormat("HH：mm：ss");
			window.setTitle("加载于："+formater.format(new Date(System.currentTimeMillis())));
			
			refreshList();
		}else
		{
			window.setVisible(false);
			JOptionPane.showMessageDialog(null, "文件不存在，无法读取规则文件", "读取失败", JOptionPane.ERROR_MESSAGE);
			window.setVisible(true);
		}
	}

	/**
	 * 当点击[添加规则]按钮后，如果输入成功返回时调用
	 *
	 * @param textFieldContent 文本内容
	 * @param tag 标签
	 */
	@Override
	public void onInputTextPanelReturn(String textFieldContent, int tag)
	{
		if(tag!=-1)
		{
			Rule mr = model.getElementAt2(tag);
			mr.ruleName=textFieldContent;
		}else
		{
			Rule mr=new Rule(textFieldContent, "", "", new ArrayList<>());
			model.addElement(mr);
		}
		window.setVisible(true);
		refreshList();
	}

	/**
	 * 当点击[添加规则]按钮后，如果输入被取消时调用
	 */
	@Override
	public void onInputTextPanelExit()
	{
		window.setVisible(true);
	}

	/**
	 * 当点击[编辑规则]按钮后，如果编辑的内容被保存时调用
	 *
	 * @param ignoreFiles 忽略的文件们
	 * @param serverPath 服务端路径
	 * @param clientPath 客户端路径
	 * @param tag 标签
	 */
	@Override
	public void onRuleEditPanelReturn(ArrayList<String> ignoreFiles, String serverPath, String clientPath, int tag)
	{
		Rule mr=model.getElementAt2(tag);
		mr.ignoreFiles.clear();
		mr.ignoreFiles.addAll(ignoreFiles);
		mr.clientPath=clientPath;
		mr.serverPath=serverPath;
		window.setVisible(true);
		refreshList();
	}

	/**
	 * 当点击[编辑规则]按钮后，如果编辑的内容被丢弃（不保存）时被调用
	 */
	@Override
	public void onRuleEditPanelCancel()
	{
		window.setVisible(true);
	}

	private void refreshList()
	{
		rulesList.setListData(model.toArray());
	}
}
