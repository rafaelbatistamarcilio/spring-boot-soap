//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.7 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2018.10.25 às 11:31:09 PM BRT 
//


package com.jdbc.ws.soap.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de anonymous complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;sequence>
 *           &lt;element name="mesagem" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;/sequence>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "mesagem"
})
@XmlRootElement(name = "excluirProdutoResponse")
public class ExcluirProdutoResponse {

    @XmlElement(required = true)
    protected String mesagem;

    /**
     * Obtém o valor da propriedade mesagem.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMesagem() {
        return mesagem;
    }

    /**
     * Define o valor da propriedade mesagem.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMesagem(String value) {
        this.mesagem = value;
    }

}
