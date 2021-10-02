package br.com.alura.loja.modelo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Projeto {

    private String nome;
    private Long id;
    private int anoDeInicio;
    
    public Projeto() {
    }

    public Projeto(String nome, Long id, int anoDeInicio) {
	super();
	this.nome = nome;
	this.id = id;
	this.anoDeInicio = anoDeInicio;
    }
    


    public String toXML() {
	return new XStream().toXML(this);
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public int getAnoDeInicio() {
	return anoDeInicio;
    }

    public void setAnoDeInicio(int anoDeInicio) {
	this.anoDeInicio = anoDeInicio;
    }

    public String toJSON() {
	return new Gson().toJson(this);
    }

}
