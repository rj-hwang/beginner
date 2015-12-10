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
}