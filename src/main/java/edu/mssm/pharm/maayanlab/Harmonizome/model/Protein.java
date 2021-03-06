package edu.mssm.pharm.maayanlab.Harmonizome.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import edu.mssm.pharm.maayanlab.Harmonizome.net.UrlCodec;

@Entity
@Table(name = "protein")
@BioEntityMetadata(name = "protein", keyColumn = "symbol", jsp="protein.jsp")
public class Protein implements BioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "symbol")
	private String symbol;

	@Column(name = "description")
	@Type(type = "text")
	private String description;

	@Column(name = "uniprot_id")
	private String uniprotId;

	@Column(name = "uniprot_url")
	private String uniprotUrl;
	
	/* Foreign key relationships
	 * ------------------------- */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "gene_fk")
	private Gene gene;
	
	/* Utilities
	 * --------- */
	@Transient
	public static final String ENDPOINT = "protein";
	
	public Protein() {
	}
	
	/* Getters & Setters 
	 * ----------------- */
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUniprotId() {
		return uniprotId;
	}

	public void setUniprotId(String uniprotId) {
		this.uniprotId = uniprotId;
	}

	public String getUniprotUrl() {
		return uniprotUrl;
	}

	public void setUniprotUrl(String uniprotUrl) {
		this.uniprotUrl = uniprotUrl;
	}

	public Gene getGene() {
		return gene;
	}

	public void setGene(Gene gene) {
		this.gene = gene;
	}
	

	/* Utility functions
	 * ----------------- */
	@Transient
	public String getKey() {
		return "symbol";
	}
	
	@Transient
	public String getValue() {
		return symbol;
	}
	
	@Transient
	public String getUrlEncodedValue() {
		return UrlCodec.encode(symbol);
	}

	@Transient
	public String getEndpoint() {
		return ENDPOINT;
	}
}
