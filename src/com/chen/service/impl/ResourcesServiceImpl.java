package com.chen.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chen.PageModel.Resource;
import com.chen.PageModel.SessionInfo;
import com.chen.PageModel.Tree;
import com.chen.dao.BaseDaoI;
import com.chen.model.PRole;
import com.chen.model.PUser;
import com.chen.model.Tresource;
import com.chen.model.Tresourcetype;
import com.chen.service.ResourcesServiceI;

@Service
public class ResourcesServiceImpl implements ResourcesServiceI {

	@Autowired
	BaseDaoI<Tresource> resourcesDaoI;
	@Autowired
	BaseDaoI<Tresourcetype> resourcetypeDaoI;

	@Autowired
	BaseDaoI<PUser> userDaoI;

	@Override
	public List<Tree> getResourceMyTree(Integer id) {
		List<Tresource> l = null;
		List<Tree> lt = new ArrayList<Tree>();

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("resourceTypeId", "0");// 菜单类型的资源
		params.put("id", id);
		l = resourcesDaoI
				.find("select distinct t from Tresource t join fetch t.tresourcetype type join fetch t.troles role join role.user user where type.id = :resourceTypeId and user.id = :id order by t.seq",
						params);
		if (l != null && l.size() > 0) {
			for (Tresource r : l) {
				System.out.println(r.getName());
				Tree tree = new Tree();
				BeanUtils.copyProperties(r, tree);
				if (r.getTresource() != null) {
					tree.setPid(r.getTresource().getId());
				}
				tree.setText(r.getName());
				tree.setIconCls(r.getIcon());
				Map<String, Object> attr = new HashMap<String, Object>();
				attr.put("url", r.getUrl());
				tree.setAttributes(attr);
				lt.add(tree);
			}
		}
		return lt;
	}

	/*
	 * @Override public List<String> getResourceList(Integer id) {
	 * List<Tresource> l = null; Set<String> lt = new HashSet<String>();
	 * 
	 * Map<String, Object> params = new HashMap<String, Object>();
	 * //params.put("id",id); l = resourcesDaoI.find(
	 * "select distinct t from Tresource t  left join   t.troles role where role.id=9 "
	 * , params); // if (l != null && l.size() > 0) { // for (Tresource r : l) {
	 * // lt.add(r.getUrl()); // } // } for(Tresource resources:l){
	 * 
	 * } PUser pUser =userDaoI.get(PUser.class, id); Set<PRole>
	 * roles=pUser.getRoles(); for(PRole pRole:roles){ for(Tresource
	 * resouce:pRole.getResources()){ lt.add(resouce.getUrl()); } } List<String>
	 * rStrings = new ArrayList<String>(); for(String temp:lt){
	 * rStrings.add(temp); } return rStrings; }
	 */

	@Override
	public List<Tree> getResourceAllMenuTree() {
		List<Tresource> l = null;
		List<Tree> lt = new ArrayList<Tree>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("typeid", "0");
		l = resourcesDaoI
				.find("select distinct t from Tresource  t join fetch t.tresourcetype type where type.id = :typeid",
						params);
		if (l != null && l.size() > 0) {
			for (Tresource r : l) {
				System.out.println(r.getName());
				Tree tree = new Tree();
				BeanUtils.copyProperties(r, tree);
				if (r.getTresource() != null) {
					tree.setPid(r.getTresource().getId());
				}
				tree.setText(r.getName());
				tree.setIconCls(r.getIcon());
				Map<String, Object> attr = new HashMap<String, Object>();
				attr.put("url", r.getUrl());
				tree.setAttributes(attr);
				lt.add(tree);
			}
		}
		return lt;
	}

	@Override
	public List<Resource> treeGrid(SessionInfo sessionInfo) {
		List<Tresource> l = null;
		List<Resource> lr = new ArrayList<Resource>();

		Map<String, Object> params = new HashMap<String, Object>();
		// if (sessionInfo != null) {
		// params.put("id", sessionInfo.getId());// 自查自己有权限的资源
		// l =
		// resourcesDaoI.find("select distinct t from Tresource t join fetch t.tresourcetype type join fetch t.troles role join role.tusers user where user.id = :id order by t.seq",
		// params);
		// } else {
		// l =
		// resourcesDaoI.find("select distinct t from Tresource t join fetch t.tresourcetype type order by t.seq",
		// params);
		// }
		l = resourcesDaoI
				.find("select distinct t from Tresource t join fetch t.tresourcetype type order by t.seq",
						params);
		if (l != null && l.size() > 0) {
			for (Tresource t : l) {
				Resource r = new Resource();
				BeanUtils.copyProperties(t, r);
				if (t.getTresource() != null) {
					r.setPid(t.getTresource().getId());
					r.setPname(t.getTresource().getName());
				}
				r.setTypeId(t.getTresourcetype().getId());
				r.setTypeName(t.getTresourcetype().getName());
				if (t.getIcon() != null && !t.getIcon().equalsIgnoreCase("")) {
					r.setIconCls(t.getIcon());
				}
				lr.add(r);
			}
		}

		return lr;
	}

