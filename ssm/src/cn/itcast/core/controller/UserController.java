package cn.itcast.core.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.core.pojo.User;
import cn.itcast.core.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private SolrServer solrServer;
	
	@RequestMapping("/insert")
	public String insert(User user,Model model) throws Exception{
		userService.save(user);
		
		//根据query查询商品列表
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.set("q", "*:*");
		QueryResponse queryResponse = solrServer.query(solrQuery);
		SolrDocumentList results = queryResponse.getResults();
		ArrayList<User> list = new ArrayList<>();
		for (SolrDocument solrDocument : results) {
			User user2 = new User();
			user2.setName((String)solrDocument.get("name"));
			user2.setBirthday((Date)solrDocument.get("birthday"));
			list.add(user2);
		}
//		List<User> list = userService.findAll();
		model.addAttribute("userList", list);
		return "list";
	}
	
	@RequestMapping("AA")
	public String AAA(){
		return "insert";
	}
	
}
