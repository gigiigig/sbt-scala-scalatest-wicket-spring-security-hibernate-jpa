package org.gg.template.rest;

import java.util.Date;
import java.util.List;

import org.gg.template.model.Authority;
import org.gg.template.model.User;
import org.gg.template.model.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class RestController {

	Logger logger = LoggerFactory.getLogger(RestController.class);

	@Autowired
	private UserDao userDao;

	/**
     *
     */
	public RestController() {
	}

	@RequestMapping("primitive")
	public @ResponseBody
	String primitive(@RequestParam Integer value) {
		return "Converted primitive " + value;
	}

	@RequestMapping("find")
	public @ResponseBody
	List<User> testFind() {
		List<User> result = userDao.findAll();
		return result;
	}

	@RequestMapping("json")
	public @ResponseBody
	String[] testJson() {

		String[] toReturn = new String[2];
		toReturn[0] = "ciao";
		toReturn[1] = "test";
		return toReturn;

	}

	@RequestMapping("testsave")
	public @ResponseBody
	boolean testsave() {

		String email = "antonini@greenconsulting.it";

		if (userDao != null) {

			User user = userDao.findByEmail(email);

			if (user == null) {

				User luigi = new User(email, new Md5PasswordEncoder().encodePassword(
				    "luigi", null), "ROLE_USER", "ROLE_ADMIN");

				userDao.save(luigi);

			}
		}

		return true;
	}

	// requires Joda-Time on the classpath
	@RequestMapping("date/{value}")
	public @ResponseBody
	String date(@PathVariable @DateTimeFormat(iso = ISO.DATE) Date value) {
		return "Converted date " + value;
	}

}
