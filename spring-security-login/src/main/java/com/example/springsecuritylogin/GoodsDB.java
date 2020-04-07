package com.example.springsecuritylogin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

/**
 * 模拟数据库表
 * 
 * @author wsh
 *
 */
@Component
public class GoodsDB {

	private static List<Goods> list = new ArrayList<Goods>();
	// 初始数据
	static {
		list.add(new Goods(1, "Iphone", "64g"));
		list.add(new Goods(2, "手套", "黑色"));
		list.add(new Goods(3, "口红", "深红"));
	}

	public int getMaxId() {
		int id = 0;
		for (Goods goods : list) {
			if (goods.getId() > id)
				id = goods.getId();
		}
		return id + 1;
	}
	
	public List<Goods> findAll(){
		return list;
	}

	public Goods getGoods(int id) {
		Goods goods = null;
		for (Goods g : list) {
			if (g.getId() == id) {
				goods = g;
				break;
			}
		}

		return goods;
	}
	
	public void save(Goods goods) {
		list.add(goods);
	}

	public void update(Goods goods, int id) {
		for (Goods g : list) {
			if (g.getId() == id) {
				g.setId(goods.getId());
				g.setName(goods.getName());
				g.setDescription(goods.getDescription());
				break;
			}
		}
	}

	public void delete(long id) {
		for (Goods g : list) {
			if (g.getId() == id) {
				list.remove(g);
				break;
			}
		}
	}

	
}
