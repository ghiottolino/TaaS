package com.nicolatesser.tikaasaservice;



import org.apache.tika.Tika;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping(value = "/documents")
public class DocumentController {

	@RequestMapping(value = "/parse", method = RequestMethod.POST)
	public @ResponseBody String handleFileUpload(
			@RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			try {

				String text = new Tika().parseToString(file.getInputStream());
				// TODO : return structure instead of text
				return text;

			} catch (Exception e) {
				return "You failed to upload the file => " + e.getMessage();
			}
		} else {
			return "You failed to upload the file because the file was empty.";
		}
	}

}
