package com.beginner.jsr353;

import org.junit.Assert;
import org.junit.Test;

import javax.json.*;
import javax.json.stream.JsonGenerator;
import java.io.StringReader;
import java.io.StringWriter;

public class TestIt {
	String originJsonStr = "{\"str\":\"a\",\"num\":1,\"bool\":false,\"k\":null}";
	String originJsonArrayStr = "[{\"id\":1,\"str\":\"a\"},{\"id\":2,\"str\":\"b\"},null]";

	@Test
	public void createJsonObject() {
		JsonObject json = Json.createObjectBuilder()
				.add("str", "a")
				.add("num", 1)
				.add("bool", false)
				.addNull("k")
				.build();

//		json.put("nested", Json.createObjectBuilder()
//			.add("id", 1)
//			.add("str", "a").build());

		System.out.println(json.getClass());
		Assert.assertEquals(originJsonStr, json.toString());
	}

	@Test
	public void createJsonArray() {
		JsonArray jsonArray = Json.createArrayBuilder()
				.add(Json.createObjectBuilder()
						.add("id", 1)
						.add("str", "a"))
				.add(Json.createObjectBuilder()
						.add("id", 2)
						.add("str", "b"))
				.addNull()
				.build();

		System.out.println(jsonArray.getClass());
		Assert.assertEquals(originJsonArrayStr, jsonArray.toString());
	}

	@Test
	public void createByReader() {
		JsonStructure j = getJsonStructure(originJsonStr);
		Assert.assertEquals(JsonValue.ValueType.OBJECT, j.getValueType());
		Assert.assertTrue(j instanceof JsonObject);
		Assert.assertEquals(originJsonStr, j.toString());

		j = getJsonStructure(originJsonArrayStr);
		Assert.assertEquals(JsonValue.ValueType.ARRAY, j.getValueType());
		Assert.assertTrue(j instanceof JsonArray);
		Assert.assertEquals(originJsonArrayStr, j.toString());
	}

	private JsonStructure getJsonStructure(String jsonValueString) {
		JsonReader reader = Json.createReader(new StringReader(jsonValueString));
		return reader.read();
	}

	@Test
	public void json2Writer() {
		JsonStructure j = getJsonStructure(originJsonStr);

		StringWriter stWriter = new StringWriter();
		JsonWriter jsonWriter = Json.createWriter(stWriter);
		jsonWriter.write(j);
		jsonWriter.close();

		Assert.assertEquals(originJsonStr, stWriter.toString());
	}

	@Test
	public void useGenerator() {
		// FileWriter writer = new FileWriter("test.txt"); // write json to file
		StringWriter writer = new StringWriter();// write json to string
		JsonGenerator gen = Json.createGenerator(writer);
		gen.writeStartObject()
				.write("str", "a")
				.write("num", 1)
				.write("bool", false)
				.writeNull("k")

				.writeStartArray("array")
				.writeStartObject()
				.write("id", 1)
				.write("n", "a")
				.writeEnd()
				.writeStartObject()
				.write("id", 2)
				.write("n", "b")
				.writeEnd()
				.writeEnd()

				.writeEnd();
		gen.close();

		String origin = "{\"str\":\"a\",\"num\":1,\"bool\":false,\"k\":null,\"array\":[{\"id\":1,\"n\":\"a\"},{\"id\":2,\"n\":\"b\"}]}";
		Assert.assertEquals(origin, writer.toString());
	}

	@Test
	// 如何修改一个现在有的 JsonObject
  // http://www.adam-bien.com/roller/abien/entry/converting_a_map_string_string
	public void modifiedJsonObject() {
		// JsonObject API 已定义其为不可变对象，虽然其继承了 Map 接口，但这个 Map 是不允许修改，
		// 这有点类似于 java8 的日期时间对象，已经创建就不可修改。要修改需要另辟途径。
		JsonObject oldOne = Json.createObjectBuilder()
				.add("id", 1)
				.add("code", "RJ")
				.build();

		JsonObjectBuilder newOne = Json.createObjectBuilder();
		oldOne.forEach((k, v) -> newOne.add(k, v));
		newOne.add("name", "rj");

		System.out.println(newOne.build());
	}

	@Test(expected = UnsupportedOperationException.class)
	// 修改一个现在有的 JsonObject 是不允许的
	public void failedModifiedJsonObject() {
		// JsonObject API 已定义其为不可变对象，虽然其继承了 Map 接口，但这个 Map 是不允许修改，
		// 这有点类似于 java8 的日期时间类，一经创建就不可修改。
		JsonObject oldOne = Json.createObjectBuilder()
				.add("id", 1)
				.add("code", "RJ")
				.build();

		oldOne.put("neeKwy", JsonValue.TRUE);
	}

	@Test(expected = UnsupportedOperationException.class)
	// 修改一个现在有的 JsonObject 是不允许的
	public void failedModifiedJsonArray() {
		// JsonArray API 已定义其为不可变对象，虽然其继承了 List 接口，但这个 List 是不允许修改，
		// 这有点类似于 java8 的日期时间类，一经创建就不可修改。
		JsonArray oldOne = Json.createArrayBuilder()
				.add(JsonValue.TRUE)
				.build();

		oldOne.add(JsonValue.FALSE);
	}
}