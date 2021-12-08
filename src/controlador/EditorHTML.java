/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
/**
 *
 * @author ernes
 */
public class EditorHTML {
    public Document abrirHTML(String src) throws IOException{
        
        File input = new File("./src/recursos/mapa.html");
        Document doc = Jsoup.parse(input, "UTF-8","");
        Element content = doc.getElementById("mapa");
        content.attr("src", src);
        return doc;
    }
}
