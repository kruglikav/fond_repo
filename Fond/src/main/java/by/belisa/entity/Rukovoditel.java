package by.belisa.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import by.belisa.validation.IValidaton;
import by.belisa.validation.ValidationResult;

@Entity
@Table(name = "RUKOVODITEL")
@GenericGenerator(name = "gen", strategy = "foreign", parameters = @Parameter(name = "property", value = "zayavkaFI"))
public class Rukovoditel implements Serializable, IValidaton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5586880192718189825L;

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "gen")
	private Integer id;

	@Lob
	@Column(name = "BIOGRAPHY")
	private String biography;

	@Column(name = "FIO", length = 60)
	private String fio;
	@Column(name = "FIO_FULL", length = 100)
	private String fullFio;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORG_ID")
	private Organization org;
	@Column(name = "BIRTHDAY", columnDefinition = "DATE")
	private Date birthday;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "RUK_UCH_STEPENI")
	private List<UchStepeni> uchStepeniList = new ArrayList<UchStepeni>();
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "RUK_UCH_ZVANIY")
	private List<UchZvaniy> uchZvaniyList= new ArrayList<UchZvaniy>();
	@Column(name = "POST")
	private String post;
	@Column(name = "LAB")
	private String lab;
	@Column(name = "JOB_PHONE")
	private String jobPhone;
	@Column(name = "HOME_PHONE")
	private String homePhone;
	@Column(name = "MOBILE_PHONE")
	private String mobilePhone;
	@Column(name = "ADDRESS")
	private String address;
	@Column(name = "EMAIL")
	private String email;

	@Column(name = "COUNT_PUBLICATION_SCOPUS")
	private String countPublicationScopus;
	@Column(name = "COUNT_PUBLICATION_ISI")
	private String countPublicationISI;
	@Column(name = "COUNT_PUBLICATION_RINC")
	private String countPublicationRINC;

	@Column(name = "CITATION_INDEX_SCOPUS")
	private String citationIndexScopus;
	@Column(name = "CITATION_INDEX_ISI")
	private String citationIndexISI;
	@Column(name = "CITATION_INDEX_RINC")
	private String citationIndexRINC;

	@Column(name = "INDEX_WITHOUT_SELF_SCOPUS")
	private String indexWithoutSelfScopus;
	@Column(name = "INDEX_WITHOUT_SELF_ISI")
	private String indexWithoutSelfISI;
	@Column(name = "INDEX_WITHOUT_SELF_RINC")
	private String indexWithoutSelfRINC;

	@Column(name = "INDEX_HIRSHA_SCOPUS")
	private String indexHirshaScopus;
	@Column(name = "INDEX_HIRSHA_ISI")
	private String indexHirshaISI;
	@Column(name = "INDEX_HIRSHA_RINC")
	private String indexHirshaRINC;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "RUKOVODITEL_ID")
	@OrderBy("id ASC")
	Set<Publication> publicationSet = new HashSet<Publication>();

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
	@PrimaryKeyJoinColumn()
	private ZayavkaFI zayavkaFI;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public String getFio() {
		return fio;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}

	public String getFullFio() {
		return fullFio;
	}

	public void setFullFio(String fullFio) {
		this.fullFio = fullFio;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	
	public List<UchStepeni> getUchStepeniList() {
		return uchStepeniList;
	}

	public void setUchStepeniList(List<UchStepeni> uchStepeniList) {
		this.uchStepeniList = uchStepeniList;
	}

	public List<UchZvaniy> getUchZvaniyList() {
		return uchZvaniyList;
	}

	public void setUchZvaniyList(List<UchZvaniy> uchZvaniyList) {
		this.uchZvaniyList = uchZvaniyList;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getLab() {
		return lab;
	}

	public void setLab(String lab) {
		this.lab = lab;
	}

	public String getJobPhone() {
		return jobPhone;
	}

	public void setJobPhone(String jobPhone) {
		this.jobPhone = jobPhone;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountPublicationScopus() {
		return countPublicationScopus;
	}

	public void setCountPublicationScopus(String countPublicationScopus) {
		this.countPublicationScopus = countPublicationScopus;
	}

	public String getCountPublicationISI() {
		return countPublicationISI;
	}

	public void setCountPublicationISI(String countPublicationISI) {
		this.countPublicationISI = countPublicationISI;
	}

	public String getCountPublicationRINC() {
		return countPublicationRINC;
	}

	public void setCountPublicationRINC(String countPublicationRINC) {
		this.countPublicationRINC = countPublicationRINC;
	}

	public String getCitationIndexScopus() {
		return citationIndexScopus;
	}

	public void setCitationIndexScopus(String citationIndexScopus) {
		this.citationIndexScopus = citationIndexScopus;
	}

	public String getCitationIndexISI() {
		return citationIndexISI;
	}

	public void setCitationIndexISI(String citationIndexISI) {
		this.citationIndexISI = citationIndexISI;
	}

	public String getCitationIndexRINC() {
		return citationIndexRINC;
	}

	public void setCitationIndexRINC(String citationIndexRINC) {
		this.citationIndexRINC = citationIndexRINC;
	}

	public String getIndexWithoutSelfScopus() {
		return indexWithoutSelfScopus;
	}

	public void setIndexWithoutSelfScopus(String indexWithoutSelfScopus) {
		this.indexWithoutSelfScopus = indexWithoutSelfScopus;
	}

	public String getIndexWithoutSelfISI() {
		return indexWithoutSelfISI;
	}

	public void setIndexWithoutSelfISI(String indexWithoutSelfISI) {
		this.indexWithoutSelfISI = indexWithoutSelfISI;
	}

	public String getIndexWithoutSelfRINC() {
		return indexWithoutSelfRINC;
	}

	public void setIndexWithoutSelfRINC(String indexWithoutSelfRINC) {
		this.indexWithoutSelfRINC = indexWithoutSelfRINC;
	}

	public String getIndexHirshaScopus() {
		return indexHirshaScopus;
	}

	public void setIndexHirshaScopus(String indexHirshaScopus) {
		this.indexHirshaScopus = indexHirshaScopus;
	}

	public String getIndexHirshaISI() {
		return indexHirshaISI;
	}

	public void setIndexHirshaISI(String indexHirshaISI) {
		this.indexHirshaISI = indexHirshaISI;
	}

	public String getIndexHirshaRINC() {
		return indexHirshaRINC;
	}

	public void setIndexHirshaRINC(String indexHirshaRINC) {
		this.indexHirshaRINC = indexHirshaRINC;
	}

	public Set<Publication> getPublicationSet() {
		return publicationSet;
	}

	public void setPublicationSet(Set<Publication> publicationSet) {
		this.publicationSet = publicationSet;
	}

	public ZayavkaFI getZayavkaFI() {
		return zayavkaFI;
	}

	public void setZayavkaFI(ZayavkaFI zayavkaFI) {
		this.zayavkaFI = zayavkaFI;
	}

	@Override
	public ValidationResult validate() {
		final int BELARUS_FORM_ID = 1;
		final int BELARUS_JUNIOR_FORM_ID = 2;
		final int COOPERATIVE_FORM_ID = 3;
		final int COOPERATIVE_JUNIOR_FORM_ID = 4;
		ValidationResult vr = new ValidationResult();
		String message = null;
		String tabNameHeadProjectOfBelarus = "во вкладке \"Руководитель проекта от РБ\"";
		String tabNameHeadScienceRating = "во вкладке \"Научный рейтинг руководителя\"";
		String tabNameAplicantOrg = "во вкладке \"Организация-заявитель от РБ\"";
		String tabNameListOfPublications = "во вкладке \"Перечень публикации руководителя\"";
		int VidFormId = zayavkaFI.getKonkursy().getTipKonkursa().getVidFormaZ().getId();
		// Проверка даты рождения
		if (this.birthday == null) {
			message = "Не заполнена дата рождения" + " " + tabNameHeadProjectOfBelarus;
			vr.getErrMessages().add(message);
		}
		// Проверка учёной степени
		if (this.uchStepeniList.isEmpty()) {
			message = "Не выбрана учённая степень" + " " + tabNameHeadProjectOfBelarus;
			vr.getErrMessages().add(message);
		}
		// Проверка ученного звания
		if (this.uchZvaniyList.isEmpty()) {
			message = "Не выбрано ученое звание" + " " + tabNameHeadProjectOfBelarus;
			vr.getErrMessages().add(message);
		}
		// Проверка долнжости
		if (this.post == null || this.post.isEmpty()) {
			message = "Не заполнена должность" + " " + tabNameHeadProjectOfBelarus;
			vr.getErrMessages().add(message);
		}
		// Проверка кафедры, лаборатории
		if (this.lab == null || this.lab.isEmpty()) {
			message = "Не заполнена кафедра,лаборатория" + " " + tabNameHeadProjectOfBelarus;
			vr.getErrMessages().add(message);
		}
		// Проверка служебного телефона
		if (this.jobPhone == null || this.jobPhone.isEmpty()) {
			message = "Не указан служебный телефон" + " " + tabNameHeadProjectOfBelarus;
			vr.getErrMessages().add(message);
		}
		// Проверка домашнего телефона
		if (this.homePhone == null || this.homePhone.isEmpty()) {
			message = "Не указан домашний телефон" + " " + tabNameHeadProjectOfBelarus;
			vr.getErrMessages().add(message);
		}
		// Проверка мобильного телефона
		if (this.mobilePhone == null || this.mobilePhone.isEmpty()) {
			message = "Не указан мобильный телефон" + " " + tabNameHeadProjectOfBelarus;
			vr.getErrMessages().add(message);
		}
		// Проверка электронной почты
		if (this.email == null || this.email.isEmpty()) {
			message = "Не указана электронная почта" + " " + tabNameHeadProjectOfBelarus;
			vr.getErrMessages().add(message);
		}
		// Проверка почтового индекса и домашнего адреса
		if (this.address == null || this.address.isEmpty()) {
			message = "Не заполнен почтовый индекс и домашний адрес" + " " + tabNameHeadProjectOfBelarus;
			vr.getErrMessages().add(message);
		}
		if (VidFormId == BELARUS_FORM_ID || VidFormId == COOPERATIVE_FORM_ID) {
			// Проверка количества публикаций
			if (this.countPublicationISI == null || this.countPublicationRINC == null || this.countPublicationScopus == null
					|| this.countPublicationISI.isEmpty() || this.countPublicationRINC.isEmpty() || this.countPublicationScopus.isEmpty()) {
				message = "Не заполенены количество публикаций" + " " + tabNameHeadScienceRating;
				vr.getErrMessages().add(message);
			}
			// Проверка индекса цитирования всех работ
			if (this.citationIndexISI == null || this.citationIndexRINC == null || this.citationIndexScopus == null
					|| this.citationIndexISI.isEmpty() || this.citationIndexRINC.isEmpty() || this.citationIndexScopus.isEmpty()) {
				message = "Не заполнены индексы цитирования всех работ" + " " + tabNameHeadScienceRating;
				vr.getErrMessages().add(message);
			}
			// Проверка индекса цитирования без самоцитирования
			if (this.indexWithoutSelfISI == null || this.indexWithoutSelfRINC == null || this.indexWithoutSelfScopus == null
					|| this.indexWithoutSelfISI.isEmpty() || this.indexWithoutSelfRINC.isEmpty() || this.indexWithoutSelfScopus.isEmpty()) {
				message = "Не заполнены индексы цитирования без самоцитирования" + " " + tabNameHeadScienceRating;
				vr.getErrMessages().add(message);
			}
			// Проверка индекса Хирши
			if (this.indexHirshaISI == null || this.indexHirshaRINC == null || this.indexHirshaScopus == null || this.indexHirshaISI.isEmpty()
					|| this.indexHirshaRINC.isEmpty() || this.indexHirshaScopus.isEmpty()) {
				message = "Не заполнены индексы Хирша" + " " + tabNameHeadScienceRating;
				vr.getErrMessages().add(message);
			}
		}
		if (VidFormId == BELARUS_FORM_ID || VidFormId == COOPERATIVE_FORM_ID) {
			// Проверка перечня публикаций руководителя
			if (this.publicationSet == null || this.publicationSet.isEmpty()) {
				message = "Не заполнены публикиции" + " " + tabNameListOfPublications;
				vr.getErrMessages().add(message);
			}
		}
		// Проверка вкладки биографии руководителя
		if (this.biography == null || this.biography.isEmpty()) {
			message = "Не заполнена вкладка \"Научная биография руководителя\"";
			vr.getErrMessages().add(message);
		}

		return vr;

	}

}
