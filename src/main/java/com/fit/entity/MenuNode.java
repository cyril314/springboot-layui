package com.fit.entity;

import java.io.Serializable;
import java.util.*;

import com.fit.enums.JudgeEnum;

/**
 * @AUTO 菜单节点
 * @Author AIM
 * @DATE 2019/4/25
 */
public class MenuNode implements Comparable<Object>, Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 节点id
	 */
	private Long id;

	/**
	 * 父节点
	 */
	private Long pid;

	/**
	 * 节点名称
	 */
	private String name;

	/**
	 * 按钮级别
	 */
	private Integer levels;

	/**
	 * 按钮级别(Y N)
	 */
	private String ismenu;

	/**
	 * 按钮的排序
	 */
	private Integer sort;

	/**
	 * 节点的url
	 */
	private String url;

	/**
	 * 节点图标
	 */
	private String icon;

	/**
	 * 子节点的集合
	 */
	private List<MenuNode> children;

	/**
	 * 查询子节点时候的临时集合
	 */
	private List<MenuNode> linkedList = new ArrayList<>();

	public MenuNode() {
		super();
	}

	public MenuNode(Long id, Long pid) {
		super();
		this.id = id;
		this.pid = pid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLevels() {
		return levels;
	}

	public void setLevels(Integer levels) {
		this.levels = levels;
	}

	public String getIsmenu() {
		return ismenu;
	}

	public void setIsmenu(String ismenu) {
		this.ismenu = ismenu;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<MenuNode> getChildren() {
		return children;
	}

	public void setChildren(List<MenuNode> children) {
		this.children = children;
	}

	public List<MenuNode> getLinkedList() {
		return linkedList;
	}

	public void setLinkedList(List<MenuNode> linkedList) {
		this.linkedList = linkedList;
	}

	/**
	 * 重写排序比较接口，首先根据等级排序，然后更具排序字段排序
	 * 
	 * @param o
	 * @return
	 */
	@Override
	public int compareTo(Object o) {
		MenuNode menuNode = (MenuNode) o;
		Integer num = menuNode.getSort();
		Integer levels = menuNode.getLevels();
		if (num == null) {
			num = 0;
		}
		if (levels == null) {
			levels = 0;
		}
		if (this.levels.compareTo(levels) == 0) {
			return this.sort.compareTo(num);
		} else {
			return this.levels.compareTo(levels);
		}

	}

	/**
	 * 构建页面菜单列表
	 */
	public static List<MenuNode> buildTitle(List<MenuNode> nodes) {
		if (nodes.size() <= 0) {
			return nodes;
		}

		// 剔除非菜单
		for (MenuNode node : nodes) {
			if (!node.getIsmenu().equals(JudgeEnum.Y.name())) {
				nodes.remove(node);
			}
		}

		// 对菜单排序，返回列表按菜单等级，序号的排序方式排列
		Collections.sort(nodes);
		return mergeList(nodes, nodes.get(nodes.size() - 1).getLevels(), null);
	}

	/**
	 * 递归合并数组为子数组，最后返回第一层
	 * 
	 * @param menuList
	 * @param listMap
	 * @return
	 */
	private static List<MenuNode> mergeList(List<MenuNode> menuList, int rank, Map<Long, List<MenuNode>> listMap) {
		// 保存当次调用总共合并了多少元素
		int n;
		// 保存当次调用总共合并出来的list
		Map<Long, List<MenuNode>> currentMap = new HashMap<Long, List<MenuNode>>();
		// 由于按等级从小到大排序，需要从后往前排序
		// 判断该节点是否属于当前循环的等级,不等于则跳出循环
		for (n = menuList.size() - 1; n >= 0 && menuList.get(n).getLevels() == rank; n--) {
			// 判断之前的调用是否有返回以该节点的id为key的map，有则设置为children列表。
			if (listMap != null && listMap.get(menuList.get(n).getId()) != null) {
				menuList.get(n).setChildren(listMap.get(menuList.get(n).getId()));
			}
			if (menuList.get(n).getPid() != null && menuList.get(n).getPid() != 0) {
				// 判断当前节点所属的pid是否已经创建了以该pid为key的键值对，没有则创建新的链表
				if (!(currentMap.containsKey(menuList.get(n).getPid()))) {
					currentMap.put(menuList.get(n).getPid(), new LinkedList<MenuNode>());
				}
				// 将该节点插入到对应的list的头部
				currentMap.get(menuList.get(n).getPid()).add(0, menuList.get(n));
			}
		}
		if (n < 0) {
			return menuList;
		} else {
			return mergeList(new ArrayList<>(menuList.subList(0, n + 1)), menuList.get(n).getLevels(), currentMap);
		}
	}
}
