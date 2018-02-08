package top.metime.updater.rulesEditor.dataModel;

import java.util.ArrayList;
import javax.swing.AbstractListModel;
import top.metime.updater.rulesEditor.mem.Rule;

/**
 * 主面板的规则列表控件专用的数据模型
 * @author innc
 */
public class MyDataModel extends AbstractListModel<String>
{
	private final ArrayList<Rule> data;
	
	public MyDataModel()
	{
		data = new ArrayList<>();
	}
	
	public void addElement(Rule rule)
	{
		data.add(rule);
	}
	
	public void setElementAt(int index, Rule rule)
	{
		data.set(index, rule);
	}
	
	public boolean containsElement(Rule rule)
	{
		return data.contains(rule);
	}
	/*
	public boolean removeElement(Rule rule)
	{
		return data.remove(rule);
	}
	*/
	public void removeElementAt(int index)
	{
		data.remove(index);
	}
	
	public void clear()
	{
		data.clear();
	}
	
	@Override
	public int getSize()
	{
		return data.size();
	}

	@Override
	public String getElementAt(int index)
	{
		return data.get(index).ruleName;
	}
	
	public Rule getElementAt2(int index)
	{
		return data.get(index);
	}
	
	public String[] toArray()
	{
		String[] cs = new String[data.size()];
		
		for(int c=0;c<data.size();c++)
		{
			cs[c] = data.get(c).ruleName;
		}
		
		return cs;
	}
	
}
