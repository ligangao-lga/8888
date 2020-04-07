package com.example.springsecuritylogin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CoodsController {
	
	@Autowired
	private GoodsDB goodsDB;

	@GetMapping("/goods")
	public String getGoods(Model model) {
		model.addAttribute("list", goodsDB.findAll());
		return "goods";
	}
	
	@PostMapping("/addgoods")
	public String addUser(@Valid Goods goods, BindingResult bindingResult, Model model) {
		goodsDB.save(goods);
		return "redirect:/goods";
	}
	
	@GetMapping("/goods/add")
	public String addGoods(Model model) {
		Goods goods = new Goods();
		goods.setId(goodsDB.getMaxId());
		model.addAttribute("goods", goods);
		return "add";
	}
	
	@GetMapping("/goods/edit/{id}") // {id}是占位符
	public String showUpdateForm(@PathVariable("id") int id, Model model) { // @PathVariable 路径变量
		Goods goods = goodsDB.getGoods(id);
		model.addAttribute("goods", goods);
		return "edit";
	}

	@PostMapping("/update/{id}")//BindingResult校验参数
	public String updateUser(@PathVariable("id") long id, @Valid Goods goods, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "update-goods";
		}
		goodsDB.save(goods);
		return "redirect:/goods";
	}

	@GetMapping("/goods/delete/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model) {
		goodsDB.delete(id);
		return "redirect:/goods";
	}
}