	@Override
	public Tresource add(Resource resource) {
		Tresource t = new Tresource();
		BeanUtils.copyProperties(resource, t);
		if (resource.getPid() != null
				&& !resource.getPid().equalsIgnoreCase("")) {
			t.setTresource(resourcesDaoI.get(Tresource.class, resource.getPid()));
		}
		if (resource.getTypeId() != null
				&& !resource.getTypeId().equalsIgnoreCase("")) {
			t.setTresourcetype(resourcetypeDaoI.get(Tresourcetype.class,
					resource.getTypeId()));
		}
		if (resource.getIconCls() != null
				&& !resource.getIconCls().equalsIgnoreCase("")) {
			t.setIcon(resource.getIconCls());
		}
		resourcesDaoI.save(t);
		return t;
	}

	@Override
	public void delete(String id) {
		Tresource tresource = resourcesDaoI.get(Tresource.class, id);
		del(tresource);

	}

	private void del(Tresource t) {
		if (t.getTresources() != null && t.getTresources().size() > 0) {
			for (Tresource r : t.getTresources()) {
				del(r);
			}
		}
		resourcesDaoI.delete(t);
	}

	@Override
	public Resource get(String id) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		Tresource t = resourcesDaoI
				.get("from Tresource t left join fetch t.tresource resource left join fetch t.tresourcetype resourceType where t.id = :id",
						params);
		Resource r = new Resource();
		BeanUtils.copyProperties(t, r);
		if (t.getTresource() != null) {
			r.setPid(t.getTresource().getId());
			r.setPname(t.getTresource().getName());
		}
		r.setTypeId(t.getTresourcetype().getId());
		r.setTypeName(t.getTresourcetype().getName());
		if (t.getIcon() != null && !t.getIcon().equalsIgnoreCase("")) {
			r.setIconCls(t.getIcon());
		}
		return r;
	}

	@Override
	public void edit(Resource resource) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", resource.getId());
		Tresource t = resourcesDaoI.get(
				"select distinct t from Tresource t where t.id = :id", params);
		if (t != null) {
			BeanUtils.copyProperties(resource, t);
			if (resource.getTypeId() != null
					&& !resource.getTypeId().equalsIgnoreCase("")) {
				t.setTresourcetype(resourcetypeDaoI.get(Tresourcetype.class,
						resource.getTypeId()));// 赋值资源类型
			}
			if (resource.getIconCls() != null
					&& !resource.getIconCls().equalsIgnoreCase("")) {
				t.setIcon(resource.getIconCls());
			}
			if (resource.getPid() != null
					&& !resource.getPid().equalsIgnoreCase("")) {// 说明前台选中了上级资源
				Tresource pt = resourcesDaoI.get(Tresource.class,
						resource.getPid());
				isChildren(t, pt);// 说明要将当前资源修改到当前资源的子/孙子资源下
				t.setTresource(pt);
			} else {
				t.setTresource(null);// 前台没有选中上级资源，所以就置空
			}
		}

	}

	/**
	 * 防止构成回路
	 * 
	 * @param t
	 *            当前节点
	 * @param pt
	 *            要修改到的节点
	 * @return
	 */
	private boolean isChildren(Tresource t, Tresource pt) {
		if (pt != null && pt.getTresource() != null) {
			if (pt.getTresource().getId().equalsIgnoreCase(t.getId())) {
				pt.setTresource(null);
				return true;
			} else {
				return isChildren(t, pt.getTresource());
			}
		}
		return false;
	}

	@Override
	public List<Tree> getResourceAllTree() {
		List<Tresource> l = null;
		List<Tree> lt = new ArrayList<Tree>();
		l = resourcesDaoI.find("select distinct t from Tresource t");
		if (l != null && l.size() > 0) {
			for (Tresource r : l) {
				Tree tree = new Tree();
				BeanUtils.copyProperties(r, tree);
				if (r.getTresource() != null) {
					tree.setPid(r.getTresource().getId());
				}
				tree.setText(r.getName());
				tree.setIconCls(r.getIcon());
				Map<String, Object> attr = new HashMap<String, Object>();
				attr.put("url", r.getUrl());
				tree.setAttributes(attr);
				lt.add(tree);
			}
		}
		return lt;
	}

	@Override
	public List<String> getResourceList(Integer id) {
		List<Tresource> l = null;
		List<String> lt = new ArrayList<String>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		l = resourcesDaoI
				.find("select distinct t from Tresource t join fetch   t.troles role  join role.user user where user.id = :id",
						params);
		if (l != null && l.size() > 0) {
			for (Tresource r : l) {
				lt.add(r.getUrl());
			}
		}

		return lt;
	}

	@Override
	public List<Tree> getResourceAllMyTree(Integer id) {
		List<Tresource> l = null;
		List<Tree> lt = new ArrayList<Tree>();

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		l = resourcesDaoI
				.find("select distinct t from Tresource t join fetch t.troles role join role.user user where  user.id = :id order by t.seq",
						params);
		if (l != null && l.size() > 0) {
			for (Tresource r : l) {
				System.out.println(r.getName());
				Tree tree = new Tree();
				BeanUtils.copyProperties(r, tree);
				if (r.getTresource() != null) {
					tree.setPid(r.getTresource().getId());
				}
				tree.setText(r.getName());
				tree.setIconCls(r.getIcon());
				Map<String, Object> attr = new HashMap<String, Object>();
				attr.put("url", r.getUrl());
				tree.setAttributes(attr);
				lt.add(tree);
			}
		}
		return lt;
	}

}
