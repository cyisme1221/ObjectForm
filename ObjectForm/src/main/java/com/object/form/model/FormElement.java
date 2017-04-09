/*
 * FormElement is an abstract class that represents an element of the form
 * 
 * id: a unique identifier
 * title: the title of the element
 * name: the name of the element
 * isRequired: it is a boolean value that determines if the element is mandatory for the form
 * isEnabled:
 * isMultipleAnswerAllowed: this boolean value determines if the element allows multiple values in the element
 * form: a reference to the form object where the element belongs to
 * answers: a list of answer objects that contain the answers to the element
 * pages: a list of page objects
 * pdfElement: a PDFElement object which contains the element in the pdf
 */
package com.object.form.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="elementType",
    discriminatorType=DiscriminatorType.STRING
)
public abstract class FormElement implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "is_required")
	private Boolean isRequired;
	
	@Column(name = "is_enabled")
	private Boolean isEnabled;
	
	@Column(name = "is_multiple_answer_allowed")
	private boolean isMultipleAnswerAllowed;	
	
	@ManyToOne
	private Form form;
	
	@ManyToMany
	@JoinTable(name = "formElement_answers",
    			joinColumns=@JoinColumn(name = "formElement_id"),
    			inverseJoinColumns=@JoinColumn(name="answer_id"))
	private List<Answer> answers;
	
	@ManyToMany(mappedBy="elements")
	private List<Page> pages;
	
	@OneToOne
	private PDFElement pdfElement;
	
	@Transient	
    private String type;
	
	public FormElement(){
		this.type = this.getClass().getAnnotation( DiscriminatorValue.class ).value();
	}
	
	@Transient
	public String getDecriminatorValue() {
	    return this.getClass().getAnnotation(DiscriminatorValue.class).value();
	}
	
	@Transient
	public void setDecriminatorValue(String type) {
	    this.type = type;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<Page> getPages() {
		return pages;
	}
	public void setPages(List<Page> pages) {
		this.pages = pages;
	}
	public PDFElement getPdfElement() {
		return pdfElement;
	}
	public void setPdfElement(PDFElement pdfElement) {
		this.pdfElement = pdfElement;
	}
	public List<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getIsRequired() {
		return isRequired;
	}
	public void setIsRequired(Boolean isRequired) {
		this.isRequired = isRequired;
	}
	public Boolean getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	public Form getForm() {
		return form;
	}
	public void setForm(Form form) {
		this.form = form;
	}
	public boolean isMultipleAnswerAllowed() {
		return isMultipleAnswerAllowed;
	}
	public void setMultipleAnswerAllowed(boolean isMultipleAnswerAllowed) {
		this.isMultipleAnswerAllowed = isMultipleAnswerAllowed;
	}
	
}