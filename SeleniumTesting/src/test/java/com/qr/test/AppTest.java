package com.qr.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.junit.Test;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


public class AppTest{

	@Test
	public void parseXMLUsingXPath() {
		try {
//			String expression = "//input[@id='searchFlightsForm:tirpType:1']";
			String expression = "/";
			XPathFactory xPathFactory = XPathFactory.newInstance();
			XPath xPath = xPathFactory.newXPath();
			XPathExpression xPathExpression = xPath.compile(expression);

			File xmlDocument = new File((AppTest.class).getResource("/com/test/Test.xml").getPath());
			InputSource inputSource = new InputSource(new FileInputStream(xmlDocument));
			Object result = xPathExpression.evaluate(inputSource, XPathConstants.NODESET);
			NodeList nodeList = (NodeList) result;
			Node node;
			System.out.println("NodeList : " + nodeList);
			System.out.println("Node Length : " + nodeList.getLength());
			for (int i = 0; i < nodeList.getLength(); i++) {
				node = nodeList.item(i);
				System.out.println("Node Name : " + node.getNodeName());
				System.out.println("Node Value : " + node.getNodeValue());
			}

		} catch (XPathExpressionException ex) {
			Logger.getLogger(AppTest.class.getName()).log(Level.SEVERE, null, ex);
		} catch (FileNotFoundException ex) {
			Logger.getLogger(AppTest.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
