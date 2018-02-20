package ru.GraduationQualificationWork;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import ru.GraduationQualificationWork.Spider.Spider;
import ru.GraduationQualificationWork.Model.DAO.LinkDao;
import ru.GraduationQualificationWork.Model.Entity.Link;

import java.util.List;


@Configuration
@SpringBootApplication
@EnableAutoConfiguration
public class GraduationQualificationWorkApplication {

	public static final Logger logger = LoggerFactory.getLogger(GraduationQualificationWorkApplication.class);


	public static void main(String[] args) {

		final ConfigurableApplicationContext context = SpringApplication.run(GraduationQualificationWorkApplication.class, args);
		final Spider spider = context.getBean(Spider.class);

		try {
		    spider.setBaseUrl("http://www.amerikos.com");
		    spider.setExcludeExtensions("txt", "xml", "jpg", "css", "js");
			spider.crawl(3);
		} catch (Exception e) {
			e.printStackTrace();
		}

		LinkDao linkDao = context.getBean(LinkDao.class);
		List<Link> request = linkDao.getAllLinks();

//		request.stream().map((x) -> x.getAdress())
//				.peek((x) -> System.out.println("#### " + x))
//				.collect(Collectors.toSet());

	}
}
