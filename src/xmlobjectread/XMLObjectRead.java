/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlobjectread;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 *
 * @author oracle
 */
public class XMLObjectRead {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here

            XMLStreamReader sr = XMLInputFactory.newInstance().createXMLStreamReader(new FileInputStream("products.xml"));
            ArrayList products = new ArrayList();
            Product p = new Product();

            while (sr.hasNext()) {

                if (sr.getEventType() == XMLStreamConstants.START_ELEMENT) {

                    if (sr.getLocalName() == "product") {
                        String nomA = sr.getAttributeValue(0);
                        p.setCod(nomA);
                        System.out.println(nomA);
                        sr.next();
                    } else if (sr.getLocalName() == "precio") {
                        sr.next();
                        System.out.println(sr.getText());
                        p.setPrice(Integer.parseInt(sr.getText()));
                    } else if (sr.getLocalName() == "descripcion") {
                        sr.next();
                        System.out.println(sr.getText());
                        p.setDesc(sr.getText());
                    } else {
                        sr.next();
                    }
                } else {
                    sr.next();
                }

                if (p.getCod() != null && p.getPrice() != 0 && p.getDesc() != null) {
                    products.add(p);
                    p = new Product();
                }
            }

            System.out.println(products.toString());
            sr.close();
        } catch (XMLStreamException | FileNotFoundException ex) {
            Logger.getLogger(XMLObjectRead.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
