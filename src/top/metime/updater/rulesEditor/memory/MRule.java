package top.metime.updater.rulesEditor.memory;

import java.util.ArrayList;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONObject;

public class MRule
{
	public String ruleName;
	public String serverPath;
	public String clientPath;
	public ArrayList<String> ignoreFiles;
	
	public MRule()
	{
		
	}
	
	public MRule(JSONObject JSONObj)
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
	
	public MRule(String ruleName, String serverPath, String clientPath, ArrayList<String> ignoreFiles)
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
		if(obj instanceof MRule)
		{
			MRule mr = (MRule) obj;
			
			return mr.ruleName.equals(ruleName);
		}
		return false;
	}

	@Override
	public int hashCode()
	{
		int hash = 3;
		hash = 67*hash+Objects.hashCode(this.ruleName);
		return hash;
	}
	
}
