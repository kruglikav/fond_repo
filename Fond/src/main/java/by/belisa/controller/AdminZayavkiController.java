package by.belisa.controller;

import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import by.belisa.bean.KonkursyDTO;
import by.belisa.bean.ZayavkaFIDTO;
import by.belisa.entity.Organization;
import by.belisa.entity.OrganizationNR;
import by.belisa.exception.DaoException;
import by.belisa.service.KonkursyService;
import by.belisa.service.ZayavkaFIService;
import by.belisa.util.Utils;

import com.liferay.portal.kernel.util.ParamUtil;

@Controller(value="adminZayavkiController")
@RequestMapping(value="VIEW")
public class AdminZayavkiController extends SaveZayavkaController{
	
	@Autowired
	@Qualifier(value = "konkursyService")
	private KonkursyService konkursyService;
	@Autowired
	@Qualifier(value = "zayavkaFIService")
	private ZayavkaFIService zayavkaFIService;

	@RenderMapping
	public String renderView(RenderRequest req, RenderResponse resp, Model model) throws DaoException{
//		int page = ParamUtil.getInteger(req, "pageIndex",1);
		List<KonkursyDTO> konkursyList =  konkursyService.getAllWithoutArhive();
		model.addAttribute("konkursyList", konkursyList);
		return "adminKonkursy";
	}
	@RenderMapping(params="view=zayavki")
	public String renderZayavkiView(RenderRequest req, RenderResponse resp, Model model) throws DaoException{
		int konkursId = ParamUtil.getInteger(req, "konkursId");
		List<ZayavkaFIDTO> zayavkiList = zayavkaFIService.getAllByKonkursId(konkursId);
		model.addAttribute("zayavkiList", zayavkiList);
		return "konkursZayavki";
	}
	@Override
	@RenderMapping(params = "view=zayavka")
	public String renderZayavkaForm(Model model, PortletRequest request) throws Exception {
		ZayavkaFIDTO zayavkaFIDTO = null;
		Integer zayavkaId = ParamUtil.getInteger(request, "zayavkaId");
		zayavkaFIDTO = zayavkaFIService.getZayavkaFIDTO(zayavkaId);
		model.addAttribute("zayavkaModel", zayavkaFIDTO);
		List<Organization> listOrg = orgService.getAll();
		model.addAttribute("listOrg", listOrg);
		List<OrganizationNR> orgNrList = orgNrService.getAllByKonkurs(zayavkaFIDTO.getKonkursId());
		model.addAttribute("orgNrList", orgNrList);
		model.addAttribute("annotationFileText", Utils.blobToHtml(zayavkaFIDTO.getAnnotationFileName(), zayavkaFIDTO.getAnnotationFile(), request));
		model.addAttribute("obosnFileText", Utils.blobToHtml(zayavkaFIDTO.getObosnFileName(), zayavkaFIDTO.getObosnFile(), request));
		
		return "zayavka";
	}
}
