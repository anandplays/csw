package com.csw.converter;

import java.io.File;
import java.io.StringReader;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonException;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonString;
import javax.json.JsonStructure;
import javax.json.JsonValue;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class JsonUtility {

	private static final Logger LOGGER = Logger.getLogger(JsonUtility.class.getName());

	private DocumentBuilderFactory documentFactory;
	private DocumentBuilder documentBuilder;
	private Document document;

	public void convertJSONToXML(String json, File xml) {
		JsonStructure obj = null;
		try {
			JsonReader jsonReader = Json.createReader(new StringReader(json));
			obj = jsonReader.read();
		} catch (JsonException e) {
			LOGGER.log(Level.SEVERE, "JSON parsing failed, input format/syntax is not porper");
			LOGGER.log(Level.SEVERE, "Conversion Failed");
			return;
		}
		documentFactory = DocumentBuilderFactory.newInstance();
		try {
			documentBuilder = documentFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			LOGGER.log(Level.SEVERE, "Error occured while instantiating xml document builder");
			return;
		}
		document = documentBuilder.newDocument();
		convertToXML(obj, null, null);
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(xml);
		try {
			Transformer transformer = transformerFactory.newTransformer();
			transformer.transform(source, result);
		} catch (TransformerException e) {
			LOGGER.log(Level.SEVERE, "Error while writing to an xml file");
			return;
		}
		LOGGER.log(Level.INFO, "conversion completed and written to : " + xml.getName());
	}

	private void convertToXML(JsonValue jsonValue, String key, Element parent) {
		switch (jsonValue.getValueType()) {
		case OBJECT:
			Element obj = document.createElement("object");

			if (parent == null) {
				document.appendChild(obj);
			} else {
				parent.appendChild(obj);
			}

			if (key != null) {
				Attr attr = document.createAttribute("name");
				attr.setValue(key);
				obj.setAttributeNode(attr);
			}

			JsonObject jsonObject = (JsonObject) jsonValue;
			for (String name : jsonObject.keySet())
				convertToXML(jsonObject.get(name), name, obj);
			break;

		case ARRAY:

			Element arr = document.createElement("array");

			if (parent == null) {
				document.appendChild(arr);
			} else {
				parent.appendChild(arr);
			}

			if (key != null) {
				Attr attr = document.createAttribute("name");
				attr.setValue(key);
				arr.setAttributeNode(attr);
			}
			JsonArray array = (JsonArray) jsonValue;

			for (JsonValue val : array)
				convertToXML(val, null, arr);
			break;

		case NUMBER:

			Element number = document.createElement("number");
			JsonNumber num = (JsonNumber) jsonValue;
			number.appendChild(document.createTextNode(num.toString()));
			parent.appendChild(number);
			if (key != null) {
				Attr attr = document.createAttribute("name");
				attr.setValue(key);
				number.setAttributeNode(attr);
			}
			break;

		case STRING:

			Element str = document.createElement("string");
			JsonString string = (JsonString) jsonValue;
			str.appendChild(document.createTextNode(string.getString()));
			parent.appendChild(str);
			if (key != null) {
				Attr attr = document.createAttribute("name");
				attr.setValue(key);
				str.setAttributeNode(attr);
			}
			break;

		case TRUE:

			Element bool = document.createElement("boolean");
			bool.appendChild(document.createTextNode("true"));
			parent.appendChild(bool);
			if (key != null) {
				Attr attr = document.createAttribute("name");
				attr.setValue(key);
				bool.setAttributeNode(attr);
			}
			break;

		case FALSE:

			Element boolFalse = document.createElement("boolean");
			boolFalse.appendChild(document.createTextNode("false"));
			parent.appendChild(boolFalse);
			if (key != null) {
				Attr attr = document.createAttribute("name");
				attr.setValue(key);
				boolFalse.setAttributeNode(attr);
			}
			break;

		case NULL:

			Element nullValue = document.createElement("null");
			parent.appendChild(nullValue);
			if (key != null) {
				Attr attr = document.createAttribute("name");
				attr.setValue(key);
				nullValue.setAttributeNode(attr);
			}
			break;
		}
	}

}
