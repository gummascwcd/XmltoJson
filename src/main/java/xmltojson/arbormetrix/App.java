package xmltojson.arbormetrix;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

public class App {

	public static int INDENT_FACTOR = 4;

	public static void main(String[] args) throws IOException {

		InputStream inputStream = new FileInputStream(new File(args[0].toString()));
		StringBuilder stringBuilder = new StringBuilder();
		int ptr = 0;
		while ((ptr = inputStream.read()) != -1) {
			stringBuilder.append((char) ptr);
		}
		String xml = stringBuilder.toString();
		JSONObject jsonObject = XML.toJSONObject(xml);
		JSONArray jsonArray = new JSONArray();
		for (String key : JSONObject.getNames(jsonObject)) {
			JSONObject JsObject = jsonObject.optJSONObject(key);
			Iterator iterator = JsObject.keys();
			String keySet = (String) iterator.next();
			jsonArray.put(JsObject.get(keySet));
		}
		String jsonString = jsonArray.toString(INDENT_FACTOR);
		String string1 = jsonString.replaceFirst(Pattern.quote("["), "");
		String string2 = string1.replaceFirst(Pattern.quote("]"), "");
		System.out.println(string2);

	}


}
