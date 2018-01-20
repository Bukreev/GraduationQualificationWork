package ru.GraduationQualificationWork;

import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.GraduationQualificationWork.Crawler.Crawler;
import ru.GraduationQualificationWork.Model.DAO.LinkDao;
import ru.GraduationQualificationWork.Model.Entity.Link;
import ru.GraduationQualificationWork.Model.Repositories.LinkRepository;
import ru.GraduationQualificationWork.Spider.SpiderController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@SpringBootApplication
@EnableAutoConfiguration
public class GraduationQualificationWorkApplication {

	public static final Logger logger = LoggerFactory.getLogger(GraduationQualificationWorkApplication.class);


	public static void main(String[] args) {

		final ConfigurableApplicationContext context = SpringApplication.run(GraduationQualificationWorkApplication.class, args);
		final Crawler spider = context.getBean(Crawler.class);

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
