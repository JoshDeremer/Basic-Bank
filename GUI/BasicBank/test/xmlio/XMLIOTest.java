/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.junit.Test;
import static org.junit.Assert.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import xmlio.XMLIO;

/**
 *
 * @author TSBob
 */
public class XMLIOTest {
    
    public XMLIOTest() {
    }

    String teststr1 = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>"
			+ "<test>"
			+ "  <name>DarylTest</name>"
			+ "</test>";
	
    String filename = "test.xml";
    
    /**
     * Test of WriteXMLFile method, of class XMLIO.
     */
    @Test
    public void testWriteXMLFile() {
        try 
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
            DocumentBuilder builder;

            builder = factory.newDocumentBuilder();

            Document doc =  builder.newDocument();

            //root element
            Element rootElement = doc.createElement("test"); 
            doc.appendChild(rootElement);

            //add account elements
            Element name = doc.createElement("name");
            name.appendChild(doc.createTextNode("DarylTest"));
            rootElement.appendChild(name);

            XMLIO io = new XMLIO();

            io.WriteXMLFile(doc, new File(filename));

            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = null, teststr2 = "";

            while ((line = reader.readLine()) != null) {
                teststr2 += line;
            }

            reader.close();

            Files.delete(FileSystems.getDefault().getPath("", filename));

                assertEquals(teststr1, teststr2);

        } catch (Exception e) {
                e.printStackTrace();
        }
    }

    /**
     * Test of ReadXMLFile method, of class XMLIO.
     */
    @Test
    public void testReadXMLFile() {
        try 
	{
            BufferedWriter writer =  new BufferedWriter(new FileWriter(filename));
            writer.write(teststr1);
            writer.close();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
            DocumentBuilder builder;

            builder = factory.newDocumentBuilder();

            Document doc =  builder.newDocument();

            //root element
            Element rootElement = doc.createElement("test"); 
            doc.appendChild(rootElement);

            //add account elements
            Element name = doc.createElement("name");
            name.appendChild(doc.createTextNode("DarylTest"));
            rootElement.appendChild(name);

            XMLIO io = new XMLIO();

            Document doc2 = io.ReadXMLFile(new File(filename));

            Element root = doc2.getDocumentElement();

            assertEquals(root.getTagName(), "test");
            NodeList children = root.getChildNodes();

            for(int i = 0; i < children.getLength(); i++)
            {
                    Node child = children.item(i);
                     if(child instanceof Element)
                     {
                             Element childElement = (Element) child;

                             Text textNode = (Text) childElement.getFirstChild();
                             assertEquals(textNode.getData().trim(), "DarylTest");
                     }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}