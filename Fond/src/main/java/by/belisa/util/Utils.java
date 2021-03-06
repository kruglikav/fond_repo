package by.belisa.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.portlet.PortletRequest;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import by.belisa.bean.CheckUslResult;
import by.belisa.validation.ValidationResult;

import com.aspose.words.Document;
import com.aspose.words.HtmlSaveOptions;
import com.aspose.words.SaveFormat;
import com.liferay.portal.util.PortalUtil;

public class Utils {

	public static String createErrorMsg(String fio, CheckUslResult checkUslResult) {
		StringBuffer message = new StringBuffer(fio);
		message.append(" не может учавствовать в конкурсе,<br/>");
		message.append("так как он уже участвует в следующих конкурсах: <br/>");
		message.append("<ul>");
		for (Map.Entry<String, String> entry : checkUslResult.getKonkursRuk().entrySet()) {
			message.append("<li>");
			message.append(entry.getKey());
			message.append(" - руководитель ");
			message.append(entry.getValue());
			message.append("</li>");
		}
		message.append("</ul>");
		return message.toString();
	}

	public static String createErrorMsg(ValidationResult vr) {
		List<String> errors = vr.getErrMessages();
		StringBuffer message = new StringBuffer("<ul>");
		for (String entry : errors) {
			message.append("<li>");
			message.append(entry);
			message.append("</li>");
		}
		message.append("</ul>");
		return message.toString();
	}

	public static String createFio(String fullFio) {
		String fio;
		String[] fioArr = fullFio.split(" ");
		if (fioArr.length == 3) {
			fio = fioArr[0] + " " + fioArr[1].charAt(0) + "." + fioArr[2].charAt(0) + ".";
		} else if (fioArr.length == 2) {
			fio = fioArr[0] + " " + fioArr[1].charAt(0) + ".";
		} else {
			fio = fullFio;
		}
		return fio;
	}

	public static void printRequestParams(PortletRequest req) {
		Map<String, String[]> params = req.getParameterMap();
		for (Map.Entry<String, String[]> entry : params.entrySet()) {
			String key = entry.getKey();
			String[] values = entry.getValue();
			System.out.print(key + ":");
			for (String s : values) {
				System.out.print("[" + s + "]");
			}
			System.out.println();
		}
	}

	public static void writePublisherReport(InputStream in, OutputStream out, boolean addFiles) throws IOException {
		int len;
		byte[] buffer = new byte[1024];
		byte[] buffer2 = new byte[1024];
		while ((len = in.read(buffer)) != -1) {
			int av = in.available();
			if (av < 1024) {
				int len2 = in.read(buffer2);
				int flag = in.read();
				if (flag != -1) {
					out.write(buffer, 0, len);
					out.write(buffer2, 0, len2);
					out.write(flag);
				} else {
					if (addFiles) {
						int indexLast = -1;
						for (int i = 0; i < len2; i++) {
							if (buffer2[i] == 125) {
								indexLast = i;
							}
						}
						if (indexLast == -1) {
							for (int i = 0; i < len; i++) {
								if (buffer[i] == 125) {
									indexLast = i;
								}
							}
							out.write(buffer, 0, indexLast - 1);
						} else {
							out.write(buffer, 0, len);
							if (indexLast != 0) {
								out.write(buffer2, 0, indexLast - 1);
							}

						}
					} else {
						out.write(buffer, 0, len);
						out.write(buffer2, 0, len2);
					}
				}
			} else {
				out.write(buffer, 0, len);
			}
		}
	}

	public static void writeLoadedFile(OutputStream out, byte[] file, boolean addFiles) throws IOException {
		byte[] newPageTag = "\\page ".getBytes();
		out.write(newPageTag);
		if (addFiles) {
			out.write(file, 6, file.length - 7);
		} else {
			out.write(file, 6, file.length - 6);
		}
	}

	public static String blobToHtml(String fileName, byte[] file, PortletRequest request) throws Exception {
		if (fileName != null && !fileName.isEmpty()) {
			ByteArrayInputStream bis = new ByteArrayInputStream(file);
			Document doc = new Document(bis);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			HtmlSaveOptions options = new HtmlSaveOptions(SaveFormat.HTML);
			options.setExportTextInputFormFieldAsText(true);
			HttpServletRequest servletRequest = PortalUtil.getHttpServletRequest(request);
			ServletContext servletContext = servletRequest.getSession().getServletContext();
			File imageFolder = new File(servletContext.getRealPath("/img/"));
			String imageFolderPath = imageFolder.getPath();
			options.setImagesFolder(imageFolderPath);
//			Pattern p = Pattern.compile(".*(/[^/]img$)");
//			Matcher m = p.matcher(imageFolderPath);
//			m.find();
			options.setImagesFolderAlias("/"+servletContext.getServletContextName()+"/img");
			doc.save(out, options);
			String fileText = out.toString("utf-8");
			return fileText;
		}
		return null;
	}
}
