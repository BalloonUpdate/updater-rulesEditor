package top.metime.updater.rulesEditor.mem;

import java.util.ArrayList;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 该类表示一个规则对象，包含规则名、服务端路径、客户端路径、忽略的文件四个内容
 * @author innc
 */

public class Rule
{
	public String ruleName;
	public String serverPath;
	public String clientPath;
	public ArrayList<String> ignoreFiles;
	
	public Rule()
	{
		
	}
	
	
	public Rule(JSONObject JSONObj)
	{
		ruleName = JSONObj.getString("ruleName");
		serverPath = JSONObj.getString("serverPath");
		clientPath = JSONObj.getString("clientPath");
		JSONArray ja = JSONObj.getJSONArray("ignoreFiles");
		
		ArrayList<String> set = new ArrayList<>();
		
		for(int c=0;c<ja.length();c++)
		{
			set.add(ja.getString(c));
		}
		
		ignoreFiles = set;
		
	}
	
	public Rule(String ruleName, String serverPath, String clientPath, ArrayList<String> ignoreFiles)
	{
		this.ruleName = ruleName;
		this.serverPath = serverPath;
		this.clientPath = clientPath;
		this.ignoreFiles = ignoreFiles;
	}
	
	
	public JSONObject toJSONObject()
	{
		JSONObject obj = new JSONObject();
		
		obj.put("ruleName", ruleName);
		obj.put("serverPath", serverPath);
		obj.put("clientPath", clientPath);
		obj.put("ignoreFiles", ignoreFiles);
		
		return obj;
	}
	

	@Override
	public boolean equals(Object obj)
	{
		if(obj instanceof Rule)
		{
			Rule mr = (Rule) obj;
			
			boolean ruleNameE = mr.ruleName.equals(ruleName);
//			boolean serverPathE = mr.serverPath.equals(serverPath);
//			boolean clientPathE = mr.clientPath.equals(clientPath);
			//boolean ignoreFilesE = false;
			
//			for(String ignoreFile : mr.ignoreFiles)
//			{
//				for(String  ec : mr.ignoreFiles)
//				{
//				}
//			}
			
			return ruleNameE;
		}
		return false;
	}

	@Override
	public int hashCode()
	{
		int hash=7;
		hash=67*hash+Objects.hashCode(ruleName);
		return hash;
	}
	
	
	
}
