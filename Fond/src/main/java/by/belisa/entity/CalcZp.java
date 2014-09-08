package by.belisa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name="CALC_ZP")
@SequenceGenerator(name="PK", sequenceName="SEQ_CALC_ZP")
public class CalcZp implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5220364701701803360L;


	@Id
	@GeneratedValue(generator="PK")
	private Integer id;
	@Column
	private String name;
	@Column
	private int salary;
	@Column
	private float rate;
	@Column
	private int duration;
	@Column(name="FOND_ZP")
	private int fondZp;
	@Column
	private String note;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getFondZp() {
		return fondZp;
	}

	public void setFondZp(int fondZp) {
		this.fondZp = fondZp;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	
	
}
